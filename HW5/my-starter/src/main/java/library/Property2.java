package library;

import org.springframework.beans.factory.InitializingBean;

public class Property2 implements InitializingBean, PropertyInterface {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Property2 from library");
    }
}