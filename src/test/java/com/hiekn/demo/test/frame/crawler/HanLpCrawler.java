package com.hiekn.demo.test.frame.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.ram.RamCrawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HanLpCrawler  extends RamCrawler {

    public HanLpCrawler() {
        addSeedAndReturn("http://www.hankcs.com/nlp/part-of-speech-tagging.html").type("indedx");
        conf.setTopN(100);
        setThreads(1);
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        Elements elements = page.select("body > section > div > div > article > table > tbody > tr > td");
        StringBuilder sb = new StringBuilder();
        for (Element ele : elements) {
            String[] html = ele.html().split("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            if(html.length == 2){
                sb.append(new PartOfSpeech(Jsoup.parse(html[0]).text(),Jsoup.parse(html[1]).text())).append("\n");
            }
        }
        try {
            Files.write(Paths.get("G:\\a.txt"),sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        HanLpCrawler hanLpCrawler = new HanLpCrawler();
        hanLpCrawler.start(1);
    }

}
