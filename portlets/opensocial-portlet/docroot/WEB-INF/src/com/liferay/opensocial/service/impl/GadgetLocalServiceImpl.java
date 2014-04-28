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

package com.liferay.opensocial.service.impl;

import com.liferay.opensocial.DuplicateGadgetURLException;
import com.liferay.opensocial.GadgetPortletCategoryNamesException;
import com.liferay.opensocial.GadgetURLException;
import com.liferay.opensocial.NoSuchGadgetException;
import com.liferay.opensocial.gadget.portlet.GadgetPortlet;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.model.impl.GadgetConstants;
import com.liferay.opensocial.service.ClpSerializer;
import com.liferay.opensocial.service.base.GadgetLocalServiceBaseImpl;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletBag;
import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.PortletApp;
import com.liferay.portal.model.PortletInfo;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.InvokerPortlet;
import com.liferay.portlet.PortletInstanceFactoryUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.portlet.PortletMode;
import javax.portlet.WindowState;

import org.apache.shindig.gadgets.spec.GadgetSpec;
import org.apache.shindig.gadgets.spec.ModulePrefs;

/**
 * @author Michael Young
 * @author Brian Wing Shun Chan
 * @author Dennis Ju
 */
public class GadgetLocalServiceImpl extends GadgetLocalServiceBaseImpl {

