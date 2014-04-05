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

package com.liferay.marketplace.service.base;

import com.liferay.marketplace.service.AppLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Ryan Park
 * @generated
 */
public class AppLocalServiceClpInvoker {
	public AppLocalServiceClpInvoker() {
		_methodName0 = "addApp";

		_methodParameterTypes0 = new String[] {
				"com.liferay.marketplace.model.App"
			};

		_methodName1 = "createApp";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteApp";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteApp";

		_methodParameterTypes3 = new String[] {
				"com.liferay.marketplace.model.App"
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

		_methodName10 = "fetchApp";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchAppByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "getApp";

		_methodParameterTypes12 = new String[] { "long" };

		_methodName13 = "getPersistedModel";

		_methodParameterTypes13 = new String[] { "java.io.Serializable" };

		_methodName14 = "getAppByUuidAndCompanyId";

		_methodParameterTypes14 = new String[] { "java.lang.String", "long" };

		_methodName15 = "getApps";

		_methodParameterTypes15 = new String[] { "int", "int" };

		_methodName16 = "getAppsCount";

		_methodParameterTypes16 = new String[] {  };

		_methodName17 = "updateApp";

		_methodParameterTypes17 = new String[] {
				"com.liferay.marketplace.model.App"
			};

		_methodName46 = "getBeanIdentifier";

		_methodParameterTypes46 = new String[] {  };

		_methodName47 = "setBeanIdentifier";

		_methodParameterTypes47 = new String[] { "java.lang.String" };

		_methodName52 = "clearInstalledAppsCache";

		_methodParameterTypes52 = new String[] {  };

		_methodName53 = "deleteApp";

		_methodParameterTypes53 = new String[] {
				"com.liferay.marketplace.model.App"
			};

		_methodName54 = "deleteApp";

		_methodParameterTypes54 = new String[] { "long" };

		_methodName55 = "fetchRemoteApp";

		_methodParameterTypes55 = new String[] { "long" };

		_methodName56 = "getApps";

		_methodParameterTypes56 = new String[] { "java.lang.String" };

		_methodName57 = "getBundledApps";

		_methodParameterTypes57 = new String[] {  };

		_methodName58 = "getInstalledApps";

		_methodParameterTypes58 = new String[] {  };

		_methodName59 = "installApp";

		_methodParameterTypes59 = new String[] { "long" };

		_methodName60 = "processMarketplaceProperties";

		_methodParameterTypes60 = new String[] { "java.util.Properties" };

		_methodName61 = "uninstallApp";

		_methodParameterTypes61 = new String[] { "long" };

		_methodName62 = "updateApp";

		_methodParameterTypes62 = new String[] {
				"long", "long", "java.lang.String", "java.io.File"
			};

		_methodName63 = "updateApp";

		_methodParameterTypes63 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.io.File"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AppLocalServiceUtil.addApp((com.liferay.marketplace.model.App)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AppLocalServiceUtil.createApp(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AppLocalServiceUtil.deleteApp(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AppLocalServiceUtil.deleteApp((com.liferay.marketplace.model.App)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AppLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AppLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AppLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AppLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AppLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AppLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AppLocalServiceUtil.fetchApp(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AppLocalServiceUtil.fetchAppByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AppLocalServiceUtil.getApp(((Long)arguments[0]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AppLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AppLocalServiceUtil.getAppByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AppLocalServiceUtil.getApps(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return AppLocalServiceUtil.getAppsCount();
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return AppLocalServiceUtil.updateApp((com.liferay.marketplace.model.App)arguments[0]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return AppLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			AppLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			AppLocalServiceUtil.clearInstalledAppsCache();

			return null;
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return AppLocalServiceUtil.deleteApp((com.liferay.marketplace.model.App)arguments[0]);
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return AppLocalServiceUtil.deleteApp(((Long)arguments[0]).longValue());
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return AppLocalServiceUtil.fetchRemoteApp(((Long)arguments[0]).longValue());
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return AppLocalServiceUtil.getApps((java.lang.String)arguments[0]);
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return AppLocalServiceUtil.getBundledApps();
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return AppLocalServiceUtil.getInstalledApps();
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			AppLocalServiceUtil.installApp(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			AppLocalServiceUtil.processMarketplaceProperties((java.util.Properties)arguments[0]);

			return null;
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			AppLocalServiceUtil.uninstallApp(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return AppLocalServiceUtil.updateApp(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.io.File)arguments[3]);
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return AppLocalServiceUtil.updateApp(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.io.File)arguments[7]);
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
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
}