package com.mengyan.message;

/**
 * 发送图片相关信息
 * TODO 现在暂时只支持发送在线图片http的方式,后续补充其他方式
 */
public class ImageMessage extends MyMessage<String> {

    private MessageType imageType;

    private ImageMessage(String content, MessageType imageType) {
        super(content);
        this.imageType = imageType;
    }

    public MessageType getType() {
        return imageType;
    }

    /**
     * 生成正常图片信息
     * @param content
     * @return
     */
    public static ImageMessage createImage(String content) {
        return new ImageMessage(content, MessageType.IMAGE);
    }

    /**
     * 生成闪照
     * @param content
     * @return
     */
    public static ImageMessage createFlashImage(String content) {
        return new ImageMessage(content, MessageType.FLASH_IMAGE);
    }
}
