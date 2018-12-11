package com.hiekn.demo.test.frame.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;

/**
 * 一次性抓取 用RamCrawler。基于内存
 * 持续性的用 BreadthCrawler
 */
public class CrawlerDemo extends BreadthCrawler {

    public CrawlerDemo(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        addSeedAndReturn("http://www.hiboot.cn").type("indedx");
        addRegex("http://www.hiboot.cn/jie/[0-9]+");
        addRegex("http://www.hiboot.cn/column/all/page/2");
        conf.setTopN(100);
        //可以设置每个线程visit的间隔，这里是毫秒
//        conf.setExecuteInterval(3000);
        setThreads(1); //看源码用
//        setResumable(true);//断点
    }

    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {
        System.out.println(page.url());
    }

    public static void main(String[] args) throws Exception {
        CrawlerDemo crawlerDemo = new CrawlerDemo("G:\\data\\crawler",true);
        crawlerDemo.start(1);
    }

}
