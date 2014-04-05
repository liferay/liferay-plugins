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

import com.liferay.portal.workflow.kaleo.service.KaleoNotificationRecipientLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoNotificationRecipientLocalServiceClpInvoker {
	public KaleoNotificationRecipientLocalServiceClpInvoker() {
		_methodName0 = "addKaleoNotificationRecipient";

		_methodParameterTypes0 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"
			};

		_methodName1 = "createKaleoNotificationRecipient";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteKaleoNotificationRecipient";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteKaleoNotificationRecipient";

		_methodParameterTypes3 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"
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

		_methodName10 = "fetchKaleoNotificationRecipient";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getKaleoNotificationRecipient";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getKaleoNotificationRecipients";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getKaleoNotificationRecipientsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateKaleoNotificationRecipient";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient"
			};

		_methodName108 = "getBeanIdentifier";

		_methodParameterTypes108 = new String[] {  };

		_methodName109 = "setBeanIdentifier";

		_methodParameterTypes109 = new String[] { "java.lang.String" };

		_methodName114 = "addKaleoNotificationRecipient";

		_methodParameterTypes114 = new String[] {
				"long", "long",
				"com.liferay.portal.workflow.kaleo.definition.Recipient",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName115 = "deleteCompanyKaleoNotificationRecipients";

		_methodParameterTypes115 = new String[] { "long" };

		_methodName116 = "deleteKaleoDefinitionKaleoNotificationRecipients";

		_methodParameterTypes116 = new String[] { "long" };

		_methodName117 = "getKaleoNotificationRecipients";

		_methodParameterTypes117 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.addKaleoNotificationRecipient((com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.createKaleoNotificationRecipient(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.deleteKaleoNotificationRecipient(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.deleteKaleoNotificationRecipient((com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.fetchKaleoNotificationRecipient(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.getKaleoNotificationRecipient(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.getKaleoNotificationRecipients(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.getKaleoNotificationRecipientsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.updateKaleoNotificationRecipient((com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)arguments[0]);
		}

		if (_methodName108.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes108, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName109.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes109, parameterTypes)) {
			KaleoNotificationRecipientLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName114.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes114, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.addKaleoNotificationRecipient(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.workflow.kaleo.definition.Recipient)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName115.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes115, parameterTypes)) {
			KaleoNotificationRecipientLocalServiceUtil.deleteCompanyKaleoNotificationRecipients(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName116.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes116, parameterTypes)) {
			KaleoNotificationRecipientLocalServiceUtil.deleteKaleoDefinitionKaleoNotificationRecipients(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName117.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes117, parameterTypes)) {
			return KaleoNotificationRecipientLocalServiceUtil.getKaleoNotificationRecipients(((Long)arguments[0]).longValue());
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
	private String _methodName108;
	private String[] _methodParameterTypes108;
	private String _methodName109;
	private String[] _methodParameterTypes109;
	private String _methodName114;
	private String[] _methodParameterTypes114;
	private String _methodName115;
	private String[] _methodParameterTypes115;
	private String _methodName116;
	private String[] _methodParameterTypes116;
	private String _methodName117;
	private String[] _methodParameterTypes117;
}