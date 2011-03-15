/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Brian Wing Shun Chan
 */
public class MicroblogsEntryServiceClp implements MicroblogsEntryService {
	public MicroblogsEntryServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addMicroblogsEntryMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addMicroblogsEntry", long.class, java.lang.String.class,
				int.class, long.class, long.class, int.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteMicroblogsEntryMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteMicroblogsEntry", long.class);

		_getMicroblogsEntryMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntry", long.class, long.class);

		_getMicroblogsEntriesMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntries", long.class, long[].class, int.class,
				long.class, long.class, int.class, long.class, int.class,
				int.class);

		_getMicroblogsEntriesCountMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntriesCount", long.class, long.class, int.class,
				long.class, long.class, int.class, long.class);

		_getMicroblogsEntriesCountMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntriesCount", long.class, long[].class,
				int.class, long.class, long.class, int.class, long.class);

		_getMicroblogsEntriesMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntries", long.class, long.class, int.class,
				long.class, long.class, int.class, long.class, int.class,
				int.class);

		_getMicroblogsEntriesByTagMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntriesByTag", java.lang.String.class,
				long.class, int.class, int.class);

		_getMicroblogsEntriesCountByTagMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntriesCountByTag", java.lang.String.class,
				long.class);

		_getMicroblogsEntriesByTagsMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntriesByTags", java.lang.String[].class,
				long.class, int.class, int.class);

		_getMicroblogsEntriesCountByTagsMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntriesCountByTags", java.lang.String[].class,
				long.class);

		_updateMicroblogsEntryMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateMicroblogsEntry", long.class, java.lang.String.class,
				int.class, com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addMicroblogsEntryMethodKey0,
				userId, ClpSerializer.translateInput(content), type,
				receiverUserId, receiverEntryId, socialRelationType,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.microblogs.model.MicroblogsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteMicroblogsEntry(long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteMicroblogsEntryMethodKey1,
				microblogsEntryId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId, long viewerUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntryMethodKey2,
				microblogsEntryId, viewerUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.microblogs.model.MicroblogsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long companyId, long[] userIds, int type, long receiverUserId,
		long receiverEntryId, int socialRelationType, long viewerUserId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesMethodKey3,
				companyId, ClpSerializer.translateInput(userIds), type,
				receiverUserId, receiverEntryId, socialRelationType,
				viewerUserId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.microblogs.model.MicroblogsEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getMicroblogsEntriesCount(long companyId, long userId, int type,
		long receiverUserId, long receiverEntryId, int socialRelationType,
		long viewerUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesCountMethodKey4,
				companyId, userId, type, receiverUserId, receiverEntryId,
				socialRelationType, viewerUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getMicroblogsEntriesCount(long companyId, long[] userIds,
		int type, long receiverUserId, long receiverEntryId,
		int socialRelationType, long viewerUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesCountMethodKey5,
				companyId, ClpSerializer.translateInput(userIds), type,
				receiverUserId, receiverEntryId, socialRelationType,
				viewerUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		long companyId, long userId, int type, long receiverUserId,
		long receiverEntryId, int socialRelationType, long viewerUserId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesMethodKey6,
				companyId, userId, type, receiverUserId, receiverEntryId,
				socialRelationType, viewerUserId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.microblogs.model.MicroblogsEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntriesByTag(
		java.lang.String tagName, long viewerUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesByTagMethodKey7,
				ClpSerializer.translateInput(tagName), viewerUserId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.microblogs.model.MicroblogsEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getMicroblogsEntriesCountByTag(java.lang.String tagName,
		long viewerUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesCountByTagMethodKey8,
				ClpSerializer.translateInput(tagName), viewerUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntriesByTags(
		java.lang.String[] tagNames, long viewerUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesByTagsMethodKey9,
				ClpSerializer.translateInput(tagNames), viewerUserId, start, end);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.microblogs.model.MicroblogsEntry>)ClpSerializer.translateOutput(returnObj);
	}

	public int getMicroblogsEntriesCountByTags(java.lang.String[] tagNames,
		long viewerUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesCountByTagsMethodKey10,
				ClpSerializer.translateInput(tagNames), viewerUserId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.microblogs.model.MicroblogsEntry updateMicroblogsEntry(
		long microblogsEntryId, java.lang.String content,
		int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateMicroblogsEntryMethodKey11,
				microblogsEntryId, ClpSerializer.translateInput(content),
				socialRelationType, ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.microblogs.model.MicroblogsEntry)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addMicroblogsEntryMethodKey0;
	private MethodKey _deleteMicroblogsEntryMethodKey1;
	private MethodKey _getMicroblogsEntryMethodKey2;
	private MethodKey _getMicroblogsEntriesMethodKey3;
	private MethodKey _getMicroblogsEntriesCountMethodKey4;
	private MethodKey _getMicroblogsEntriesCountMethodKey5;
	private MethodKey _getMicroblogsEntriesMethodKey6;
	private MethodKey _getMicroblogsEntriesByTagMethodKey7;
	private MethodKey _getMicroblogsEntriesCountByTagMethodKey8;
	private MethodKey _getMicroblogsEntriesByTagsMethodKey9;
	private MethodKey _getMicroblogsEntriesCountByTagsMethodKey10;
	private MethodKey _updateMicroblogsEntryMethodKey11;
}