<!DOCTYPE html>

<#include init/>

<html class="<@liferay.language key="lang.dir" />" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	${theme.include(top_head_include)}
</head>

<body class="${css_class}">

<a href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>

<#if is_signed_in>
	<@liferay.dockbar />
</#if>

<div id="wrapper">
	<div id="banner" role="banner">
		<div id="heading">
			<h1 class="company-title">
				<a class="${logo_css_class}" href="${company_url}" title="<@liferay.language_format arguments="${company_name}" key="go-to-x" />">
					<span>${company_name}</span>
				</a>
			</h1>

			<h2 class="community-title">
				<a href="${community_default_url}" title="<@liferay.language_format arguments="${community_name}" key="go-to-x" />">
					<span>${community_name}</span>
				</a>
			</h2>

			<h3 class="page-title">
				<span>${the_title}</span>
			</h3>
		</div>

		<#if !is_signed_in>
			<a href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
	</div>

	<div id="content">
		<div class="site-breadcrumbs" id="breadcrumbs">
			<h1>
				<span><@liferay.language key="breadcrumbs" /></span>
			</h1>

			<@liferay.breadcrumbs/>
		</div>

		<#if selectable>
			${theme.include(content_include)}
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			${theme.wrapPortlet("portlet.ftl", content_include)}
		</#if>
	</div>

	<div id="footer" role="contentinfo">
		<p class="powered-by">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p>
	</div>
</div>

${theme.include(bottom_include)}

</body>

</html>