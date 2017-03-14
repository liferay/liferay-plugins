<nav class="navbar navbar-default navbar-transparent navbar-fixed-top navbar-color-on-scroll ${nav_css_class}" id="navigation" role="navigation">
	<div class="container">
	<h1 class="hide-accessible"><@liferay.language key="navigation" /></h1>
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#main-site-navigation">
				<span class="sr-only">Menu</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="title-logo-wrapper">
				<a class="navbar-brand" href=${site_default_url} title=${site_name}><h1>${site_name}</h1></a>
			</div>
		</div>
		<div class="collapse navbar-collapse" id="main-site-navigation">
			<ul aria-label="<@liferay.language key="site-pages" />" role="menubar" class="nav navbar-nav navbar-right">
				<#list nav_items as nav_item>
					<#assign
						nav_item_attr_has_popup = ""
						nav_item_attr_selected = ""
						nav_item_css_class = ""
						nav_item_layout = nav_item.getLayout()
					/>

					<#if nav_item.isSelected()>
						<#assign
							nav_item_attr_has_popup = "aria-haspopup='true'"
							nav_item_attr_selected = "aria-selected='true'"
							nav_item_css_class = "selected"
						/>
					</#if>

					<li ${nav_item_attr_selected} class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
						<a aria-labelledby="layout_${nav_item.getLayoutId()}" ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem" ><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>

						<#if nav_item.hasChildren()>
							<div class="dropdown">
								<ul class="child-menu dropdown-menu" role="menu">
									<#list nav_item.getChildren() as nav_child>
										<#assign
											nav_child_attr_selected = ""
											nav_child_css_class = ""
										/>

										<#if nav_item.isSelected()>
											<#assign
												nav_child_attr_selected = "aria-selected='true'"
												nav_child_css_class = "selected"
											/>
										</#if>

										<li ${nav_child_attr_selected} class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
											<a aria-labelledby="layout_${nav_child.getLayoutId()}" href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
										</li>
									</#list>
								</ul>
							</div>
						</#if>
					</li>
				</#list>
				<#if !is_signed_in>
					<li class="btn btn-round btn-theme">
						<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow" >${sign_in_text}</a>
					</li>
				</#if>
			</ul>
		</div>
	</div>
</nav>