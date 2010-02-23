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
		<aop:pointcut id="transactionOperation" expression="bean(*Service)" />
		<aop:advisor advice-ref="transactionAdvice" pointcut-ref="transactionOperation" order="1" />
	</aop:config>
</beans>