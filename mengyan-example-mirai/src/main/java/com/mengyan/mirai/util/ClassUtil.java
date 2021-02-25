package com.mengyan.mirai.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 与Class相关的工具类
 */
public class ClassUtil {
    /**
     * 从包中获取所有的class
     * @param pack
     * @return
     */
    public static Set<Class<?>> getClasses(String pack) {
        // class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否向下迭代查询
        boolean recursive = true;
        // 获取包的名字进行替换
        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举集合
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 迭代每一个元素
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议名称
                String protocol = url.getProtocol();
                // 如果是以文件形式保存
                if ("file".equals(protocol)) {
                    System.out.println("file类型的扫描");

                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    System.out.println("jar类型的扫描");
                    // 如果是jar包文件
                    // 定义一个JarFIle
                    JarFile jar;
                    // 获取jar
                    jar = ((JarURLConnection)url.openConnection()).getJarFile();
                    // 从此jar包得到一个枚举类
                    Enumeration<JarEntry> entries = jar.entries();
                    // 循环
                    while (entries.hasMoreElements()) {
                        // 从jar里获取一个实体 可以是目录或者是文件
                        JarEntry entry = entries.nextElement();

                        String name = entry.getName();

                        // 如果以/开头
                        if (name.charAt(0) == '/') {
                            // 获取后面的字符
                            name = name.substring(1);
                        }
                        // 如果前半部分和定义的包名相同
                        if (name.startsWith(packageDirName)) {
                            int idx = name.lastIndexOf('/');

                            // 如果以/ 结尾 是一个包
                            if (idx != -1) {
                                packageName = name.substring(0, idx).replace('/', '.');
                            }
                            // 如果可以迭代下去并且是一个包
                            if ((idx != -1) || recursive) {
                                // 如果是一个.class文件 而不是目录
                                if (name.endsWith(".class") && !entry.isDirectory()) {
                                    // 去掉".class"获取真正的类名
                                    String className = name.substring(packageName.length() + 1, name.length() - 6);
                                    try {
                                        // 添加到classes
                                       //  classes.add(Class.forName(packageName + '.' + className));
                                        classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                                    } catch (ClassNotFoundException e) {
                                        // 找不到这个class文件
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            // 从jar包获取文件出错 或 从指定路径下获取文件枚举集合出错
            e.printStackTrace();
        }
        return classes;
    }

    /**
     * 以文件形式来获取包下的所有class
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static  void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个FIle
        File dir = new File(packagePath);
        // 如果不存在 或者不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在就获取包下的所有文件 包括目录
        // 自定义过滤规则 如果包含子目录并可以循环或是以.class结尾的文件才可以通过
        File[] dirFiles = dir.listFiles(pathname -> (recursive && pathname.isDirectory()) || (pathname.getName().endsWith(".class")));
        // 循环所有文件
        for (File file : dirFiles) {
            // 如果是目录 继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                        file.getAbsolutePath(),
                        recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉.class
                String className = file.getName().substring(0, file.getName().length() - 6);
                //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                try {
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // 找不到此类的.class文件
                    e.printStackTrace();
                }
            }
        }
    }
}
