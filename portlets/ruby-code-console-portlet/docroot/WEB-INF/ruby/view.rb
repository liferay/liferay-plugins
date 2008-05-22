$renderResponse.setContentType "text/html"

out = $renderResponse.getPortletOutputStream
namespace = $renderResponse.getNamespace

out.print <<-EOF
<style type="text/css">
	\##{namespace}code {
		width: 98%;
		height: 200px;
		font-family: monospace;
	}

	\##{namespace}out {
		font-family: monospace;
		padding: 4px;
		border: 1px solid \#ccc;
		width: 98%;
		height: 200px;
		overflow: auto;
	}
</style>

<script type="text/javascript">
	function execCode() {
		jQuery.get(
			'#{$renderResponse.createResourceURL}',
			{
				#{namespace}cmd: "exec",
				#{namespace}code: document.#{namespace}fm.#{namespace}consoleInput.value
			},
			function(data){
				jQuery("\##{namespace}out").empty().append(data);
			});

		return false;
	}
</script>

<form name="#{namespace}fm">
	Code:

	<br />

	<textarea name="#{namespace}consoleInput" id="#{namespace}consoleInput">
##
## Un-comment the following lines, then click "Execute" to see the result.
##

#\$resourceResponse.setContentType "text/html"
#out = $resourceResponse.getPortletOutputStream

#out.println `date`
</textarea>

	<br /><br />

	<input type="button" value="Execute" onClick="execCode();" />

	<br /><br />

	Portlet OutputStream:

	<br />

	<pre id="#{namespace}out"><!--//--></pre>

</form>
EOF
