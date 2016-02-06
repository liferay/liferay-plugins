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

package com.liferay.alloy.mvc;

import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class AlloySearchResult {

	public List<BaseModel<?>> getBaseModels() throws Exception {
		if (baseModels != null) {
			return baseModels;
		}

		List<BaseModel<?>> baseModels = new ArrayList<>();

		Document[] documents = hits.getDocs();

		for (int i = 0; i < documents.length; i++) {
			Document document = hits.doc(i);

			long entryClassPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			BaseModel<?> baseModel = alloyServiceInvoker.fetchModel(
				entryClassPK);

			if (baseModel == null) {
				continue;
			}

			baseModels.add(baseModel);
		}

		this.baseModels = baseModels;

		return baseModels;
	}

	public Hits getHits() {
		return hits;
	}

	public PortletURL getPortletURL() {
		return portletURL;
	}

	public int getSize() {
		return size;
	}

	protected void afterPropertiesSet() {
		size = hits.getLength();
	}

	protected void setAlloyServiceInvoker(
		AlloyServiceInvoker alloyServiceInvoker) {

		this.alloyServiceInvoker = alloyServiceInvoker;
	}

	protected void setHits(Hits hits) {
		this.hits = hits;
	}

	protected void setPortletURL(PortletURL portletURL) {
		setPortletURL(portletURL, new HashMap<String, Serializable>());
	}

	protected void setPortletURL(
		PortletURL portletURL, Map<String, Serializable> attributes) {

		this.portletURL = portletURL;

		if ((attributes == null) || attributes.isEmpty()) {
			return;
		}

		for (Map.Entry<String, Serializable> entry : attributes.entrySet()) {
			this.portletURL.setParameter(
				entry.getKey(), String.valueOf(entry.getValue()));
		}
	}

	protected AlloyServiceInvoker alloyServiceInvoker;
	protected List<BaseModel<?>> baseModels;
	protected Hits hits;
	protected PortletURL portletURL;
	protected int size;

}