package org.contextmapper.generated.newquestioncontext.config;

import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.AddPreferencesCommand.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.CreatedQuestionEvent.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.NewQuestion.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.NewQuestion.class.getName() + ".tags");
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfos.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfosViewedEvent.class.getName());
            createCache(
                cm,
                org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfosViewedEvent.class.getName() + ".tagInfos"
            );
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.NotifiedQuestionEvent.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.NotifiedUsers.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.NotifiedUsers.class.getName() + ".users");
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.PreferencesAddedEvent.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.PrepareQuestionCommand.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.ResourceId.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.SendByPreferencesCommand.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.UserPreferences.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.UserPreferences.class.getName() + ".preferences");
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.UserPreferencesTagInfos.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.UserWithPreferencesId.class.getName());
            createCache(cm, org.contextmapper.generated.newquestioncontext.domain.ViewTagsForQuestionCommand.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cache.clear();
        } else {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
