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

package com.liferay.bbb.service.base;

import com.liferay.bbb.service.BBBMeetingLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Shinn Lok
 * @generated
 */
public class BBBMeetingLocalServiceClpInvoker {
	public BBBMeetingLocalServiceClpInvoker() {
		_methodName0 = "addBBBMeeting";

		_methodParameterTypes0 = new String[] { "com.liferay.bbb.model.BBBMeeting" };

		_methodName1 = "createBBBMeeting";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteBBBMeeting";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteBBBMeeting";

		_methodParameterTypes3 = new String[] { "com.liferay.bbb.model.BBBMeeting" };

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

		_methodName10 = "fetchBBBMeeting";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getBBBMeeting";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getActionableDynamicQuery";

		_methodParameterTypes12 = new String[] {  };

		_methodName14 = "deletePersistedModel";

		_methodParameterTypes14 = new String[] {
				"com.liferay.portal.model.PersistedModel"
			};

		_methodName15 = "getPersistedModel";

		_methodParameterTypes15 = new String[] { "java.io.Serializable" };

		_methodName16 = "getBBBMeetings";

		_methodParameterTypes16 = new String[] { "int", "int" };

		_methodName17 = "getBBBMeetingsCount";

		_methodParameterTypes17 = new String[] {  };

		_methodName18 = "updateBBBMeeting";

		_methodParameterTypes18 = new String[] {
				"com.liferay.bbb.model.BBBMeeting"
			};

		_methodName53 = "getBeanIdentifier";

		_methodParameterTypes53 = new String[] {  };

		_methodName54 = "setBeanIdentifier";

		_methodParameterTypes54 = new String[] { "java.lang.String" };

		_methodName59 = "addBBBMeeting";

		_methodParameterTypes59 = new String[] {
				"long", "long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName60 = "deleteBBBMeeting";

		_methodParameterTypes60 = new String[] {
				"com.liferay.bbb.model.BBBMeeting"
			};

		_methodName61 = "deleteBBBMeeting";

		_methodParameterTypes61 = new String[] { "long" };

		_methodName62 = "getBBBMeeting";

		_methodParameterTypes62 = new String[] { "long" };

		_methodName63 = "getBBBMeetings";

		_methodParameterTypes63 = new String[] { "int" };

		_methodName64 = "getBBBMeetings";

		_methodParameterTypes64 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName65 = "getBBBMeetings";

		_methodParameterTypes65 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String", "int",
				"boolean", "int", "int", "java.lang.String", "java.lang.String"
			};

		_methodName66 = "getBBBMeetingsCount";

		_methodParameterTypes66 = new String[] { "long" };

		_methodName67 = "getBBBMeetingsCount";

		_methodParameterTypes67 = new String[] { "long", "int" };

		_methodName68 = "getBBBMeetingsCount";

		_methodParameterTypes68 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String", "int",
				"boolean"
			};

		_methodName69 = "updateBBBMeeting";

		_methodParameterTypes69 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.util.List",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName70 = "updateStatus";

		_methodParameterTypes70 = new String[] { "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.addBBBMeeting((com.liferay.bbb.model.BBBMeeting)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.createBBBMeeting(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.deleteBBBMeeting(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.deleteBBBMeeting((com.liferay.bbb.model.BBBMeeting)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.fetchBBBMeeting(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeeting(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.deletePersistedModel((com.liferay.portal.model.PersistedModel)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeetings(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeetingsCount();
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.updateBBBMeeting((com.liferay.bbb.model.BBBMeeting)arguments[0]);
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			BBBMeetingLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.addBBBMeeting(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				((Integer)arguments[7]).intValue(),
				(java.util.List<com.liferay.bbb.model.BBBParticipant>)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.deleteBBBMeeting((com.liferay.bbb.model.BBBMeeting)arguments[0]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.deleteBBBMeeting(((Long)arguments[0]).longValue());
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeeting(((Long)arguments[0]).longValue());
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeetings(((Integer)arguments[0]).intValue());
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeetings(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.bbb.model.BBBMeeting>)arguments[3]);
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeetings(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Boolean)arguments[5]).booleanValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				(java.lang.String)arguments[8], (java.lang.String)arguments[9]);
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeetingsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeetingsCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.getBBBMeetingsCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.updateBBBMeeting(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.util.List<com.liferay.bbb.model.BBBParticipant>)arguments[6],
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return BBBMeetingLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
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
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
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
}