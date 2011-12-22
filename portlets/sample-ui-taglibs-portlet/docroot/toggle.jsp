<%--
/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

Code:

<br /><br />

<pre>&lt;liferay-ui:toggle
&nbsp;&nbsp;&nbsp;&nbsp;id="toggle_id_sample_ui_taglibs_test"
&nbsp;&nbsp;&nbsp;&nbsp;showImage='&lt;%= themeDisplay.getPathThemeImages() + "/arrows/01_down.png" %&gt;'
&nbsp;&nbsp;&nbsp;&nbsp;hideImage='&lt;%= themeDisplay.getPathThemeImages() + "/arrows/01_right.png" %&gt;'
&nbsp;&nbsp;&nbsp;&nbsp;defaultShowContent="true"
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

	The attributes <em>onImage</em> and <em>offImage</em> are optional and default to the images in this sample. You can also customize the default images by replacing them in a custom theme.
</div>

<div class="separator"></div>

&laquo; <a href="<portlet:renderURL />">Back</a>