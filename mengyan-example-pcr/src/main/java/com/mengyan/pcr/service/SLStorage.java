package com.mengyan.pcr.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于储存每个人SL的状态
 */
public class SLStorage {
    private static Map<Long, Map<Long, Boolean>> slStorage = new ConcurrentHashMap<Long, Map<Long, Boolean>>();

    /**
     * 重置每个人的SL状态
     * pcr每日6点会刷新每日跟随刷新时间进行
     * 或许这个方法应该叫Reset?
     * 但是打轴翻车重打的时候大家说Remake  :D
     */
    public synchronized static void remake() {
        slStorage = new ConcurrentHashMap<Long, Map<Long, Boolean>>();
    }

    /**
     * 查询是否进行过SL，如果没有则记录
     * 如果成功记录SL则返回true
     * 如果已经SL过则会返回false
     * @param groupId
     * @param memberId
     * @return
     */
    public synchronized static boolean isSLAndRecord(Long groupId, Long memberId) {
        Map<Long, Boolean> groupStorage = slStorage.get(groupId);
        if (groupStorage == null) {
            // 没有进行过SL
            groupStorage =  new ConcurrentHashMap<Long, Boolean>();
            groupStorage.put(memberId, true);
            slStorage.put(groupId, groupStorage);
            // 已成功记录SL
            return true;
        } else {
            Boolean isSL = groupStorage.get(memberId);
            if (isSL == null) {
                // 没有进行过SL
                groupStorage.put(memberId, true);
                // 已成功记录SL
                return true;
            } else if (isSL) {
                // 已经进行过SL
                // 没有成功记录
                return false;
            } else {
                // 没有进行过SL
                // 这个分支在逻辑正常的情况下不会触发
                return false;
            }
        }
    }

    /**
     * 记录SL
     * 成功返回true
     * @param groupId
     * @param memberId
     * @return
     */
    public synchronized static boolean record(Long groupId, Long memberId) {
        Map<Long, Boolean> groupStorage = slStorage.get(groupId);
        if (groupStorage == null) {
            groupStorage =  new ConcurrentHashMap<Long, Boolean>();
            groupStorage.put(memberId, true);
            slStorage.put(groupId, groupStorage);
            return true;
        } else {
            groupStorage.put(memberId, true);
            return true;
        }
    }

    /**
     * 判断这个人有没有SL
     * 有进行过SL返回true
     * 没有进行过SL返回false
     * @param groupId
     * @param memberId
     * @return
     */
    public static boolean isSL(Long groupId, Long memberId) {
        Map<Long, Boolean> groupStorage = slStorage.get(groupId);
        if (groupStorage == null) {
            // 没有进行过SL
            return false;
        } else {
            Boolean isSL = groupStorage.get(memberId);
            if (isSL == null) {
                // 没有进行过SL
                return false;
            } else if (isSL) {
                // 已经进行SL
                return true;
            } else {
                // 没有进行过SL
                return false;
            }
        }
    }


}
