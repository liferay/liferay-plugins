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

import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoLogLocalServiceClpInvoker {
	public KaleoLogLocalServiceClpInvoker() {
		_methodName0 = "addKaleoLog";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoLog"
			};

		_methodName1 = "createKaleoLog";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteKaleoLog";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteKaleoLog";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoLog"
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

		_methodName10 = "fetchKaleoLog";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getKaleoLog";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getKaleoLogs";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getKaleoLogsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateKaleoLog";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoLog"
			};

		_methodName102 = "getBeanIdentifier";

		_methodParameterTypes102 = new String[] {  };

		_methodName103 = "setBeanIdentifier";

		_methodParameterTypes103 = new String[] { "java.lang.String" };

		_methodName108 = "addActionExecutionKaleoLog";

		_methodParameterTypes108 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoAction", "long",
				"long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName109 = "addNodeEntryKaleoLog";

		_methodParameterTypes109 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName110 = "addNodeExitKaleoLog";

		_methodParameterTypes110 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName111 = "addTaskAssignmentKaleoLog";

		_methodParameterTypes111 = new String[] {
				"java.util.List",
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName112 = "addTaskCompletionKaleoLog";

		_methodParameterTypes112 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName113 = "addTaskUpdateKaleoLog";

		_methodParameterTypes113 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName114 = "addWorkflowInstanceEndKaleoLog";

		_methodParameterTypes114 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName115 = "addWorkflowInstanceStartKaleoLog";

		_methodParameterTypes115 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName116 = "deleteCompanyKaleoLogs";

		_methodParameterTypes116 = new String[] { "long" };

		_methodName117 = "deleteKaleoDefinitionKaleoLogs";

		_methodParameterTypes117 = new String[] { "long" };

		_methodName118 = "deleteKaleoInstanceKaleoLogs";

		_methodParameterTypes118 = new String[] { "long" };

		_methodName119 = "getKaleoInstanceKaleoLogs";

		_methodParameterTypes119 = new String[] {
				"long", "java.util.List", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName120 = "getKaleoInstanceKaleoLogsCount";

		_methodParameterTypes120 = new String[] { "long", "java.util.List" };

		_methodName121 = "getKaleoTaskInstanceTokenKaleoLogs";

		_methodParameterTypes121 = new String[] {
				"long", "java.util.List", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName122 = "getKaleoTaskInstanceTokenKaleoLogsCount";

		_methodParameterTypes122 = new String[] { "long", "java.util.List" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoLog)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return KaleoLogLocalServiceUtil.createKaleoLog(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return KaleoLogLocalServiceUtil.deleteKaleoLog(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return KaleoLogLocalServiceUtil.deleteKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoLog)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return KaleoLogLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return KaleoLogLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return KaleoLogLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return KaleoLogLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return KaleoLogLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return KaleoLogLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoLogLocalServiceUtil.fetchKaleoLog(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoLog(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoLogs(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoLogsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return KaleoLogLocalServiceUtil.updateKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoLog)arguments[0]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			KaleoLogLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addActionExecutionKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoAction)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addNodeEntryKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[1],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addNodeExitKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskAssignmentKaleoLog((java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[1],
				(java.lang.String)arguments[2],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskCompletionKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				(java.lang.String)arguments[1],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskUpdateKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				(java.lang.String)arguments[1],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addWorkflowInstanceEndKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addWorkflowInstanceStartKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteCompanyKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteKaleoDefinitionKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteKaleoInstanceKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogs(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogsCount(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1]);
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoTaskInstanceTokenKaleoLogs(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoTaskInstanceTokenKaleoLogsCount(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1]);
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
}