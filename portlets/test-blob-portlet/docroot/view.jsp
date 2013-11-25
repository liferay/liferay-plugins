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

<% long testBlobEntryId = -1; %>

Create Blob : <%= (testBlobEntryId = _createBlob()) > 0 ? "PASSED" : "FAILED" %> <br />

Read Blob : <%= _readBlob(testBlobEntryId, data1) ? "PASSED" : "FAILED" %> <br />

Update Blob : <%= _updateBlob(testBlobEntryId) ? "PASSED" : "FAILED" %> <br />

Delete Blob : <%= _deleteBlob(testBlobEntryId) ? "PASSED" : "FAILED" %> <br />

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
		long testBlobEntryId = CounterLocalServiceUtil.increment();

		BlobEntry blobEntry = BlobEntryUtil.create(testBlobEntryId);

		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(data1);

		OutputBlob outputBlob = new OutputBlob(
			unsyncByteArrayInputStream, data1.length);

		blobEntry.setBlobField(outputBlob);

		blobEntry = BlobEntryLocalServiceUtil.addBlobEntry(blobEntry);

		return blobEntry.getTestBlobEntryId();
	}
	catch (Exception e) {
		System.err.println(e);
		e.printStackTrace();

		return -1;
	}
}

private static boolean _deleteBlob(long testBlobEntryId) {
	try {
		BlobEntryLocalServiceUtil.deleteBlobEntry(testBlobEntryId);

		BlobEntry blobEntry = BlobEntryLocalServiceUtil.fetchBlobEntry(testBlobEntryId);

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

private static boolean _readBlob(long testBlobEntryId, byte[] expectedData) {
	try {
		BlobEntry blobEntry = BlobEntryLocalServiceUtil.getBlobEntry(testBlobEntryId);

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

private static boolean _updateBlob(long testBlobEntryId) {
	try {
		BlobEntry blobEntry = BlobEntryLocalServiceUtil.getBlobEntry(testBlobEntryId);

		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(data2);

		OutputBlob outputBlob = new OutputBlob(
			unsyncByteArrayInputStream, data2.length);

		blobEntry.setBlobField(outputBlob);

		BlobEntryLocalServiceUtil.updateBlobEntry(blobEntry);

		blobEntry = BlobEntryLocalServiceUtil.getBlobEntry(testBlobEntryId);

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