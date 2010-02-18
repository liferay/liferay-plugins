/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.gadgets.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.gadgets.GadgetsEntryNameException;
import com.liferay.gadgets.model.GadgetsEntry;
import com.liferay.gadgets.portlet.GadgetsPortlet;
import com.liferay.gadgets.service.ClpSerializer;
import com.liferay.gadgets.service.base.GadgetsEntryLocalServiceBaseImpl;
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
 * <a href="GadgetsEntryLocalServiceImpl.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Michael Young
 * @author Brian Wing Shun Chan
 */
public class GadgetsEntryLocalServiceImpl
	extends GadgetsEntryLocalServiceBaseImpl {

	public GadgetsEntry addGadgetsEntry(
			long companyId, String name, String url, String xml)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		long gadgetsEntryId = CounterLocalServiceUtil.increment();

		GadgetsEntry gadgetsEntry = gadgetsEntryPersistence.create(
			gadgetsEntryId);

		gadgetsEntry.setCompanyId(companyId);
		gadgetsEntry.setCreateDate(now);
		gadgetsEntry.setModifiedDate(now);
		gadgetsEntry.setName(name);
		gadgetsEntry.setUrl(url);
		gadgetsEntry.setXml(xml);

		gadgetsEntryPersistence.update(gadgetsEntry, false);

		initGadgetsEntry(gadgetsEntry);

		return gadgetsEntry;
	}

	public void deleteGadgetsEntry(GadgetsEntry gadgetsEntry)
		throws SystemException {

		gadgetsEntryPersistence.remove(gadgetsEntry);
	}

	public void deleteGadgetsEntry(long gadgetsEntryId)
		throws PortalException, SystemException {

		GadgetsEntry gadgetsEntry = gadgetsEntryPersistence.findByPrimaryKey(
			gadgetsEntryId);

		deleteGadgetsEntry(gadgetsEntry);
	}

	public void destroyGadgetsEntries()
		throws PortalException, SystemException {

		List<GadgetsEntry> gadgetsEntries = gadgetsEntryPersistence.findAll();

		for (GadgetsEntry gadgetsEntry : gadgetsEntries) {
			destroyGadgetsEntry(gadgetsEntry);
		}
	}

	public List<GadgetsEntry> getGadgetsEntries(
			long companyId, int start, int end)
		throws SystemException {

		return gadgetsEntryPersistence.findByCompanyId(
			companyId, start, end);
	}

	public int getGadgetsEntriesCount(long companyId)
		throws SystemException {

		return gadgetsEntryPersistence.countByCompanyId(companyId);
	}

	public void initGadgetsEntries()
		throws PortalException, SystemException {

		List<GadgetsEntry> gadgetsEntries = gadgetsEntryPersistence.findAll();

		for (GadgetsEntry gadgetsEntry : gadgetsEntries) {
			initGadgetsEntry(gadgetsEntry);
		}
	}

	public GadgetsEntry updateGadgetsEntry(
			long gadgetsEntryId, String name, String xml)
		throws PortalException, SystemException {

		Date now = new Date();

		validate(name);

		GadgetsEntry gadgetsEntry = gadgetsEntryPersistence.create(
			gadgetsEntryId);

		gadgetsEntry.setModifiedDate(now);
		gadgetsEntry.setName(name);
		gadgetsEntry.setXml(xml);

		gadgetsEntryPersistence.update(gadgetsEntry, false);

		return gadgetsEntry;
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

	protected void destroyGadgetsEntry(GadgetsEntry gadgetsEntry)
		throws PortalException, SystemException {

		try {
			Portlet portlet = getPortlet(gadgetsEntry);

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

	protected Portlet getPortlet(GadgetsEntry gadgetsEntry) throws Exception {
		Portlet portlet = _portletsPool.get(gadgetsEntry.getGadgetsEntryId());

		if (portlet != null) {
			return portlet;
		}

		StringBuilder sb = new StringBuilder();

		sb.append(GadgetsPortlet.PORTLET_NAME_PREFIX);
		sb.append(gadgetsEntry.getCompanyId());
		sb.append(StringPool.UNDERLINE);
		sb.append(gadgetsEntry.getGadgetsEntryId());

		String portletId = PortalUtil.getJsSafePortletId(sb.toString());

		portlet = PortletLocalServiceUtil.newPortlet(
			gadgetsEntry.getCompanyId(), portletId);

		portlet.setTimestamp(System.currentTimeMillis());

		PortletApp portletApp = PortletLocalServiceUtil.getPortletApp(
			ClpSerializer.SERVLET_CONTEXT_NAME);

		portlet.setPortletApp(portletApp);

		portlet.setPortletName(portletId);
		portlet.setDisplayName(portletId);
		portlet.setPortletClass(GadgetsPortlet.class.getName());

		Map<String, String> initParams = portlet.getInitParams();

		initParams.put(
			InvokerPortlet.INIT_INVOKER_PORTLET_NAME, _GADGETS_PORTLET_NAME);

		addPortletExtraInfo(portlet, portletApp, gadgetsEntry.getName());

		_portletsPool.put(gadgetsEntry.getGadgetsEntryId(), portlet);

		PortletBag portletBag = PortletBagPool.get(_GADGETS_PORTLET_ID);

		portletBag = (PortletBag)portletBag.clone();

		portletBag.setPortletName(portletId);
		portletBag.setPortletInstance(new GadgetsPortlet());

		PortletBagPool.put(portletId, portletBag);

		return portlet;
	}

	protected void initGadgetsEntry(GadgetsEntry gadgetsEntry)
		throws PortalException, SystemException {

		try {
			Portlet portlet = getPortlet(gadgetsEntry);

			PortletLocalServiceUtil.deployRemotePortlet(
				portlet, _GADGETS_CATEGORY);
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

	protected void validate(String name) throws PortalException {
		if (Validator.isNull(name)) {
			throw new GadgetsEntryNameException();
		}
	}

	private static final String _GADGETS_CATEGORY = "category.gadgets";

	private static final String _GADGETS_PORTLET_ID = "2_WAR_gadgetsportlet";

	private static final String _GADGETS_PORTLET_NAME = "2";

	private static Map<Long, Portlet> _portletsPool =
		new ConcurrentHashMap<Long, Portlet>();

}