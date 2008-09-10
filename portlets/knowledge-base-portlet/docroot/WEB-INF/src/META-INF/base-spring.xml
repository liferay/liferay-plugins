<?xml version="1.0" encoding="UTF-8"?>

<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.5.xsd http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.5.xsd"
>
	<aop:config>
		<aop:pointcut id="serviceOperation" expression="bean(*Service.impl)" />
		<aop:pointcut id="persistenceOperation" expression="bean(*Persistence.impl)" />
		<aop:advisor advice-ref="serviceTransactionAdvice" pointcut-ref="serviceOperation" />
		<aop:advisor advice-ref="persistenceTransactionAdvice" pointcut-ref="persistenceOperation" />
	</aop:config>
	<tx:advice id="serviceTransactionAdvice" transaction-manager="liferayTransactionManager">
		<tx:attributes>
			<tx:method name="add*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="check*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="clear*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="delete*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="set*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="update*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="*" propagation="SUPPORTS" read-only="true" />
  		</tx:attributes>
	</tx:advice>
	<tx:advice id="persistenceTransactionAdvice" transaction-manager="liferayTransactionManager">
		<tx:attributes>
			<tx:method name="remove*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="set*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="update*" propagation="REQUIRED" rollback-for="com.liferay.portal.PortalException,com.liferay.portal.SystemException" />
			<tx:method name="*" propagation="SUPPORTS" read-only="true" />
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