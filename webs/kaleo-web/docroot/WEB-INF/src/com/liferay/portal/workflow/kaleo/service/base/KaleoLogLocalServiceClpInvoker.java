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

		_methodName12 = "getActionableDynamicQuery";

		_methodParameterTypes12 = new String[] {  };

		_methodName14 = "deletePersistedModel";

		_methodParameterTypes14 = new String[] {
				"com.liferay.portal.model.PersistedModel"
			};

		_methodName15 = "getPersistedModel";

		_methodParameterTypes15 = new String[] { "java.io.Serializable" };

		_methodName16 = "getKaleoLogs";

		_methodParameterTypes16 = new String[] { "int", "int" };

		_methodName17 = "getKaleoLogsCount";

		_methodParameterTypes17 = new String[] {  };

		_methodName18 = "updateKaleoLog";

		_methodParameterTypes18 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoLog"
			};

		_methodName105 = "getBeanIdentifier";

		_methodParameterTypes105 = new String[] {  };

		_methodName106 = "setBeanIdentifier";

		_methodParameterTypes106 = new String[] { "java.lang.String" };

		_methodName111 = "addActionExecutionKaleoLog";

		_methodParameterTypes111 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoAction", "long",
				"long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName112 = "addNodeEntryKaleoLog";

		_methodParameterTypes112 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName113 = "addNodeExitKaleoLog";

		_methodParameterTypes113 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoNode",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName114 = "addTaskAssignmentKaleoLog";

		_methodParameterTypes114 = new String[] {
				"java.util.List",
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName115 = "addTaskCompletionKaleoLog";

		_methodParameterTypes115 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName116 = "addTaskUpdateKaleoLog";

		_methodParameterTypes116 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName117 = "addWorkflowInstanceEndKaleoLog";

		_methodParameterTypes117 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName118 = "addWorkflowInstanceStartKaleoLog";

		_methodParameterTypes118 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName119 = "deleteCompanyKaleoLogs";

		_methodParameterTypes119 = new String[] { "long" };

		_methodName120 = "deleteKaleoDefinitionKaleoLogs";

		_methodParameterTypes120 = new String[] { "long" };

		_methodName121 = "deleteKaleoInstanceKaleoLogs";

		_methodParameterTypes121 = new String[] { "long" };

		_methodName122 = "getKaleoInstanceKaleoLogs";

		_methodParameterTypes122 = new String[] {
				"long", "java.util.List", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName123 = "getKaleoInstanceKaleoLogsCount";

		_methodParameterTypes123 = new String[] { "long", "java.util.List" };

		_methodName124 = "getKaleoTaskInstanceTokenKaleoLogs";

		_methodParameterTypes124 = new String[] {
				"long", "java.util.List", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName125 = "getKaleoTaskInstanceTokenKaleoLogsCount";

		_methodParameterTypes125 = new String[] { "long", "java.util.List" };
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
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
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
			return KaleoLogLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoLogLocalServiceUtil.deletePersistedModel((com.liferay.portal.model.PersistedModel)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoLogs(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoLogsCount();
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return KaleoLogLocalServiceUtil.updateKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoLog)arguments[0]);
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			KaleoLogLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName111.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes111, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addActionExecutionKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoAction)arguments[1],
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addNodeEntryKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[1],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addNodeExitKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoNode)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskAssignmentKaleoLog((java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[1],
				(java.lang.String)arguments[2],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskCompletionKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				(java.lang.String)arguments[1],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addTaskUpdateKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				(java.lang.String)arguments[1],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addWorkflowInstanceEndKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			return KaleoLogLocalServiceUtil.addWorkflowInstanceStartKaleoLog((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteCompanyKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteKaleoDefinitionKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			KaleoLogLocalServiceUtil.deleteKaleoInstanceKaleoLogs(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogs(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoLog>)arguments[4]);
		}

		if (_methodName123.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes123, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogsCount(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1]);
		}

		if (_methodName124.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes124, parameterTypes)) {
			return KaleoLogLocalServiceUtil.getKaleoTaskInstanceTokenKaleoLogs(((Long)arguments[0]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.workflow.kaleo.model.KaleoLog>)arguments[4]);
		}

		if (_methodName125.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes125, parameterTypes)) {
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
	private String _methodName105;
	private String[] _methodParameterTypes105;
	private String _methodName106;
	private String[] _methodParameterTypes106;
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
}