/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.microblogs.service.MicroblogsEntryServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class MicroblogsEntryServiceClpInvoker {
	public MicroblogsEntryServiceClpInvoker() {
		_methodName26 = "getOSGiServiceIdentifier";

		_methodParameterTypes26 = new String[] {  };

		_methodName31 = "addMicroblogsEntry";

		_methodParameterTypes31 = new String[] {
				"long", "java.lang.String", "int", "long", "int",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName32 = "deleteMicroblogsEntry";

		_methodParameterTypes32 = new String[] { "long" };

		_methodName33 = "getMicroblogsEntries";

		_methodParameterTypes33 = new String[] { "int", "int" };

		_methodName34 = "getMicroblogsEntries";

		_methodParameterTypes34 = new String[] { "java.lang.String", "int", "int" };

		_methodName35 = "getMicroblogsEntriesCount";

		_methodParameterTypes35 = new String[] {  };

		_methodName36 = "getMicroblogsEntriesCount";

		_methodParameterTypes36 = new String[] { "java.lang.String" };

		_methodName37 = "getMicroblogsEntry";

		_methodParameterTypes37 = new String[] { "long" };

		_methodName38 = "getUserMicroblogsEntries";

		_methodParameterTypes38 = new String[] { "long", "int", "int" };

		_methodName39 = "getUserMicroblogsEntries";

		_methodParameterTypes39 = new String[] { "long", "int", "int", "int" };

		_methodName40 = "getUserMicroblogsEntriesCount";

		_methodParameterTypes40 = new String[] { "long" };

		_methodName41 = "getUserMicroblogsEntriesCount";

		_methodParameterTypes41 = new String[] { "long", "int" };

		_methodName42 = "updateMicroblogsEntry";

		_methodParameterTypes42 = new String[] {
				"long", "java.lang.String", "int",
				"com.liferay.portal.kernel.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return MicroblogsEntryServiceUtil.addMicroblogsEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[5]);
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return MicroblogsEntryServiceUtil.deleteMicroblogsEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntries((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntriesCount();
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntriesCount((java.lang.String)arguments[0]);
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getMicroblogsEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return MicroblogsEntryServiceUtil.getUserMicroblogsEntriesCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return MicroblogsEntryServiceUtil.updateMicroblogsEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName26;
	private String[] _methodParameterTypes26;
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
	private String _methodName42;
	private String[] _methodParameterTypes42;
}