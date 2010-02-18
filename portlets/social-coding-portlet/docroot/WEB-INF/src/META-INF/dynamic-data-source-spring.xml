<?xml version="1.0" encoding="UTF-8"?>

<beans
	default-destroy-method="destroy"
	default-init-method="afterPropertiesSet"
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
>
	<bean id="transactionAdvice" class="com.liferay.portal.dao.jdbc.aop.DynamicDataSourceTransactionInterceptor">
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