<?xml version="1.0" encoding="UTF-8"?>

<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.5.xsd http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-2.5.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.5.xsd"
>
	<context:annotation-config/>
	<aop:config>
		<aop:pointcut id="serviceOperation" expression="bean(*Service.impl)" />
		<aop:pointcut id="persistenceOperation" expression="bean(*Persistence.impl)" />
		<aop:advisor advice-ref="serviceTransactionAdvice" pointcut-ref="serviceOperation" />
		<aop:advisor advice-ref="persistenceTransactionAdvice" pointcut-ref="persistenceOperation" />
	</aop:config>
	<tx:advice id="serviceTransactionAdvice" transaction-manager="liferayTransactionManager">
		<tx:attributes>
			<tx:method name="get*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="search*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
  		</tx:attributes>
	</tx:advice>
	<tx:advice id="persistenceTransactionAdvice" transaction-manager="liferayTransactionManager">
		<tx:attributes>
			<tx:method name="contains*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="count*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="find*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="get*" propagation="SUPPORTS" read-only="true" />
			<tx:method name="*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
  		</tx:attributes>
	</tx:advice>
	<bean id="portletBeanFactoryPostProcessor" class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />
	<bean id="portletClassLoader" class="com.liferay.portal.kernel.portlet.PortletClassLoaderUtil" factory-method="getClassLoader" />
	<bean id="logInterceptor" class="com.liferay.portal.spring.aop.LogInterceptor" />
	<bean id="baseLogService" abstract="true">
		<property name="interceptorNames">
			<list>
				<value>logInterceptor</value>
			</list>
		</property>
	</bean>
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
	<bean id="basePersistence" abstract="true">
		<property name="dataSource" ref="liferayDataSource" />
		<property name="sessionFactory" ref="liferaySessionFactory" />
	</bean>
</beans>