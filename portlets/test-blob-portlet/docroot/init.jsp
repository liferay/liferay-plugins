<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

<%@ page import="com.liferay.counter.service.CounterLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.dao.jdbc.OutputBlob" %><%@
page import="com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream" %><%@
page import="com.liferay.testblob.model.BlobEntry" %><%@
page import="com.liferay.testblob.service.BlobEntryLocalServiceUtil" %><%@
page import="com.liferay.testblob.service.persistence.BlobEntryUtil" %>

<%@ page import="java.io.InputStream" %>

<%@ page import="java.sql.Blob" %>

<%@ page import="java.util.Random" %>