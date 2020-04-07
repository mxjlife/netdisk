package com.mxjlife.netdisk.common.cache;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.mxjlife.netdisk.mapper.ConfigMapper;
import com.mxjlife.netdisk.pojo.base.ConfigInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description: guava缓存
 * author: mxj
 * Date: 2018/9/20
 * Time: 9:40
 */
@Slf4j
@Component
public class GuavaCache {

    private static ConfigMapper configMapper;

    @Autowired
    public void setConfigDao(ConfigMapper configMapper) {
        GuavaCache.configMapper = configMapper;
    }

    private GuavaCache() {
    }

    private static volatile LoadingCache<String,String> cache;
    static {
        cache = CacheBuilder.newBuilder()
            // 设置并发级别为5，并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(5)
            // 设置写缓存后过期时间
            .expireAfterWrite(1, TimeUnit.MINUTES)
            // 设置缓存容器的初始容量为100
            .initialCapacity(100)
            // 设置缓存最大容量为10000，超过100之后就会按照LRU最近最少使用算法来移除缓存项
            .maximumSize(1000)
            // 设置要统计缓存的命中率
            .recordStats()
            // 设置缓存的移除通知
            .removalListener((RemovalListener<String, String>) rn -> {
                if(log.isDebugEnabled()){
                    log.debug("缓存{}被移除",rn.getKey());
                }
            })
            // build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) {
                    String value = null;
                    try {
                        if(configMapper != null){
                            ConfigInfo config = configMapper.getByKey(key);
                            if(config != null && StringUtils.isNotBlank(config.getValue())){
                                value = config.getValue();
                            } else {
                                value = "";
                            }
                        }
                    } catch (Exception e){
                        log.error("加载guava缓存出错: {}", e.getMessage());
                    }
                    if(log.isDebugEnabled()){
                        log.debug("加载guava缓存 [{}={}]", key, value);
                    }
                    return value;
                }
            });
    }

    /**
     * @desction: 获取缓存
     */
    public static String get(String key){
        //此方法缓存中有则返回, 没有则返回null , get 方法没有时会load
//        return StringUtils.isNotEmpty(key)?cache.getIfPresent(key):null;
        //此方法缓存中有则返回, 没有则返回null
        String res = null;
        if(StringUtils.isNotBlank(key)){
            try {
                res = cache.get(key);
            } catch (Exception e){
                log.error("加载guava缓存出错: {}", e.getMessage());
            }

        }
        return res;
    }

    /**
     * @desction: 获取缓存
     */
    public static <T> T get(String key, Class<T> clazz){
        //此方法缓存中有则返回, 没有则返回null , get 方法没有时会load
//        return StringUtils.isNotEmpty(key)?cache.getIfPresent(key):null;
        //此方法缓存中有则返回, 没有则返回null
        T res = null;
        if(StringUtils.isNotBlank(key)){
            try {
                String s = cache.get(key);
                if(StringUtils.isNotBlank(s)){
                    res = JSONObject.parseObject(s, clazz);
                }
            } catch (Exception e){
                log.error("加载guava缓存出错: {}", e.getMessage());
            }

        }
        return res;
    }

    /**
     * @desction: 放入缓存
     */
    public static void put(String key, String value){
        if(StringUtils.isNotBlank(key) && value !=null){
            cache.put(key,value);
        }
    }

    /**
     * @desction: 放入缓存
     */
    public static void put(String key, Object value){
        if(StringUtils.isNotBlank(key) && value !=null){
            String s = JSONObject.toJSONString(value);
            cache.put(key,s);
        }
    }

    /**
     * @desction: 移除缓存
     */
    public static void remove(String key){
        if(StringUtils.isNotEmpty(key)){
            cache.invalidate(key);
        }
    }
    /**
     * @desction: 批量删除缓存
     */
    public static void remove(List<String> keys){
        if(keys !=null && keys.size() >0){
            cache.invalidateAll(keys);
        }
    }
}
