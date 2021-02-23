package com.mengyan.mirai;

import com.mengyan.mirai.example.QQProperties;
import com.mengyan.mirai.listener.GroupMessageEventHandlers;
import com.mengyan.mirai.listener.factory.ListenerFactory;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

public class Start {
    private static final Integer qq = QQProperties.getQQ();
    private static final String pwd = QQProperties.getPwd();

    public static void main(String[] args) {
        final Bot bot = BotFactory.INSTANCE.newBot(qq, pwd, new BotConfiguration() {{
            // 设置Bot运行路径,会生成device.json
            // setWorkingDir(new File("dir"));
            // 将设备信息储存进默认文件名device.json
            // 出现当前上网环境异常时可使用旧版mirai登录一段时间后使用旧版的设备信息即可正常登录
            fileBasedDeviceInfo("deviceInfo.json");
            // 存储为 "myDeviceInfo.json"
            // fileBasedDeviceInfo("myDeviceInfo.json")
            // 自定义设备信息
            // 在线生成自定义设备信息的 device.json: https://ryoii.github.io/mirai-devicejs-generator/
            // setDeviceInfo()
            // 切换登录协议
            // 分别有ANDROID_PHONE，ANDROID_PAD，ANDROID_WATCH
            // 默认为ANDROID_PHONE
            setProtocol(MiraiProtocol.ANDROID_PAD);

        }});

        bot.getEventChannel().registerListenerHost(ListenerFactory.getSingleton(GroupMessageEventHandlers.class));
        bot.login();

        while (true) {

        }
    }


}
