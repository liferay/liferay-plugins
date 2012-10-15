/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.oauth.service.base;

import com.liferay.portal.oauth.service.ApplicationUserLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class ApplicationUserLocalServiceClpInvoker {
	public ApplicationUserLocalServiceClpInvoker() {
		_methodName0 = "addApplicationUser";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.oauth.model.ApplicationUser"
			};

		_methodName1 = "createApplicationUser";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteApplicationUser";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteApplicationUser";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.oauth.model.ApplicationUser"
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

		_methodName9 = "fetchApplicationUser";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getApplicationUser";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getApplicationUsers";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getApplicationUsersCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateApplicationUser";

		_methodParameterTypes14 = new String[] {
				"com.liferay.portal.oauth.model.ApplicationUser"
			};

		_methodName15 = "updateApplicationUser";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.oauth.model.ApplicationUser", "boolean"
			};

		_methodName38 = "getBeanIdentifier";

		_methodParameterTypes38 = new String[] {  };

		_methodName39 = "setBeanIdentifier";

		_methodParameterTypes39 = new String[] { "java.lang.String" };

		_methodName44 = "addApplicationUser";

		_methodParameterTypes44 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"boolean", "com.liferay.portal.service.ServiceContext"
			};

		_methodName45 = "deleteApplicationUser";

		_methodParameterTypes45 = new String[] {
				"long", "long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName46 = "deleteApplicationUser";

		_methodParameterTypes46 = new String[] {
				"com.liferay.portal.oauth.model.ApplicationUser",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName47 = "getApplicationUserByAccessToken";

		_methodParameterTypes47 = new String[] { "java.lang.String" };

		_methodName48 = "getApplicationUsers";

		_methodParameterTypes48 = new String[] { "long" };

		_methodName49 = "getApplicationUsers";

		_methodParameterTypes49 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName50 = "getApplicationUsersByUserId";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "getApplicationUsersByUserId";

		_methodParameterTypes51 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName52 = "getApplicationUsersByUserIdCount";

		_methodParameterTypes52 = new String[] { "long" };

		_methodName53 = "getApplicationUsersCount";

		_methodParameterTypes53 = new String[] { "long" };

		_methodName54 = "getAuthorizedApplicationUsersByOwnerId";

		_methodParameterTypes54 = new String[] {
				"long", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName55 = "getAuthorizedApplicationUsersByOwnerIdCount";

		_methodParameterTypes55 = new String[] { "long", "boolean" };

		_methodName56 = "getAuthorizedApplicationUsersByUserId";

		_methodParameterTypes56 = new String[] { "long", "boolean" };

		_methodName57 = "getAuthorizedApplicationUsersByUserId";

		_methodParameterTypes57 = new String[] {
				"long", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName58 = "getAuthorizedApplicationUsersByUserIdCount";

		_methodParameterTypes58 = new String[] { "long", "boolean" };

		_methodName59 = "updateApplicationUser";

		_methodParameterTypes59 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName60 = "updateAuthorized";

		_methodParameterTypes60 = new String[] {
				"long", "long", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.addApplicationUser((com.liferay.portal.oauth.model.ApplicationUser)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.createApplicationUser(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.deleteApplicationUser(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.deleteApplicationUser((com.liferay.portal.oauth.model.ApplicationUser)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.fetchApplicationUser(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUser(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUsers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUsersCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.updateApplicationUser((com.liferay.portal.oauth.model.ApplicationUser)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.updateApplicationUser((com.liferay.portal.oauth.model.ApplicationUser)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			ApplicationUserLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.addApplicationUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.deleteApplicationUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.deleteApplicationUser((com.liferay.portal.oauth.model.ApplicationUser)arguments[0],
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUserByAccessToken((java.lang.String)arguments[0]);
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUsers(((Long)arguments[0]).longValue());
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUsers(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUsersByUserId(((Long)arguments[0]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUsersByUserId(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUsersByUserIdCount(((Long)arguments[0]).longValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getApplicationUsersCount(((Long)arguments[0]).longValue());
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByOwnerId(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByOwnerIdCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByUserId(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByUserId(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.getAuthorizedApplicationUsersByUserIdCount(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.updateApplicationUser(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return ApplicationUserLocalServiceUtil.updateAuthorized(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
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
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
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
}