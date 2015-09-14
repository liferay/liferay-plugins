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

package com.liferay.incrementalsearch.hook.action;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.FacetedSearcher;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.facet.AssetEntriesFacet;
import com.liferay.portal.kernel.search.facet.Facet;
import com.liferay.portal.kernel.search.facet.ScopeFacet;
import com.liferay.portal.kernel.search.facet.config.FacetConfiguration;
import com.liferay.portal.kernel.search.facet.config.FacetConfigurationUtil;
import com.liferay.portal.kernel.search.facet.util.FacetFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.service.LayoutServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.AssetRendererFactory;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.util.ContentUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.portlet.PortletConfig;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Adolfo Pérez
 */
public class IncrementalSearchAction extends BaseStrutsPortletAction {

	@Override
	public void serveResource(
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		PortletPreferences portletPreferences =
			resourceRequest.getPreferences();

		String searchConfiguration = getSearchConfiguration(portletPreferences);

		Iterable<Document> documents = search(
			resourceRequest, searchConfiguration);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (Document document : documents) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(Field.TITLE, document.get(Field.TITLE));

			JSONArray folderNamesJSONArray = JSONFactoryUtil.createJSONArray();

			String[] folderNames = document.getValues("folderNames");

			ArrayUtil.reverse(folderNames);

			for (String folderName : folderNames) {
				if (Validator.isNotNull(folderName)) {
					folderNamesJSONArray.put(folderName);
				}
			}

			jsonObject.put("folderNames", folderNamesJSONArray);

			String viewURL = getViewURL(
				resourceRequest, resourceResponse, document);

			jsonObject.put("url", viewURL);

			jsonArray.put(jsonObject);
		}

		resourceResponse.setContentType(ContentTypes.APPLICATION_JSON);

		PortletResponseUtil.write(resourceResponse, jsonArray.toString());
	}

	protected String getSearchConfiguration(
		PortletPreferences portletPreferences) {

		String searchConfiguration = portletPreferences.getValue(
			"searchConfiguration", StringPool.BLANK);

		if (Validator.isNull(searchConfiguration)) {
			boolean advancedConfiguration = GetterUtil.getBoolean(
				portletPreferences.getValue("advancedConfiguration", null));

			if (!advancedConfiguration) {
				searchConfiguration = ContentUtil.get(
					PortalClassLoaderUtil.getClassLoader(),
					PropsUtil.get(PropsKeys.SEARCH_FACET_CONFIGURATION));
			}
		}

		return searchConfiguration;
	}

	protected PortletURL getViewFullContentURL(
			HttpServletRequest request, ThemeDisplay themeDisplay,
			String portletId, Document document)
		throws Exception {

		long groupId = GetterUtil.getLong(document.get(Field.GROUP_ID));

		if (groupId == 0) {
			Layout layout = themeDisplay.getLayout();

			groupId = layout.getGroupId();
		}

		long scopeGroupId = GetterUtil.getLong(
			document.get(Field.SCOPE_GROUP_ID));

		if (scopeGroupId == 0) {
			scopeGroupId = themeDisplay.getScopeGroupId();
		}

		long plid = LayoutConstants.DEFAULT_PLID;

		Layout layout = (Layout)request.getAttribute(WebKeys.LAYOUT);

		if (layout != null) {
			plid = layout.getPlid();
		}

		if (plid == 0) {
			plid = LayoutServiceUtil.getDefaultPlid(
				groupId, scopeGroupId, portletId);
		}

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, portletId, plid, PortletRequest.RENDER_PHASE);

		portletURL.setPortletMode(PortletMode.VIEW);
		portletURL.setWindowState(WindowState.MAXIMIZED);

		return portletURL;
	}

	protected String getViewURL(
			PortletRequest portletRequest, PortletResponse portletResponse,
			Document document)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		String className = document.get(Field.ENTRY_CLASS_NAME);

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if (assetRendererFactory == null) {
			String portletId = document.get(Field.PORTLET_ID);

			PortletURL viewFullContentURL = getViewFullContentURL(
				request, themeDisplay, portletId, document);

			return viewFullContentURL.toString();
		}

		long classPK = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));
		long resourcePrimKey = GetterUtil.getLong(
			document.get(Field.ROOT_ENTRY_CLASS_PK));

		if (resourcePrimKey > 0) {
			classPK = resourcePrimKey;
		}

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			className, classPK);

		AssetRenderer assetRenderer = assetRendererFactory.getAssetRenderer(
			classPK);

		PortletURL viewFullContentURL = getViewFullContentURL(
			request, themeDisplay, PortletKeys.ASSET_PUBLISHER, document);

		viewFullContentURL.setParameter(
			"struts_action", "/asset_publisher/view_content");
		viewFullContentURL.setParameter(
			"assetEntryId", String.valueOf(assetEntry.getEntryId()));
		viewFullContentURL.setParameter("type", assetRendererFactory.getType());

		long scopeGroupId = themeDisplay.getScopeGroupId();

		if (Validator.isNotNull(assetRenderer.getUrlTitle())) {
			if ((assetRenderer.getGroupId() > 0) &&
				(assetRenderer.getGroupId() != scopeGroupId)) {

				viewFullContentURL.setParameter(
					"groupId", String.valueOf(assetRenderer.getGroupId()));
			}

			viewFullContentURL.setParameter(
				"urlTitle", assetRenderer.getUrlTitle());
		}

		String viewFullContentURLString = viewFullContentURL.toString();

		return assetRenderer.getURLViewInContext(
			PortalUtil.getLiferayPortletRequest(portletRequest),
			PortalUtil.getLiferayPortletResponse(portletResponse),
			viewFullContentURLString);
	}

	protected Iterable<Document> search(
			ResourceRequest resourceRequest, String searchConfiguration)
		throws Exception {

		Indexer indexer = FacetedSearcher.getInstance();

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			resourceRequest);

		SearchContext searchContext = SearchContextFactory.getInstance(request);

		Facet assetEntriesFacet = new AssetEntriesFacet(searchContext);

		assetEntriesFacet.setStatic(true);

		searchContext.addFacet(assetEntriesFacet);

		Facet scopeFacet = new ScopeFacet(searchContext);

		scopeFacet.setStatic(true);

		searchContext.addFacet(scopeFacet);

		searchContext.setEnd(10);

		String keywords = ParamUtil.getString(request, "keywords");

		searchContext.setKeywords(keywords + StringPool.STAR);

		searchContext.setStart(0);

		List<FacetConfiguration> facetConfigurations =
			FacetConfigurationUtil.load(searchConfiguration);

		for (FacetConfiguration facetConfiguration : facetConfigurations) {
			Facet facet = FacetFactoryUtil.create(
				searchContext, facetConfiguration);

			searchContext.addFacet(facet);
		}

		Hits hits = indexer.search(searchContext);

		if (hits.getLength() == 0) {
			return Collections.emptyList();
		}
		else {
			return Arrays.asList(hits.getDocs());
		}
	}

}