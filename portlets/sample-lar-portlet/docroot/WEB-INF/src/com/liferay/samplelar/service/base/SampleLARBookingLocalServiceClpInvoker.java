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

package com.liferay.samplelar.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.samplelar.service.SampleLARBookingLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Mate Thurzo
 * @generated
 */
@ProviderType
public class SampleLARBookingLocalServiceClpInvoker {
	public SampleLARBookingLocalServiceClpInvoker() {
		_methodName0 = "addSampleLARBooking";

		_methodParameterTypes0 = new String[] {
				"com.liferay.samplelar.model.SampleLARBooking"
			};

		_methodName1 = "createSampleLARBooking";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSampleLARBooking";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSampleLARBooking";

		_methodParameterTypes3 = new String[] {
				"com.liferay.samplelar.model.SampleLARBooking"
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

		_methodName10 = "fetchSampleLARBooking";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchSampleLARBookingByUuidAndGroupId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "getSampleLARBooking";

		_methodParameterTypes12 = new String[] { "long" };

		_methodName13 = "getActionableDynamicQuery";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "getIndexableActionableDynamicQuery";

		_methodParameterTypes14 = new String[] {  };

		_methodName16 = "getExportActionableDynamicQuery";

		_methodParameterTypes16 = new String[] {
				"com.liferay.exportimport.kernel.lar.PortletDataContext"
			};

		_methodName17 = "deletePersistedModel";

		_methodParameterTypes17 = new String[] {
				"com.liferay.portal.kernel.model.PersistedModel"
			};

		_methodName18 = "getPersistedModel";

		_methodParameterTypes18 = new String[] { "java.io.Serializable" };

		_methodName19 = "getSampleLARBookingsByUuidAndCompanyId";

		_methodParameterTypes19 = new String[] { "java.lang.String", "long" };

		_methodName20 = "getSampleLARBookingsByUuidAndCompanyId";

		_methodParameterTypes20 = new String[] {
				"java.lang.String", "long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName21 = "getSampleLARBookingByUuidAndGroupId";

		_methodParameterTypes21 = new String[] { "java.lang.String", "long" };

		_methodName22 = "getSampleLARBookings";

		_methodParameterTypes22 = new String[] { "int", "int" };

		_methodName23 = "getSampleLARBookingsCount";

		_methodParameterTypes23 = new String[] {  };

		_methodName24 = "updateSampleLARBooking";

		_methodParameterTypes24 = new String[] {
				"com.liferay.samplelar.model.SampleLARBooking"
			};

		_methodName43 = "getOSGiServiceIdentifier";

		_methodParameterTypes43 = new String[] {  };

		_methodName48 = "addSampleLARBooking";

		_methodParameterTypes48 = new String[] {
				"long", "long", "java.lang.String",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName49 = "deleteSampleLARBooking";

		_methodParameterTypes49 = new String[] { "long" };

		_methodName50 = "deleteSampleLARBooking";

		_methodParameterTypes50 = new String[] {
				"com.liferay.samplelar.model.SampleLARBooking"
			};

		_methodName51 = "deleteSampleLARBookings";

		_methodParameterTypes51 = new String[] { "long" };

		_methodName52 = "getSampleLARBookings";

		_methodParameterTypes52 = new String[] { "long", "int", "int" };

		_methodName53 = "getSampleLARBookingsCount";

		_methodParameterTypes53 = new String[] { "long" };

		_methodName54 = "updateSampleLARBooking";

		_methodParameterTypes54 = new String[] {
				"long", "long", "java.lang.String",
				"com.liferay.portal.kernel.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.addSampleLARBooking((com.liferay.samplelar.model.SampleLARBooking)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.createSampleLARBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.deleteSampleLARBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.deleteSampleLARBooking((com.liferay.samplelar.model.SampleLARBooking)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.fetchSampleLARBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.fetchSampleLARBookingByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getSampleLARBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getExportActionableDynamicQuery((com.liferay.exportimport.kernel.lar.PortletDataContext)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getSampleLARBookingsByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getSampleLARBookingsByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.samplelar.model.SampleLARBooking>)arguments[4]);
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getSampleLARBookingByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getSampleLARBookings(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getSampleLARBookingsCount();
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.updateSampleLARBooking((com.liferay.samplelar.model.SampleLARBooking)arguments[0]);
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.addSampleLARBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[3]);
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.deleteSampleLARBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.deleteSampleLARBooking((com.liferay.samplelar.model.SampleLARBooking)arguments[0]);
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			SampleLARBookingLocalServiceUtil.deleteSampleLARBookings(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getSampleLARBookings(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.getSampleLARBookingsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return SampleLARBookingLocalServiceUtil.updateSampleLARBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[3]);
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
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName43;
	private String[] _methodParameterTypes43;
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