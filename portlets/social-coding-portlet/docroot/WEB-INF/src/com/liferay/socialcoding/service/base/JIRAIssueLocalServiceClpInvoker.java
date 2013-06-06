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

package com.liferay.socialcoding.service.base;

import com.liferay.socialcoding.service.JIRAIssueLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAIssueLocalServiceClpInvoker {
	public JIRAIssueLocalServiceClpInvoker() {
		_methodName0 = "addJIRAIssue";

		_methodParameterTypes0 = new String[] {
				"com.liferay.socialcoding.model.JIRAIssue"
			};

		_methodName1 = "createJIRAIssue";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteJIRAIssue";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteJIRAIssue";

		_methodParameterTypes3 = new String[] {
				"com.liferay.socialcoding.model.JIRAIssue"
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

		_methodName9 = "fetchJIRAIssue";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getJIRAIssue";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getJIRAIssues";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getJIRAIssuesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateJIRAIssue";

		_methodParameterTypes14 = new String[] {
				"com.liferay.socialcoding.model.JIRAIssue"
			};

		_methodName57 = "getBeanIdentifier";

		_methodParameterTypes57 = new String[] {  };

		_methodName58 = "setBeanIdentifier";

		_methodParameterTypes58 = new String[] { "java.lang.String" };

		_methodName63 = "getAssigneeJIRAIssues";

		_methodParameterTypes63 = new String[] {
				"java.util.Date", "long", "java.lang.String", "int", "int"
			};

		_methodName64 = "getAssigneeJIRAIssues";

		_methodParameterTypes64 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName65 = "getAssigneeJIRAIssues";

		_methodParameterTypes65 = new String[] {
				"long", "java.lang.String", "java.lang.String", "int", "int"
			};

		_methodName66 = "getAssigneeJIRAIssuesCount";

		_methodParameterTypes66 = new String[] {
				"java.util.Date", "long", "java.lang.String"
			};

		_methodName67 = "getAssigneeJIRAIssuesCount";

		_methodParameterTypes67 = new String[] { "long", "java.lang.String" };

		_methodName68 = "getAssigneeJIRAIssuesCount";

		_methodParameterTypes68 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName69 = "getFirstAssigneeJIRAIssue";

		_methodParameterTypes69 = new String[] { "long", "java.lang.String" };

		_methodName70 = "getFirstReporterJIRAIssue";

		_methodParameterTypes70 = new String[] { "long", "java.lang.String" };

		_methodName71 = "getJIRAIssue";

		_methodParameterTypes71 = new String[] { "long" };

		_methodName72 = "getJIRAIssue";

		_methodParameterTypes72 = new String[] { "java.lang.String" };

		_methodName73 = "getLastAssigneeJIRAIssue";

		_methodParameterTypes73 = new String[] { "long", "java.lang.String" };

		_methodName74 = "getLastreporterJIRAIssue";

		_methodParameterTypes74 = new String[] { "long", "java.lang.String" };

		_methodName75 = "getReporterJIRAIssues";

		_methodParameterTypes75 = new String[] {
				"java.util.Date", "long", "java.lang.String", "int", "int"
			};

		_methodName76 = "getReporterJIRAIssues";

		_methodParameterTypes76 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName77 = "getReporterJIRAIssues";

		_methodParameterTypes77 = new String[] {
				"long", "java.lang.String", "java.lang.String", "int", "int"
			};

		_methodName78 = "getReporterJIRAIssuesCount";

		_methodParameterTypes78 = new String[] {
				"java.util.Date", "long", "java.lang.String"
			};

		_methodName79 = "getReporterJIRAIssuesCount";

		_methodParameterTypes79 = new String[] { "long", "java.lang.String" };

		_methodName80 = "getReporterJIRAIssuesCount";

		_methodParameterTypes80 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName81 = "updateJIRAIssues";

		_methodParameterTypes81 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.addJIRAIssue((com.liferay.socialcoding.model.JIRAIssue)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.createJIRAIssue(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.deleteJIRAIssue(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.deleteJIRAIssue((com.liferay.socialcoding.model.JIRAIssue)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.fetchJIRAIssue(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getJIRAIssue(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getJIRAIssues(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getJIRAIssuesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.updateJIRAIssue((com.liferay.socialcoding.model.JIRAIssue)arguments[0]);
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			JIRAIssueLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssues((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssues(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssues(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getFirstAssigneeJIRAIssue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getFirstReporterJIRAIssue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getJIRAIssue(((Long)arguments[0]).longValue());
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getJIRAIssue((java.lang.String)arguments[0]);
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getLastAssigneeJIRAIssue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getLastreporterJIRAIssue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssues((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssues(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssues(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			JIRAIssueLocalServiceUtil.updateJIRAIssues(((Long)arguments[0]).longValue());

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
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
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
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
}