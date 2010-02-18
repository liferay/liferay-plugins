<?xml version="1.0" encoding="UTF-8"?>

<beans
	default-destroy-method="destroy"
	default-init-method="afterPropertiesSet"
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
>
	<bean id="liferayDataSource" class="org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy">
		<property name="targetDataSource">
			<bean class="com.liferay.portal.kernel.util.InfrastructureUtil" factory-method="getDataSource" />
		</property>
	</bean>
</beans>