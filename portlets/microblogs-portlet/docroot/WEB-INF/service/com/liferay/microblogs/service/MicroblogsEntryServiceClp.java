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

		_getMicroblogsEntriesMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntries", int.class, int.class);

		_getMicroblogsEntriesMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntries", java.lang.String.class, int.class,
				int.class);

		_getMicroblogsEntriesCountMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntriesCount");

		_getMicroblogsEntriesCountMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntriesCount", java.lang.String.class);

		_getMicroblogsEntryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"getMicroblogsEntry", long.class);

		_getUserMicroblogsEntriesMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"getUserMicroblogsEntries", long.class, int.class, int.class);

		_getUserMicroblogsEntriesMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"getUserMicroblogsEntries", long.class, int.class, int.class,
				int.class);

		_getUserMicroblogsEntriesCountMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getUserMicroblogsEntriesCount", long.class);

		_getUserMicroblogsEntriesCountMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getUserMicroblogsEntriesCount", long.class, int.class);

		_updateMicroblogsEntryMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateMicroblogsEntry", long.class, java.lang.String.class,
				int.class, com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.microblogs.model.MicroblogsEntry addMicroblogsEntry(
		long userId, java.lang.String content, int type, long receiverUserId,
		long receiverMicroblogsEntryId, int socialRelationType,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addMicroblogsEntryMethodKey0,
				userId, ClpSerializer.translateInput(content), type,
				receiverUserId, receiverMicroblogsEntryId, socialRelationType,
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

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesMethodKey2,
				start, end);

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

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getMicroblogsEntries(
		java.lang.String assetTagName, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesMethodKey3,
				ClpSerializer.translateInput(assetTagName), start, end);

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

	public int getMicroblogsEntriesCount()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesCountMethodKey4);

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

	public int getMicroblogsEntriesCount(java.lang.String assetTagName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntriesCountMethodKey5,
				ClpSerializer.translateInput(assetTagName));

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

	public com.liferay.microblogs.model.MicroblogsEntry getMicroblogsEntry(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMicroblogsEntryMethodKey6,
				microblogsEntryId);

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

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getUserMicroblogsEntriesMethodKey7,
				microblogsEntryUserId, start, end);

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

	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> getUserMicroblogsEntries(
		long microblogsEntryUserId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getUserMicroblogsEntriesMethodKey8,
				microblogsEntryUserId, type, start, end);

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

	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getUserMicroblogsEntriesCountMethodKey9,
				microblogsEntryUserId);

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

	public int getUserMicroblogsEntriesCount(long microblogsEntryUserId,
		int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getUserMicroblogsEntriesCountMethodKey10,
				microblogsEntryUserId, type);

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
	private MethodKey _getMicroblogsEntriesMethodKey2;
	private MethodKey _getMicroblogsEntriesMethodKey3;
	private MethodKey _getMicroblogsEntriesCountMethodKey4;
	private MethodKey _getMicroblogsEntriesCountMethodKey5;
	private MethodKey _getMicroblogsEntryMethodKey6;
	private MethodKey _getUserMicroblogsEntriesMethodKey7;
	private MethodKey _getUserMicroblogsEntriesMethodKey8;
	private MethodKey _getUserMicroblogsEntriesCountMethodKey9;
	private MethodKey _getUserMicroblogsEntriesCountMethodKey10;
	private MethodKey _updateMicroblogsEntryMethodKey11;
}