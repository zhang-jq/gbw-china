package com.spider.gbwchina.web;

import com.alibaba.fastjson.JSON;
import com.spider.gbwchina.downloader.SimpleHttpClientDownloader;
import com.spider.gbwchina.model.Product;
import com.spider.gbwchina.pipeline.SimplePipeline;
import com.spider.gbwchina.processor.GbwChinaProcessor;
import com.spider.gbwchina.service.ProductService;
import com.spider.gbwchina.spider.SimpleSpider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@RestController
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private SimplePipeline simplePipeline;

    @GetMapping("/run")
    public String run() {
        try {
            SimpleHttpClientDownloader downloader = new SimpleHttpClientDownloader();
            SimpleSpider spider = SimpleSpider.create(new GbwChinaProcessor())
                    .addUrl("http://www.gbw-china.com")
                    .addPipeline(simplePipeline)
                    .setUUID(UUID.randomUUID().toString().replace("-", ""))
                    .thread(1);
            downloader.setUUID(spider.getUUID());
            spider.setDownloader(downloader);

            try {
                SpiderMonitor.instance().register(spider);
            } catch (Exception e) {
                e.printStackTrace();
            }
            spider.start();
            return "开始执行任务！";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "执行任务失败！";
    }

}
