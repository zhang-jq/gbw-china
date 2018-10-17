package com.spider.gbwchina.scheduling;

import com.spider.gbwchina.downloader.SimpleHttpClientDownloader;
import com.spider.gbwchina.pipeline.SimplePipeline;
import com.spider.gbwchina.processor.GbwChinaProcessor;
import com.spider.gbwchina.spider.SimpleSpider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.monitor.SpiderMonitor;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 定时爬虫
 */
@Component
public class SpiderScheduler {

    @Resource
    private SimplePipeline simplePipeline;

    @Scheduled(cron = "0 0 0 1 * ?")
//    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void run() {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
