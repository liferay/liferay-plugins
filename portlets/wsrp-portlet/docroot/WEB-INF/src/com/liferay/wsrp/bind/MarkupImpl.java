/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wsrp.bind;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.types.BlockingInteractionResponse;
import oasis.names.tc.wsrp.v2.types.Extension;
import oasis.names.tc.wsrp.v2.types.GetMarkup;
import oasis.names.tc.wsrp.v2.types.GetResource;
import oasis.names.tc.wsrp.v2.types.HandleEvents;
import oasis.names.tc.wsrp.v2.types.HandleEventsResponse;
import oasis.names.tc.wsrp.v2.types.InitCookie;
import oasis.names.tc.wsrp.v2.types.MarkupContext;
import oasis.names.tc.wsrp.v2.types.MarkupResponse;
import oasis.names.tc.wsrp.v2.types.PerformBlockingInteraction;
import oasis.names.tc.wsrp.v2.types.ReleaseSessions;
import oasis.names.tc.wsrp.v2.types.ResourceResponse;

/**
 * <a href="MarkupImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MarkupImpl implements WSRP_v2_Markup_PortType {

	public MarkupResponse getMarkup(GetMarkup getMarkup)
		throws RemoteException {

		try {
			return doGetMarkup(getMarkup);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public ResourceResponse getResource(GetResource getResource)
		throws RemoteException {

		try {
			return doGetResource(getResource);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public HandleEventsResponse handleEvents(HandleEvents handleEvents)
		throws RemoteException {

		try {
			return doHandleEvents(handleEvents);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public Extension[] initCookie(InitCookie initCookie)
		throws RemoteException {

		try {
			return doInitCookie(initCookie);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public BlockingInteractionResponse performBlockingInteraction(
			PerformBlockingInteraction performBlockingInteraction)
		throws RemoteException {

		try {
			return doPerformBlockingInteraction(performBlockingInteraction);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public Extension[] releaseSessions(ReleaseSessions releaseSessions)
		throws RemoteException {

		try {
			return doReleaseSessions(releaseSessions);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	protected MarkupResponse doGetMarkup(GetMarkup getMarkup) throws Exception {
		MarkupContext markupContext = new MarkupContext();

		markupContext.setItemString("Hello World");

		MarkupResponse markupResponse = new MarkupResponse();

		markupResponse.setMarkupContext(markupContext);

		return markupResponse;
	}

	protected ResourceResponse doGetResource(GetResource getResource)
		throws Exception {

		ResourceResponse resourceResponse = new ResourceResponse();

		return resourceResponse;
	}

	protected HandleEventsResponse doHandleEvents(HandleEvents handleEvents)
		throws Exception {

		HandleEventsResponse handleEventsResponse = new HandleEventsResponse();

		return handleEventsResponse;
	}

	protected Extension[] doInitCookie(InitCookie initCookie) throws Exception {
		return null;
	}

	protected BlockingInteractionResponse doPerformBlockingInteraction(
			PerformBlockingInteraction performBlockingInteraction)
		throws Exception {

		BlockingInteractionResponse blockingInteractionResponse =
			new BlockingInteractionResponse();

		return blockingInteractionResponse;
	}

	protected Extension[] doReleaseSessions(ReleaseSessions releaseSessions)
		throws Exception {

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(MarkupImpl.class);

}