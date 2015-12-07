#set ($VOID = $velocityPortletPreferences.setValue("portletSetupPortletDecoratorId", "barebone"))

<div aria-expanded="false" class="$nav_collapse collapse" id="navigationCollapse">
	#if ($has_navigation && $is_setup_complete)
		<div class="$nav_css_class $nav_css_right site-navigation" id="navigation" role="navigation">
			#navigation_menu($velocityPortletPreferences.toString())
		</div>
	#end
</div>

#set ($VOID = $velocityPortletPreferences.reset())