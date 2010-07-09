<?xml version="1.0" encoding="UTF-8"?>

<beans
	default-destroy-method="destroy"
	default-init-method="afterPropertiesSet"
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:util="http://www.springframework.org/schema/util"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util-3.0.xsd"
>
	<bean id="com.liferay.portal.spring.aop.ChainableMethodAdviceInjector" class="com.liferay.portal.spring.aop.ChainableMethodAdviceInjector">
		<property name="injectCondition">
			<util:constant static-field="com.liferay.portal.util.PropsValues.CLUSTER_LINK_ENABLED" />
		</property>
		<property name="newChainableMethodAdvice">
			<bean class="com.liferay.portal.cluster.ClusterableAdvice">
				<property name="servletContextName" ref="servletContextName" />
			</bean>
		</property>
		<property name="parentChainableMethodAdvice" ref="serviceAdvice" />
	</bean>
</beans>