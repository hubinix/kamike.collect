/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.utils;

import cn.exinhua.fetch.entity.Content;
import cn.exinhua.fetch.entity.Description;
import cn.exinhua.fetch.entity.Link;
import cn.exinhua.fetch.entity.Page;
import cn.exinhua.fetch.entity.Raw;
import com.kamike.misc.UuidUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 解析辅助类
 *
 * @author
 */
public class ParseUtils {

 

    public static Raw getRaw(HttpResponse response, RegexRule regexRule, int limit) throws UnsupportedEncodingException {
        String url = response.getUrl().toString();
        
        String charset = CharsetDetector.guessEncoding(response.getContent());
        Page page=new Page();
        String html = new String(response.getContent(), charset);
        page.setHtml(html);

        Document doc = Jsoup.parse(page.getHtml());
        doc.setBaseUri(url);
        page.setDoc(doc);

        String title = doc.title();
        String text = doc.text();

        ArrayList<Link> links = null;
        if (limit == 0) {
            links = new ArrayList<Link>();
        } else {
            //这里可以修改
            links = limitFilter(LinkUtils.getAll(page), regexRule, limit);
        }
        //
        Content content = new Content();
        content.setCreateDate(new Date(System.currentTimeMillis()));
        content.setId(UuidUtils.base58Uuid());
        content.setText(text);
        content.setType("html");
        content.setUrl(url);

        Description description = new Description();
        description.setContentId(content.getId());
        description.setCreateDate(new Date(System.currentTimeMillis()));
        description.setId(UuidUtils.base58Uuid());
        description.setTitle(title);

        Raw raw = new Raw();
        raw.setContent(content);
        raw.setDescription(description);
        raw.setLinks(links);

        return raw;
    }

    private static ArrayList<Link> limitFilter(ArrayList<Link> origin_links, RegexRule regexRule, int limit) {
        ArrayList<Link> result = new ArrayList<Link>();
        int updatesize;
        if (limit == 0) {
            updatesize = origin_links.size();
        } else {
            updatesize = Math.min(limit, origin_links.size());
        }

        int sum = 0;
        for (int i = 0; i < origin_links.size(); i++) {
            if (sum >= updatesize) {
                break;
            }
            Link link = origin_links.get(i);
            if (!regexRule.satisfy(link.getUrl())) {
                continue;
            }
            result.add(link);
            sum++;
        }
        return result;
    }
}
