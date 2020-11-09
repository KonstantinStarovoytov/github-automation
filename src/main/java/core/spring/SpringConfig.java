package core.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.SimpleThreadScope;


@Configuration
@ComponentScan(basePackages = "components, pages, tests")
public class SpringConfig {

    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return new CustomScopeRegisteringBeanFactoryPostProcessor();
    }

    public static class CustomScopeRegisteringBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
            configurableListableBeanFactory.registerScope("thread", new SimpleThreadScope());
        }
    }

    @Bean(name = "applicationContextProvider")
    public Spring applicationContextProvider() {
        return new Spring();
    }
}