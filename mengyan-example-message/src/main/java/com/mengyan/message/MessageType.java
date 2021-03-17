package com.mengyan.message;

public enum MessageType {
    TEXT("text","文本"),
    IMAGE("image","图片"),
    AT("at","AT个人"),
    AT_ALL("at_all","AT全体"),
    FLASH_IMAGE("flash_image","闪照"),
    VOICE("voice","语音"),
    DICE("dice","骰子"),
    MESSAGE_CHAIN("message_chain", "消息链");
    private String type;
    private String name;

    MessageType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
