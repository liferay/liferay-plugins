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

package com.liferay.portal.workflow.kaleo.service.base;

import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoDefinitionLocalServiceClpInvoker {
	public KaleoDefinitionLocalServiceClpInvoker() {
		_methodName0 = "addKaleoDefinition";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoDefinition"
			};

		_methodName1 = "createKaleoDefinition";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteKaleoDefinition";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteKaleoDefinition";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoDefinition"
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

		_methodName10 = "fetchKaleoDefinition";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getKaleoDefinition";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getKaleoDefinitions";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getKaleoDefinitionsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateKaleoDefinition";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoDefinition"
			};

		_methodName102 = "getBeanIdentifier";

		_methodParameterTypes102 = new String[] {  };

		_methodName103 = "setBeanIdentifier";

		_methodParameterTypes103 = new String[] { "java.lang.String" };

		_methodName108 = "activateKaleoDefinition";

		_methodParameterTypes108 = new String[] {
				"long", "long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName109 = "activateKaleoDefinition";

		_methodParameterTypes109 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName110 = "activateKaleoDefinition";

		_methodParameterTypes110 = new String[] {
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName111 = "addKaleoDefinition";

		_methodParameterTypes111 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName112 = "deactivateKaleoDefinition";

		_methodParameterTypes112 = new String[] {
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName113 = "deleteCompanyKaleoDefinitions";

		_methodParameterTypes113 = new String[] { "long" };

		_methodName114 = "deleteKaleoDefinition";

		_methodParameterTypes114 = new String[] {
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName115 = "getKaleoDefinition";

		_methodParameterTypes115 = new String[] {
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName116 = "getKaleoDefinitions";

		_methodParameterTypes116 = new String[] {
				"boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName117 = "getKaleoDefinitions";

		_methodParameterTypes117 = new String[] {
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName118 = "getKaleoDefinitions";

		_methodParameterTypes118 = new String[] {
				"java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName119 = "getKaleoDefinitions";

		_methodParameterTypes119 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName120 = "getKaleoDefinitionsCount";

		_methodParameterTypes120 = new String[] {
				"boolean", "com.liferay.portal.service.ServiceContext"
			};

		_methodName121 = "getKaleoDefinitionsCount";

		_methodParameterTypes121 = new String[] {
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName122 = "getKaleoDefinitionsCount";

		_methodParameterTypes122 = new String[] {
				"java.lang.String", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName123 = "getKaleoDefinitionsCount";

		_methodParameterTypes123 = new String[] {
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName124 = "getLatestKaleoDefinition";

		_methodParameterTypes124 = new String[] {
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName125 = "incrementKaleoDefinition";

		_methodParameterTypes125 = new String[] {
				"com.liferay.portal.workflow.kaleo.definition.Definition",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName126 = "updateTitle";

		_methodParameterTypes126 = new String[] {
				"java.lang.String", "int", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.addKaleoDefinition((com.liferay.portal.workflow.kaleo.model.KaleoDefinition)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.createKaleoDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.deleteKaleoDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.deleteKaleoDefinition((com.liferay.portal.workflow.kaleo.model.KaleoDefinition)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.fetchKaleoDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinition(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.updateKaleoDefinition((com.liferay.portal.workflow.kaleo.model.KaleoDefinition)arguments[0]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			KaleoDefinitionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			KaleoDefinitionLocalServiceUtil.activateKaleoDefinition(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);

			return null;
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			KaleoDefinitionLocalServiceUtil.activateKaleoDefinition(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);

			return null;
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			KaleoDefinitionLocalServiceUtil.activateKaleoDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);

			return null;
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.addKaleoDefinition((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			KaleoDefinitionLocalServiceUtil.deactivateKaleoDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);

			return null;
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			KaleoDefinitionLocalServiceUtil.deleteCompanyKaleoDefinitions(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			KaleoDefinitionLocalServiceUtil.deleteKaleoDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);

			return null;
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinition((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(((Boolean)arguments[0]).booleanValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitions((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitions((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount(((Boolean)arguments[0]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount((com.liferay.portal.service.ServiceContext)arguments[0]);
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount((java.lang.String)arguments[0],
				((Boolean)arguments[1]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName123.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes123, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getKaleoDefinitionsCount((java.lang.String)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName124.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes124, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.getLatestKaleoDefinition((java.lang.String)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName125.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes125, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.incrementKaleoDefinition((com.liferay.portal.workflow.kaleo.definition.Definition)arguments[0],
				(java.lang.String)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName126.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes126, parameterTypes)) {
			return KaleoDefinitionLocalServiceUtil.updateTitle((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				(java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
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
	private String _methodName102;
	private String[] _methodParameterTypes102;
	private String _methodName103;
	private String[] _methodParameterTypes103;
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName109;
	private String[] _methodParameterTypes109;
	private String _methodName110;
	private String[] _methodParameterTypes110;
	private String _methodName111;
	private String[] _methodParameterTypes111;
	private String _methodName112;
	private String[] _methodParameterTypes112;
	private String _methodName113;
	private String[] _methodParameterTypes113;
	private String _methodName114;
	private String[] _methodParameterTypes114;
	private String _methodName115;
	private String[] _methodParameterTypes115;
	private String _methodName116;
	private String[] _methodParameterTypes116;
	private String _methodName117;
	private String[] _methodParameterTypes117;
	private String _methodName118;
	private String[] _methodParameterTypes118;
	private String _methodName119;
	private String[] _methodParameterTypes119;
	private String _methodName120;
	private String[] _methodParameterTypes120;
	private String _methodName121;
	private String[] _methodParameterTypes121;
	private String _methodName122;
	private String[] _methodParameterTypes122;
	private String _methodName123;
	private String[] _methodParameterTypes123;
	private String _methodName124;
	private String[] _methodParameterTypes124;
	private String _methodName125;
	private String[] _methodParameterTypes125;
	private String _methodName126;
	private String[] _methodParameterTypes126;
}