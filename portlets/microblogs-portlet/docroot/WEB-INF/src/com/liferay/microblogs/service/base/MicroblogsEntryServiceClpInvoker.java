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

package com.liferay.microblogs.service.base;

import com.liferay.microblogs.service.MicroblogsEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class MicroblogsEntryServiceClpInvoker {
	public MicroblogsEntryServiceClpInvoker() {
		_methodName24 = "getBeanIdentifier";

		_methodParameterTypes24 = new String[] {  };

		_methodName25 = "setBeanIdentifier";

		_methodParameterTypes25 = new String[] { "java.lang.String" };

		_methodName30 = "addMicroblogsEntry";

		_methodParameterTypes30 = new String[] {
				"long", "java.lang.String", "int", "long", "long", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName31 = "deleteMicroblogsEntry";

		_methodParameterTypes31 = new String[] { "long" };

		_methodName32 = "getMicroblogsEntries";

		_methodParameterTypes32 = new String[] { "int", "int" };

		_methodName33 = "getMicroblogsEntries";

		_methodParameterTypes33 = new String[] { "java.lang.String", "int", "int" };

		_methodName34 = "getMicroblogsEntriesCount";

		_methodParameterTypes34 = new String[] {  };

		_methodName35 = "getMicroblogsEntriesCount";

		_methodParameterTypes35 = new String[] { "java.lang.String" };

		_methodName36 = "getMicroblogsEntry";

		_methodParameterTypes36 = new String[] { "long" };

		_methodName37 = "getUserMicroblogsEntries";

		_methodParameterTypes37 = new String[] { "long", "int", "int" };

		_methodName38 = "getUserMicroblogsEntries";

		_methodParameterTypes38 = new String[] { "long", "int", "int", "int" };

		_methodName39 = "getUserMicroblogsEntriesCount";

		_methodParameterTypes39 = new String[] { "long" };

		_methodName40 = "getUserMicroblogsEntriesCount";

		_methodParameterTypes40 = new String[] { "long", "int" };

		_methodName41 = "updateMicroblogsEntry";

		_methodParameterTypes41 = new String[] {
				"long", "java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			MicroblogsEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return MicroblogsEntryServiceUtil.addMicroblogsEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return MicroblogsEntryServiceUtil.deleteMicroblogsEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntries((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntriesCount();
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntriesCount((java.lang.String)arguments[0]);
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return MicroblogsEntryServiceUtil.updateMicroblogsEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
}