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

package com.liferay.opensocial.service.base;

import com.liferay.opensocial.service.GadgetLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class GadgetLocalServiceClpInvoker {
	public GadgetLocalServiceClpInvoker() {
		_methodName0 = "addGadget";

		_methodParameterTypes0 = new String[] {
				"com.liferay.opensocial.model.Gadget"
			};

		_methodName1 = "createGadget";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteGadget";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteGadget";

		_methodParameterTypes3 = new String[] {
				"com.liferay.opensocial.model.Gadget"
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

		_methodName10 = "fetchGadget";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchGadgetByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "getGadget";

		_methodParameterTypes12 = new String[] { "long" };

		_methodName13 = "getPersistedModel";

		_methodParameterTypes13 = new String[] { "java.io.Serializable" };

		_methodName14 = "getGadgetByUuidAndCompanyId";

		_methodParameterTypes14 = new String[] { "java.lang.String", "long" };

		_methodName15 = "getGadgets";

		_methodParameterTypes15 = new String[] { "int", "int" };

		_methodName16 = "getGadgetsCount";

		_methodParameterTypes16 = new String[] {  };

		_methodName17 = "updateGadget";

		_methodParameterTypes17 = new String[] {
				"com.liferay.opensocial.model.Gadget"
			};

		_methodName50 = "getBeanIdentifier";

		_methodParameterTypes50 = new String[] {  };

		_methodName51 = "setBeanIdentifier";

		_methodParameterTypes51 = new String[] { "java.lang.String" };

		_methodName56 = "addGadget";

		_methodParameterTypes56 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName57 = "deleteGadget";

		_methodParameterTypes57 = new String[] {
				"com.liferay.opensocial.model.Gadget"
			};

		_methodName58 = "deleteGadget";

		_methodParameterTypes58 = new String[] { "long" };

		_methodName59 = "deleteGadgets";

		_methodParameterTypes59 = new String[] { "long" };

		_methodName60 = "destroyGadget";

		_methodParameterTypes60 = new String[] { "java.lang.String", "long" };

		_methodName61 = "destroyGadgets";

		_methodParameterTypes61 = new String[] {  };

		_methodName62 = "fetchGadget";

		_methodParameterTypes62 = new String[] { "long", "java.lang.String" };

		_methodName63 = "getGadget";

		_methodParameterTypes63 = new String[] { "long", "java.lang.String" };

		_methodName64 = "getGadget";

		_methodParameterTypes64 = new String[] { "java.lang.String", "long" };

		_methodName65 = "getGadgets";

		_methodParameterTypes65 = new String[] { "long", "int", "int" };

		_methodName66 = "getGadgetsCount";

		_methodParameterTypes66 = new String[] { "long" };

		_methodName67 = "initGadget";

		_methodParameterTypes67 = new String[] {
				"java.lang.String", "long", "long", "java.lang.String",
				"java.lang.String"
			};

		_methodName68 = "initGadgets";

		_methodParameterTypes68 = new String[] {  };

		_methodName69 = "updateGadget";

		_methodParameterTypes69 = new String[] { "long", "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return GadgetLocalServiceUtil.addGadget((com.liferay.opensocial.model.Gadget)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return GadgetLocalServiceUtil.createGadget(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return GadgetLocalServiceUtil.deleteGadget(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return GadgetLocalServiceUtil.deleteGadget((com.liferay.opensocial.model.Gadget)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return GadgetLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return GadgetLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return GadgetLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return GadgetLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return GadgetLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return GadgetLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return GadgetLocalServiceUtil.fetchGadget(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return GadgetLocalServiceUtil.fetchGadgetByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return GadgetLocalServiceUtil.getGadget(((Long)arguments[0]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return GadgetLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return GadgetLocalServiceUtil.getGadgetByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return GadgetLocalServiceUtil.getGadgets(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return GadgetLocalServiceUtil.getGadgetsCount();
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return GadgetLocalServiceUtil.updateGadget((com.liferay.opensocial.model.Gadget)arguments[0]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return GadgetLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			GadgetLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return GadgetLocalServiceUtil.addGadget(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return GadgetLocalServiceUtil.deleteGadget((com.liferay.opensocial.model.Gadget)arguments[0]);
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return GadgetLocalServiceUtil.deleteGadget(((Long)arguments[0]).longValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			GadgetLocalServiceUtil.deleteGadgets(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			GadgetLocalServiceUtil.destroyGadget((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			GadgetLocalServiceUtil.destroyGadgets();

			return null;
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return GadgetLocalServiceUtil.fetchGadget(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return GadgetLocalServiceUtil.getGadget(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return GadgetLocalServiceUtil.getGadget((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return GadgetLocalServiceUtil.getGadgets(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return GadgetLocalServiceUtil.getGadgetsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			GadgetLocalServiceUtil.initGadget((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4]);

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			GadgetLocalServiceUtil.initGadgets();

			return null;
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return GadgetLocalServiceUtil.updateGadget(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
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
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
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
}