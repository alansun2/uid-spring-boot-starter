package com.alan344.uid.baidu;

import com.alan344.uid.baidu.impl.CachedUidGenerator;
import com.alan344.uid.baidu.impl.DefaultUidGenerator;
import com.alan344.uid.baidu.worker.DisposableWorkerIdAssigner;
import com.alan344.uid.baidu.worker.WorkerIdAssigner;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author AlanSun
 * @date 2022/12/11 19:43
 **/
@MapperScan(basePackages = "com.alan344.uid.baidu.worker.dao")
@ConditionalOnProperty(prefix = "uid", name = "enable-uid-gen", matchIfMissing = true)
@EnableConfigurationProperties(UidProperties.class)
@Configuration(proxyBeanMethods = false)
public class UidAutoConfiguration {
    @Autowired
    private UidProperties uidProperties;

    @Bean
    public WorkerIdAssigner workerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

    @Primary
    @Bean
    public CachedUidGenerator cachedUidGenerator(WorkerIdAssigner workerIdAssigner) {
        final CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        cachedUidGenerator.setWorkerIdAssigner(workerIdAssigner);
        cachedUidGenerator.setTimeBits(uidProperties.getTimeBits());
        cachedUidGenerator.setWorkerBits(uidProperties.getWorkerBits());
        cachedUidGenerator.setSeqBits(uidProperties.getSeqBits());
        cachedUidGenerator.setEpochStr(uidProperties.getEpochStr());
        return cachedUidGenerator;
    }


    @Bean
    public DefaultUidGenerator defaultUidGenerator(WorkerIdAssigner workerIdAssigner) {
        final DefaultUidGenerator defaultUidGenerator = new DefaultUidGenerator();
        defaultUidGenerator.setWorkerIdAssigner(workerIdAssigner);
        defaultUidGenerator.setTimeBits(uidProperties.getTimeBits());
        defaultUidGenerator.setWorkerBits(uidProperties.getWorkerBits());
        defaultUidGenerator.setSeqBits(uidProperties.getSeqBits());
        defaultUidGenerator.setEpochStr(uidProperties.getEpochStr());
        return defaultUidGenerator;
    }

    @Configuration(proxyBeanMethods = false)
    public static class UidMapperConfigurationCustomizer implements ConfigurationCustomizer {

        @Override
        public void customize(org.apache.ibatis.session.Configuration configuration) {
            final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
            final Resource resource = resourceResolver.getResource("classpath:mapper/WORKER_NODE.xml");
            XMLMapperBuilder xmlMapperBuilder;
            try {
                xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(),
                        configuration, resource.toString(), configuration.getSqlFragments());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            xmlMapperBuilder.parse();
        }
    }

}