package client;

import library.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("library")
@Configuration(proxyBeanMethods = false)
public class MyConfiguration {

    @Bean
    @ConditionalOnBean(Property1.class)
    public Property1 propertyTestClass1() {
        return new Property1();
    }

    @Bean
    @ConditionalOnMissingBean(Property1.class)
    public MissingBean1 propertyBeanTestClass1() {
        return new MissingBean1();
    }

    @Bean
    @ConditionalOnBean(Property2.class)
    public Property2 propertyTestClass2() {
        return new Property2();
    }

    @Bean
    @ConditionalOnMissingBean(Property2.class)
    public MissingBean2 propertyBeanTestClass2() {
        return new MissingBean2();
    }
}