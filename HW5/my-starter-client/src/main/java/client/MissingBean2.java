package client;

import library.PropertyInterface;
import org.springframework.beans.factory.InitializingBean;

public class MissingBean2 implements InitializingBean, PropertyInterface {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MissingBean2 from client");
    }
}