#set ($layout_set_title = $site_name)

#set ($pageDistributionType = $theme.getSetting("page-distribution-type"))
#set ($userCardVisible = $theme.getSetting("user-card-visible"))

#if ($page_group.isUser() && $layout.isPrivateLayout())
	#set ($layout_set_title = $languageUtil.get($locale, "my-dashboard"))
#end

#if ($pageDistributionType == "horizontal")
	#set ($css_class = "$css_class user-card-horizontal")
	#set ($firstColumnClass = "col-md-3")
	#set ($secondColumnClass = "col-md-9")
	#set ($userIconSize = "user-icon-xxl")
#else
	#set ($css_class = "$css_class user-card-vertical")
	#set ($firstColumnClass = "col-md-12")
	#set ($secondColumnClass = "col-md-12")
	#set ($userIconSize = "user-icon-xl")
#end