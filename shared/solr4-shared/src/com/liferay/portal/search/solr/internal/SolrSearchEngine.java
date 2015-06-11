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

package com.liferay.portal.search.solr.internal;

import com.liferay.portal.kernel.search.BaseSearchEngine;
import com.liferay.portal.kernel.search.IndexSearcher;
import com.liferay.portal.kernel.search.IndexWriter;
import com.liferay.portal.kernel.search.SearchEngine;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Dictionary;

import org.apache.lucene.util.Version;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	immediate = true,
	property = {
		"lucene.version=LUCENE_43", "search.engine.id=SYSTEM_ENGINE",
		"search.engine.impl=Solr"
	},
	service = {SolrSearchEngine.class, SearchEngine.class}
)
public class SolrSearchEngine extends BaseSearchEngine {

	@Override
	public synchronized String backup(long companyId, String backupName)
		throws SearchException {

		return StringPool.BLANK;
	}

	@Override
	public void initialize(long companyId) {
		super.initialize(companyId);
	}

	@Override
	public synchronized void removeBackup(long companyId, String backupName)
		throws SearchException {
	}

	@Override
	public void removeCompany(long companyId) {
		super.removeCompany(companyId);
	}

	@Override
	public synchronized void restore(long companyId, String backupName)
		throws SearchException {
	}

	@Override
	@Reference(target = "(search.engine.impl=Solr)", unbind = "-")
	public void setIndexSearcher(IndexSearcher indexSearcher) {
		super.setIndexSearcher(indexSearcher);
	}

	@Override
	@Reference(target = "(search.engine.impl=Solr)", unbind = "-")
	public void setIndexWriter(IndexWriter indexWriter) {
		super.setIndexWriter(indexWriter);
	}

	@Activate
	protected void activate(ComponentContext componentContext) {
		Dictionary<String, Object> properties =
			componentContext.getProperties();

		String vendor = GetterUtil.getString(
			properties.get("search.engine.impl"));

		setVendor(vendor);

		BundleContext bundleContext = componentContext.getBundleContext();

		String versionString = GetterUtil.getString(
			properties.get("lucene.version"), "LUCENE_43");

		Version version = Version.valueOf(versionString);

		Dictionary<String, Object> versionProperties =
			new HashMapDictionary<>();

		versionProperties.put("search.engine.impl", vendor);

		_serviceRegistration = bundleContext.registerService(
			Version.class, version, versionProperties);
	}

	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			_serviceRegistration.unregister();
		}

		_serviceRegistration = null;
	}

	private volatile ServiceRegistration<Version> _serviceRegistration;

}