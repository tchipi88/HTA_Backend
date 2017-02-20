/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ganeo.appli.hta.config;

import java.util.Set;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import org.ganeo.appli.hta.HTAProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 *
 * @author tchipnangngansopa
 */
@SuppressWarnings("unused")
@Configuration
@EnableCaching
@AutoConfigureAfter(value = {  JpaConfig.class })
public class CacheConfig {
     private final Logger log = LoggerFactory.getLogger(CacheConfig.class);

    @PersistenceContext
    private EntityManager entityManager;

     private net.sf.ehcache.CacheManager cacheManager;

    @PreDestroy
    public void destroy() {
               cacheManager.shutdown();
    }

    @Bean
    public CacheManager cacheManager(HTAProperties jHipsterProperties) {
        log.debug("Starting Ehcache");
        cacheManager = net.sf.ehcache.CacheManager.create();
        cacheManager.getConfiguration().setMaxBytesLocalHeap(jHipsterProperties.getCache().getEhcache().getMaxBytesLocalHeap());
        log.debug("Registering Ehcache Metrics gauges");
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        for (EntityType<?> entity : entities) {

            String name = entity.getName();
            if (name == null || entity.getJavaType() != null) {
                name = entity.getJavaType().getName();
            }
            Assert.notNull(name, "entity cannot exist without a identifier");

            net.sf.ehcache.Cache cache = cacheManager.getCache(name);
            if (cache != null) {
                cache.getCacheConfiguration().setTimeToLiveSeconds(jHipsterProperties.getCache().getTimeToLiveSeconds());
               
            }
        }
        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;
    }
}
