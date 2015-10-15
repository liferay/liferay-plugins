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

package com.liferay.knowledgebase.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.knowledgebase.service.KBFolderServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

/**
 * Provides the HTTP utility for the
 * {@link KBFolderServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderServiceSoap
 * @see HttpPrincipal
 * @see KBFolderServiceUtil
 * @generated
 */
@ProviderType
public class KBFolderServiceHttp {
	public static com.liferay.knowledgebase.model.KBFolder addKBFolder(
		HttpPrincipal httpPrincipal, long groupId,
		long parentResourceClassNameId, long parentResourcePrimKey,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"addKBFolder", _addKBFolderParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentResourceClassNameId, parentResourcePrimKey, name,
					description, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBFolder deleteKBFolder(
		HttpPrincipal httpPrincipal, long kbFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"deleteKBFolder", _deleteKBFolderParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBFolder fetchKBFolderByUrlTitle(
		HttpPrincipal httpPrincipal, long groupId, long parentKbFolderId,
		java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"fetchKBFolderByUrlTitle",
					_fetchKBFolderByUrlTitleParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentKbFolderId, urlTitle);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBFolder getKBFolder(
		HttpPrincipal httpPrincipal, long kbFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"getKBFolder", _getKBFolderParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBFolder getKBFolderByUrlTitle(
		HttpPrincipal httpPrincipal, long groupId, long parentKbFolderId,
		java.lang.String urlTitle)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"getKBFolderByUrlTitle",
					_getKBFolderByUrlTitleParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentKbFolderId, urlTitle);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBFolder> getKBFolders(
		HttpPrincipal httpPrincipal, long groupId, long parentKBFolderId,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"getKBFolders", _getKBFoldersParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentKBFolderId, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBFolder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getKBFoldersCount(HttpPrincipal httpPrincipal,
		long groupId, long parentKBFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"getKBFoldersCount", _getKBFoldersCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					parentKBFolderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void moveKBFolder(HttpPrincipal httpPrincipal,
		long kbFolderId, long parentKBFolderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"moveKBFolder", _moveKBFolderParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbFolderId, parentKBFolderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBFolder updateKBFolder(
		HttpPrincipal httpPrincipal, long parentResourceClassNameId,
		long parentResourcePrimKey, long kbFolderId, java.lang.String name,
		java.lang.String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBFolderServiceUtil.class,
					"updateKBFolder", _updateKBFolderParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					parentResourceClassNameId, parentResourcePrimKey,
					kbFolderId, name, description);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.knowledgebase.model.KBFolder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBFolderServiceHttp.class);
	private static final Class<?>[] _addKBFolderParameterTypes0 = new Class[] {
			long.class, long.class, long.class, java.lang.String.class,
			java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteKBFolderParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchKBFolderByUrlTitleParameterTypes2 = new Class[] {
			long.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _getKBFolderParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getKBFolderByUrlTitleParameterTypes4 = new Class[] {
			long.class, long.class, java.lang.String.class
		};
	private static final Class<?>[] _getKBFoldersParameterTypes5 = new Class[] {
			long.class, long.class, int.class, int.class
		};
	private static final Class<?>[] _getKBFoldersCountParameterTypes6 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _moveKBFolderParameterTypes7 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _updateKBFolderParameterTypes8 = new Class[] {
			long.class, long.class, long.class, java.lang.String.class,
			java.lang.String.class
		};
}