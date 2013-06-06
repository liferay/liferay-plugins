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

package com.liferay.opensocial.service.base;

import com.liferay.opensocial.service.OAuthConsumerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OAuthConsumerLocalServiceClpInvoker {
	public OAuthConsumerLocalServiceClpInvoker() {
		_methodName0 = "addOAuthConsumer";

		_methodParameterTypes0 = new String[] {
				"com.liferay.opensocial.model.OAuthConsumer"
			};

		_methodName1 = "createOAuthConsumer";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteOAuthConsumer";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteOAuthConsumer";

		_methodParameterTypes3 = new String[] {
				"com.liferay.opensocial.model.OAuthConsumer"
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

		_methodName9 = "fetchOAuthConsumer";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getOAuthConsumer";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getOAuthConsumers";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getOAuthConsumersCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateOAuthConsumer";

		_methodParameterTypes14 = new String[] {
				"com.liferay.opensocial.model.OAuthConsumer"
			};

		_methodName41 = "getBeanIdentifier";

		_methodParameterTypes41 = new String[] {  };

		_methodName42 = "setBeanIdentifier";

		_methodParameterTypes42 = new String[] { "java.lang.String" };

		_methodName47 = "addOAuthConsumer";

		_methodParameterTypes47 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};

		_methodName48 = "deleteOAuthConsumer";

		_methodParameterTypes48 = new String[] { "long" };

		_methodName49 = "deleteOAuthConsumer";

		_methodParameterTypes49 = new String[] {
				"com.liferay.opensocial.model.OAuthConsumer"
			};

		_methodName50 = "deleteOAuthConsumers";

		_methodParameterTypes50 = new String[] { "java.lang.String" };

		_methodName51 = "fetchOAuthConsumer";

		_methodParameterTypes51 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName52 = "getOAuthConsumer";

		_methodParameterTypes52 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName53 = "getOAuthConsumers";

		_methodParameterTypes53 = new String[] { "java.lang.String" };

		_methodName54 = "getOAuthConsumers";

		_methodParameterTypes54 = new String[] { "java.lang.String", "int", "int" };

		_methodName55 = "getOAuthConsumersCount";

		_methodParameterTypes55 = new String[] { "java.lang.String" };

		_methodName56 = "updateOAuthConsumer";

		_methodParameterTypes56 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.addOAuthConsumer((com.liferay.opensocial.model.OAuthConsumer)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.createOAuthConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.deleteOAuthConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.deleteOAuthConsumer((com.liferay.opensocial.model.OAuthConsumer)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.fetchOAuthConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getOAuthConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getOAuthConsumers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getOAuthConsumersCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.updateOAuthConsumer((com.liferay.opensocial.model.OAuthConsumer)arguments[0]);
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			OAuthConsumerLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.addOAuthConsumer(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5]);
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.deleteOAuthConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.deleteOAuthConsumer((com.liferay.opensocial.model.OAuthConsumer)arguments[0]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			OAuthConsumerLocalServiceUtil.deleteOAuthConsumers((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.fetchOAuthConsumer((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getOAuthConsumer((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getOAuthConsumers((java.lang.String)arguments[0]);
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getOAuthConsumers((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.getOAuthConsumersCount((java.lang.String)arguments[0]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return OAuthConsumerLocalServiceUtil.updateOAuthConsumer(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5]);
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
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
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
}