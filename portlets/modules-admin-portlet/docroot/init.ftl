<#--
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
-->

<#assign aui = PortletJspTagLibs["/META-INF/aui.tld"] />
<#assign liferay_portlet = PortletJspTagLibs["/META-INF/liferay-portlet-ext.tld"] />
<#assign liferay_security = PortletJspTagLibs["/META-INF/liferay-security.tld"] />
<#assign liferay_theme = PortletJspTagLibs["/META-INF/liferay-theme.tld"] />
<#assign liferay_ui = PortletJspTagLibs["/META-INF/liferay-ui.tld"] />
<#assign liferay_util = PortletJspTagLibs["/META-INF/liferay-util.tld"] />
<#assign portlet = PortletJspTagLibs["/META-INF/liferay-portlet.tld"] />

<#assign OSGiHeader = staticUtil["aQute.libg.header.OSGiHeader"] />

<#assign ModulesAdminUtil = objectUtil("com.liferay.modulesadmin.util.ModulesAdminUtil") />
<#assign LanguageUtil = staticUtil["com.liferay.portal.kernel.language.LanguageUtil"] />
<#assign UnicodeLanguageUtil = staticUtil["com.liferay.portal.kernel.language.UnicodeLanguageUtil"] />
<#assign SessionErrors = staticUtil["com.liferay.portal.kernel.servlet.SessionErrors"] />
<#assign FastDateFormatFactoryUtil = staticUtil["com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"] />
<#assign ListUtil = staticUtil["com.liferay.portal.kernel.util.ListUtil"] />
<#assign ParamUtil = staticUtil["com.liferay.portal.kernel.util.ParamUtil"] />
<#assign PortalUtil = staticUtil["com.liferay.portal.kernel.util.PortalUtil"] />
<#assign Validator = staticUtil["com.liferay.portal.kernel.util.Validator"] />
<#assign PropsValues = staticUtil["com.liferay.portal.util.PropsValues"] />
<#assign WebKeys = staticUtil["com.liferay.portal.util.WebKeys"] />

<#assign Calendar = staticUtil["java.util.Calendar"] />

<#assign Bundle = staticUtil["org.osgi.framework.Bundle"] />
<#assign Constants = staticUtil["org.osgi.framework.Constants"] />
<#assign BundleStartLevel = staticUtil["org.osgi.framework.startlevel.BundleStartLevel"] />

<@portlet["defineObjects"] />

<@liferay_theme["defineObjects"] />

<#assign currentURL = PortalUtil.getCurrentURL(request) />

<#assign dateFormatDateTime = FastDateFormatFactoryUtil.getDateTime(locale, timeZone) />

<#assign bundleContext = renderRequest.getAttribute("bundleContext") />
<#assign pageContext = .vars["javax.servlet.jsp.jspPageContext"] />