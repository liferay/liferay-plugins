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

package com.liferay.wsrp.service.impl;

import com.liferay.client.soap.wsrp.v2.types.PortletDescription;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.InvokerPortlet;
import com.liferay.wsrp.model.WSRPConsumer;
import com.liferay.wsrp.model.WSRPConsumerPortlet;
import com.liferay.wsrp.portlet.ConsumerPortlet;
import com.liferay.wsrp.service.ClpSerializer;
import com.liferay.wsrp.service.base.WSRPConsumerPortletLocalServiceBaseImpl;
import com.liferay.wsrp.util.WSRPConsumerManager;
import com.liferay.wsrp.util.WSRPConsumerManagerFactory;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletMode;

/**
 * <a href="WSRPConsumerPortletLocalServiceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WSRPConsumerPortletLocalServiceImpl
	extends WSRPConsumerPortletLocalServiceBaseImpl {

	public WSRPConsumerPortlet addWSRPConsumerPortlet(
			long wsrpConsumerId, String name, String portletHandle)
		throws PortalException, SystemException {

		Date now = new Date();

		long wsrpConsumerPortletId = CounterLocalServiceUtil.increment();

		WSRPConsumerPortlet wsrpConsumerPortlet =
			wsrpConsumerPortletPersistence.create(wsrpConsumerPortletId);

		wsrpConsumerPortlet.setCreateDate(now);
		wsrpConsumerPortlet.setModifiedDate(now);
		wsrpConsumerPortlet.setWsrpConsumerId(wsrpConsumerId);
		wsrpConsumerPortlet.setName(name);
		wsrpConsumerPortlet.setPortletHandle(portletHandle);

		wsrpConsumerPortletPersistence.update(wsrpConsumerPortlet, false);

		initWSRPConsumerPortlet(wsrpConsumerPortlet);

		return wsrpConsumerPortlet;
	}

	public void deleteWSRPConsumerPortlet(long wsrpConsumerPortletId)
		throws PortalException, SystemException {

		WSRPConsumerPortlet wsrpConsumerPortlet =
			wsrpConsumerPortletPersistence.findByPrimaryKey(
				wsrpConsumerPortletId);

		deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
	}

	public void deleteWSRPConsumerPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException, SystemException {

		destroyWSRPConsumerPortlet(wsrpConsumerPortlet);

		wsrpConsumerPortletPersistence.remove(wsrpConsumerPortlet);
	}

	public void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws PortalException, SystemException {

		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			wsrpConsumerPortletPersistence.findByWsrpConsumerId(wsrpConsumerId);

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			deleteWSRPConsumerPortlet(wsrpConsumerPortlet);
		}
	}

	public void destroyWSRPConsumerPortlets()
		throws PortalException, SystemException {

		List<WSRPConsumerPortlet> wsrpConsumerPortlets =
			wsrpConsumerPortletPersistence.findAll();

		for (WSRPConsumerPortlet wsrpConsumerPortlet : wsrpConsumerPortlets) {
			destroyWSRPConsumerPortlet(wsrpConsumerPortlet);
		}
	}

	public List<WSRPConsumerPortlet> getWSRPConsumerPortlets(
			long wsrpConsumerId, int start, int end)
		throws SystemException {

		return wsrpConsumerPortletPersistence.findByWsrpConsumerId(
			wsrpConsumerId, start, end);
	}

	public int getWSRPConsumerPortletsCount(long wsrpConsumerId)
		throws SystemException {

		return wsrpConsumerPortletPersistence.countByWsrpConsumerId(
			wsrpConsumerId);
	}

	public void initWSRPConsumerPortlets()
		throws PortalException, SystemException {

		for (WSRPConsumerPortlet wsrpConsumerPortlet :
				wsrpConsumerPortletPersistence.findAll()) {

			initWSRPConsumerPortlet(wsrpConsumerPortlet);
		}
	}

	public WSRPConsumerPortlet updateWSRPConsumerPortlet(
			long wsrpConsumerPortletId, String name)
		throws PortalException, SystemException {

		WSRPConsumerPortlet wsrpConsumerPortlet =
			wsrpConsumerPortletPersistence.findByPrimaryKey(
				wsrpConsumerPortletId);

		wsrpConsumerPortlet.setModifiedDate(new Date());
		wsrpConsumerPortlet.setName(name);

		wsrpConsumerPortletPersistence.update(wsrpConsumerPortlet, false);

		return wsrpConsumerPortlet;
	}

	protected void destroyWSRPConsumerPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException, SystemException {

		try {
			Portlet portlet = getPortlet(wsrpConsumerPortlet);

			PortletLocalServiceUtil.destroyPortlet(portlet);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected Portlet getPortlet(WSRPConsumerPortlet wsrpConsumerPortlet)
		throws Exception {

		Portlet portlet = (Portlet)_portletsPool.get(
			wsrpConsumerPortlet.getWsrpConsumerPortletId());

		if (portlet != null) {
			return portlet;
		}

		WSRPConsumer wsrpConsumer = wsrpConsumerPersistence.findByPrimaryKey(
			wsrpConsumerPortlet.getWsrpConsumerId());

		WSRPConsumerManager wsrpConsumerManager =
			WSRPConsumerManagerFactory.getWSRPConsumerManager(wsrpConsumer);

		PortletDescription portletDescription =
			wsrpConsumerManager.getPortletDescription(
				wsrpConsumerPortlet.getPortletHandle());

		String portletId = PortalUtil.getJsSafePortletId(
			ConsumerPortlet.PORTLET_NAME_PREFIX +
				wsrpConsumerPortlet.getWsrpConsumerPortletId());

		portlet = PortletLocalServiceUtil.newPortlet(
			CompanyConstants.SYSTEM, portletId);

		portlet.setTimestamp(System.currentTimeMillis());

		PortletApp portletApp = PortletLocalServiceUtil.getPortletApp(
			ClpSerializer.SERVLET_CONTEXT_NAME);

		portlet.setPortletApp(portletApp);

		portlet.setPortletName(portletId);
		portlet.setDisplayName(portletId);
		portlet.setPortletClass(ConsumerPortlet.class.getName());

		Map<String, String> initParams = portlet.getInitParams();

		initParams.put(
			InvokerPortlet.INIT_INVOKER_PORTLET_NAME, _CONSUMER_PORTLET_NAME);

		Set<String> mimeTypeModes = new HashSet<String>();

		mimeTypeModes.add(PortletMode.VIEW.toString().toLowerCase());

		portlet.getPortletModes().put(
			ContentTypes.TEXT_HTML, mimeTypeModes);

		String title = WSRPConsumerManager.getLocalizedStringValue(
			portletDescription.getTitle(),
			portletDescription.getDisplayName().getValue());
		String shortTitle = WSRPConsumerManager.getLocalizedStringValue(
			portletDescription.getShortTitle(), title);
		String keywords = StringUtil.merge(
			WSRPConsumerManager.getLocalizedStringValues(
				portletDescription.getKeywords()),
			StringPool.SPACE);

		PortletInfo portletInfo = new PortletInfo(title, shortTitle, keywords);

		portlet.setPortletInfo(portletInfo);

		_portletsPool.put(
			wsrpConsumerPortlet.getWsrpConsumerPortletId(), portlet);

		PortletBag portletBag = PortletBagPool.get(_CONSUMER_PORTLET_ID);

		portletBag = (PortletBag)portletBag.clone();

		portletBag.setPortletName(portletId);
		portletBag.setPortletInstance(new ConsumerPortlet());

		PortletBagPool.put(portletId, portletBag);

		return portlet;
	}

	protected void initWSRPConsumerPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException, SystemException {

		try {
			Portlet portlet = getPortlet(wsrpConsumerPortlet);

			PortletLocalServiceUtil.deployRemotePortlet(portlet);
		}
		catch (PortalException pe) {
			throw pe;
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	private static final String _CONSUMER_PORTLET_ID = "2_WAR_wsrpportlet";

	private static final String _CONSUMER_PORTLET_NAME = "2";

	private static Map<Long, Portlet> _portletsPool =
		new ConcurrentHashMap<Long, Portlet>();

}