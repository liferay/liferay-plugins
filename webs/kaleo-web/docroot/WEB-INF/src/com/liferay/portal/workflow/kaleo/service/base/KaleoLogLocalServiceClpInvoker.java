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

import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
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

		_methodName9 = "fetchKaleoLog";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getKaleoLog";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getKaleoLogs";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getKaleoLogsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateKaleoLog";

		_methodParameterTypes14 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoLog"
			};

		_methodName95 = "getBeanIdentifier";

		_methodParameterTypes95 = new String[] {  };

		_methodName96 = "setBeanIdentifier";

		_methodParameterTypes96 = new String[] { "java.lang.String" };

		_methodName101 = "addActionExecutionKaleoLog";

		_methodParameterTypes101 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoAction", "long",
				"long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName102 = "addNodeEntryKaleoLog";

		_methodParameterTypes102 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName103 = "addNodeExitKaleoLog";

		_methodParameterTypes103 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName104 = "addTaskAssignmentKaleoLog";

		_methodParameterTypes104 = new String[] {
				"java.util.List",
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName105 = "addTaskCompletionKaleoLog";

		_methodParameterTypes105 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName106 = "addTaskUpdateKaleoLog";

		_methodParameterTypes106 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName107 = "addWorkflowInstanceEndKaleoLog";

		_methodParameterTypes107 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName108 = "addWorkflowInstanceStartKaleoLog";

		_methodParameterTypes108 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName109 = "deleteCompanyKaleoLogs";

		_methodParameterTypes109 = new String[] { "long" };

		_methodName110 = "deleteKaleoDefinitionKaleoLogs";

		_methodParameterTypes110 = new String[] { "long" };

		_methodName111 = "deleteKaleoInstanceKaleoLogs";

		_methodParameterTypes111 = new String[] { "long" };

		_methodName112 = "getKaleoInstanceKaleoLogs";

		_methodParameterTypes112 = new String[] {
				"long", "java.util.List", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName113 = "getKaleoInstanceKaleoLogsCount";

		_methodParameterTypes113 = new String[] { "long", "java.util.List" };

		_methodName114 = "getKaleoTaskInstanceTokenKaleoLogs";

		_methodParameterTypes114 = new String[] {
				"long", "java.util.List", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName115 = "getKaleoTaskInstanceTokenKaleoLogsCount";

		_methodParameterTypes115 = new String[] { "long", "java.util.List" };
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
			return KaleoLogLocalServiceUtil.fetchKaleoLog(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoLog(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoLogs(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoLogsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoLogLocalServiceUtil.updateKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoLog)arguments[0]);
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			KaleoLogLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addActionExecutionKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoAction)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addNodeEntryKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[1],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addNodeExitKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskAssignmentKaleoLog((java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[1],
				(java.lang.String)arguments[2],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskCompletionKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				(java.lang.String)arguments[1],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskUpdateKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				(java.lang.String)arguments[1],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addWorkflowInstanceEndKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addWorkflowInstanceStartKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteCompanyKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteKaleoDefinitionKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteKaleoInstanceKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogs(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogsCount(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1]);
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoTaskInstanceTokenKaleoLogs(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
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
}