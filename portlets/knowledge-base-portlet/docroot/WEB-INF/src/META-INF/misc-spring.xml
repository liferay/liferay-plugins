<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">
	<bean id="portletBeanFactoryPostProcessor" class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />
	<bean id="portletClassLoader" class="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" lazy-init="true" factory-method="getClassLoader" />
	<bean id="logInterceptor" class="com.liferay.portal.spring.aop.LogInterceptor" lazy-init="true" />
	<bean id="velocityServiceInterceptor" class="com.liferay.portal.spring.aop.BeanInterceptor" lazy-init="true">
		<property name="classLoader">
			<ref bean="portletClassLoader" />
		</property>
		<property name="exceptionSafe">
			<value>true</value>
		</property>
	</bean>
</beans>