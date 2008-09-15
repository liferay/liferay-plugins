<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/application_builder/init.jsp" %>

<%
PortletPreferences prefs = renderRequest.getPreferences();

String portletResource = ParamUtil.getString(request, "portletResource");

if (Validator.isNotNull(portletResource)) {
	prefs = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}

String script = PrefsParamUtil.getString(prefs, request, "script", ConfigurationActionImpl.DEFAULT_SCRIPT);
String language = PrefsParamUtil.getString(prefs, request, "language", ConfigurationActionImpl.DEFAULT_LANGUAGE);

String portletResourceNamespace = PortalUtil.getPortletNamespace(portletResource);
%>

<style type="text/css">
	#<portlet:namespace/>scriptOutputContainer {
		border: 1px solid #ccc;
		font-family: monospace;
		font-size: 12px;
		height: 200px;
		margin-top: 2em;
		overflow: auto;
		padding: 4px;
		width: 98%;
	}
</style>

<script type="text/javascript">
	function <portlet:namespace />test() {
		var script = <portlet:namespace />scriptEditor.getCode();
		var languageCombo = document.getElementById("<portlet:namespace />language");
		var language = jQuery('#<portlet:namespace />language').val();

		jQuery.get(
			'<liferay-portlet:resourceURL portletName="<%= portletResource %>" />',
			{
				<%= portletResourceNamespace %>cmd: "exec",
				<%= portletResourceNamespace %>language: language,
				<%= portletResourceNamespace %>script: script
			},
			function(data) {
				if (!data.match(/^@ERROR@$/m)) {
					jQuery("#<portlet:namespace />scriptOutputContainer .scriptError").hide();
					jQuery("#<portlet:namespace />scriptOutputContainer .scriptOutput").empty().append(data);
					jQuery("#<portlet:namespace />scriptOutputContainer .scriptOutputTest").show();
				}
				else {
					jQuery("#<portlet:namespace />scriptOutputContainer .scriptOutputTest").hide();
					jQuery("#<portlet:namespace />scriptOutputContainer .scriptError").empty().text(data);
					jQuery("#<portlet:namespace />scriptOutputContainer .scriptError").show();
				}

				jQuery("#<portlet:namespace />scriptOutputContainer").slideDown();
				jQuery("#<portlet:namespace />scriptOutputContainer .scriptOutput a").click(function() {});
				jQuery("#<portlet:namespace />scriptOutputContainer .scriptOutput form").submit(function() {return false;});
			}
		);

		return false;
	}

	function <portlet:namespace />save() {
		var script = <portlet:namespace />scriptEditor.getCode();

		document.<portlet:namespace />fm.<portlet:namespace />script.value = script;

		return true;
	}

	var previouslySelectedLanguage = "<%= language %>";

	jQuery(document).ready(function() {
		jQuery('#<portlet:namespace />language').change(function() {
			var newSelectedLanguage = jQuery(this).val();

			if (newSelectedLanguage != previouslySelectedLanguage) {
				<portlet:namespace />scriptEditor.edit(<portlet:namespace />scriptEditor.getCode(), newSelectedLanguage);
				previouslySelectedLanguage = newSelectedLanguage;
			}
		});
	});
</script>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fm">
	<input  name="<portlet:namespace />script" type="hidden" value="" />

	<liferay-ui:message key="scripting-language" />
	<br />

	<select id="<portlet:namespace />language" name="<portlet:namespace />language">
		<%
		for (Object supportedLanguageObj: com.liferay.applicationbuilder.portlet.ConfigurationActionImpl.getSupportedLanguagesInfo().keySet()) {
			String supportedLanguage = (String)supportedLanguageObj;
		%>
			<option <%= supportedLanguage.equals(language) ? "selected" : "" %> value="<%= supportedLanguage %>"><%= supportedLanguage.substring(0, 1).toUpperCase() + supportedLanguage.substring(1).toLowerCase() %></option>
		<%
			}
		%>
	</select>

	<br />

	<table>
		<tr>
			<td width="70%">
				<liferay-ui:message key="script" />
				<br />
				<textarea class="codepress <%= language %>" id="<portlet:namespace />scriptEditor" name="<portlet:namespace />scriptEditor"
					style="height: 300px; width: 98%;" wrap="off"><%= script %></textarea>
			</td>
			<td>
				<div class="syntax-help"  >
					<%@ include file="/application_builder/script_help.jsp" %>
				</div>
			</td>
		</tr>
	</table>

	<br /><br />

	<input type="submit" value="<liferay-ui:message key="save" />" onClick="<portlet:namespace />save();" />
	<input type="button" value="<liferay-ui:message key="test" />" onClick="<portlet:namespace />test();" />

	<div id="<portlet:namespace/>scriptOutputContainer" style="display: none">
		<div class="<portlet:namespace/>scriptOutputTest">
			<liferay-ui:message key="links-and-forms-have-been-deactivated-to-avoid-odd-behaviour." />
			<div class="scriptOutput"><!--//--></div>
		</div>
		<pre class="scriptError"><!--//--></pre>
	</div>

</form>

<script src="<%= themeDisplay.getPathContext() %>/html/js/editor/codepress/codepress.js" type="text/javascript"></script>