package com.mengyan.mirai.example;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.message.data.Voice;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.io.IOException;

/**
 * mirai框架发送音频消息
 */
public class SendVoiceMessageExample {
    public static void main(String[] args) throws IOException {
        Bot bot = LoginExample.login();
        if (bot.isOnline()) {
            // Contact是联系人对象, group与friend等都继承了这个对象
            Contact contact =bot.getGroup(910726832);
            // 创建一个外部资源对象
            ExternalResource externalResource = ExternalResource.Companion
                    .create(SendVoiceMessageExample.class.getClassLoader().getResourceAsStream("cbd.amr"));
            // 将资源上传获得Voice对象用于发送消息
            Voice voice = ExternalResource.Companion.uploadAsVoice(externalResource, contact);
            // 上传后就可关闭资源
            try {
                externalResource.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 给联系人对象发送消息
            contact.sendMessage(voice);
        }
    }
}
