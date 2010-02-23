<?xml version="1.0" encoding="UTF-8"?>

<beans
	default-destroy-method="destroy"
	default-init-method="afterPropertiesSet"
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"
>
	<bean id="liferayHibernateSessionFactory" class="com.liferay.portal.spring.hibernate.PortletHibernateConfiguration">
		<property name="dataSource" ref="liferayDataSource" />
	</bean>
	<bean id="liferaySessionFactory" class="com.liferay.portal.dao.orm.hibernate.SessionFactoryImpl">
		<property name="sessionFactoryClassLoader" ref="portletClassLoader" />
		<property name="sessionFactoryImplementor" ref="liferayHibernateSessionFactory" />
	</bean>
	<bean id="liferayTransactionManager" class="com.liferay.portal.kernel.util.InfrastructureUtil" factory-method="getTransactionManager" />
</beans>