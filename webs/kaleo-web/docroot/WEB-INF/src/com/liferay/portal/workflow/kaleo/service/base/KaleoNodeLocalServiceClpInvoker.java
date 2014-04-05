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

import com.liferay.portal.workflow.kaleo.service.KaleoNodeLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoNodeLocalServiceClpInvoker {
	public KaleoNodeLocalServiceClpInvoker() {
		_methodName0 = "addKaleoNode";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoNode"
			};

		_methodName1 = "createKaleoNode";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteKaleoNode";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteKaleoNode";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoNode"
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

		_methodName10 = "fetchKaleoNode";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getKaleoNode";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getKaleoNodes";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getKaleoNodesCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateKaleoNode";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoNode"
			};

		_methodName102 = "getBeanIdentifier";

		_methodParameterTypes102 = new String[] {  };

		_methodName103 = "setBeanIdentifier";

		_methodParameterTypes103 = new String[] { "java.lang.String" };

		_methodName108 = "addKaleoNode";

		_methodParameterTypes108 = new String[] {
				"long", "com.liferay.portal.workflow.kaleo.definition.Node",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName109 = "deleteCompanyKaleoNodes";

		_methodParameterTypes109 = new String[] { "long" };

		_methodName110 = "deleteKaleoDefinitionKaleoNodes";

		_methodParameterTypes110 = new String[] { "long" };

		_methodName111 = "getKaleoDefinitionKaleoNodes";

		_methodParameterTypes111 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.addKaleoNode((com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.createKaleoNode(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.deleteKaleoNode(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.deleteKaleoNode((com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.fetchKaleoNode(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.getKaleoNode(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.getKaleoNodes(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.getKaleoNodesCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.updateKaleoNode((com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[0]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			KaleoNodeLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.addKaleoNode(((Long)arguments[0]).longValue(),
				(com.liferay.portal.workflow.kaleo.definition.Node)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			KaleoNodeLocalServiceUtil.deleteCompanyKaleoNodes(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			KaleoNodeLocalServiceUtil.deleteKaleoDefinitionKaleoNodes(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return KaleoNodeLocalServiceUtil.getKaleoDefinitionKaleoNodes(((Long)arguments[0]).longValue());
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
}