<?xml version="1.0"?>

<beans
	default-destroy-method="destroy"
	default-init-method="afterPropertiesSet"
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
>
	<bean id="transactionAdvice" class="com.liferay.portal.kernel.spring.util.SpringFactoryUtil" factory-method="newBean">
		<constructor-arg value="com.liferay.portal.dao.jdbc.aop.DynamicDataSourceTransactionInterceptor" />
		<constructor-arg>
			<map>
				<entry key="transactionAttributeSource" value-ref="transactionAttributeSource" />
				<entry key="transactionManager" value-ref="liferayTransactionManager" />
			</map>
		</constructor-arg>
	</bean>
</beans>