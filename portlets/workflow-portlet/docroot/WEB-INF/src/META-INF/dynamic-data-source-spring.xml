<?xml version="1.0" encoding="UTF-8"?>

<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd"
>
	<bean id="transactionAdvice" class="com.liferay.portal.dao.jdbc.aop.DynamicDataSourceTransactionInterceptor">
		<property name="dynamicDataSourceTargetSource">
			<bean class="com.liferay.portal.kernel.util.InfrastructureUtil" factory-method="getDynamicDataSourceTargetSource" />
		</property>
		<property name="transactionManager" ref="liferayTransactionManager" />
		<property name="transactionAttributeSource">
			<bean class="org.springframework.transaction.annotation.AnnotationTransactionAttributeSource">
				<constructor-arg>
					<bean class="com.liferay.portal.spring.annotation.PortalTransactionAnnotationParser" />
				</constructor-arg>
			</bean>
		</property>
	</bean>
</beans>