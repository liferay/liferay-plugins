<?xml version="1.0"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd">

<beans>
	<bean id="portletBeanFactoryPostProcessor" class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />
	<bean id="portletClassLoader" class="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" lazy-init="true" factory-method="getClassLoader" />
	<bean id="velocityServiceInterceptor" class="com.liferay.portal.spring.aop.ServiceInterceptor" lazy-init="true">
		<property name="classLoader">
			<ref bean="portletClassLoader" />
		</property>
		<property name="exceptionSafe">
			<value>true</value>
		</property>
	</bean>
</beans>