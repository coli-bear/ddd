package com.study;

import com.study.contextmapping.DownstreamContext;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StrategicPatternsApplication {
    private final DownstreamContext downstreamContext;

    public StrategicPatternsApplication(DownstreamContext downstreamContext) {
        this.downstreamContext = downstreamContext;
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StrategicPatternsApplication.class, args);
        context.close();

    }

    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            String upstreamData = downstreamContext.getUpstreamData();
            System.out.println("Upstream Data: " + upstreamData);
        };
    }
}
