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

import com.liferay.knowledgebase.service.KBTemplateServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.HttpPrincipal;
import com.liferay.portal.service.http.TunnelUtil;

/**
 * Provides the HTTP utility for the
 * {@link KBTemplateServiceUtil} service utility. The
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
 * @see KBTemplateServiceSoap
 * @see HttpPrincipal
 * @see KBTemplateServiceUtil
 * @generated
 */
@ProviderType
public class KBTemplateServiceHttp {
	public static com.liferay.knowledgebase.model.KBTemplate addKBTemplate(
		HttpPrincipal httpPrincipal, java.lang.String portletId,
		java.lang.String title, java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBTemplateServiceUtil.class,
					"addKBTemplate", _addKBTemplateParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					portletId, title, content, serviceContext);

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

			return (com.liferay.knowledgebase.model.KBTemplate)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplate deleteKBTemplate(
		HttpPrincipal httpPrincipal, long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBTemplateServiceUtil.class,
					"deleteKBTemplate", _deleteKBTemplateParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbTemplateId);

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

			return (com.liferay.knowledgebase.model.KBTemplate)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteKBTemplates(HttpPrincipal httpPrincipal,
		long groupId, long[] kbTemplateIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBTemplateServiceUtil.class,
					"deleteKBTemplates", _deleteKBTemplatesParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					kbTemplateIds);

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

	public static java.util.List<com.liferay.knowledgebase.model.KBTemplate> getGroupKBTemplates(
		HttpPrincipal httpPrincipal, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBTemplate> orderByComparator) {
		try {
			MethodKey methodKey = new MethodKey(KBTemplateServiceUtil.class,
					"getGroupKBTemplates", _getGroupKBTemplatesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.knowledgebase.model.KBTemplate>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getGroupKBTemplatesCount(HttpPrincipal httpPrincipal,
		long groupId) {
		try {
			MethodKey methodKey = new MethodKey(KBTemplateServiceUtil.class,
					"getGroupKBTemplatesCount",
					_getGroupKBTemplatesCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplate getKBTemplate(
		HttpPrincipal httpPrincipal, long kbTemplateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBTemplateServiceUtil.class,
					"getKBTemplate", _getKBTemplateParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbTemplateId);

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

			return (com.liferay.knowledgebase.model.KBTemplate)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplateSearchDisplay getKBTemplateSearchDisplay(
		HttpPrincipal httpPrincipal, long groupId, java.lang.String title,
		java.lang.String content, java.util.Date startDate,
		java.util.Date endDate, boolean andOperator, int[] curStartValues,
		int cur, int delta,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.knowledgebase.model.KBTemplate> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBTemplateServiceUtil.class,
					"getKBTemplateSearchDisplay",
					_getKBTemplateSearchDisplayParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					title, content, startDate, endDate, andOperator,
					curStartValues, cur, delta, orderByComparator);

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

			return (com.liferay.knowledgebase.model.KBTemplateSearchDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.knowledgebase.model.KBTemplate updateKBTemplate(
		HttpPrincipal httpPrincipal, long kbTemplateId, java.lang.String title,
		java.lang.String content,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(KBTemplateServiceUtil.class,
					"updateKBTemplate", _updateKBTemplateParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					kbTemplateId, title, content, serviceContext);

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

			return (com.liferay.knowledgebase.model.KBTemplate)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(KBTemplateServiceHttp.class);
	private static final Class<?>[] _addKBTemplateParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteKBTemplateParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _deleteKBTemplatesParameterTypes2 = new Class[] {
			long.class, long[].class
		};
	private static final Class<?>[] _getGroupKBTemplatesParameterTypes3 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getGroupKBTemplatesCountParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getKBTemplateParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getKBTemplateSearchDisplayParameterTypes6 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.util.Date.class, java.util.Date.class, boolean.class,
			int[].class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _updateKBTemplateParameterTypes7 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			com.liferay.portal.service.ServiceContext.class
		};
}