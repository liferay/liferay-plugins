<nav class="sort-pages modify-pages" id="navigation">
	<h1>
		<span><@liferay.language key="navigation" /></span>
	</h1>

	<ul>
		<#list nav_items as nav_item>
			<#assign nav_css_class = '' />

			<#if (nav_item_index = 0)>
				<#assign nav_css_class = 'first' />
			</#if>

			<#if (nav_item_index = (nav_items?size - 1))>
				<#assign nav_css_class = 'last' />
			</#if>

			<#if nav_item.isSelected()>
				<#assign nav_css_class = "${nav_css_class} selected" />
			</#if>

			<#if (nav_css_class?has_content)>
				<li class="${nav_css_class}">
			<#else>
				<li>
			</#if>

				<a href="${nav_item.getURL()}" ${nav_item.getTarget()}><span>${nav_item.icon()} ${nav_item.getName()}</span></a>

				<#if nav_item.hasChildren()>
					<ul class="child-menu">
						<#list nav_item.getChildren() as nav_child>
							<#if nav_child.isSelected()>
								<li class="selected">
							<#else>
								<li>
							</#if>

								<a href="${nav_child.getURL()}" ${nav_child.getTarget()}>${nav_child.getName()}</a>
							</li>
						</#list>
					</ul>
				</#if>
			</li>
		</#list>
	</ul>
</nav>