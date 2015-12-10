#set ($portlet_display = $portletDisplay)

#set ($portlet_back_url = $htmlUtil.escapeHREF($portlet_display.getURLBack()))
#set ($portlet_content_css_class = "portlet-content")
#set ($portlet_display_name = $htmlUtil.escape($portlet_display.getPortletDisplayName()))
#set ($portlet_id = $htmlUtil.escapeAttribute($portlet_display.getId()))
#set ($portlet_title = $htmlUtil.escape($portlet_display.getTitle()))
#set ($portlet_toolbar = $portlet_display.getPortletToolbar())

<section class="portlet" id="portlet_$portlet_id">
	#if ($portlet_display.isPortletDecorate() && !$portlet_display.isStateMax())
		<header class="portlet-topper">
			<div class="portlet-title-default">
				<span class="portlet-name-text">$portlet_display_name</span>
			</div>

			#foreach ($portletTitleMenu in $portlet_toolbar.getPortletTitleMenus($portlet_id, $renderRequest))
				<menu class="icon-monospaced portlet-title-menu portlet-topper-toolbar" id="portlet-title-menu_${portlet_id}_${velocityCount}" type="toolbar">
					#menu($portletTitleMenu)
				</menu>
			#end

			<menu class="portlet-topper-toolbar" id="portlet-topper-toolbar_$portlet_id" type="toolbar">
				$theme.portletIconOptions()
			</menu>
		</header>

		#set ($portlet_content_css_class = $portlet_content_css_class + " portlet-content-editable")
	#end

	<div class="$portlet_content_css_class">
		$portlet_display.writeContent($writer)
	</div>
</section>