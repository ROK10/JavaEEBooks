package library;

import org.springframework.beans.factory.InitializingBean;

public class Bean1 implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean1 from library");
    }
}