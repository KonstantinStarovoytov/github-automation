package core.spring;

import com.codeborne.selenide.SelenideElement;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class Spring implements ApplicationContextAware {

    private static ApplicationContext appContext;

    public static <Type> Type get(Class<Type> beanClass) {
        return appContext.getBean(beanClass);
    }

    public static <Type> Type get(Class<Type> beanClass, SelenideElement self) {
        return appContext.getBean(beanClass, self);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }
}