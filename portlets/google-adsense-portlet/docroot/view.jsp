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

<aui:script position="inline">
	google_ad_client = "<%= adClient %>";
	google_ad_channel = "<%= adChannel %>";
	google_ad_type = "<%= adTypeValue %>";
	google_ad_height = <%= adTypeHeight %>;
	google_ad_width = <%= adTypeWidth %>;
	google_ad_format = "<%= adTypeWidth %>x<%= adTypeHeight %>_as";

	google_color_border = "<%= colorBorder %>";
	google_color_bg = "<%= colorBg %>";
	google_color_link = "<%= colorLink %>";
	google_color_text = "<%= colorText %>";
	google_color_url = "<%= colorUrl %>";
</aui:script>

<script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js"></script>