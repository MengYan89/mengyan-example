package com.mengyan.mirai.example;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageRecallEvent;
import net.mamoe.mirai.message.data.*;

import java.util.HashMap;
import java.util.Map;

/**
 * marai框架撤回消息事件
 */
public class GroupRecallEventExample {
    public static void main(String[] args) {
        Map<Integer, ContactAndMessage> messageEcha = new HashMap<>();
        Bot bot = LoginExample.login();
        if(bot.isOnline()) {
            bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, event -> {
                System.out.println(event.getGroup().getId()+":"+event.getMessage());
                System.out.println(event.getMessage().contentToString());
                System.out.println(event.getMessage().serializeToMiraiCode());
                System.out.println(event.getTime());
                messageEcha.put(event.getTime(), new ContactAndMessage(event.getGroup(), event.getMessage()));
            });
            bot.getEventChannel().subscribeAlways(MessageRecallEvent.class, event -> {
                ContactAndMessage contactAndMessage = messageEcha.get(event.getMessageTime());
                if (contactAndMessage != null) {
                    MessageChain chain = new MessageChainBuilder()
                            .append(new PlainText("群友"+event.getAuthor().getId()+"撤回了一条消息:"))
                            .append(contactAndMessage.getMessages())
                            .build();
                    contactAndMessage.getContact().sendMessage(chain);
                }
            });
        }

        while (true) {

        }

    }

    public static class ContactAndMessage {
        private Contact contact;
        private MessageChain messages;

        public ContactAndMessage(Contact contact, MessageChain messages) {
            this.contact = contact;
            this.messages = messages;
        }

        public Contact getContact() {
            return contact;
        }

        public void setContact(Contact contact) {
            this.contact = contact;
        }

        public MessageChain getMessages() {
            return messages;
        }

        public void setMessages(MessageChain messages) {
            this.messages = messages;
        }
    }
}
