<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

<%@ include file="/init.jsp" %>

<%
String dlDisplayLocalDestinationId = portletDisplay.getNamespace() + "document-library-container";
String viewerId = portletDisplay.getNamespace() + "viewer";
String widgetUrlPrefix = PortalUtil.getPortalURL(request) + PortalUtil.getPathContext() + "/widget/";
String dlDisplayPath = preferences.getValue("dlDisplayPath", StringPool.BLANK);
%>

<c:choose>
	<c:when test='<%= Validator.isNull(dlDisplayPath) %>'>
		<span class="portlet-msg-error"><liferay-ui:message key="please-contact-the-administrator-to-setup-this-portlet" /></span>
	</c:when>
	<c:otherwise>
		<div id="<%= dlDisplayLocalDestinationId %>"></div>
		<div class="results-grid" id="<%= viewerId %>"></div>

		<script type="text/javascript" src="<%= request.getContextPath() %>/js/portal_mashups.js"></script>

		<script type="text/javascript">
			var dlDisplayFragmentSelector = ".breadcrumbs,.results-grid:first,.taglib-search-iterator-page-iterator-bottom:first,.results-grid:eq(1),.taglib-search-iterator-page-iterator-bottom:eq(1)";
			var dlDisplayLocalDestinationId = "#<%= dlDisplayLocalDestinationId %>";
			var dlDisplayURL = "<%= widgetUrlPrefix + dlDisplayPath %>";

			function <portlet:namespace/>fragmentProcessor() {
				jQuery(dlDisplayLocalDestinationId + ' .results-grid:eq(1) .col-1 a').each(function (i, val) {
					var name = jQuery(this).text();
					var url = jQuery(this).attr("href");

					jQuery(this).unbind("click");
					jQuery(this).click(function() {
						<portlet:namespace/>itemProcessor(name, url);
						return false;
					});
				});
			};

			function <portlet:namespace/>itemProcessor(name, url) {
				var code = '';

				if (name.search(/.(mp3|mpg|mpeg|avi|wav)$/) != -1) {
					code += <portlet:namespace/>multimediaViewerCode(name, url);
				}
				else if (name.search(/.(bmp|png|jpg|jpeg|gif)$/) != -1) {
					code += <portlet:namespace/>imageViewerCode(name, url);
				}
				else {
					code += "There is no appropriate viewer for file: '" + name + "'";
				}

				jQuery('#<%= viewerId %>').hide();
				jQuery('#<%= viewerId %>').html(code);
				jQuery('#<%= viewerId %>').fadeIn("slow");
			};

			function <portlet:namespace/>imageViewerCode(name, url) {
				return "<img src='" + url + "'/>\n<br />\n<div>Displaying file: " + name + "</div>";
			}

			function <portlet:namespace/>multimediaViewerCode(name, url) {
				var objTypeTag = "audio/mpeg";

				if (navigator.userAgent.toLowerCase().indexOf("windows") != -1) { // Use MIME type application/x-mplayer2
					objTypeTag = "application/x-mplayer2";
				}

				var output = "";

				output += "<object width='280' height='69'>\n"; // Width is the WMP minimum. Height = 45 (WMP controls) + 24 (WMP status bar)
				output += "<param name='type' value='" + objTypeTag + "'>\n";
				output += "<param name='src' value='" + url + "'>\n";
				output += "<param name='autostart' value='1'>\n";
				output += "<param name='showcontrols' value='1'>\n";
				output += "<param name='showstatusbar' value='1'>\n";
				output += "<embed src ='" + url + "' type='" + objTypeTag + "' autoplay='true' autostart='1' width='280' height='69' controller='1' showstatusbar='1' bgcolor='#ffffff'></embed>\n";
				output += "</object>\n";
				output += "<br /><div>Playing file name: " + name + "</div>\n";

				return output;
			}

			var options = {
				rewriteURLs: true,
				afterInsert: <portlet:namespace/>fragmentProcessor
			};

			Liferay.PortalMashups.insert(dlDisplayURL, dlDisplayFragmentSelector, dlDisplayLocalDestinationId, options);
		</script>
	</c:otherwise>
</c:choose>