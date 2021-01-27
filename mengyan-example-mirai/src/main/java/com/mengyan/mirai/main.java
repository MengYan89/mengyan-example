package com.mengyan.mirai;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;

import java.io.File;

public class main {
    private static final Integer qq = 0;
    private static final String pwd = "1";
    public static void main(String[] args) {
        //1.创建和配置BOT
        final Bot bot = BotFactory.INSTANCE.newBot(qq, pwd, new BotConfiguration(){{
            // 设置Bot运行路径,会生成device.json
            setWorkingDir(new File("dir"));
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

        bot.login();

        while (true){

        }
    }
}
