<?xml version="1.0" encoding="UTF-8"?>

<beans
	default-destroy-method="destroy"
	default-init-method="afterPropertiesSet"
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
>
	<bean class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />
	<bean class="com.liferay.portal.spring.annotation.BeanReferenceAnnotationBeanPostProcessor" />
	<bean id="portletClassLoader" class="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" factory-method="getClassLoader" />
	<bean id="servletContextName" class="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" factory-method="getServletContextName" />
	<bean id="basePersistence" abstract="true">
		<property name="dataSource" ref="liferayDataSource" />
		<property name="sessionFactory" ref="liferaySessionFactory" />
	</bean>
	<bean id="serviceAdvice" class="com.liferay.portal.monitoring.statistics.service.ServiceMonitorAdvice" factory-method="getInstance">
		<property name="monitoringDestinationName" value="liferay/monitoring" />
		<property name="nextMethodInterceptor" ref="asyncAdvice" />
	</bean>
	<bean id="asyncAdvice" class="com.liferay.portal.messaging.async.AsyncAdvice">
		<property name="defaultDestinationName" value="liferay/async_service" />
		<property name="nextMethodInterceptor" ref="threadLocalCacheAdvice" />
	</bean>
	<bean id="threadLocalCacheAdvice" class="com.liferay.portal.cache.ThreadLocalCacheAdvice">
		<property name="nextMethodInterceptor" ref="transactionAdvice" />
	</bean>
	<bean id="transactionAdvice" class="com.liferay.portal.spring.transaction.TransactionInterceptor">
		<property name="transactionManager" ref="liferayTransactionManager" />
		<property name="transactionAttributeSource">
			<bean class="com.liferay.portal.spring.transaction.AnnotationTransactionAttributeSource" />
		</property>
	</bean>
	<aop:config proxy-target-class="false">
		<aop:pointcut id="serviceOperation" expression="bean(*Service)" />
		<aop:advisor advice-ref="serviceAdvice" pointcut-ref="serviceOperation" />
	</aop:config>
</beans>