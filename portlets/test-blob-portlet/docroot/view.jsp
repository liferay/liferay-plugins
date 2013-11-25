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

<%@ include file="/init.jsp" %>

<p>

	<%
	long testBlobEntryId = 0;

	Random random = new Random();

	byte[] bytes = new byte[4096];

	random.nextBytes(bytes);
	%>

	Create=<%= (testBlobEntryId = _create(bytes)) > 0 ? "PASSED" : "FAILED" %><br />
	Read=<%= _read(testBlobEntryId, bytes) ? "PASSED" : "FAILED" %><br />
	Update=<%= _update(testBlobEntryId) ? "PASSED" : "FAILED" %><br />
	Delete=<%= _delete(testBlobEntryId) ? "PASSED" : "FAILED" %><br />
</p>

<%!
private static long _create(byte[] bytes) {
	try {
		long testBlobEntryId = CounterLocalServiceUtil.increment();

		TestBlobEntry testBlobEntry = TestBlobEntryUtil.create(testBlobEntryId);

		UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(bytes);

		OutputBlob outputBlob = new OutputBlob(unsyncByteArrayInputStream, bytes.length);

		testBlobEntry.setBlobField(outputBlob);

		TestBlobEntryLocalServiceUtil.addTestBlobEntry(testBlobEntry);

		return testBlobEntry.getTestBlobEntryId();
	}
	catch (Exception e) {
		e.printStackTrace();

		return 0;
	}
}

private static boolean _delete(long testBlobEntryId) {
	try {
		TestBlobEntryLocalServiceUtil.deleteTestBlobEntry(testBlobEntryId);

		TestBlobEntry testBlobEntry = TestBlobEntryLocalServiceUtil.fetchTestBlobEntry(testBlobEntryId);

		if (testBlobEntry != null) {
			throw new Exception("Test blob entry was not deleted");
		}

		return true;
	}
	catch (Exception e) {
		e.printStackTrace();

		return false;
	}
}

private static boolean _read(long testBlobEntryId, byte[] bytes) {
	try {
		TestBlobEntry testBlobEntry = TestBlobEntryLocalServiceUtil.getTestBlobEntry(testBlobEntryId);

		Blob blob = testBlobEntry.getBlobField();

		InputStream inputStream = blob.getBinaryStream();

		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] != (byte)inputStream.read()) {
				throw new Exception("Test blob entry bytes do not match at index " + i);
			}
		}

		if (inputStream.read() != -1) {
			throw new Exception("Test blob entry has more bytes than expected");
		}

		inputStream.close();

		return true;
	}
	catch (Exception e) {
		e.printStackTrace();

		return false;
	}
}

private static boolean _update(long testBlobEntryId) {
	try {
		TestBlobEntry testBlobEntry = TestBlobEntryLocalServiceUtil.getTestBlobEntry(testBlobEntryId);

		Random random = new Random();

		byte[] bytes = new byte[4096];

		random.nextBytes(bytes);

		UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(bytes);

		OutputBlob outputBlob = new OutputBlob(unsyncByteArrayInputStream, bytes.length);

		testBlobEntry.setBlobField(outputBlob);

		TestBlobEntryLocalServiceUtil.updateTestBlobEntry(testBlobEntry);

		testBlobEntry = TestBlobEntryLocalServiceUtil.getTestBlobEntry(testBlobEntryId);

		Blob blob = testBlobEntry.getBlobField();

		InputStream inputStream = blob.getBinaryStream();

		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] != (byte)inputStream.read()) {
				throw new Exception("Test blob entry bytes do not match at index " + i);
			}
		}

		if (inputStream.read() != -1) {
			throw new Exception("Test blob entry has more bytes than expected");
		}

		inputStream.close();

		return true;
	}
	catch (Exception e) {
		e.printStackTrace();

		return false;
	}
}
%>