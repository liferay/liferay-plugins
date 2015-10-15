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

import com.liferay.knowledgebase.service.KBCommentServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

/**
 * Provides the HTTP utility for the
 * {@link KBCommentServiceUtil} service utility. The
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
 * @see KBCommentServiceSoap
 * @see HttpPrincipal
 * @see KBCommentServiceUtil
 * @generated
 */
@ProviderType
public class KBCommentServiceHttp {
	public static com.liferay.knowledgebase.model.KBComment deleteKBComment(
		HttpPrincipal httpPrincipal,
		com.liferay.knowledgebase.model.KBComment kbComment)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"deleteKBComment", _deleteKBCommentParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, kbComment);

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

			return (com.liferay.knowledgebase.model.KBComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBComment deleteKBComment(
		HttpPrincipal httpPrincipal, long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"deleteKBComment", _deleteKBCommentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbCommentId);

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

			return (com.liferay.knowledgebase.model.KBComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBComment getKBComment(
		HttpPrincipal httpPrincipal, long kbCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"getKBComment", _getKBCommentParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbCommentId);

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

			return (com.liferay.knowledgebase.model.KBComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		HttpPrincipal httpPrincipal, long groupId, int status, int start,
		int end) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"getKBComments", _getKBCommentsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status, start, end);

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

			return (java.util.List<com.liferay.knowledgebase.model.KBComment>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.knowledgebase.model.KBComment> getKBComments(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String className,
		long classPK, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"getKBComments", _getKBCommentsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					className, classPK, status, start, end);

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

			return (java.util.List<com.liferay.knowledgebase.model.KBComment>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getKBCommentsCount(HttpPrincipal httpPrincipal,
		long groupId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"getKBCommentsCount", _getKBCommentsCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					status);

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

	public static int getKBCommentsCount(HttpPrincipal httpPrincipal,
		long groupId, java.lang.String className, long classPK, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"getKBCommentsCount", _getKBCommentsCountParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					className, classPK, status);

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

	public static com.liferay.knowledgebase.model.KBComment updateKBComment(
		HttpPrincipal httpPrincipal, long kbCommentId, long classNameId,
		long classPK, java.lang.String content, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"updateKBComment", _updateKBCommentParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbCommentId, classNameId, classPK, content, status,
					serviceContext);

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

			return (com.liferay.knowledgebase.model.KBComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBComment updateKBComment(
		HttpPrincipal httpPrincipal, long kbCommentId, long classNameId,
		long classPK, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"updateKBComment", _updateKBCommentParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbCommentId, classNameId, classPK, content, serviceContext);

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

			return (com.liferay.knowledgebase.model.KBComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBComment updateStatus(
		HttpPrincipal httpPrincipal, long kbCommentId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBCommentServiceUtil.class,
					"updateStatus", _updateStatusParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbCommentId, status, serviceContext);

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

			return (com.liferay.knowledgebase.model.KBComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBCommentServiceHttp.class);
	private static final Class<?>[] _deleteKBCommentParameterTypes0 = new Class[] {
			com.liferay.knowledgebase.model.KBComment.class
		};
	private static final Class<?>[] _deleteKBCommentParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getKBCommentParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getKBCommentsParameterTypes3 = new Class[] {
			long.class, int.class, int.class, int.class
		};
	private static final Class<?>[] _getKBCommentsParameterTypes4 = new Class[] {
			long.class, java.lang.String.class, long.class, int.class, int.class,
			int.class
		};
	private static final Class<?>[] _getKBCommentsCountParameterTypes5 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _getKBCommentsCountParameterTypes6 = new Class[] {
			long.class, java.lang.String.class, long.class, int.class
		};
	private static final Class<?>[] _updateKBCommentParameterTypes7 = new Class[] {
			long.class, long.class, long.class, java.lang.String.class,
			int.class, com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _updateKBCommentParameterTypes8 = new Class[] {
			long.class, long.class, long.class, java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _updateStatusParameterTypes9 = new Class[] {
			long.class, int.class,
			com.liferay.portal.service.ServiceContext.class
		};
}