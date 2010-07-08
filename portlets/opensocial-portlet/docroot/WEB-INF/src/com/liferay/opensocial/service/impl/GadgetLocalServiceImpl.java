/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.service.impl;

import com.liferay.opensocial.GadgetNameException;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.portlet.GadgetPortlet;
import com.liferay.opensocial.service.ClpSerializer;
import com.liferay.opensocial.service.base.GadgetLocalServiceBaseImpl;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.InvokerPortlet;
import com.liferay.portlet.PortletConfigFactoryUtil;
import com.liferay.portlet.PortletInstanceFactoryUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

/**
 * <a href="GadgetLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 * @author Brian Wing Shun Chan
 */
public class GadgetLocalServiceImpl extends GadgetLocalServiceBaseImpl {

	public Gadget addGadget(long companyId, String name, String url)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		long gadgetId = counterLocalService.increment();

		Gadget gadget = gadgetPersistence.create(gadgetId);

		gadget.setCompanyId(companyId);
		gadget.setCreateDate(now);
		gadget.setModifiedDate(now);
		gadget.setName(name);
		gadget.setUrl(url);

		gadgetPersistence.update(gadget, false);

		gadgetLocalService.initGadget(companyId, gadgetId, name);

		return gadget;
	}

	public void deleteGadget(Gadget gadget)
		throws PortalException, SystemException {

		gadgetLocalService.destroyGadget(
			gadget.getCompanyId(), gadget.getGadgetId(),
			gadget.getName());

		gadgetPersistence.remove(gadget);
	}

	public void deleteGadget(long gadgetId)
		throws PortalException, SystemException {

		Gadget gadget = gadgetPersistence.findByPrimaryKey(gadgetId);

		deleteGadget(gadget);
	}

	@Clusterable
	public void destroyGadget(long companyId, long gadgetId, String name)
		throws PortalException, SystemException {

		try {
			Portlet portlet = getPortlet(companyId, gadgetId, name);

			PortletLocalServiceUtil.destroyRemotePortlet(portlet);

			PortletInstanceFactoryUtil.destroy(portlet);
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

	public void destroyGadgets() throws PortalException, SystemException {
		List<Gadget> gadgets = gadgetPersistence.findAll();

		for (Gadget gadget : gadgets) {
			destroyGadget(
				gadget.getCompanyId(), gadget.getGadgetId(), gadget.getName());
		}
	}

	public List<Gadget> getGadgets(long companyId, int start, int end)
		throws SystemException {

		return gadgetPersistence.findByCompanyId(companyId, start, end);
	}

	public int getGadgetsCount(long companyId) throws SystemException {
		return gadgetPersistence.countByCompanyId(companyId);
	}

	@Clusterable
	public void initGadget(
			long companyId, long gadgetId, String name)
		throws PortalException, SystemException {

		try {
			Portlet portlet = getPortlet(companyId, gadgetId, name);

			PortletLocalServiceUtil.deployRemotePortlet(
				portlet, _OPENSOCIAL_CATEGORY);
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

	public void initGadgets()
		throws PortalException, SystemException {

		List<Gadget> gadgets = gadgetPersistence.findAll();

		for (Gadget gadget : gadgets) {
			initGadget(
				gadget.getCompanyId(), gadget.getGadgetId(), gadget.getName());
		}
	}

	public Gadget updateGadget(long companyId, long gadgetId, String name)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		Gadget gadget = gadgetPersistence.findByPrimaryKey(gadgetId);

		gadget.setModifiedDate(now);
		gadget.setName(name);

		gadgetPersistence.update(gadget, false);

		try {
			Portlet portlet = getPortlet(companyId, gadgetId, name);

			portlet.setPortletInfo(new PortletInfo(name, name, name, name));

			PortletConfigFactoryUtil.update(portlet);
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

		return gadget;
	}

	protected void addPortletExtraInfo(
		Portlet portlet, PortletApp portletApp, String title) {

		Set<String> mimeTypePortletModes = new HashSet<String>();

		mimeTypePortletModes.add(PortletMode.VIEW.toString());

		portlet.getPortletModes().put(
			ContentTypes.TEXT_HTML, mimeTypePortletModes);

		Set<String> mimeTypeWindowStates = new HashSet<String>();

		mimeTypeWindowStates.add(WindowState.MAXIMIZED.toString());
		mimeTypeWindowStates.add(WindowState.MINIMIZED.toString());
		mimeTypeWindowStates.add(WindowState.NORMAL.toString());

		portlet.getWindowStates().put(
			ContentTypes.TEXT_HTML, mimeTypeWindowStates);

		PortletInfo portletInfo = new PortletInfo(title, title, title, title);

		portlet.setPortletInfo(portletInfo);
	}

	protected Portlet getPortlet(long companyId, long gadgetId, String name) 
		throws Exception {

		Portlet portlet = _portletsPool.get(gadgetId);

		if (portlet != null) {
			return portlet;
		}

		StringBuilder sb = new StringBuilder();

		sb.append(GadgetPortlet.PORTLET_NAME_PREFIX);
		sb.append(companyId);
		sb.append(StringPool.UNDERLINE);
		sb.append(gadgetId);

		String portletId = PortalUtil.getJsSafePortletId(sb.toString());

		portlet = PortletLocalServiceUtil.clonePortlet(
			companyId, _GADGET_PORTLET_ID);

		portlet.setPortletId(portletId);
		portlet.setTimestamp(System.currentTimeMillis());

		PortletApp portletApp = PortletLocalServiceUtil.getPortletApp(
			ClpSerializer.SERVLET_CONTEXT_NAME);

		portlet.setPortletApp(portletApp);

		portlet.setPortletName(portletId);
		portlet.setDisplayName(portletId);
		portlet.setPortletClass(GadgetPortlet.class.getName());

		Map<String, String> initParams = portlet.getInitParams();

		initParams.put(
			InvokerPortlet.INIT_INVOKER_PORTLET_NAME, _GADGET_PORTLET_NAME);

		addPortletExtraInfo(portlet, portletApp, name);

		_portletsPool.put(gadgetId, portlet);

		PortletBag portletBag = PortletBagPool.get(_GADGET_PORTLET_ID);

		portletBag = (PortletBag)portletBag.clone();

		portletBag.setPortletName(portletId);
		portletBag.setPortletInstance(new GadgetPortlet());

		PortletBagPool.put(portletId, portletBag);

		return portlet;
	}

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new GadgetNameException();
		}
	}

	private static final String _GADGET_PORTLET_ID = "2_WAR_opensocialportlet";

	private static final String _GADGET_PORTLET_NAME = "2";

	private static final String _OPENSOCIAL_CATEGORY = "category.opensocial";

	private static Map<Long, Portlet> _portletsPool =
		new ConcurrentHashMap<Long, Portlet>();

}