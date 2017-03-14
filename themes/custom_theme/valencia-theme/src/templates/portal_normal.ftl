<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${the_title} - ${company_name}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<@liferay.control_menu />

<div class="container-fluid liferay-menu-buffer" id="wrapper">
	<header id="banner" role="banner">
		<#if !is_signed_in && !has_navigation>
			<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">${sign_in_text}</a>
		</#if>

		<#if has_navigation && is_setup_complete>
			<#include "${full_templates_path}/navigation.ftl" />
		</#if>
		<div id="header-carousel" class="carousel slide" data-ride="carousel">
			<div class="carousel slide" data-ride="carousel">
				<div class="carousel-inner">
					<div class="item active">
						<div class="page-header header-filter" style="background-image: url('http://i.imgur.com/nsHGXAf.jpg');">
							<div class="container">
								<div class="row">
									<div class="col-md-8 col-md-offset-2 text-center">
										<h1 class="site-title">
											<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />"><img alt="${logo_description}" height="${site_logo_height}" src="${site_logo}" width="${site_logo_width}" /></a>
											
											<#if show_site_name>
												<span class="site-name" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
												${site_name}
												</span>
											</#if>
										</h1>
										<h2>Helping you go farther than you imagined.</h2>
										<div class="buttons">
											<a href="#" class="btn btn-primary btn-lg">See more</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>		
	</header>

	<div class="main main-raised">
			<section class="section-wrapper">
					<div class="container">
							<div class="row">
									<div class="col-md-8 col-md-offset-2">
											<h5 class="description">This is the paragraph where you can write more details about your product. Keep you user engaged by providing
													meaningful information. Remember that by this time, the user is curious, otherwise he wouldn&#039;t
													scroll to get here.</h5>
									</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<section id="content">
										<h1 class="hide-accessible">${the_title}</h1>

										<nav id="breadcrumbs">
											<@liferay.breadcrumbs />
										</nav>

										<#if selectable>
											<@liferay_util["include"] page=content_include />
										<#else>
											${portletDisplay.recycle()}

											${portletDisplay.setTitle(the_title)}

											<@liferay_theme["wrap-portlet"] page="portlet.ftl">
												<@liferay_util["include"] page=content_include />
											</@>
										</#if>

									</section>

								</div>
							</div>

	<footer id="footer" role="contentinfo">
		<p class="powered-by">
			<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
		</p>
	</footer>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

<!-- inject:js -->
<!-- endinject -->

</body>

</html>