<?xml version="1.0" encoding="UTF-8"?>

<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.5.xsd http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd"
>
	<bean id="beanReferenceAnnotationBeanPostProcessor" class="com.liferay.portal.spring.annotation.BeanReferenceAnnotationBeanPostProcessor" />
	<aop:config>
		<aop:pointcut id="transactionOperation" expression="bean(*Service.impl)" />
		<aop:advisor advice-ref="transactionAdvice" pointcut-ref="transactionOperation" />
	</aop:config>
	<bean id="transactionAdvice" class="org.springframework.transaction.interceptor.TransactionInterceptor">
		<property name="transactionManager" ref="liferayTransactionManager" />
		<property name="transactionAttributeSource">
			<bean class="org.springframework.transaction.annotation.AnnotationTransactionAttributeSource">
				<constructor-arg>
					<bean class="com.liferay.portal.spring.annotation.PortalTransactionAnnotationParser" />
				</constructor-arg>
			</bean>
		</property>
	</bean>
	<bean id="portletBeanFactoryPostProcessor" class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />
	<bean id="portletClassLoader" class="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" factory-method="getClassLoader" />
	<bean id="logAdvice" class="com.liferay.portal.spring.aop.LogAdvice" />
	<bean id="velocityServiceInterceptor" class="com.liferay.portal.spring.aop.BeanInterceptor">
		<property name="classLoader" ref="portletClassLoader" />
		<property name="exceptionSafe" value="true" />
	</bean>
	<bean id="baseVelocityService" abstract="true">
		<property name="interceptorNames">
			<list>
				<value>velocityServiceInterceptor</value>
			</list>
		</property>
	</bean>
	<bean id="baseModelExtensionAdvice" class="com.liferay.portal.spring.aop.ModelExtensionAdvice" abstract="true" />
	<bean id="basePersistence" abstract="true">
		<property name="dataSource" ref="liferayDataSource" />
		<property name="sessionFactory" ref="liferaySessionFactory" />
	</bean>
</beans>