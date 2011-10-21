<#assign liferay_util = PortalJspTagLibs["/WEB-INF/tld/liferay-util.tld"] />

<#assign deviceBrand = themeDisplay.device.getBrand()?lower_case>

<#if (deviceBrand == 'desktop' || deviceBrand == 'unknown')>
	<@liferay_util["include"] page="/html/portlet/message_boards/view_message.jsp" strict=true />
<#else>
	<@liferay_util["include"] page="/html/seven_cogs/portlet/message_boards/view_message.jsp" strict=true />
</#if>