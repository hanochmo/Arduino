package com.ruoyi.arduino;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Component
@Lazy(false)
public class SpringContextUtil implements ApplicationContextAware {

    /**
     * ApplicationContext创建
     */
    private static ApplicationContext applicationContext;

    /**
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     *获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 通过name获取bean
     * @param name
     * @param <T>
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T)applicationContext.getBean(name);
    }


    /**
     * 通过class获取bean
     * @param clz
     * @param <T>
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clz) throws BeansException {
        return (T)applicationContext.getBean(clz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
