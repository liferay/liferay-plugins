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

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<liferay-util:html-bottom>
	<script src="http://bible.logos.com/jsapi/referencetagging.js" type="text/javascript"></script>

	<aui:script position="inline">
		Logos.ReferenceTagging.lbsBibleVersion = "ESV";
		Logos.ReferenceTagging.lbsLinksOpenNewWindow = true;
		Logos.ReferenceTagging.lbsLibronixLinkIcon = "dark";
		Logos.ReferenceTagging.lbsNoSearchTagNames = [ "h1", "h2", "h3" ];
		Logos.ReferenceTagging.tag();
	</aui:script>
</liferay-util:html-bottom>