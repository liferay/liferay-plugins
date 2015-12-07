<!DOCTYPE html>

#parse ($init)

<html class="$root_css_class" dir="#language ("lang.dir")" lang="$w3c_language_id">

<head>
	<title>$the_title - $company_name</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	$theme.include($top_head_include)
</head>

<body class="$css_class">

#quick_access("#main-content")

$theme.include($body_top_include)

#control_menu()

#product_menu_sidebar($liferay_product_menu_state)

<div id="wrapper">
	<header id="banner" role="banner">
		<div class="container-fluid-1280">
			<nav class="navbar">
				<div class="navbar-header">
					<a class="$logo_css_class" href="$site_default_url" rel="home" title="#language_format ("go-to-x", [$site_name])">
						<img alt="$logo_description" height="$company_logo_height" src="$site_logo" width="$company_logo_width" />

						#if ($show_site_name)
							<span class="site-name" title="#language_format ("go-to-x", [$site_name])">
								$site_name
							</span>
						#end
					</a>

					<button class="collapsed navbar-toggle" data-target="#navigationCollapse" data-toggle="collapse" type="button">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>

				#if ($has_navigation && $is_setup_complete)
					#parse ("$full_templates_path/navigation.vm")
				#end
			</nav>

		</div>
	</header>

	<main id="content" role="main">
		<h1 class="hide-accessible">$the_title</h1>

		#if ($selectable)
			$theme.include($content_include)
		#else
			$portletDisplay.recycle()

			$portletDisplay.setTitle($the_title)

			$theme.wrapPortlet("portlet.vm", $content_include)
		#end
	</main>

	<footer id="footer" role="contentinfo">
		<div class="container-fluid-1280" id="company-info">
			<div class="text-center" id="footer-brand">
				<img alt="$logo_description" height="$company_logo_height" src="$site_logo" width="$company_logo_width" />
			</div>

			#if ($has_navigation)
				#parse ("$full_templates_path/footer_navigation.vm")
			#end

			#parse ("$full_templates_path/social_media.vm")
		</div>
		<div class="container-fluid-1280">
			<p id="copyright">
				<small>#language ("powered-by") <a href="http://www.liferay.com" rel="external">Liferay</a></small>
			</p>
		</div>
	</footer>
</div>

$theme.include($body_bottom_include)

$theme.include($bottom_include)

</body>

</html>