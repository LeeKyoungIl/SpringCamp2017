package com.leekyoungil.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.net.URI;

/**
 * Created by leekyoungil (leekyoungil@gmail.com) on 4/14/2017.
 *
 * http 통신을 위한 서비스
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class HttpService {

    private final @NotNull RestTemplate restTemplate;

    public String getHttpHtmlByUrl (String url) {
        URI uri = URI.create(url);
        String responseString = null;

        try {
            responseString = restTemplate.getForObject(uri, String.class);
        } catch (RestClientException rex) {
            log.error("Http통신중 오류가 발생했습니다...");
        }

        return responseString;
    }
}
