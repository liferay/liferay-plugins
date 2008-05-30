$renderResponse.setContentType "text/html"

themeDisplay = $renderRequest.getAttribute("THEME_DISPLAY");

namespace = $renderResponse.getNamespace

out = $renderResponse.getPortletOutputStream

out.print <<-EOF
<style type="text/css">
	\##{namespace}consoleOutput {
		border: 1px solid \#ccc;
		font-family: monospace;
		height: 200px;
		overflow: auto;
		padding: 4px;
		width: 98%;
	}
</style>

<script type="text/javascript">
	function #{namespace}execute() {
		var inputContent = jQuery('input[@name=#{namespace}xsd]');
		var content = #{namespace}consoleInput.getCode();

		jQuery.get(
			'#{$renderResponse.createResourceURL}',
			{
				#{namespace}cmd: "exec",
				#{namespace}consoleInput: content
			},
			function(data) {
				jQuery("\##{namespace}consoleOutput").empty().append(data);
			}
		);

		return false;
	}
</script>

<form name="#{namespace}fm">
	Input

	<br />

	<textarea class="codepress ruby" style="height: 300px; width: 98%;" id="#{namespace}consoleInput" name="#{namespace}consoleInput" wrap="off">
\$resourceResponse.setContentType "text/html"

out = $resourceResponse.getPortletOutputStream

out.println `date`
</textarea>

	<br /><br />

	<input type="button" value="Execute" onClick="#{namespace}execute();" />

	<br /><br />

	Output

	<br />

	<pre id="#{namespace}consoleOutput"><!--//--></pre>
</form>

<script src="#{themeDisplay.getPathContext}/html/js/editor/codepress/codepress.js" type="text/javascript"></script>
EOF