package com.study.contextmapping;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.Method;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class DownstreamContext {

    public String getUpstreamData() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpHost localhost = new HttpHost("localhost", 8080);
            ClassicHttpRequest request = new BasicClassicHttpRequest(Method.GET, localhost, "/");
            return client.execute(request, response -> {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

