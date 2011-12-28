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

package com.liferay.wsrp.proxy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import oasis.names.tc.wsrp.v1.bind.WSRP_v1_Markup_Binding_SOAPStub;
import oasis.names.tc.wsrp.v1.intf.WSRP_v1_Markup_PortType;
import oasis.names.tc.wsrp.v1.types.BlockingInteractionResponse;
import oasis.names.tc.wsrp.v1.types.Extension;
import oasis.names.tc.wsrp.v1.types.GetMarkup;
import oasis.names.tc.wsrp.v1.types.InitCookie;
import oasis.names.tc.wsrp.v1.types.MarkupResponse;
import oasis.names.tc.wsrp.v1.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v1.types.ReleaseSessions;

/**
 * @author Michael Young
 */
public class MarkupServiceHandler implements InvocationHandler {

	public MarkupServiceHandler(WSRP_v1_Markup_Binding_SOAPStub markupService) {
		_markupService = markupService;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
		throws Throwable {

		try {
			return doInvoke(proxy, method, args);
		}
		catch (Throwable t) {
			_log.error(t, t);

			throw t;
		}
	}

	public Object doInvoke(Object proxy, Method method, Object[] args)
		throws Exception {

		Object v2Bean = null;

		String methodName = method.getName();

		if (methodName.equals("getMarkup")) {
			Object v1Bean = TypeConvertorUtil.convert(args[0], 2);

			GetMarkup getMarkup = (GetMarkup)v1Bean;

			MarkupResponse markupResponse = _markupService.getMarkup(getMarkup);

			v2Bean = TypeConvertorUtil.convert(markupResponse, 1);
		}
		else if (methodName.equals("initCookie")) {
			Object v1Bean = TypeConvertorUtil.convert(args[0], 2);

			InitCookie initCookie = (InitCookie)v1Bean;

			Extension[] extensions = _markupService.initCookie(initCookie);

			v2Bean = TypeConvertorUtil.convert(extensions, 1);
		}
		else if (methodName.equals("performBlockingInteraction")) {
			Object v1Bean = TypeConvertorUtil.convert(args[0], 2);

			PerformBlockingInteraction performBlockingInteraction =
				(PerformBlockingInteraction)v1Bean;

			BlockingInteractionResponse blockingInteractionResponse =
				_markupService.performBlockingInteraction(
					performBlockingInteraction);

			v2Bean = TypeConvertorUtil.convert(blockingInteractionResponse, 1);
		}
		else if (methodName.equals("releaseSessions")) {
			Object v1Bean = TypeConvertorUtil.convert(args[0], 2);

			ReleaseSessions releaseSessions = (ReleaseSessions)v1Bean;

			Extension[] extensions =
				_markupService.releaseSessions(releaseSessions);

			v2Bean = TypeConvertorUtil.convert(extensions, 1);
		}

		return v2Bean;
	}

	private static Log _log = LogFactoryUtil.getLog(MarkupServiceHandler.class);

	private WSRP_v1_Markup_PortType _markupService;

}