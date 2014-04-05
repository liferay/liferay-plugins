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

package com.liferay.calendar.service.base;

import com.liferay.calendar.service.CalendarResourceLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Eduardo Lundgren
 * @generated
 */
public class CalendarResourceLocalServiceClpInvoker {
	public CalendarResourceLocalServiceClpInvoker() {
		_methodName0 = "addCalendarResource";

		_methodParameterTypes0 = new String[] {
				"com.liferay.calendar.model.CalendarResource"
			};

		_methodName1 = "createCalendarResource";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteCalendarResource";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteCalendarResource";

		_methodParameterTypes3 = new String[] {
				"com.liferay.calendar.model.CalendarResource"
			};

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

		_methodName10 = "fetchCalendarResource";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchCalendarResourceByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "fetchCalendarResourceByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getCalendarResource";

		_methodParameterTypes13 = new String[] { "long" };

		_methodName14 = "getPersistedModel";

		_methodParameterTypes14 = new String[] { "java.io.Serializable" };

		_methodName15 = "getCalendarResourceByUuidAndCompanyId";

		_methodParameterTypes15 = new String[] { "java.lang.String", "long" };

		_methodName16 = "getCalendarResourceByUuidAndGroupId";

		_methodParameterTypes16 = new String[] { "java.lang.String", "long" };

		_methodName17 = "getCalendarResources";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getCalendarResourcesCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateCalendarResource";

		_methodParameterTypes19 = new String[] {
				"com.liferay.calendar.model.CalendarResource"
			};

		_methodName76 = "getBeanIdentifier";

		_methodParameterTypes76 = new String[] {  };

		_methodName77 = "setBeanIdentifier";

		_methodParameterTypes77 = new String[] { "java.lang.String" };

		_methodName82 = "addCalendarResource";

		_methodParameterTypes82 = new String[] {
				"long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "java.util.Map", "java.util.Map", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName83 = "deleteCalendarResource";

		_methodParameterTypes83 = new String[] {
				"com.liferay.calendar.model.CalendarResource"
			};

		_methodName84 = "deleteCalendarResource";

		_methodParameterTypes84 = new String[] { "long" };

		_methodName85 = "deleteCalendarResources";

		_methodParameterTypes85 = new String[] { "long" };

		_methodName86 = "fetchCalendarResource";

		_methodParameterTypes86 = new String[] { "long", "long" };

		_methodName87 = "getCalendarResource";

		_methodParameterTypes87 = new String[] { "long" };

		_methodName88 = "getCalendarResources";

		_methodParameterTypes88 = new String[] { "long" };

		_methodName89 = "search";

		_methodParameterTypes89 = new String[] {
				"long", "long[][]", "long[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName90 = "searchByKeywords";

		_methodParameterTypes90 = new String[] {
				"long", "long[][]", "long[][]", "java.lang.String", "boolean",
				"boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName91 = "searchCount";

		_methodParameterTypes91 = new String[] {
				"long", "long[][]", "long[][]", "java.lang.String", "boolean"
			};

		_methodName92 = "searchCount";

		_methodParameterTypes92 = new String[] {
				"long", "long[][]", "long[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "boolean"
			};

		_methodName93 = "updateAsset";

		_methodParameterTypes93 = new String[] {
				"long", "com.liferay.calendar.model.CalendarResource",
				"long[][]", "java.lang.String[][]"
			};

		_methodName94 = "updateCalendarResource";

		_methodParameterTypes94 = new String[] {
				"long", "java.util.Map", "java.util.Map", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.addCalendarResource((com.liferay.calendar.model.CalendarResource)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.createCalendarResource(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.deleteCalendarResource(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.deleteCalendarResource((com.liferay.calendar.model.CalendarResource)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.fetchCalendarResource(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.fetchCalendarResourceByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.fetchCalendarResourceByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getCalendarResource(((Long)arguments[0]).longValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getCalendarResourceByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getCalendarResourceByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getCalendarResources(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getCalendarResourcesCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.updateCalendarResource((com.liferay.calendar.model.CalendarResource)arguments[0]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			CalendarResourceLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.addCalendarResource(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[6],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[7],
				((Boolean)arguments[8]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.deleteCalendarResource((com.liferay.calendar.model.CalendarResource)arguments[0]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.deleteCalendarResource(((Long)arguments[0]).longValue());
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			CalendarResourceLocalServiceUtil.deleteCalendarResources(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.fetchCalendarResource(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getCalendarResource(((Long)arguments[0]).longValue());
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.getCalendarResources(((Long)arguments[0]).longValue());
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[10]);
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.searchByKeywords(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue(),
				((Boolean)arguments[5]).booleanValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[8]);
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			CalendarResourceLocalServiceUtil.updateAsset(((Long)arguments[0]).longValue(),
				(com.liferay.calendar.model.CalendarResource)arguments[1],
				(long[])arguments[2], (java.lang.String[])arguments[3]);

			return null;
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return CalendarResourceLocalServiceUtil.updateCalendarResource(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
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
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
}