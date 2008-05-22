$renderResponse.setContentType "text/html"

namespace = $renderResponse.getNamespace

out = $renderResponse.getPortletOutputStream

out.print <<-EOF
<style type="text/css">
	\##{namespace}codeInput {
		font-family: monospace;
		height: 200px;
		width: 98%;
	}

	\##{namespace}codeOutput {
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
				#{namespace}codeInput: document.#{namespace}fm.#{namespace}codeInput.value
			},
			function(data) {
				jQuery("\##{namespace}codeOutput").empty().append(data);
			}
		);

		return false;
	}
</script>

<form name="#{namespace}fm">
	Code Input

	<br />

	<textarea class="lfr-textarea" id="#{namespace}codeInput" name="#{namespace}codeInput">
\$resourceResponse.setContentType "text/html"

out = $resourceResponse.getPortletOutputStream

out.println `date`
</textarea>

	<br /><br />

	<input type="button" value="Execute" onClick="#{namespace}execute();" />

	<br /><br />

	Code Output

	<br />

	<pre id="#{namespace}codeOutput"><!--//--></pre>
</form>
EOF