	public Gadget addGadget(
			long companyId, String url, String portletCategoryNames,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(companyId, url, portletCategoryNames);

		long gadgetId = counterLocalService.increment();

		Gadget gadget = gadgetPersistence.create(gadgetId);

		gadget.setUuid(serviceContext.getUuid());
		gadget.setCompanyId(companyId);
		gadget.setCreateDate(now);
		gadget.setModifiedDate(now);

		GadgetSpec gadgetSpec = null;

		try {
			gadgetSpec = ShindigUtil.getGadgetSpec(url);
		}
		catch (Exception e) {
			throw new GadgetURLException(e);
		}

		ModulePrefs modulePrefs = gadgetSpec.getModulePrefs();

		gadget.setName(modulePrefs.getTitle());
		gadget.setUrl(url);
		gadget.setPortletCategoryNames(portletCategoryNames);

		gadgetPersistence.update(gadget);

		gadgetLocalService.initGadget(
			gadget.getUuid(), companyId, gadgetId, gadget.getName(),
			gadget.getPortletCategoryNames());

		return gadget;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public Gadget deleteGadget(Gadget gadget) throws SystemException {

		// Gadget

		gadgetLocalService.destroyGadget(
			gadget.getUuid(), gadget.getCompanyId());

		gadgetPersistence.remove(gadget);

		// OAuth consumer

		String gadgetKey = GadgetConstants.toPublishedGadgetKey(
			gadget.getGadgetId());

		oAuthConsumerLocalService.deleteOAuthConsumers(gadgetKey);

		return gadget;
	}

	@Override
	public Gadget deleteGadget(long gadgetId)
		throws PortalException, SystemException {

		Gadget gadget = gadgetPersistence.findByPrimaryKey(gadgetId);

		return deleteGadget(gadget);
	}

	@Override
	public void deleteGadgets(long companyId) throws SystemException {
		List<Gadget> gadgets = gadgetPersistence.findByCompanyId(companyId);

		for (Gadget gadget : gadgets) {
			gadgetLocalService.deleteGadget(gadget);
		}
	}

	@Clusterable
	public void destroyGadget(String uuid, long companyId)
		throws SystemException {

		try {
			Portlet portlet = _portletsPool.remove(uuid);

			if (portlet == null) {
				portlet = PortletLocalServiceUtil.getPortletById(
					companyId, getPortletId(uuid));
			}

			PortletInstanceFactoryUtil.destroy(portlet);
		}
		catch (SystemException se) {
			throw se;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public void destroyGadgets() throws SystemException {
		List<Gadget> gadgets = gadgetPersistence.findAll();

		for (Gadget gadget : gadgets) {
			destroyGadget(gadget.getUuid(), gadget.getCompanyId());
		}
	}

	public Gadget fetchGadget(long companyId, String url)
		throws SystemException {

		return gadgetPersistence.fetchByC_U(companyId, url);
	}

	public Gadget getGadget(long companyId, String url)
		throws PortalException, SystemException {

		return gadgetPersistence.findByC_U(companyId, url);
	}

	public Gadget getGadget(String uuid, long companyId)
		throws PortalException, SystemException {

		List<Gadget> gadgets = gadgetPersistence.findByUuid_C(uuid, companyId);

		if (gadgets.isEmpty()) {
			throw new NoSuchGadgetException(
				"No gadget exists with uuid " + uuid);
		}

		return gadgets.get(0);
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
			String uuid, long companyId, long gadgetId, String name,
			String portletCategoryNames)
		throws PortalException, SystemException {

		try {
			Portlet portlet = getPortlet(uuid, companyId, name);

			String[] portletCategoryNamesArray = StringUtil.split(
				portletCategoryNames);

			PortletLocalServiceUtil.deployRemotePortlet(
				portlet, portletCategoryNamesArray);
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

	public void initGadgets() throws PortalException, SystemException {
		List<Gadget> gadgets = gadgetPersistence.findAll();

		for (Gadget gadget : gadgets) {
			initGadget(
				gadget.getUuid(), gadget.getCompanyId(), gadget.getGadgetId(),
				gadget.getName(), gadget.getPortletCategoryNames());
		}
	}

	public Gadget updateGadget(long gadgetId, String portletCategoryNames)
		throws PortalException, SystemException {

		validate(portletCategoryNames);

		Gadget gadget = gadgetPersistence.findByPrimaryKey(gadgetId);

		gadget.setPortletCategoryNames(portletCategoryNames);

		gadgetPersistence.update(gadget);

		gadgetLocalService.initGadget(
			gadget.getUuid(), gadget.getCompanyId(), gadgetId, gadget.getName(),
			gadget.getPortletCategoryNames());

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

	protected Portlet getPortlet(String uuid, long companyId, String name)
		throws Exception {

		Portlet portlet = _portletsPool.get(uuid);

		if (portlet != null) {
			return portlet;
		}

		String portletId = getPortletId(uuid);

		portlet = PortletLocalServiceUtil.clonePortlet(_GADGET_PORTLET_ID);

		portlet.setCompanyId(companyId);
		portlet.setPortletId(portletId);
		portlet.setTimestamp(System.currentTimeMillis());

		PortletApp portletApp = PortletLocalServiceUtil.getPortletApp(
			ClpSerializer.getServletContextName());

		portlet.setPortletApp(portletApp);

		portlet.setPortletName(portletId);
		portlet.setDisplayName(portletId);
		portlet.setPortletClass(GadgetPortlet.class.getName());

		Map<String, String> initParams = portlet.getInitParams();

		initParams.put(
			InvokerPortlet.INIT_INVOKER_PORTLET_NAME, _GADGET_PORTLET_NAME);

		addPortletExtraInfo(portlet, portletApp, name);

		_portletsPool.put(uuid, portlet);

		PortletBag portletBag = PortletBagPool.get(_GADGET_PORTLET_ID);

		portletBag = (PortletBag)portletBag.clone();

		portletBag.setPortletName(portletId);
		portletBag.setPortletInstance(new GadgetPortlet());

		PortletBagPool.put(portletId, portletBag);

		return portlet;
	}

	protected String getPortletId(String uuid) {
		String portletId = GadgetPortlet.PORTLET_NAME_PREFIX.concat(uuid);

		portletId = PortalUtil.getJsSafePortletId(
			PortalUUIDUtil.toJsSafeUuid(portletId));

		return portletId;
	}

	protected void validate(
			long companyId, String url, String portletCategoryNames)
		throws PortalException, SystemException {

		Gadget gadget = gadgetPersistence.fetchByC_U(companyId, url);

		if (gadget != null) {
			throw new DuplicateGadgetURLException();
		}

		validate(portletCategoryNames);
	}

	protected void validate(String portletCategoryNames)
		throws PortalException {

		if (Validator.isNull(portletCategoryNames)) {
			throw new GadgetPortletCategoryNamesException();
		}
	}

	private static final String _GADGET_PORTLET_ID = "2_WAR_opensocialportlet";

	private static final String _GADGET_PORTLET_NAME = "2";

	private static Map<String, Portlet> _portletsPool =
		new ConcurrentHashMap<String, Portlet>();

}