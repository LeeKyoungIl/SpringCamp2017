package com.leekyoungil.service;

import com.leekyoungil.channel.MessageInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 * Created by kellin on 4/14/2017.
 */
@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class SpringCampTestService {

    private final @NotNull HttpService httpService;
    private final @NotNull PublishService publishService;

    public boolean publishToStream () {
        final String daum = "http://www.daum.net";
        String resultData = httpService.getHttpHtmlByUrl(daum);

        MessageInterface messageInterface = new MessageInterface(resultData);

        if (publishService.publish(messageInterface)) {
            log.info("메시지 브로커에 데이터 전송이 성공하였습니다.");
            return true;
        } else {
            log.error("메시지 브로커에 데이터 전송을 실패하였습니다.");
            return false;
        }
    }
}
