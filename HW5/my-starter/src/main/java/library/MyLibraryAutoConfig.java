package library;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class MyLibraryAutoConfig {

    @Bean
    MyLibraryClass myLibraryClass() {
        return new MyLibraryClass();
    }

    @Bean
    @ConditionalOnProperty(prefix = "pref", name = "propertyName", havingValue = "value1")
    public Property1 propertyTestClass1(){
        return new Property1();
    }

    @Bean
    @ConditionalOnProperty(prefix = "pref", name = "propertyName", havingValue = "value2")
    public Property2 propertyTestClass2(){
        return new Property2();
    }


    @Bean
    @ConditionalOnBean(Bean1.class)
    public Bean1 beanTestClass(){
        return new Bean1();
    }

    @Bean
    @ConditionalOnMissingBean(MissingBean1.class)
    public  MissingBean1 missingBeanTestClass(){
        return new MissingBean1();
    }
}
