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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ page import="com.liferay.portal.kernel.util.FileUtil" %>
<%@ page import="java.io.File" %>
<%@ page import="javax.portlet.PortletURL" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<%
String filename = new String("../webapps/" + themeDisplay.getTheme().getContextPath() + "/css/custom.css");

File file = new File(filename);

if (file.canRead()) {

	PortletURL viewArticleURL = renderResponse.createRenderURL();

	String cssinput = request.getParameter("customcss");

	if (cssinput != null) {
		FileUtil.write(file, cssinput.trim());
	}

	String cssoutput = FileUtil.read(file);

	%>
		<form action="<%= viewArticleURL.toString() %>" method="post" style="height: 336px; ">
			<textarea name="customcss" style="width: 100%; padding: 0; margin: 0; height: 300px; "><%= cssoutput.trim() %></textarea>
			<input type="submit" name="Submit" value="Save" style="float: right; margin: 12px; ">
		</form>
	<%

}
%>