jQuery(document).ready(
	function() {
		jQuery("#navigation ul li").sifr(
			{
				path: themeDisplay.getPathThemeRoot()+'fonts/',
				color:'02245c',
				link:'02245c',
				hover:'fff'
			}
		);

		jQuery(".portlet-title").sifr(
			{
				path: themeDisplay.getPathThemeRoot()+'fonts/',
				color: '02245c'
			}
		);

		jQuery(".lfr-dock.interactive-mode h2 span").sifr(
			{
				path: themeDisplay.getPathThemeRoot()+'fonts/',
				font: 'Keyboard Plaque',
				color:'ffffff'
			}
		);

		jQuery("#navigation ul li").hover(
			function(){
				jQuery(this).addClass('hover');
			},
			function(){
				jQuery(this).removeClass('hover');
			}
		);
	}
);