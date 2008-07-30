<?xml version="1.0"?>
<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd">

<beans>
	<bean id="liferayDataSourceTarget" class="com.liferay.portal.kernel.jndi.PortalJNDIUtil" lazy-init="true" factory-method="getDataSource" />
	<bean id="liferayDataSource" class="org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy" lazy-init="true">
		<property name="targetDataSource">
			<ref bean="liferayDataSourceTarget" />
		</property>
	</bean>
	<bean id="liferayHibernateSessionFactory" class="com.liferay.portal.spring.hibernate.PortletHibernateConfiguration" lazy-init="true">
		<property name="dataSource">
			<ref bean="liferayDataSource" />
		</property>
	</bean>
	<bean id="liferaySessionFactory" class="com.liferay.portal.dao.orm.hibernate.SessionFactoryImpl" lazy-init="true">
		<property name="sessionFactoryImplementor">
			<ref bean="liferayHibernateSessionFactory" />
		</property>
	</bean>
	<bean id="liferayTransactionManager" class="org.springframework.orm.hibernate3.HibernateTransactionManager" lazy-init="true">
		<property name="dataSource">
			<ref bean="liferayDataSource" />
		</property>
		<property name="sessionFactory">
			<ref bean="liferayHibernateSessionFactory" />
		</property>
	</bean>
</beans>