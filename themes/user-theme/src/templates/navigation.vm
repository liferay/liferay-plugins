#if ($is_signed_in && $userCardVisible == true)
	#set ($main_menu_style = "style='background-image: url($user.getPortraitURL($themeDisplay));'")

	<div class="aspect-ratio-bg-cover text-center user-container">
		<div class="user-info">
			<div class="aspect-ratio-bg-cover user-icon $userIconSize" $main_menu_style></div>

			<div class="h3">$user_name</div>

			<div class="time-zone">
				<small>
					<i class="icon-map-marker"></i> $time_zone
				</small>
			</div>
		</div>

		<div class="aspect-ratio-bg-cover bg-icon" $main_menu_style></div>
	</div>
#end

#set ($VOID = $velocityPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone"))

<div aria-expanded="false">
	#if ($has_navigation && $is_setup_complete)
		<nav class="$nav_css_class site-navigation" id="navigation" role="navigation">
			#navigation_menu($velocityPortletPreferences.toString())
		</nav>
	#end
</div>

#set ($VOID = $velocityPortletPreferences.reset())