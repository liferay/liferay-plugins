<#assign liferay_util = PortalJspTagLibs["/WEB-INF/tld/liferay-util.tld"] />

<#assign deviceBrand = themeDisplay.device.getBrand()?lower_case>

<#if (deviceBrand == 'desktop' || deviceBrand == 'unknown')>
	<@liferay_util["include"] page="/html/taglib/ui/asset_tags_summary/page.jsp" strict=true />
</#if>