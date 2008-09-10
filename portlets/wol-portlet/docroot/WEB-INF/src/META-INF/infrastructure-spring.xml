<?xml version="1.0" encoding="UTF-8"?>

<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd"
>
	<bean id="liferayDataSource" class="org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy">
		<property name="targetDataSource" ref="liferayDataSourceTarget" />
	</bean>
	<bean id="liferayDataSourceTarget" class="com.liferay.portal.kernel.jndi.PortalJNDIUtil" factory-method="getDataSource" />
</beans>