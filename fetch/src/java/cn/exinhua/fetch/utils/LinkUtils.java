/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.exinhua.fetch.utils;

import cn.exinhua.fetch.entity.Link;
import cn.exinhua.fetch.entity.Page;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

/**
 *
 * @author Brin
 */
public class LinkUtils {

    public static ArrayList<Link> getLinks(Document doc) {
        ArrayList<Link> links = new ArrayList<Link>();
        Elements link_elements = doc.select("a[href]");
        for (Element link : link_elements) {
            String anchor = link.text();
            String href = link.attr("abs:href");
            links.add(new Link(anchor, href));
        }
        return links;
    }

    public static ArrayList<Link> getImgs(Document doc) {
        ArrayList<Link> links = new ArrayList<Link>();
        Elements link_elements = doc.select("img[src]");
        for (Element link : link_elements) {
            String anchor = link.text();
            String href = link.attr("abs:src");
            links.add(new Link(anchor, href));
        }
        return links;
    }

    public static ArrayList<Link> getCSS(Document doc) {
        ArrayList<Link> links = new ArrayList<Link>();
        Elements link_elements = doc.select("link[href]");
        for (Element link : link_elements) {
            String anchor = link.text();
            String href = link.attr("abs:href");
            links.add(new Link(anchor, href));
        }
        return links;
    }

    public static ArrayList<Link> getJS(Document doc) {
        ArrayList<Link> links = new ArrayList<Link>();
        Elements link_elements = doc.select("script[src]");
        for (Element link : link_elements) {
            String anchor = link.text();
            String href = link.attr("abs:src");
            links.add(new Link(anchor, href));
        }
        return links;
    }

    public static ArrayList<Link> getLinks(Page page) {
        try {
            return getLinks(page.getDoc());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static ArrayList<Link> getAll(Page page) {
        try {
            ArrayList<Link> result = getLinks(page.getDoc());
            result.addAll(getImgs(page.getDoc()));
            result.addAll(getCSS(page.getDoc()));
            result.addAll(getJS(page.getDoc()));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
