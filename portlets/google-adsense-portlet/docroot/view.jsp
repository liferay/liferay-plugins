<%
/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

<script type="text/javascript">
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
</script>

<script type="text/javascript" src="http://pagead2.googlesyndication.com/pagead/show_ads.js"></script>