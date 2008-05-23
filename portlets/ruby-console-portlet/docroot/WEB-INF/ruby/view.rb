$renderResponse.setContentType "text/html"

namespace = $renderResponse.getNamespace

out = $renderResponse.getPortletOutputStream

out.print <<-EOF
<style type="text/css">
	\##{namespace}consoleInput {
		font-family: monospace;
		height: 200px;
		width: 98%;
	}

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
		jQuery.get(
			'#{$renderResponse.createResourceURL}',
			{
				#{namespace}cmd: "exec",
				#{namespace}consoleInput: document.#{namespace}fm.#{namespace}consoleInput.value
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

	<textarea class="lfr-textarea" id="#{namespace}consoleInput" name="#{namespace}consoleInput">
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
EOF