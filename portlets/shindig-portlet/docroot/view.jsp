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

<%@ include file="/init.jsp" %>

<%
String gadgetParams = "specUrl: '" + gadgetUrl + "'";

if (gadgetHeight > 0) {
	gadgetParams += ", height: '" + gadgetHeight + "'";
}

long classPK = layout.getGroup().getClassPK();

String ownerId = "G-" + classPK;

if (layout.getGroup().isUser()) {
	ownerId = String.valueOf(classPK);
}

String baseSecurityToken = ShindigUtil.createBaseSecurityToken(ownerId, themeDisplay.getUserId(), "appId" , PortalUtil.getPortalURL(themeDisplay), gadgetUrl);
%>

<%@ page import="com.liferay.portal.util.PortalUtil"%>
<%@ page import="com.liferay.portlet.shindig.util.ShindigUtil"%>

<div id="<portlet:namespace />gadget-chrome" class="gadgets-gadget-chrome"></div>

<script type="text/javascript">
jQuery(
	function() {
		var gadget = gadgets.container.createGadget({<%= gadgetParams %>});

 		// This is a non-standard expando on gadget to help us create a better
 		// relationship with the stored preferences.
		gadget.portletId = '<portlet:namespace />';

		gadget.setServerBase('<%= renderRequest.getContextPath() %>/');

		gadgets.container.addGadget(gadget);

		gadget.secureToken = escape(['<%= baseSecurityToken %>', gadget.id].join(':'));

		gadgets.container.layoutManager.addGadgetChromeId('<portlet:namespace />gadget-chrome');

		jQuery('#portlet-small-icon-bar_<%= portletDisplay.getId() %>').prepend([
				'<nobr><a href="javascript: gadgets.container.getGadget(',gadget.id,').handleOpenUserPrefsDialog();">',
					'<img src="<%= renderRequest.getContextPath() %>/action.png" title="Gadget Settings" align="absmiddle" border="0" />',
				'</a></nobr>'
			].join('')
		);

		jQuery('#p_p_id<portlet:namespace/> .portlet-title-default').after([
				' - ',
				'<a href="javascript: gadgets.container.getGadget(',gadget.id,').handleOpenUserPrefsDialog();">',
					'Gadget Settings',
				'</a>'
			].join('')
		);

		gadgets.container.renderGadget(gadget);
	}
);
</script>