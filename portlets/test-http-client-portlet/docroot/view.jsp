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

<p>
	testPostFileAndStringParts=<%= _testPostFileAndStringParts() %>
</p>

<p>
	testPostFilePart=<%= _testPostFilePart() %>
</p>

<p>
	testPostFileParts=<%= _testPostFileParts() %>
</p>

<p>
	testPostStringPart=<%= _testPostStringPart() %>
</p>

<p>
	testPostStringParts=<%= _testPostStringParts() %>
</p>

<%!
private static String _testPostFileAndStringParts() throws Exception {
	Http.Options options = new Http.Options();

	options.addPart("string1", "string1");
	options.setLocation("http://localhost:8080/test-http-client-portlet/test_http_client/testPostFileAndStringParts");
	options.setPost(true);

	return HttpUtil.URLtoString(options);
}

private static String _testPostFilePart() throws Exception {
	Http.Options options = new Http.Options();

	options.addPart("string1", "string1");
	options.setLocation("http://localhost:8080/test-http-client-portlet/test_http_client/testPostFilePart");
	options.setPost(true);

	return HttpUtil.URLtoString(options);
}

private static String _testPostFileParts() throws Exception {
	Http.Options options = new Http.Options();

	options.addPart("string1", "string1");
	options.setLocation("http://localhost:8080/test-http-client-portlet/test_http_client/testPostFileParts");
	options.setPost(true);

	return HttpUtil.URLtoString(options);
}

private static String _testPostStringPart() throws Exception {
	Http.Options options = new Http.Options();

	options.addPart("string1", "string1");
	options.setLocation("http://localhost:8080/test-http-client-portlet/test_http_client/testPostStringPart");
	options.setPost(true);

	return HttpUtil.URLtoString(options);
}

private static String _testPostStringParts() throws Exception {
	Http.Options options = new Http.Options();

	options.addPart("string1", "string1");
	options.setLocation("http://localhost:8080/test-http-client-portlet/test_http_client/testPostStringParts");
	options.setPost(true);

	return HttpUtil.URLtoString(options);
}
%>