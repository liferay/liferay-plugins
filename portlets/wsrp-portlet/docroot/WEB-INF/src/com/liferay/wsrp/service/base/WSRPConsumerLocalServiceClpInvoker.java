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

package com.liferay.wsrp.service.base;

import com.liferay.wsrp.service.WSRPConsumerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerLocalServiceClpInvoker {
	public WSRPConsumerLocalServiceClpInvoker() {
		_methodName0 = "addWSRPConsumer";

		_methodParameterTypes0 = new String[] {
				"com.liferay.wsrp.model.WSRPConsumer"
			};

		_methodName1 = "createWSRPConsumer";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteWSRPConsumer";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteWSRPConsumer";

		_methodParameterTypes3 = new String[] {
				"com.liferay.wsrp.model.WSRPConsumer"
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

		_methodName9 = "fetchWSRPConsumer";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getWSRPConsumer";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getWSRPConsumers";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getWSRPConsumersCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateWSRPConsumer";

		_methodParameterTypes14 = new String[] {
				"com.liferay.wsrp.model.WSRPConsumer"
			};

		_methodName39 = "getBeanIdentifier";

		_methodParameterTypes39 = new String[] {  };

		_methodName40 = "setBeanIdentifier";

		_methodParameterTypes40 = new String[] { "java.lang.String" };

		_methodName45 = "addWSRPConsumer";

		_methodParameterTypes45 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName46 = "deleteWSRPConsumer";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "deleteWSRPConsumer";

		_methodParameterTypes47 = new String[] {
				"com.liferay.wsrp.model.WSRPConsumer"
			};

		_methodName48 = "getWSRPConsumer";

		_methodParameterTypes48 = new String[] { "java.lang.String" };

		_methodName49 = "getWSRPConsumers";

		_methodParameterTypes49 = new String[] { "long", "int", "int" };

		_methodName50 = "getWSRPConsumersCount";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "registerWSRPConsumer";

		_methodParameterTypes51 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.kernel.util.UnicodeProperties",
				"java.lang.String"
			};

		_methodName52 = "restartConsumer";

		_methodParameterTypes52 = new String[] { "long" };

		_methodName53 = "updateServiceDescription";

		_methodParameterTypes53 = new String[] { "long" };

		_methodName54 = "updateWSRPConsumer";

		_methodParameterTypes54 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.addWSRPConsumer((com.liferay.wsrp.model.WSRPConsumer)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.createWSRPConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.deleteWSRPConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.deleteWSRPConsumer((com.liferay.wsrp.model.WSRPConsumer)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.fetchWSRPConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.getWSRPConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.getWSRPConsumers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.getWSRPConsumersCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.updateWSRPConsumer((com.liferay.wsrp.model.WSRPConsumer)arguments[0]);
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			WSRPConsumerLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.addWSRPConsumer(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.deleteWSRPConsumer(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.deleteWSRPConsumer((com.liferay.wsrp.model.WSRPConsumer)arguments[0]);
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.getWSRPConsumer((java.lang.String)arguments[0]);
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.getWSRPConsumers(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.getWSRPConsumersCount(((Long)arguments[0]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.registerWSRPConsumer(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.kernel.util.UnicodeProperties)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			WSRPConsumerLocalServiceUtil.restartConsumer(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			WSRPConsumerLocalServiceUtil.updateServiceDescription(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return WSRPConsumerLocalServiceUtil.updateWSRPConsumer(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6]);
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
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
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
}