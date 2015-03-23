<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<h3>hibernate-clustered</h3>

<%
if (!GetterUtil.getBoolean(PropsUtil.get(PropsKeys.HIBERNATE_CACHE_USE_QUERY_CACHE)) || !GetterUtil.getBoolean(PropsUtil.get(PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE))) {
%>

	<p>
		Hibernate second level cache is disabled, please enable it by updating portal-ext.properties with:
	</p>

	<p>
		hibernate.cache.use_query_cache=true<br />
		hibernate.cache.use_second_level_cache=true
	</p>

<%
	}
	else {
%>

	<p>

		<%= _testAttributeList("hibernate-clustered", "com.liferay.portlet.asset.model.impl.AssetTagPropertyImpl", false, 1000, 60) %>
		<%= _testAttributeList("hibernate-clustered", "com.liferay.testcacheconfiguration.HibernateClusteredObject", false, 100000, 6000) %>

	</p>

<%
	}
%>

<h3>liferay-multi-vm-clustered</h3>

<p>

	<%= _testAttributeList("liferay-multi-vm-clustered", "com.liferay.portal.kernel.dao.orm.EntityCache.com.liferay.portal.model.impl.UserImpl", false, 10, 60) %>
	<%= _testAttributeList("liferay-multi-vm-clustered", "com.liferay.testcacheconfiguration.MultiVMClusteredObject", false, 100000, 6000) %>

</p>

<h3>liferay-single-vm</h3>

<p>

	<%= _testAttributeList("liferay-single-vm", "com.liferay.portal.util.WebCachePool", false, 1, 10) %>

</p>

<%!
private static String _testAttributeList(String cacheManagerName, String name, Object... values) throws Exception {
	Registry registry = RegistryUtil.getRegistry();

	MBeanServer mBeanServer = registry.getService(MBeanServer.class);

	ObjectName objectName = new ObjectName("net.sf.ehcache:type=CacheConfiguration,CacheManager=" + cacheManagerName + ",name=" + name);

	AttributeList attributeList = mBeanServer.getAttributes(objectName, new String[] {"Eternal", "MaxElementsInMemory", "TimeToIdleSeconds"});

	for (int i = 0; i < values.length; i++) {
		Attribute attribute = (Attribute)attributeList.get(i);

		String value = String.valueOf(attribute.getValue());

		if (!Validator.equals(value, String.valueOf(values[i]))) {
			return name + "=FAILED<br />";
		}
	}

	return name + "=PASSED<br />";
}
%>