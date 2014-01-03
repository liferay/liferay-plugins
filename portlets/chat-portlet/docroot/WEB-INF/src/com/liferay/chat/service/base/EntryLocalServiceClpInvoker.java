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

package com.liferay.chat.service.base;

import com.liferay.chat.service.EntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntryLocalServiceClpInvoker {
	public EntryLocalServiceClpInvoker() {
		_methodName0 = "addEntry";

		_methodParameterTypes0 = new String[] { "com.liferay.chat.model.Entry" };

		_methodName1 = "createEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteEntry";

		_methodParameterTypes3 = new String[] { "com.liferay.chat.model.Entry" };

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getEntry";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getEntries";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getEntriesCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateEntry";

		_methodParameterTypes15 = new String[] { "com.liferay.chat.model.Entry" };

		_methodName46 = "getBeanIdentifier";

		_methodParameterTypes46 = new String[] {  };

		_methodName47 = "setBeanIdentifier";

		_methodParameterTypes47 = new String[] { "java.lang.String" };

		_methodName52 = "addEntry";

		_methodParameterTypes52 = new String[] {
				"long", "long", "long", "java.lang.String"
			};

		_methodName53 = "addEntry";

		_methodParameterTypes53 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName54 = "deleteEntries";

		_methodParameterTypes54 = new String[] { "long" };

		_methodName55 = "getNewEntries";

		_methodParameterTypes55 = new String[] { "long", "long", "int", "int" };

		_methodName56 = "getOldEntries";

		_methodParameterTypes56 = new String[] { "long", "int", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return EntryLocalServiceUtil.addEntry((com.liferay.chat.model.Entry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return EntryLocalServiceUtil.createEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return EntryLocalServiceUtil.deleteEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return EntryLocalServiceUtil.deleteEntry((com.liferay.chat.model.Entry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return EntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return EntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return EntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return EntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return EntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return EntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return EntryLocalServiceUtil.fetchEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return EntryLocalServiceUtil.getEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return EntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return EntryLocalServiceUtil.getEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return EntryLocalServiceUtil.getEntriesCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return EntryLocalServiceUtil.updateEntry((com.liferay.chat.model.Entry)arguments[0]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return EntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			EntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return EntryLocalServiceUtil.addEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(), (java.lang.String)arguments[3]);
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return EntryLocalServiceUtil.addEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			EntryLocalServiceUtil.deleteEntries(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return EntryLocalServiceUtil.getNewEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return EntryLocalServiceUtil.getOldEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
}