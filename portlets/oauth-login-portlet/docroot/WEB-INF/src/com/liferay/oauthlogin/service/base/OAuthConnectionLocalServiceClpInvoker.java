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

package com.liferay.oauthlogin.service.base;

import com.liferay.oauthlogin.service.OAuthConnectionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Andy Yang
 * @generated
 */
public class OAuthConnectionLocalServiceClpInvoker {
	public OAuthConnectionLocalServiceClpInvoker() {
		_methodName0 = "addOAuthConnection";

		_methodParameterTypes0 = new String[] {
				"com.liferay.oauthlogin.model.OAuthConnection"
			};

		_methodName1 = "createOAuthConnection";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteOAuthConnection";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteOAuthConnection";

		_methodParameterTypes3 = new String[] {
				"com.liferay.oauthlogin.model.OAuthConnection"
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

		_methodName10 = "fetchOAuthConnection";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getOAuthConnection";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getOAuthConnections";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getOAuthConnectionsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateOAuthConnection";

		_methodParameterTypes15 = new String[] {
				"com.liferay.oauthlogin.model.OAuthConnection"
			};

		_methodName38 = "getBeanIdentifier";

		_methodParameterTypes38 = new String[] {  };

		_methodName39 = "setBeanIdentifier";

		_methodParameterTypes39 = new String[] { "java.lang.String" };

		_methodName44 = "addOAuthConnection";

		_methodParameterTypes44 = new String[] {
				"boolean", "java.lang.String", "java.lang.String", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int", "int",
				"java.lang.String", "int", "int", "java.lang.String",
				"java.lang.String", "int", "java.lang.String", "int",
				"java.lang.String", "java.io.File",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName45 = "deleteOAuthConnection";

		_methodParameterTypes45 = new String[] { "long" };

		_methodName46 = "getOAuthConnectionsEnabled";

		_methodParameterTypes46 = new String[] { "boolean" };

		_methodName47 = "updateOAuthConnection";

		_methodParameterTypes47 = new String[] {
				"long", "boolean", "java.lang.String", "java.lang.String", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int", "int",
				"java.lang.String", "int", "int", "java.lang.String",
				"java.lang.String", "int", "java.lang.String", "int",
				"java.lang.String", "java.io.File",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.addOAuthConnection((com.liferay.oauthlogin.model.OAuthConnection)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.createOAuthConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.deleteOAuthConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.deleteOAuthConnection((com.liferay.oauthlogin.model.OAuthConnection)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.fetchOAuthConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.getOAuthConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.getOAuthConnections(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.getOAuthConnectionsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.updateOAuthConnection((com.liferay.oauthlogin.model.OAuthConnection)arguments[0]);
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			OAuthConnectionLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.addOAuthConnection(((Boolean)arguments[0]).booleanValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8],
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				(java.lang.String)arguments[14],
				(java.lang.String)arguments[15],
				((Integer)arguments[16]).intValue(),
				(java.lang.String)arguments[17],
				((Integer)arguments[18]).intValue(),
				(java.lang.String)arguments[19], (java.io.File)arguments[20],
				(com.liferay.portal.service.ServiceContext)arguments[21]);
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.deleteOAuthConnection(((Long)arguments[0]).longValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.getOAuthConnectionsEnabled(((Boolean)arguments[0]).booleanValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return OAuthConnectionLocalServiceUtil.updateOAuthConnection(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.lang.String)arguments[9],
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				(java.lang.String)arguments[12],
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				((Integer)arguments[17]).intValue(),
				(java.lang.String)arguments[18],
				((Integer)arguments[19]).intValue(),
				(java.lang.String)arguments[20], (java.io.File)arguments[21],
				(com.liferay.portal.service.ServiceContext)arguments[22]);
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
}