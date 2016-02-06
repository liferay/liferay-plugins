include_class 'com.liferay.portal.kernel.util.WebKeys'

$renderResponse.setContentType "text/html"

themeDisplay = $renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

namespace = $renderResponse.getNamespace

out = $renderResponse.getPortletOutputStream

html = <<-EOF
<style type="text/css">
	\##{namespace}consoleOutput {
		border: 1px solid \#ccc;
		font-family: monospace;
		font-size: 12px;
		height: 200px;
		overflow: auto;
		padding: 4px;
		width: 98%;
	}
</style>

<script type="text/javascript">
	// <![CDATA[
		function #{namespace}execute() {
			var content = $('textarea##{namespace}consoleInput').val();

			jQuery.post(
				'#{$renderResponse.createResourceURL}',
				{
					#{namespace}cmd: "exec",
					#{namespace}consoleInput: content,
					p_auth: Liferay.authToken
				},
				function(data) {
					if (!data.match(/^@ERROR@$/m) && document.#{namespace}fm.#{namespace}outputMode.checked) {
						jQuery("\##{namespace}consoleOutput").empty().append(data);
					}
					else {
						jQuery("\##{namespace}consoleOutput").empty().text(data);
					}
				}
			);

			return false;
		}
	// ]]>
</script>

<form name="#{namespace}fm">
	Input

	<br />

	<textarea id="#{namespace}consoleInput" name="#{namespace}consoleInput" style="height: 300px; width: 98%;" wrap="off">
\$resourceResponse.setContentType "text/html"

out = $resourceResponse.getPortletOutputStream

out.println `date`
</textarea>

	<br /><br />

	<input type="button" value="Execute" onClick="#{namespace}execute();" />

	<input type="checkbox" name="#{namespace}outputMode" />

	Rendered Output

	<br /><br />

	Output

	<br />

	<pre id="#{namespace}consoleOutput"><!--//--></pre>
</form>

EOF

out.print html