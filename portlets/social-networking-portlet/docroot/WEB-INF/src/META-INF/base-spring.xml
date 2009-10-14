<?xml version="1.0" encoding="UTF-8"?>

<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.5.xsd http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd"
>
	<bean class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />
	<bean class="com.liferay.portal.spring.annotation.BeanReferenceAnnotationBeanPostProcessor" />
	<bean id="basePersistence" abstract="true">
		<property name="dataSource" ref="liferayDataSource" />
		<property name="sessionFactory" ref="liferaySessionFactory" />
	</bean>
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
	<aop:config>
		<aop:pointcut id="transactionOperation" expression="bean(*Service.impl) || bean(*Service.velocity)" />
		<aop:advisor advice-ref="transactionAdvice" pointcut-ref="transactionOperation" />
	</aop:config>
	<bean id="velocityHookAdvice" class="com.liferay.portal.spring.aop.ServiceVelocityAdvice">
		<property name="classLoader">
			<bean class="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" factory-method="getClassLoader" />
		</property>
		<property name="exceptionSafe" value="true" />
	</bean>
	<aop:config>
		<aop:aspect ref="velocityHookAdvice">
			<aop:around pointcut="bean(*.velocity)" method="invoke" />
		</aop:aspect>
	</aop:config>
</beans>