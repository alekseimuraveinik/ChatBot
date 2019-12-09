package root;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextHolder {
    public static ApplicationContext context;

    public static ApplicationContext get(){
        if(context == null)
            context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        return context;
    }

    private ContextHolder(){}
}
