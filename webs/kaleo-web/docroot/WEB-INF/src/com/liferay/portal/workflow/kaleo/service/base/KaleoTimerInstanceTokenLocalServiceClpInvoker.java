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

import com.liferay.portal.workflow.kaleo.service.KaleoTimerInstanceTokenLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoTimerInstanceTokenLocalServiceClpInvoker {
	public KaleoTimerInstanceTokenLocalServiceClpInvoker() {
		_methodName0 = "addKaleoTimerInstanceToken";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken"
			};

		_methodName1 = "createKaleoTimerInstanceToken";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteKaleoTimerInstanceToken";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteKaleoTimerInstanceToken";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken"
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

		_methodName9 = "fetchKaleoTimerInstanceToken";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getKaleoTimerInstanceToken";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getKaleoTimerInstanceTokens";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getKaleoTimerInstanceTokensCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateKaleoTimerInstanceToken";

		_methodParameterTypes14 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken"
			};

		_methodName95 = "getBeanIdentifier";

		_methodParameterTypes95 = new String[] {  };

		_methodName96 = "setBeanIdentifier";

		_methodParameterTypes96 = new String[] { "java.lang.String" };

		_methodName101 = "addKaleoTimerInstanceToken";

		_methodParameterTypes101 = new String[] {
				"long", "long", "long", "java.lang.String", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName102 = "addKaleoTimerInstanceTokens";

		_methodParameterTypes102 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken",
				"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken",
				"java.util.Collection", "java.util.Map",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName103 = "completeKaleoTimerInstanceToken";

		_methodParameterTypes103 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName104 = "completeKaleoTimerInstanceTokens";

		_methodParameterTypes104 = new String[] {
				"java.util.List", "com.liferay.portal.service.ServiceContext"
			};

		_methodName105 = "completeKaleoTimerInstanceTokens";

		_methodParameterTypes105 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName106 = "deleteKaleoTimerInstanceToken";

		_methodParameterTypes106 = new String[] { "long", "long" };

		_methodName107 = "deleteKaleoTimerInstanceTokens";

		_methodParameterTypes107 = new String[] { "long" };

		_methodName108 = "getKaleoTimerInstanceToken";

		_methodParameterTypes108 = new String[] { "long", "long" };

		_methodName109 = "getKaleoTimerInstanceTokens";

		_methodParameterTypes109 = new String[] {
				"long", "boolean", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName110 = "getKaleoTimerInstanceTokensCount";

		_methodParameterTypes110 = new String[] {
				"long", "boolean", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.addKaleoTimerInstanceToken((com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.createKaleoTimerInstanceToken(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.deleteKaleoTimerInstanceToken(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.deleteKaleoTimerInstanceToken((com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.fetchKaleoTimerInstanceToken(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.getKaleoTimerInstanceToken(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.getKaleoTimerInstanceTokens(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.getKaleoTimerInstanceTokensCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.updateKaleoTimerInstanceToken((com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken)arguments[0]);
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			KaleoTimerInstanceTokenLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.addKaleoTimerInstanceToken(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.addKaleoTimerInstanceTokens((com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken)arguments[0],
				(com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)arguments[1],
				(java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTimer>)arguments[2],
				(java.util.Map<java.lang.String, java.io.Serializable>)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName103.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes103, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.completeKaleoTimerInstanceToken(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName104.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes104, parameterTypes)) {
			KaleoTimerInstanceTokenLocalServiceUtil.completeKaleoTimerInstanceTokens((java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken>)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);

			return null;
		}

		if (_methodName105.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes105, parameterTypes)) {
			KaleoTimerInstanceTokenLocalServiceUtil.completeKaleoTimerInstanceTokens(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);

			return null;
		}

		if (_methodName106.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes106, parameterTypes)) {
			KaleoTimerInstanceTokenLocalServiceUtil.deleteKaleoTimerInstanceToken(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName107.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes107, parameterTypes)) {
			KaleoTimerInstanceTokenLocalServiceUtil.deleteKaleoTimerInstanceTokens(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.getKaleoTimerInstanceToken(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.getKaleoTimerInstanceTokens(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName110.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes110, parameterTypes)) {
			return KaleoTimerInstanceTokenLocalServiceUtil.getKaleoTimerInstanceTokensCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Boolean)arguments[2]).booleanValue(),
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
}