package com.mengyan.NettyInAction.m;

import java.net.InetSocketAddress;

/**
 * 13-1 LogEvent 消息
 */
public class LogEvent {
    public static final byte SEPARATOR = (byte) ':';
    private final InetSocketAddress source;
    private final String logfile;
    private final String msg;
    private final long received;

    /**
     * 用于传出消息的构造函数
     * @param logfile
     * @param msg
     */
    public LogEvent(String logfile, String msg) {
        this(null, logfile, msg, -1);
    }

    /**
     * 用于传入消息的构造函数
     * @param source
     * @param logfile
     * @param msg
     * @param received
     */
    public LogEvent(InetSocketAddress source, String logfile, String msg, long received) {
        this.source = source;
        this.logfile = logfile;
        this.msg = msg;
        this.received = received;
    }

    /**
     * 返回发送LogEvent的源的InetSocketAddress
     * @return
     */
    public InetSocketAddress getSource() {
        return source;
    }

    /**
     * 返回发送的LogEvent的日志文件的名称
     * @return
     */
    public String getLogfile() {
        return logfile;
    }

    /**
     * 返回消息内容
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 返回接受LogEvent的时间
     * @return
     */
    public long getReceived() {
        return received;
    }
}
