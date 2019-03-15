package com.codeup.ourpueblo.Controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TestScraper {
    public static String scrapeText(String url) throws IOException {
//        String url = "https://www.sanantonio.gov/mayor";
        System.out.println("Fetching %s..."+ url);

        Document doc = Jsoup.connect(url).get();
        Elements pane = doc.select("div#dnn_ctr25435_HtmlModule_lblContent");
        String text = pane.text();

        System.out.println(text);
        return text;
    }

}