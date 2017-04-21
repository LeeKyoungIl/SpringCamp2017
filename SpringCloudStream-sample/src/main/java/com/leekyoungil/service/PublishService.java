package com.leekyoungil.service;

import com.leekyoungil.channel.MessageInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 * Created by leekyoungil (leekyoungil@gmail.com) on 4/10/2017.
 *
 * Publish class
 */
@Log4j2
@Service
@EnableBinding(Source.class)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PublishService {

    private final @NotNull Source source;

    public boolean publish (MessageInterface messageInterface) {
        try {
            return source.output().send(MessageBuilder.withPayload(messageInterface).build());
        } catch (Exception e) {
            log.error("메시지 전송이 실패했습니다.");
            return false;
        }
    }
}
