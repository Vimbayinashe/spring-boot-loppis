package se.iths.springloppis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class BeanScopesDemo {

    Logger logger = LoggerFactory.getLogger(BeanScopesDemo.class);

    @PostConstruct
    public void init() {
        logger.info("BeanScopesDemo bean is alive!!");
    }

    // Singleton - default bean type in @Component
    // Prototype - Ny böna vid varje tillfälle den refereras
    // Session - används enbart i webbsammanhang. En instans per 'session'
    // Request - används enbart i webbsammanhang. En instans per 'request'

}
