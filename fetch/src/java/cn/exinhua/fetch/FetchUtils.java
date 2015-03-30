/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch;

import cn.exinhua.fetch.entity.Fetch;
import cn.exinhua.fetch.entity.Link;
import cn.exinhua.fetch.entity.Raw;
import cn.exinhua.fetch.entity.db.ContentWriter;
import cn.exinhua.fetch.entity.db.DescriptionWriter;
import cn.exinhua.fetch.entity.db.LinkReader;
import cn.exinhua.fetch.entity.db.LinkWriter;
import cn.exinhua.fetch.utils.HttpRequest;
import cn.exinhua.fetch.utils.HttpResponse;
import cn.exinhua.fetch.utils.ParseUtils;
import cn.exinhua.fetch.utils.RegexRule;
import com.kamike.db.Transaction;
import com.kamike.misc.UuidUtils;
import com.kamike.watch.EventInst;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brin
 */
public class FetchUtils {

    public static void start(List<Fetcher> fetchers) {

        try {

            for (Fetcher fetcher : fetchers) {
                Fetch fetch = fetcher.getFetch();
                if (fetch == null) {
                    continue;
                }
                Link link = new Link(fetch.getTitle(), fetch.getUrl());
               link.setRoot(fetch.getUrl());
                fetch(fetcher.createRequest(), fetcher.getRegexRule(), link, 10000);
                EventInst.getInstance().getAsyncEventBus().post(fetcher);

            }

        } catch (Exception ex) {
            Logger.getLogger(FetchUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fetch(HttpRequest request, RegexRule regexRule, Link link) {
        fetch(request, regexRule, link, 1000);
    }

    public static void fetch(HttpRequest request, RegexRule regexRule, Link link, int limit) {
        try {

            HttpResponse response = request.getResponse(link);
            Raw raw = ParseUtils.getRaw(response, regexRule, limit);
            Transaction ts = new Transaction();
            LinkWriter linkWriter = new LinkWriter(ts);
            LinkReader linkReader = new LinkReader();
            for (Link childLink : raw.getLinks()) {
                childLink.setDepth(link.getDepth() + 1);
                childLink.setCreateDate(new Date(System.currentTimeMillis()));
                childLink.setExpired(false);
                childLink.setFetched(false);
                childLink.setId(UuidUtils.base58Uuid());
                childLink.setRoot(link.getRoot());

                if (childLink.getDepth() < request.getDepth() && linkReader.countHash(childLink.getHash()) == 0) {
                    linkWriter.add(childLink);
                }
            }
            //保存内容啊。。
            ContentWriter contentWriter = new ContentWriter(ts);
            contentWriter.add(raw.getContent());
            DescriptionWriter descriptionWriter=new DescriptionWriter(ts);
            descriptionWriter.add(raw.getDescription());
            ts.save();
            

        } catch (Exception ex) {
            Logger.getLogger(FetchUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
