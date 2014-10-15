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

package com.liferay.asset.sharing.service.base;

import com.liferay.asset.sharing.service.AssetSharingEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetSharingEntryLocalServiceClpInvoker {
	public AssetSharingEntryLocalServiceClpInvoker() {
		_methodName0 = "addAssetSharingEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.asset.sharing.model.AssetSharingEntry"
			};

		_methodName1 = "createAssetSharingEntry";

		_methodParameterTypes1 = new String[] {
				"com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK"
			};

		_methodName2 = "deleteAssetSharingEntry";

		_methodParameterTypes2 = new String[] {
				"com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK"
			};

		_methodName3 = "deleteAssetSharingEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.asset.sharing.model.AssetSharingEntry"
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

		_methodName10 = "fetchAssetSharingEntry";

		_methodParameterTypes10 = new String[] {
				"com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK"
			};

		_methodName11 = "getAssetSharingEntry";

		_methodParameterTypes11 = new String[] {
				"com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK"
			};

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getAssetSharingEntries";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getAssetSharingEntriesCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateAssetSharingEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.asset.sharing.model.AssetSharingEntry"
			};

		_methodName40 = "getBeanIdentifier";

		_methodParameterTypes40 = new String[] {  };

		_methodName41 = "setBeanIdentifier";

		_methodParameterTypes41 = new String[] { "java.lang.String" };

		_methodName46 = "addAssetSharingEntries";

		_methodParameterTypes46 = new String[] { "long", "long", "java.util.Map" };

		_methodName47 = "addAssetSharingEntry";

		_methodParameterTypes47 = new String[] { "long", "long", "long", "long" };

		_methodName48 = "deleteAssetSharingEntries";

		_methodParameterTypes48 = new String[] { "long", "long" };

		_methodName49 = "getAssetSharingEntries";

		_methodParameterTypes49 = new String[] { "long", "long" };

		_methodName50 = "getAssetSharingEntries";

		_methodParameterTypes50 = new String[] { "long", "long", "long" };

		_methodName51 = "getSharedToAssetSharingEntries";

		_methodParameterTypes51 = new String[] { "long", "long", "int", "int" };

		_methodName52 = "getSharedToAssetSharingEntries";

		_methodParameterTypes52 = new String[] {
				"long", "long", "long", "int", "int"
			};

		_methodName53 = "getSharedToAssetSharingEntriesCount";

		_methodParameterTypes53 = new String[] { "long", "long" };

		_methodName54 = "getSharedToAssetSharingEntriesCount";

		_methodParameterTypes54 = new String[] { "long", "long", "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.addAssetSharingEntry((com.liferay.asset.sharing.model.AssetSharingEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.createAssetSharingEntry((com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK)arguments[0]);
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.deleteAssetSharingEntry((com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK)arguments[0]);
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.deleteAssetSharingEntry((com.liferay.asset.sharing.model.AssetSharingEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.fetchAssetSharingEntry((com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK)arguments[0]);
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getAssetSharingEntry((com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getAssetSharingEntriesCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.updateAssetSharingEntry((com.liferay.asset.sharing.model.AssetSharingEntry)arguments[0]);
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			AssetSharingEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			AssetSharingEntryLocalServiceUtil.addAssetSharingEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.lang.Long, long[]>)arguments[2]);

			return null;
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			AssetSharingEntryLocalServiceUtil.addAssetSharingEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());

			return null;
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			AssetSharingEntryLocalServiceUtil.deleteAssetSharingEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getAssetSharingEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getSharedToAssetSharingEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getSharedToAssetSharingEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getSharedToAssetSharingEntriesCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return AssetSharingEntryLocalServiceUtil.getSharedToAssetSharingEntriesCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
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
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
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