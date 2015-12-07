<nav id="footer-recursive">
	<ul class="list-inline" role="menubar">
		#if (!$is_signed_in)
			<li role="presentation">
				<a data-redirect="$is_login_redirect_required" href="$sign_in_url" id="sign-in" rel="nofollow">$sign_in_text</a>
			</li>
		#end

		#foreach ($nav_item in $nav_items)
			<li role="presentation">
				<a aria-labelledby="layout_$nav_item.getLayoutId()" href="$nav_item.getURL()" $nav_item.getTarget() role="menuitem">
					$nav_item.getName()
				</a>
			</li>
		#end
	</ul>
</nav>