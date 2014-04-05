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

import com.liferay.portal.workflow.kaleo.service.KaleoTaskInstanceTokenLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskInstanceTokenLocalServiceClpInvoker {
	public KaleoTaskInstanceTokenLocalServiceClpInvoker() {
		_methodName0 = "addKaleoTaskInstanceToken";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken"
			};

		_methodName1 = "createKaleoTaskInstanceToken";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteKaleoTaskInstanceToken";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteKaleoTaskInstanceToken";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken"
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

		_methodName9 = "fetchKaleoTaskInstanceToken";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getKaleoTaskInstanceToken";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getKaleoTaskInstanceTokens";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getKaleoTaskInstanceTokensCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateKaleoTaskInstanceToken";

		_methodParameterTypes14 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken"
			};

		_methodName15 = "updateKaleoTaskInstanceToken";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"boolean"
			};

		_methodName106 = "getBeanIdentifier";

		_methodParameterTypes106 = new String[] {  };

		_methodName107 = "setBeanIdentifier";

		_methodParameterTypes107 = new String[] { "java.lang.String" };

		_methodName112 = "addKaleoTaskInstanceToken";

		_methodParameterTypes112 = new String[] {
				"long", "long", "java.lang.String", "java.util.Collection",
				"java.util.Date", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName113 = "assignKaleoTaskInstanceToken";

		_methodParameterTypes113 = new String[] {
				"long", "java.lang.String", "long", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName114 = "completeKaleoTaskInstanceToken";

		_methodParameterTypes114 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName115 = "deleteCompanyKaleoTaskInstanceTokens";

		_methodParameterTypes115 = new String[] { "long" };

		_methodName116 = "deleteKaleoDefinitionKaleoTaskInstanceTokens";

		_methodParameterTypes116 = new String[] { "long" };

		_methodName117 = "deleteKaleoInstanceKaleoTaskInstanceTokens";

		_methodParameterTypes117 = new String[] { "long" };

		_methodName118 = "getCompanyKaleoTaskInstanceTokens";

		_methodParameterTypes118 = new String[] { "long", "int", "int" };

		_methodName119 = "getCompanyKaleoTaskInstanceTokensCount";

		_methodParameterTypes119 = new String[] { "long" };

		_methodName120 = "getKaleoTaskInstanceTokens";

		_methodParameterTypes120 = new String[] {
				"java.lang.Boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName121 = "getKaleoTaskInstanceTokens";

		_methodParameterTypes121 = new String[] {
				"java.util.List", "java.lang.Boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName122 = "getKaleoTaskInstanceTokens";

		_methodParameterTypes122 = new String[] {
				"long", "java.lang.Boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName123 = "getKaleoTaskInstanceTokens";

		_methodParameterTypes123 = new String[] { "long", "long" };

		_methodName124 = "getKaleoTaskInstanceTokens";

		_methodParameterTypes124 = new String[] {
				"java.lang.String", "long", "java.lang.Boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName125 = "getKaleoTaskInstanceTokensCount";

		_methodParameterTypes125 = new String[] {
				"java.lang.Boolean", "com.liferay.portal.service.ServiceContext"
			};

		_methodName126 = "getKaleoTaskInstanceTokensCount";

		_methodParameterTypes126 = new String[] {
				"java.util.List", "java.lang.Boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName127 = "getKaleoTaskInstanceTokensCount";

		_methodParameterTypes127 = new String[] {
				"long", "java.lang.Boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName128 = "getKaleoTaskInstanceTokensCount";

		_methodParameterTypes128 = new String[] {
				"java.lang.String", "long", "java.lang.Boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName129 = "getSubmittingUserKaleoTaskInstanceTokens";

		_methodParameterTypes129 = new String[] {
				"long", "java.lang.Boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName130 = "getSubmittingUserKaleoTaskInstanceTokensCount";

		_methodParameterTypes130 = new String[] {
				"long", "java.lang.Boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName131 = "search";

		_methodParameterTypes131 = new String[] {
				"java.lang.String", "java.lang.Boolean", "java.lang.Boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName132 = "search";

		_methodParameterTypes132 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.Long[][]",
				"java.util.Date", "java.util.Date", "java.lang.Boolean",
				"java.lang.Boolean", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName133 = "search";

		_methodParameterTypes133 = new String[] {
				"java.lang.String", "java.lang.String[][]", "java.lang.Boolean",
				"java.lang.Boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName134 = "search";

		_methodParameterTypes134 = new String[] {
				"java.lang.String", "java.lang.String[][]", "java.lang.Long[][]",
				"java.util.Date", "java.util.Date", "java.lang.Boolean",
				"java.lang.Boolean", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName135 = "searchCount";

		_methodParameterTypes135 = new String[] {
				"java.lang.String", "java.lang.Boolean", "java.lang.Boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName136 = "searchCount";

		_methodParameterTypes136 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.Long[][]",
				"java.util.Date", "java.util.Date", "java.lang.Boolean",
				"java.lang.Boolean", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName137 = "searchCount";

		_methodParameterTypes137 = new String[] {
				"java.lang.String", "java.lang.String[][]", "java.lang.Boolean",
				"java.lang.Boolean", "com.liferay.portal.service.ServiceContext"
			};

		_methodName138 = "searchCount";

		_methodParameterTypes138 = new String[] {
				"java.lang.String", "java.lang.String[][]", "java.lang.Long[][]",
				"java.util.Date", "java.util.Date", "java.lang.Boolean",
				"java.lang.Boolean", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName139 = "updateDueDate";

		_methodParameterTypes139 = new String[] {
				"long", "java.util.Date",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.addKaleoTaskInstanceToken((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.createKaleoTaskInstanceToken(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.deleteKaleoTaskInstanceToken(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.deleteKaleoTaskInstanceToken((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.fetchKaleoTaskInstanceToken(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceToken(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokens(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokensCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.updateKaleoTaskInstanceToken((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.updateKaleoTaskInstanceToken((com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			KaleoTaskInstanceTokenLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName112.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes112, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.addKaleoTaskInstanceToken(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment>)arguments[3],
				(java.util.Date)arguments[4],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName113.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes113, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.assignKaleoTaskInstanceToken(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.completeKaleoTaskInstanceToken(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			KaleoTaskInstanceTokenLocalServiceUtil.deleteCompanyKaleoTaskInstanceTokens(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			KaleoTaskInstanceTokenLocalServiceUtil.deleteKaleoDefinitionKaleoTaskInstanceTokens(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			KaleoTaskInstanceTokenLocalServiceUtil.deleteKaleoInstanceKaleoTaskInstanceTokens(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName118.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes118, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getCompanyKaleoTaskInstanceTokens(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName119.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes119, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getCompanyKaleoTaskInstanceTokensCount(((Long)arguments[0]).longValue());
		}

		if (_methodName120.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes120, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokens((java.lang.Boolean)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName121.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes121, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokens((java.util.List<java.lang.Long>)arguments[0],
				(java.lang.Boolean)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName122.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes122, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokens(((Long)arguments[0]).longValue(),
				(java.lang.Boolean)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName123.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes123, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokens(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName124.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes124, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokens((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.Boolean)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName125.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes125, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokensCount((java.lang.Boolean)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName126.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes126, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokensCount((java.util.List<java.lang.Long>)arguments[0],
				(java.lang.Boolean)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName127.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes127, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokensCount(((Long)arguments[0]).longValue(),
				(java.lang.Boolean)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName128.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes128, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getKaleoTaskInstanceTokensCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.Boolean)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName129.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes129, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getSubmittingUserKaleoTaskInstanceTokens(((Long)arguments[0]).longValue(),
				(java.lang.Boolean)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName130.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes130, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.getSubmittingUserKaleoTaskInstanceTokensCount(((Long)arguments[0]).longValue(),
				(java.lang.Boolean)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName131.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes131, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.Boolean)arguments[1],
				(java.lang.Boolean)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5],
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName132.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes132, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.Long[])arguments[2],
				(java.util.Date)arguments[3], (java.util.Date)arguments[4],
				(java.lang.Boolean)arguments[5],
				(java.lang.Boolean)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[10],
				(com.liferay.portal.service.ServiceContext)arguments[11]);
		}

		if (_methodName133.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes133, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String[])arguments[1],
				(java.lang.Boolean)arguments[2],
				(java.lang.Boolean)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName134.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes134, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String[])arguments[1],
				(java.lang.Long[])arguments[2], (java.util.Date)arguments[3],
				(java.util.Date)arguments[4], (java.lang.Boolean)arguments[5],
				(java.lang.Boolean)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[10],
				(com.liferay.portal.service.ServiceContext)arguments[11]);
		}

		if (_methodName135.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes135, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.Boolean)arguments[1],
				(java.lang.Boolean)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName136.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes136, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.Long[])arguments[2],
				(java.util.Date)arguments[3], (java.util.Date)arguments[4],
				(java.lang.Boolean)arguments[5],
				(java.lang.Boolean)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName137.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes137, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.String[])arguments[1],
				(java.lang.Boolean)arguments[2],
				(java.lang.Boolean)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName138.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes138, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.String[])arguments[1],
				(java.lang.Long[])arguments[2], (java.util.Date)arguments[3],
				(java.util.Date)arguments[4], (java.lang.Boolean)arguments[5],
				(java.lang.Boolean)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName139.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes139, parameterTypes)) {
			return KaleoTaskInstanceTokenLocalServiceUtil.updateDueDate(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);
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
	private String _methodName106;
	private String[] _methodParameterTypes106;
	private String _methodName107;
	private String[] _methodParameterTypes107;
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
	private String _methodName127;
	private String[] _methodParameterTypes127;
	private String _methodName128;
	private String[] _methodParameterTypes128;
	private String _methodName129;
	private String[] _methodParameterTypes129;
	private String _methodName130;
	private String[] _methodParameterTypes130;
	private String _methodName131;
	private String[] _methodParameterTypes131;
	private String _methodName132;
	private String[] _methodParameterTypes132;
	private String _methodName133;
	private String[] _methodParameterTypes133;
	private String _methodName134;
	private String[] _methodParameterTypes134;
	private String _methodName135;
	private String[] _methodParameterTypes135;
	private String _methodName136;
	private String[] _methodParameterTypes136;
	private String _methodName137;
	private String[] _methodParameterTypes137;
	private String _methodName138;
	private String[] _methodParameterTypes138;
	private String _methodName139;
	private String[] _methodParameterTypes139;
}