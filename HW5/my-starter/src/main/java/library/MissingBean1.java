package library;

import org.springframework.beans.factory.InitializingBean;

public class MissingBean1 implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MissingBean1 from library");
    }
}