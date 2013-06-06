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

package com.liferay.portal.workflow.kaleo.service.base;

import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoTaskAssignmentInstanceLocalServiceClpInvoker {
	public KaleoTaskAssignmentInstanceLocalServiceClpInvoker() {
		_methodName0 = "addKaleoTaskAssignmentInstance";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance"
			};

		_methodName1 = "createKaleoTaskAssignmentInstance";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteKaleoTaskAssignmentInstance";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteKaleoTaskAssignmentInstance";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance"
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

		_methodName9 = "fetchKaleoTaskAssignmentInstance";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getKaleoTaskAssignmentInstance";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getKaleoTaskAssignmentInstances";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getKaleoTaskAssignmentInstancesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateKaleoTaskAssignmentInstance";

		_methodParameterTypes14 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance"
			};

		_methodName95 = "getBeanIdentifier";

		_methodParameterTypes95 = new String[] {  };

		_methodName96 = "setBeanIdentifier";

		_methodParameterTypes96 = new String[] { "java.lang.String" };

		_methodName101 = "addKaleoTaskAssignmentInstance";

		_methodParameterTypes101 = new String[] {
				"long",
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName102 = "addTaskAssignmentInstances";

		_methodParameterTypes102 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.util.Collection", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName103 = "assignKaleoTaskAssignmentInstance";

		_methodParameterTypes103 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName104 = "completeKaleoTaskInstanceToken";

		_methodParameterTypes104 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName105 = "deleteCompanyKaleoTaskAssignmentInstances";

		_methodParameterTypes105 = new String[] { "long" };

		_methodName106 = "deleteKaleoDefinitionKaleoTaskAssignmentInstances";

		_methodParameterTypes106 = new String[] { "long" };

		_methodName107 = "deleteKaleoInstanceKaleoTaskAssignmentInstances";

		_methodParameterTypes107 = new String[] { "long" };

		_methodName108 = "deleteKaleoTaskAssignmentInstances";

		_methodParameterTypes108 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken"
			};

		_methodName109 = "getKaleoTaskAssignmentInstances";

		_methodParameterTypes109 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.addKaleoTaskAssignmentInstance((com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.createKaleoTaskAssignmentInstance(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.deleteKaleoTaskAssignmentInstance(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.deleteKaleoTaskAssignmentInstance((com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.fetchKaleoTaskAssignmentInstance(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.getKaleoTaskAssignmentInstance(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.getKaleoTaskAssignmentInstances(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.getKaleoTaskAssignmentInstancesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.updateKaleoTaskAssignmentInstance((com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)arguments[0]);
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			KaleoTaskAssignmentInstanceLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.addKaleoTaskAssignmentInstance(((Long)arguments[0]).longValue(),
				(com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[1],
				(java.lang.String)arguments[2],
				((Long)arguments[3]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.addTaskAssignmentInstances((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				(java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>)arguments[1],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.assignKaleoTaskAssignmentInstance((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.completeKaleoTaskInstanceToken(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			KaleoTaskAssignmentInstanceLocalServiceUtil.deleteCompanyKaleoTaskAssignmentInstances(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			KaleoTaskAssignmentInstanceLocalServiceUtil.deleteKaleoDefinitionKaleoTaskAssignmentInstances(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			KaleoTaskAssignmentInstanceLocalServiceUtil.deleteKaleoInstanceKaleoTaskAssignmentInstances(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			KaleoTaskAssignmentInstanceLocalServiceUtil.deleteKaleoTaskAssignmentInstances((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0]);

			return null;
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return KaleoTaskAssignmentInstanceLocalServiceUtil.getKaleoTaskAssignmentInstances(((Long)arguments[0]).longValue());
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
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
	private String _methodName101;
	private String[] _methodParameterTypes101;
	private String _methodName102;
	private String[] _methodParameterTypes102;
	private String _methodName103;
	private String[] _methodParameterTypes103;
	private String _methodName104;
	private String[] _methodParameterTypes104;
	private String _methodName105;
	private String[] _methodParameterTypes105;
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName109;
	private String[] _methodParameterTypes109;
}