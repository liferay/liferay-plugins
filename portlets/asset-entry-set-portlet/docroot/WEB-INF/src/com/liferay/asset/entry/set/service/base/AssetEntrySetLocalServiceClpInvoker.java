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

package com.liferay.asset.entry.set.service.base;

import com.liferay.asset.entry.set.service.AssetEntrySetLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetLocalServiceClpInvoker {
	public AssetEntrySetLocalServiceClpInvoker() {
		_methodName0 = "addAssetEntrySet";

		_methodParameterTypes0 = new String[] {
				"com.liferay.asset.entry.set.model.AssetEntrySet"
			};

		_methodName1 = "createAssetEntrySet";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAssetEntrySet";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAssetEntrySet";

		_methodParameterTypes3 = new String[] {
				"com.liferay.asset.entry.set.model.AssetEntrySet"
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

		_methodName10 = "fetchAssetEntrySet";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getAssetEntrySet";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getAssetEntrySets";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getAssetEntrySetsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateAssetEntrySet";

		_methodParameterTypes15 = new String[] {
				"com.liferay.asset.entry.set.model.AssetEntrySet"
			};

		_methodName62 = "getBeanIdentifier";

		_methodParameterTypes62 = new String[] {  };

		_methodName63 = "setBeanIdentifier";

		_methodParameterTypes63 = new String[] { "java.lang.String" };

		_methodName68 = "addAssetEntrySet";

		_methodParameterTypes68 = new String[] {
				"long", "long", "long", "long",
				"com.liferay.portal.kernel.json.JSONObject", "boolean"
			};

		_methodName69 = "addFileAttachment";

		_methodParameterTypes69 = new String[] { "long", "java.io.File" };

		_methodName70 = "deleteAssetEntrySet";

		_methodParameterTypes70 = new String[] {
				"com.liferay.asset.entry.set.model.AssetEntrySet"
			};

		_methodName71 = "deleteAssetEntrySet";

		_methodParameterTypes71 = new String[] { "long" };

		_methodName72 = "fetchAssetEntrySet";

		_methodParameterTypes72 = new String[] { "long", "long", "int", "int" };

		_methodName73 = "getAssetEntrySet";

		_methodParameterTypes73 = new String[] { "long", "long", "int", "int" };

		_methodName74 = "getNewAssetEntrySets";

		_methodParameterTypes74 = new String[] {
				"long", "long", "long",
				"com.liferay.portal.kernel.json.JSONArray",
				"java.lang.String[][]", "int", "int", "int", "int"
			};

		_methodName75 = "getNewChildAssetEntrySets";

		_methodParameterTypes75 = new String[] {
				"long", "long", "long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName76 = "getOldAssetEntrySets";

		_methodParameterTypes76 = new String[] {
				"long", "long", "long",
				"com.liferay.portal.kernel.json.JSONArray",
				"java.lang.String[][]", "int", "int", "int", "int"
			};

		_methodName77 = "getOldChildAssetEntrySets";

		_methodParameterTypes77 = new String[] {
				"long", "long", "long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName78 = "likeAssetEntrySet";

		_methodParameterTypes78 = new String[] { "long", "long", "int", "int" };

		_methodName79 = "unlikeAssetEntrySet";

		_methodParameterTypes79 = new String[] { "long", "long", "int", "int" };

		_methodName80 = "updateAssetEntry";

		_methodParameterTypes80 = new String[] { "long", "java.lang.String[][]" };

		_methodName81 = "updateAssetEntrySet";

		_methodParameterTypes81 = new String[] {
				"long", "com.liferay.portal.kernel.json.JSONObject", "boolean"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.addAssetEntrySet((com.liferay.asset.entry.set.model.AssetEntrySet)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.createAssetEntrySet(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.deleteAssetEntrySet(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.deleteAssetEntrySet((com.liferay.asset.entry.set.model.AssetEntrySet)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.fetchAssetEntrySet(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getAssetEntrySet(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getAssetEntrySets(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getAssetEntrySetsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.updateAssetEntrySet((com.liferay.asset.entry.set.model.AssetEntrySet)arguments[0]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			AssetEntrySetLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.addAssetEntrySet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(com.liferay.portal.kernel.json.JSONObject)arguments[4],
				((Boolean)arguments[5]).booleanValue());
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.addFileAttachment(((Long)arguments[0]).longValue(),
				(java.io.File)arguments[1]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.deleteAssetEntrySet((com.liferay.asset.entry.set.model.AssetEntrySet)arguments[0]);
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.deleteAssetEntrySet(((Long)arguments[0]).longValue());
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.fetchAssetEntrySet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getAssetEntrySet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getNewAssetEntrySets(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(com.liferay.portal.kernel.json.JSONArray)arguments[3],
				(java.lang.String[])arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue());
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getNewChildAssetEntrySets(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getOldAssetEntrySets(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(com.liferay.portal.kernel.json.JSONArray)arguments[3],
				(java.lang.String[])arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue());
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.getOldChildAssetEntrySets(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.likeAssetEntrySet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.unlikeAssetEntrySet(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			AssetEntrySetLocalServiceUtil.updateAssetEntry(((Long)arguments[0]).longValue(),
				(java.lang.String[])arguments[1]);

			return null;
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return AssetEntrySetLocalServiceUtil.updateAssetEntrySet(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.json.JSONObject)arguments[1],
				((Boolean)arguments[2]).booleanValue());
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
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
}