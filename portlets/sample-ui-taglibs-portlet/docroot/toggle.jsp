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

Code:

<br /><br />

<pre>&lt;liferay-ui:toggle
    id="toggle_id_sample_ui_taglibs_test"
    showImage='&lt;%= themeDisplay.getPathThemeImages() + "/arrows/01_down.png" %&gt;'
    hideImage='&lt;%= themeDisplay.getPathThemeImages() + "/arrows/01_right.png" %&gt;'
    defaultShowContent="true"
/&gt;</pre>

<div class="separator"></div>

Result:

<br /><br />

<div>
	<liferay-ui:toggle
		id="toggle_id_sample_ui_taglibs_test"
		showImage='<%= themeDisplay.getPathThemeImages() + "/arrows/01_down.png" %>'
		hideImage='<%= themeDisplay.getPathThemeImages() + "/arrows/01_right.png" %>'
		defaultShowContent="true"
	/>
</div>

<div id="toggle_id_sample_ui_taglibs_test" style="display: <liferay-ui:toggle-value id="toggle_id_sample_ui_taglibs_test" />; padding-top: 10px;">
	This content is toggable. The preference for this is persited based on the specified id. If the user is a guest, the preference is persisted for the session only. If the user is authenticated, the preference is persisted in the database for all future requests.

	<br /><br />

	The attributes <i>onImage</i> and <i>offImage</i> are optional and default to the images in this sample. You can also customize the default images by replacing them in a custom theme.
</div>

<div class="separator"></div>

&laquo; <a href="<portlet:renderURL />">Back</a>