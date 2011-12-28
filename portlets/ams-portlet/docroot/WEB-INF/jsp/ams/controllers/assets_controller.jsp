<%--
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
--%>

<%@ include file="/WEB-INF/jsp/ams/controllers/init.jsp" %>

<%@ page import="com.liferay.ams.model.Asset" %>
<%@ page import="com.liferay.ams.model.impl.AssetImpl" %>
<%@ page import="com.liferay.ams.service.AssetLocalServiceUtil" %>
<%@ page import="com.liferay.counter.service.CounterLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %>

<%!
public class AlloyControllerImpl extends BaseAlloyControllerImpl {

	public void delete() throws Exception {
		long assetId = ParamUtil.getLong(request, "id");

		AssetLocalServiceUtil.deleteAsset(assetId);

		addSuccessMessage();

		PortletURL redirectURL = liferayPortletResponse.createRenderURL();

		redirectTo(redirectURL);
	}

	public void index() throws Exception {
		List<Asset> assets = AssetLocalServiceUtil.getAssets(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		renderRequest.setAttribute("assets", assets);
	}

	public void save() throws Exception {
		Asset asset = new AssetImpl();

		BeanPropertiesUtil.setProperties(asset, request);

		if (asset.isNew()) {
			asset.setPrimaryKey(CounterLocalServiceUtil.increment());

			AssetLocalServiceUtil.addAsset(asset);
		}

		addSuccessMessage();

		String redirect = ParamUtil.getString(request, "redirect");

		redirectTo(redirect);
	}

	public void view() throws Exception {
		long assetId = ParamUtil.getLong(request, "id");

		Asset asset = AssetLocalServiceUtil.getAsset(assetId);

		renderRequest.setAttribute("asset", asset);
	}

}
%>