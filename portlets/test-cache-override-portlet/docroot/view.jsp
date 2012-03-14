<%
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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
%>

<%@ page import="javax.management.MBeanServer" %>
<%@ page import="javax.management.ObjectName" %>
<%@ page import="javax.management.AttributeList" %>
<%@ page import="com.liferay.portal.kernel.cache.MultiVMPoolUtil" %>
<%@ page import="com.liferay.portal.kernel.bean.PortalBeanLocatorUtil" %>
<%@ page import="javax.management.Attribute" %>

<%
MultiVMPoolUtil.getCache("com.liferay.test.CacheOverrideTest");

MBeanServer mbeanServer = (MBeanServer)PortalBeanLocatorUtil.locate("mBeanServer");

%>

<p>
<b>Testing the ability to override cache settings from plugins.  If any of the below values are false, then the test has failed.</b></b>
</p>

<hr />
<p>
	<b>Testing hibernate caches</b>
</p>

<%
ObjectName assetTagPropertyObjectName = new ObjectName(
	"net.sf.ehcache:type=CacheConfiguration,CacheManager=hibernate-clustered,name=com.liferay.portlet.asset.model.impl.AssetTagPropertyImpl");
AttributeList assetTagPropertyAttributeList = mbeanServer.getAttributes(
	assetTagPropertyObjectName, new String[] {"Eternal","MaxElementsInMemory","TimeToIdleSeconds"});

ObjectName hibernateNewObjectTestObjectName= new ObjectName(
	"net.sf.ehcache:type=CacheConfiguration,CacheManager=hibernate-clustered,name=com.liferay.portlet.cache.override.HibernateObjectTest");
AttributeList hibernateNewObjectTestAttributeList = mbeanServer.getAttributes(
	hibernateNewObjectTestObjectName, new String[] {"Eternal","MaxElementsInMemory","TimeToIdleSeconds"});

%>

<table>
	<tr>
		<td colspan="2">Overriding existing values: <%=assetTagPropertyObjectName%></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td>Eternal</td>
		<td>
			<%= String.valueOf(equals(assetTagPropertyAttributeList.get(0), false))%>
		</td>
	</tr>
	<tr>
		<td>MaxElementsInMemory</td>
		<td>
			<%= String.valueOf(equals(assetTagPropertyAttributeList.get(1), 1000))%>
		</td>
	</tr>
	<tr>
		<td>TimeToIdleSeconds</td>
		<td>
			<%= String.valueOf(equals(assetTagPropertyAttributeList.get(2), 60))%>
		</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">Adding new Object to cache</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td>Eternal</td>
		<td>
			<%= String.valueOf(equals(hibernateNewObjectTestAttributeList.get(0), false))%>
		</td>
	</tr>
	<tr>
		<td>MaxElementsInMemory</td>
		<td>
			<%= String.valueOf(equals(hibernateNewObjectTestAttributeList.get(1), 100000))%>
		</td>
	</tr>
	<tr>
		<td>TimeToIdleSeconds</td>
		<td>
			<%= String.valueOf(equals(hibernateNewObjectTestAttributeList.get(2), 6000))%>
		</td>
	</tr>
</table>

<hr>

<p>
	<b>Testing Single VM Caches</b>
</p>

<%
ObjectName liferayResourceCacheObjectName = new ObjectName(
	"net.sf.ehcache:type=CacheConfiguration,CacheManager=liferay-single-vm,name=com.liferay.portal.velocity.LiferayResourceCacheUtil");
AttributeList liferayResourceCacheAttributeList = mbeanServer.getAttributes(
	liferayResourceCacheObjectName, new String[] {"Eternal","MaxElementsInMemory","TimeToIdleSeconds"});


%>

<table>
	<tr>
		<td colspan="2">Overriding existing values: <%= liferayResourceCacheObjectName %></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td>Eternal</td>
		<td>
			<%= String.valueOf(equals(liferayResourceCacheAttributeList.get(0), false))%>
		</td>
	</tr>
	<tr>
		<td>MaxElementsInMemory</td>
		<td>
			<%= String.valueOf(equals(liferayResourceCacheAttributeList.get(1), 1))%>
		</td>
	</tr>
	<tr>
		<td>TimeToIdleSeconds</td>
		<td>
			<%= String.valueOf(equals(liferayResourceCacheAttributeList.get(2), 10))%>
		</td>
	</tr>
</table>

<hr>

<p>
	<b>Testing Multi VM Caches</b>
</p>

<%
ObjectName userObjectName = new ObjectName(
	"net.sf.ehcache:type=CacheConfiguration,CacheManager=liferay-multi-vm-clustered,name=com.liferay.portal.kernel.dao.orm.EntityCache.com.liferay.portal.model.impl.UserImpl");
AttributeList userAttributeList = mbeanServer.getAttributes(
	userObjectName, new String[] {"Eternal","MaxElementsInMemory","TimeToIdleSeconds"});

ObjectName multiVMNewObjectTestObjectName = new ObjectName(
	"net.sf.ehcache:type=CacheConfiguration,CacheManager=liferay-multi-vm-clustered,name=com.liferay.portlet.cache.override.MultiVMObjectTest");
AttributeList multiVMNewObjectTestAttributeList = mbeanServer.getAttributes(
	multiVMNewObjectTestObjectName, new String[] {"Eternal","MaxElementsInMemory","TimeToIdleSeconds"});

%>

<table>
	<tr>
		<td colspan="2">Overriding existing values: <%= userObjectName %></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td>Eternal</td>
		<td>
			<%= String.valueOf(equals(userAttributeList.get(0), false))%>
		</td>
	</tr>
	<tr>
		<td>MaxElementsInMemory</td>
		<td>
			<%= String.valueOf(equals(userAttributeList.get(1), 10))%>
		</td>
	</tr>
	<tr>
		<td>TimeToIdleSeconds</td>
		<td>
			<%= String.valueOf(equals(userAttributeList.get(2), 60))%>
		</td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">Adding new custom cache: <%= multiVMNewObjectTestObjectName %></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td>Eternal</td>
		<td>
			<%= String.valueOf(equals(multiVMNewObjectTestAttributeList.get(0), false))%>
		</td>
	</tr>
	<tr>
		<td>MaxElementsInMemory</td>
		<td>
			<%= String.valueOf(equals(multiVMNewObjectTestAttributeList.get(1), 100000))%>
		</td>
	</tr>
	<tr>
		<td>TimeToIdleSeconds</td>
		<td>
			<%= String.valueOf(equals(multiVMNewObjectTestAttributeList.get(2), 6000))%>
		</td>
	</tr>
</table>

<%!
	private boolean equals(Object attribute, Object value) {
		System.out.println(attribute + "=" + value);
		return ((Attribute)attribute).getValue().toString().equals(value.toString());
	}

%>

