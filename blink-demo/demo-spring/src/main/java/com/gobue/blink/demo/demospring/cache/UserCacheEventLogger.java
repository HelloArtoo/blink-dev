package com.gobue.blink.demo.demospring.cache;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class UserCacheEventLogger implements CacheEventListener<Object, Object> {

    @Override
    public void onEvent(CacheEvent<?, ?> cacheEvent) {
        log.info("person caching event {} {} {} {}",
                cacheEvent.getType(),
                cacheEvent.getKey(),
                cacheEvent.getOldValue(),
                cacheEvent.getNewValue());
    }
}
