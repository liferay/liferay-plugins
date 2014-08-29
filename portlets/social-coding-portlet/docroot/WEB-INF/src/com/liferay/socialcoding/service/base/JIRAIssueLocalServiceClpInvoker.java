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

package com.liferay.socialcoding.service.base;

import com.liferay.socialcoding.service.JIRAIssueLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
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

		_methodName15 = "updateJIRAIssue";

		_methodParameterTypes15 = new String[] {
				"com.liferay.socialcoding.model.JIRAIssue", "boolean"
			};

		_methodName72 = "getBeanIdentifier";

		_methodParameterTypes72 = new String[] {  };

		_methodName73 = "setBeanIdentifier";

		_methodParameterTypes73 = new String[] { "java.lang.String" };

		_methodName78 = "getAssigneeJIRAIssues";

		_methodParameterTypes78 = new String[] {
				"java.util.Date", "long", "java.lang.String", "int", "int"
			};

		_methodName79 = "getAssigneeJIRAIssues";

		_methodParameterTypes79 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName80 = "getAssigneeJIRAIssues";

		_methodParameterTypes80 = new String[] {
				"long", "java.lang.String", "java.lang.String", "int", "int"
			};

		_methodName81 = "getAssigneeJIRAIssuesCount";

		_methodParameterTypes81 = new String[] {
				"java.util.Date", "long", "java.lang.String"
			};

		_methodName82 = "getAssigneeJIRAIssuesCount";

		_methodParameterTypes82 = new String[] { "long", "java.lang.String" };

		_methodName83 = "getAssigneeJIRAIssuesCount";

		_methodParameterTypes83 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName84 = "getFirstAssigneeJIRAIssue";

		_methodParameterTypes84 = new String[] { "long", "java.lang.String" };

		_methodName85 = "getFirstReporterJIRAIssue";

		_methodParameterTypes85 = new String[] { "long", "java.lang.String" };

		_methodName86 = "getJIRAIssue";

		_methodParameterTypes86 = new String[] { "long" };

		_methodName87 = "getJIRAIssue";

		_methodParameterTypes87 = new String[] { "java.lang.String" };

		_methodName88 = "getLastAssigneeJIRAIssue";

		_methodParameterTypes88 = new String[] { "long", "java.lang.String" };

		_methodName89 = "getLastreporterJIRAIssue";

		_methodParameterTypes89 = new String[] { "long", "java.lang.String" };

		_methodName90 = "getReporterJIRAIssues";

		_methodParameterTypes90 = new String[] {
				"java.util.Date", "long", "java.lang.String", "int", "int"
			};

		_methodName91 = "getReporterJIRAIssues";

		_methodParameterTypes91 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName92 = "getReporterJIRAIssues";

		_methodParameterTypes92 = new String[] {
				"long", "java.lang.String", "java.lang.String", "int", "int"
			};

		_methodName93 = "getReporterJIRAIssuesCount";

		_methodParameterTypes93 = new String[] {
				"java.util.Date", "long", "java.lang.String"
			};

		_methodName94 = "getReporterJIRAIssuesCount";

		_methodParameterTypes94 = new String[] { "long", "java.lang.String" };

		_methodName95 = "getReporterJIRAIssuesCount";

		_methodParameterTypes95 = new String[] {
				"long", "java.lang.String", "java.lang.String"
			};

		_methodName96 = "updateJIRAIssues";

		_methodParameterTypes96 = new String[] { "long" };
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

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.updateJIRAIssue((com.liferay.socialcoding.model.JIRAIssue)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			JIRAIssueLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssues((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssues(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssues(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getAssigneeJIRAIssuesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getFirstAssigneeJIRAIssue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getFirstReporterJIRAIssue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getJIRAIssue(((Long)arguments[0]).longValue());
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getJIRAIssue((java.lang.String)arguments[0]);
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getLastAssigneeJIRAIssue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getLastreporterJIRAIssue(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssues((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssues(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssues(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return JIRAIssueLocalServiceUtil.getReporterJIRAIssuesCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
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
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
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
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
}