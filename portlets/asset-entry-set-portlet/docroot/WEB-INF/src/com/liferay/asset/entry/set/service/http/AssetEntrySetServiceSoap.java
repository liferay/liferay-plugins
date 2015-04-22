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

package com.liferay.asset.entry.set.service.http;

import com.liferay.asset.entry.set.service.AssetEntrySetServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link com.liferay.asset.entry.set.service.AssetEntrySetServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.asset.entry.set.model.AssetEntrySetSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.asset.entry.set.model.AssetEntrySet}, that is translated to a
 * {@link com.liferay.asset.entry.set.model.AssetEntrySetSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetServiceHttp
 * @see com.liferay.asset.entry.set.model.AssetEntrySetSoap
 * @see com.liferay.asset.entry.set.service.AssetEntrySetServiceUtil
 * @generated
 */
public class AssetEntrySetServiceSoap {
	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap addAssetEntrySet(
		long parentAssetEntrySetId, long creatorClassNameId,
		long creatorClassPK, java.lang.String payload,
		boolean privateAssetEntrySet) throws RemoteException {
		try {
			com.liferay.asset.entry.set.model.AssetEntrySet returnValue = AssetEntrySetServiceUtil.addAssetEntrySet(parentAssetEntrySetId,
					creatorClassNameId, creatorClassPK, payload,
					privateAssetEntrySet);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap deleteAssetEntrySet(
		long assetEntrySetId) throws RemoteException {
		try {
			com.liferay.asset.entry.set.model.AssetEntrySet returnValue = AssetEntrySetServiceUtil.deleteAssetEntrySet(assetEntrySetId);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap fetchAssetEntrySet(
		long assetEntrySetId, int childAssetEntrySetsLimit,
		int likedParticipantsLimit) throws RemoteException {
		try {
			com.liferay.asset.entry.set.model.AssetEntrySet returnValue = AssetEntrySetServiceUtil.fetchAssetEntrySet(assetEntrySetId,
					childAssetEntrySetsLimit, likedParticipantsLimit);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap getAssetEntrySet(
		long assetEntrySetId, int childAssetEntrySetsLimit,
		int likedParticipantsLimit) throws RemoteException {
		try {
			com.liferay.asset.entry.set.model.AssetEntrySet returnValue = AssetEntrySetServiceUtil.getAssetEntrySet(assetEntrySetId,
					childAssetEntrySetsLimit, likedParticipantsLimit);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap[] getNewAssetEntrySets(
		long createTime, long parentAssetEntrySetId, java.lang.String sharedTo,
		java.lang.String[] assetTagNames, int childAssetEntrySetsLimit,
		int likedParticipantsLimit, int start, int end)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> returnValue =
				AssetEntrySetServiceUtil.getNewAssetEntrySets(createTime,
					parentAssetEntrySetId, sharedTo, assetTagNames,
					childAssetEntrySetsLimit, likedParticipantsLimit, start, end);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap[] getNewChildAssetEntrySets(
		long createTime, long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> returnValue =
				AssetEntrySetServiceUtil.getNewChildAssetEntrySets(createTime,
					parentAssetEntrySetId, start, end, orderByComparator);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap[] getOldAssetEntrySets(
		long createTime, long parentAssetEntrySetId, java.lang.String sharedTo,
		java.lang.String[] assetTagNames, int childAssetEntrySetsLimit,
		int likedParticipantsLimit, int start, int end)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> returnValue =
				AssetEntrySetServiceUtil.getOldAssetEntrySets(createTime,
					parentAssetEntrySetId, sharedTo, assetTagNames,
					childAssetEntrySetsLimit, likedParticipantsLimit, start, end);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap[] getOldChildAssetEntrySets(
		long createTime, long parentAssetEntrySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> returnValue =
				AssetEntrySetServiceUtil.getOldChildAssetEntrySets(createTime,
					parentAssetEntrySetId, start, end, orderByComparator);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap likeAssetEntrySet(
		long assetEntrySetId, int childAssetEntrySetsLimit,
		int likedParticipantsLimit) throws RemoteException {
		try {
			com.liferay.asset.entry.set.model.AssetEntrySet returnValue = AssetEntrySetServiceUtil.likeAssetEntrySet(assetEntrySetId,
					childAssetEntrySetsLimit, likedParticipantsLimit);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap unlikeAssetEntrySet(
		long assetEntrySetId, int childAssetEntrySetsLimit,
		int likedParticipantsLimit) throws RemoteException {
		try {
			com.liferay.asset.entry.set.model.AssetEntrySet returnValue = AssetEntrySetServiceUtil.unlikeAssetEntrySet(assetEntrySetId,
					childAssetEntrySetsLimit, likedParticipantsLimit);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.asset.entry.set.model.AssetEntrySetSoap updateAssetEntrySet(
		long assetEntrySetId, java.lang.String payload,
		boolean privateAssetEntrySet) throws RemoteException {
		try {
			com.liferay.asset.entry.set.model.AssetEntrySet returnValue = AssetEntrySetServiceUtil.updateAssetEntrySet(assetEntrySetId,
					payload, privateAssetEntrySet);

			return com.liferay.asset.entry.set.model.AssetEntrySetSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AssetEntrySetServiceSoap.class);
}