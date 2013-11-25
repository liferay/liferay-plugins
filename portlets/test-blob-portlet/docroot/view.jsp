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

<%@ page import="com.liferay.counter.service.CounterLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.jdbc.OutputBlob" %>
<%@ page import="com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream" %>
<%@ page import="com.liferay.testblob.model.BlobEntry" %>
<%@ page import="com.liferay.testblob.service.BlobEntryLocalServiceUtil" %>
<%@ page import="com.liferay.testblob.service.persistence.BlobEntryUtil" %>

<%@ page import="java.io.InputStream" %>

<%@ page import="java.sql.Blob" %>

<%@ page import="java.util.Random" %>

<% long blobEntryId = -1; %>

Create Blob : <%= (blobEntryId = _createBlob()) > 0 ? "PASSED" : "FAILED" %> <br />

Read Blob : <%= _readBlob(blobEntryId, data1) ? "PASSED" : "FAILED" %> <br />

Update Blob : <%= _updateBlob(blobEntryId) ? "PASSED" : "FAILED" %> <br />

Delete Blob : <%= _deleteBlob(blobEntryId) ? "PASSED" : "FAILED" %> <br />

<%!

private static final int _TEST_DATA_SIZE = 4096;
private static final byte[] data1 = new byte[_TEST_DATA_SIZE];
private static final byte[] data2 = new byte[_TEST_DATA_SIZE];

static {
	Random random = new Random();

	random.nextBytes(data1);
	random.nextBytes(data2);
}

private static long _createBlob() {
	try {
		long blobEntryId = CounterLocalServiceUtil.increment();

		BlobEntry blobEntry = BlobEntryUtil.create(blobEntryId);

		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(data1);

		OutputBlob outputBlob = new OutputBlob(
			unsyncByteArrayInputStream, data1.length);

		blobEntry.setBlobField(outputBlob);

		blobEntry = BlobEntryLocalServiceUtil.addBlobEntry(blobEntry);

		return blobEntry.getBlobEntryId();
	}
	catch (Exception e) {
		System.err.println(e);
		e.printStackTrace();

		return -1;
	}
}

private static boolean _deleteBlob(long blobEntryId) {
	try {
		BlobEntryLocalServiceUtil.deleteBlobEntry(blobEntryId);

		BlobEntry blobEntry = BlobEntryLocalServiceUtil.fetchBlobEntry(blobEntryId);

		if (blobEntry != null) {
			throw new Exception("BlobEntry still exists after deletion.");
		}

		return true;
	}
	catch (Exception e) {
		System.err.println(e);
		e.printStackTrace();

		return false;
	}
}

private static boolean _readBlob(long blobEntryId, byte[] expectedData) {
	try {
		BlobEntry blobEntry = BlobEntryLocalServiceUtil.getBlobEntry(blobEntryId);

		Blob blob = blobEntry.getBlobField();

		InputStream inputStream = blob.getBinaryStream();

		for (int i = 0; i < expectedData.length; i++) {
			if (expectedData[i] != (byte)inputStream.read()) {
				throw new Exception("Mismatch at index : " + i);
			}
		}

		if (inputStream.read() != -1) {
			throw new Exception("Blob has more data than expected.");
		}

		inputStream.close();

		return true;
	}
	catch (Exception e) {
		System.err.println(e);
		e.printStackTrace();

		return false;
	}
}

private static boolean _updateBlob(long blobEntryId) {
	try {
		BlobEntry blobEntry = BlobEntryLocalServiceUtil.getBlobEntry(blobEntryId);

		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(data2);

		OutputBlob outputBlob = new OutputBlob(
			unsyncByteArrayInputStream, data2.length);

		blobEntry.setBlobField(outputBlob);

		BlobEntryLocalServiceUtil.updateBlobEntry(blobEntry);

		blobEntry = BlobEntryLocalServiceUtil.getBlobEntry(blobEntryId);

		Blob blob = blobEntry.getBlobField();

		InputStream inputStream = blob.getBinaryStream();

		for (int i = 0; i < data2.length; i++) {
			if (data2[i] != (byte)inputStream.read()) {
				throw new Exception("Mismatch at index : " + i);
			}
		}

		if (inputStream.read() != -1) {
			throw new Exception("Blob has more data than expected.");
		}

		inputStream.close();

		return true;
	}
	catch (Exception e) {
		System.err.println(e);
		e.printStackTrace();

		return false;
	}
}
%>