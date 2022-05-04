package library;

import org.springframework.beans.factory.InitializingBean;

public class Property1 implements InitializingBean, PropertyInterface {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Property1 from library");
    }
}