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

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.ContentTypes" %>
<%@ page import="com.liferay.portal.kernel.util.DateFormats" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.StringPool" %>
<%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="com.liferay.portal.util.PortalUtil" %>

<%@ page import="com.liferay.util.PwdGenerator"%>

<%@ page import="com.liferay.searchcontainer.bean.Book" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.NumberFormat" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Random"%>

<%@ page import="javax.portlet.PortletMode"%>
<%@ page import="javax.portlet.PortletURL" %>
<%@ page import="javax.portlet.WindowState" %>


<portlet:defineObjects />

<liferay-theme:defineObjects />

<%
WindowState windowState = renderRequest.getWindowState();
PortletMode portletMode = renderRequest.getPortletMode();

String currentURL = PortalUtil.getCurrentURL(request);

List<String> publishers = (List<String>)session.getAttribute("PUBLISHERS");

if (publishers == null) {
	publishers = new ArrayList<String>();

	for (int i = 0; i < 10; i++) {
		publishers.add(PwdGenerator.getPassword() + "_publisher");
	}

	session.setAttribute("PUBLISHERS", publishers);
}


List<Book> books = (List<Book>)session.getAttribute("BOOKS");

if (books == null) {
	books = new ArrayList<Book>();

	Calendar cdr = Calendar.getInstance();
	long upper = cdr.getTimeInMillis();

	cdr.set(Calendar.YEAR, 1900);
	long lower = cdr.getTimeInMillis();

    Random rand = new Random();

	for (int i = 255; i >= 0; i--) {
		Book b = new Book();

		b.setBookId((long)i);
		b.setTitle(PwdGenerator.getPassword(14) + "_title");
		b.setAuthor(PwdGenerator.getPassword() + "_author");
		b.setPublisher(publishers.get(i%10));

		long millis = (long)(rand.nextDouble() * (upper-lower)) + lower;

		b.setPublicationDate(new Date(millis));

		books.add(b);
	}

	session.setAttribute("BOOKS", books);
}

DateFormat dateFormatDate = DateFormats.getDate(locale, timeZone);
%>
