package com.leekyoungil.listener;

import com.leekyoungil.channel.MessageInterface;
import com.leekyoungil.util.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * Created by leekyoungil (leekyoungil@gmail.com) on 4/11/2017.
 *
 * Subscribe class
 */
@Log4j2
@EnableBinding(Sink.class)
public class SubscribeListener {

    @Value("${base.filePath}")
    private String filePath;
    @Value("${base.serverNo}")
    private String serverNo;

    @StreamListener(Sink.INPUT)
    public void subscribe (Message<?> message) {
        MessageInterface messageInterface = (MessageInterface) message.getPayload();

        if (messageInterface.getCode() != 200) {
            log.error("원본 콘텐츠에 문제가 있습니다.");
            return;
        }

        try {
            if (FileUtil.writeFileByString(filePath+"/daum_index_"+serverNo+".html", messageInterface.getMessage())) {
                log.info("파일 저장 완료");
            }
        } catch (IOException e) {
            log.error("파일 저장중 문제가 발생했습니다.");
            e.printStackTrace();
        }
    }
}
