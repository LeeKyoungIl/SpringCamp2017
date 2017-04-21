package com.leekyoungil.channel;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * Created by leekyoungil (leekyoungil@gmail.com) on 4/10/2017.
 *
 * Pub/Sub에서 사용할 Data Payload Model
 */
@Data
@NoArgsConstructor
public class MessageInterface {

    private int code = 200;
    private String message;
    private String uniqueValue;

    public MessageInterface (String message) {
        if (StringUtils.isEmpty(message)) {
            this.code = 500;
        } else {
            this.message = message;
            this.uniqueValue = UUID.randomUUID().toString().replaceAll("-", "");
        }
    }
}
