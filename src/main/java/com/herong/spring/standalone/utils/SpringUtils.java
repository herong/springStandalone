package com.herong.spring.standalone.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * @author hr spring tool
 */
public class SpringUtils implements ApplicationContextAware {

	/**
	 * spring ApplicationContext
	 */
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringUtils.applicationContext = applicationContext;
	}

	public <T> T getBean(String name, Class<T> cls) {
		if (StringUtils.isEmpty(name)) {
			throw new NullPointerException("name 为空");
		}

		if (cls == null) {
			throw new NullPointerException("cls 为空");
		}

		if (applicationContext == null) {
			throw new NullPointerException("applicationContext is null");
		}

		return applicationContext.getBean(name, cls);
	}
}
