<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.bean.BeanParamUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.KeyValuePair" %>
<%@ page import="com.liferay.portal.kernel.util.KeyValuePairComparator" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.UnicodeProperties" %>
<%@ page import="com.liferay.portal.model.Portlet" %>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil" %>
<%@ page import="com.liferay.portal.util.PortalUtil" %>
<%@ page import="com.liferay.wsrp.NoSuchConsumerException" %>
<%@ page import="com.liferay.wsrp.NoSuchConsumerPortletException" %>
<%@ page import="com.liferay.wsrp.NoSuchProducerException" %>
<%@ page import="com.liferay.wsrp.WSRPConsumerNameException" %>
<%@ page import="com.liferay.wsrp.WSRPConsumerPortletNameException" %>
<%@ page import="com.liferay.wsrp.WSRPConsumerWSDLException" %>
<%@ page import="com.liferay.wsrp.WSRPProducerNameException" %>
<%@ page import="com.liferay.wsrp.model.WSRPConsumer" %>
<%@ page import="com.liferay.wsrp.model.WSRPConsumerPortlet" %>
<%@ page import="com.liferay.wsrp.model.WSRPProducer" %>
<%@ page import="com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil" %>
<%@ page import="com.liferay.wsrp.service.WSRPConsumerPortletLocalServiceUtil" %>
<%@ page import="com.liferay.wsrp.service.WSRPProducerLocalServiceUtil" %>
<%@ page import="com.liferay.wsrp.util.WebKeys" %>
<%@ page import="com.liferay.wsrp.util.WSRPConsumerManager" %>
<%@ page import="com.liferay.wsrp.util.WSRPConsumerManagerFactory" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletURL" %>

<%@ page import="oasis.names.tc.wsrp.v2.types.LocalizedString" %>
<%@ page import="oasis.names.tc.wsrp.v2.types.ModelDescription" %>
<%@ page import="oasis.names.tc.wsrp.v2.types.PortletDescription" %>
<%@ page import="oasis.names.tc.wsrp.v2.types.PropertyDescription" %>
<%@ page import="oasis.names.tc.wsrp.v2.types.ServiceDescription" %>

<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);
%>