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

package com.liferay.sampleservicebuilder.service.base;

import com.liferay.sampleservicebuilder.service.FooLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FooLocalServiceClpInvoker {
	public FooLocalServiceClpInvoker() {
		_methodName0 = "addFoo";

		_methodParameterTypes0 = new String[] {
				"com.liferay.sampleservicebuilder.model.Foo"
			};

		_methodName1 = "createFoo";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteFoo";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteFoo";

		_methodParameterTypes3 = new String[] {
				"com.liferay.sampleservicebuilder.model.Foo"
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

		_methodName10 = "fetchFoo";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchFooByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "fetchFooByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getFoo";

		_methodParameterTypes13 = new String[] { "long" };

		_methodName14 = "getPersistedModel";

		_methodParameterTypes14 = new String[] { "java.io.Serializable" };

		_methodName15 = "getFooByUuidAndCompanyId";

		_methodParameterTypes15 = new String[] { "java.lang.String", "long" };

		_methodName16 = "getFooByUuidAndGroupId";

		_methodParameterTypes16 = new String[] { "java.lang.String", "long" };

		_methodName17 = "getFoos";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getFoosCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateFoo";

		_methodParameterTypes19 = new String[] {
				"com.liferay.sampleservicebuilder.model.Foo"
			};

		_methodName56 = "getBeanIdentifier";

		_methodParameterTypes56 = new String[] {  };

		_methodName57 = "setBeanIdentifier";

		_methodParameterTypes57 = new String[] { "java.lang.String" };

		_methodName62 = "addFoo";

		_methodParameterTypes62 = new String[] {
				"java.lang.String", "boolean", "int", "java.util.Date",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName63 = "deleteFoo";

		_methodParameterTypes63 = new String[] {
				"com.liferay.sampleservicebuilder.model.Foo"
			};

		_methodName64 = "deleteFoo";

		_methodParameterTypes64 = new String[] { "long" };

		_methodName65 = "getFoos";

		_methodParameterTypes65 = new String[] {
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName66 = "getFoos";

		_methodParameterTypes66 = new String[] {
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName67 = "getLocalObject";

		_methodParameterTypes67 = new String[] {  };

		_methodName68 = "updateAsset";

		_methodParameterTypes68 = new String[] {
				"long", "com.liferay.sampleservicebuilder.model.Foo", "long[][]",
				"java.lang.String[][]"
			};

		_methodName69 = "updateFoo";

		_methodParameterTypes69 = new String[] {
				"long", "java.lang.String", "boolean", "int", "java.util.Date",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return FooLocalServiceUtil.addFoo((com.liferay.sampleservicebuilder.model.Foo)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return FooLocalServiceUtil.createFoo(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return FooLocalServiceUtil.deleteFoo(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return FooLocalServiceUtil.deleteFoo((com.liferay.sampleservicebuilder.model.Foo)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return FooLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return FooLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return FooLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return FooLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return FooLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return FooLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return FooLocalServiceUtil.fetchFoo(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return FooLocalServiceUtil.fetchFooByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return FooLocalServiceUtil.fetchFooByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return FooLocalServiceUtil.getFoo(((Long)arguments[0]).longValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return FooLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return FooLocalServiceUtil.getFooByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return FooLocalServiceUtil.getFooByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return FooLocalServiceUtil.getFoos(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return FooLocalServiceUtil.getFoosCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return FooLocalServiceUtil.updateFoo((com.liferay.sampleservicebuilder.model.Foo)arguments[0]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return FooLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			FooLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			FooLocalServiceUtil.addFoo((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Integer)arguments[2]).intValue(),
				(java.util.Date)arguments[3], (java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);

			return null;
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return FooLocalServiceUtil.deleteFoo((com.liferay.sampleservicebuilder.model.Foo)arguments[0]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return FooLocalServiceUtil.deleteFoo(((Long)arguments[0]).longValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return FooLocalServiceUtil.getFoos(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[2]);
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return FooLocalServiceUtil.getFoos((com.liferay.portal.kernel.util.OrderByComparator)arguments[0]);
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return FooLocalServiceUtil.getLocalObject();
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			FooLocalServiceUtil.updateAsset(((Long)arguments[0]).longValue(),
				(com.liferay.sampleservicebuilder.model.Foo)arguments[1],
				(long[])arguments[2], (java.lang.String[])arguments[3]);

			return null;
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			FooLocalServiceUtil.updateFoo(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Boolean)arguments[2]).booleanValue(),
				((Integer)arguments[3]).intValue(),
				(java.util.Date)arguments[4], (java.lang.String)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);

			return null;
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
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
}