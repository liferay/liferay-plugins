<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

<c:choose>
	<c:when test="<%= Validator.isNotNull(movie) %>">

		<%

		// http://www.macromedia.com/cfusion/knowledgebase/index.cfm?id=tn_12701

		Properties flashAttributesProps = PropertiesUtil.load(flashAttributes);

		String align = GetterUtil.getString(flashAttributesProps.getProperty("align"), "left");
		String allowScriptAccess = GetterUtil.getString(flashAttributesProps.getProperty("allowScriptAccess"), "sameDomain");
		String base = GetterUtil.getString(flashAttributesProps.getProperty("base"), ".");
		String bgcolor = GetterUtil.getString(flashAttributesProps.getProperty("bgcolor"), "#FFFFFF");
		String devicefont = GetterUtil.getString(flashAttributesProps.getProperty("devicefont"), "true");
		String height = GetterUtil.getString(flashAttributesProps.getProperty("height"), "500");
		String loop = GetterUtil.getString(flashAttributesProps.getProperty("loop"), "true");
		String menu = GetterUtil.getString(flashAttributesProps.getProperty("menu"), "false");
		String play = GetterUtil.getString(flashAttributesProps.getProperty("play"), "false");
		String quality = GetterUtil.getString(flashAttributesProps.getProperty("quality"), "best");
		String salign = GetterUtil.getString(flashAttributesProps.getProperty("salign"), "");
		String scale = GetterUtil.getString(flashAttributesProps.getProperty("scale"), "showall");
		String swliveconnect = GetterUtil.getString(flashAttributesProps.getProperty("swliveconnect"), "false");
		String width = GetterUtil.getString(flashAttributesProps.getProperty("width"), "100%");
		String wmode = GetterUtil.getString(flashAttributesProps.getProperty("wmode"), "opaque");

		flashVariables = StringUtil.replace(flashVariables, "\n", "&");
		%>

		<liferay-ui:flash
			align="<%= align %>"
			allowScriptAccess="<%= allowScriptAccess %>"
			base="<%= base %>"
			bgcolor="<%= bgcolor %>"
			devicefont="<%= devicefont %>"
			flashvars="<%= flashVariables %>"
			height="<%= height %>"
			loop="<%= loop %>"
			menu="<%= menu %>"
			movie="<%= movie %>"
			play="<%= play %>"
			quality="<%= quality %>"
			salign="<%= salign %>"
			scale="<%= scale %>"
			swliveconnect="<%= swliveconnect %>"
			width="<%= width %>"
			wmode="<%= wmode %>"
		/>
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/html/portal/portlet_not_setup.jsp" />
	</c:otherwise>
</c:choose>