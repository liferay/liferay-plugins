package com.liferay.portlet.oauth.mvc;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.RequiredFieldException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.oauth.OAuthConstants;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class OAuthApplicationAdminPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		long applicationId = ParamUtil.getLong(request,
				OAuthConstants.WEB_APP_ID);
		
		if (0 != applicationId) {
			try {
				OAuthApplication app = OAuthApplicationLocalServiceUtil
										.fetchOAuthApplication(applicationId);
				
				request.setAttribute(OAuthConstants.WEB_APP_BEAN, app);
			}
			catch (Exception e) {
				if (e instanceof SystemException) {
					SessionErrors.add(request, e.getClass().getName(), e);
				}
				else {
					throw new PortletException(e.fillInStackTrace());
				}
			}
			
		}
		super.render(request, response);
	}
	
	public void deleteOAuthApp(ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws IOException, PortletException {
		long applicationId = ParamUtil.getLong(actionRequest,
				OAuthConstants.WEB_APP_ID);
		
		try {
			if (0 != applicationId) {
				OAuthApplicationLocalServiceUtil.
					deleteOAuthApplication(applicationId);
			}
			else {
				SessionErrors.add(actionRequest,
						"cant-complete-operation-without-id");
			}
		} catch (Exception e) {
			if (e instanceof SystemException) {
				SessionErrors.add(actionRequest,
						e.getClass().getName(), e);
			}
			else {
				throw new PortletException(e.fillInStackTrace());
			}
		}
	}
	
	public void editOAuthApp(ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws IOException, PortletException {
		long applicationId = ParamUtil.getLong(actionRequest,
				OAuthConstants.WEB_APP_ID);
		
		if (0 != applicationId) {
			try {
				OAuthApplication app = OAuthApplicationLocalServiceUtil
										.fetchOAuthApplication(applicationId);
				
				extractData(actionRequest, app);
				
				OAuthApplicationLocalServiceUtil.updateOAuthApplication(app);
				
				actionRequest.setAttribute(OAuthConstants.WEB_APP_BEAN, app);
			}
			catch (Exception e) {
				if (e instanceof SystemException) {
					SessionErrors.add(actionRequest,
							e.getClass().getName(), e);
				}
				else {
					throw new PortletException(e.fillInStackTrace());
				}
			}
		}
		else {
			SessionErrors.add(actionRequest,
					"cant-complete-operation-without-id");
		}
	}

	public void addOAuthApp(ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws IOException, PortletException {
		OAuthApplication app = 
				OAuthApplicationLocalServiceUtil.createOAuthApplication(0L);
		
		try {
			extractData(actionRequest, app);
			
			
			if (Validator.isNull(app.getName())) {
				throw new RequiredFieldException("required-field",
						OAuthConstants.WEB_APP_NAME_ID);
			}
			if (Validator.isNull(app.getCallBackURL())) {
				throw new RequiredFieldException("required-field",
						OAuthConstants.WEB_APP_CALLBACKURL_ID);
			}
			if (!Validator.isUrl(app.getCallBackURL())) {
				throw new MalformedURLException();
			}
			if (Validator.isNull(app.getWebsite())) {
				throw new RequiredFieldException("required-field",
						OAuthConstants.WEB_APP_WEBSITE_ID);
			}
			if (!Validator.isUrl(app.getWebsite())) {
				throw new MalformedURLException();
			}
			
			app = OAuthApplicationLocalServiceUtil
						.addOAuthApplication(app.getAccessLevel(),
								app.getCallBackURL(), app.getDescription(),
								app.getName(), app.getOwnerId(),
								app.getWebsite());
			
			SessionMessages.add(actionRequest,
					OAuthConstants.WEB_APP_REQ_PROCESSED);
		}
		catch (Exception e) {
			if (e instanceof SystemException) {
				e.printStackTrace();
			} else if (e instanceof RequiredFieldException ||
						e instanceof MalformedURLException) {
				SessionErrors.add(actionRequest, e.getClass().getName(), e);
			}
			else {
				throw new PortletException(e.fillInStackTrace());
			}
		}
		
		actionRequest.setAttribute(OAuthConstants.WEB_APP_BEAN, app);
		
	}
	
	
	
	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		super.processAction(actionRequest, actionResponse);
	}

	protected void extractData(ActionRequest actionRequest, OAuthApplication app) {
		app.setAccessLevel(ParamUtil.getInteger(
				actionRequest, OAuthConstants.WEB_APP_ACCESS_TYPE,
				OAuthConstants.ACCESS_TYPE_READ));
		app.setOwnerId(PortalUtil.getUserId(actionRequest));
		app.setCallBackURL(ParamUtil.getString(
				actionRequest, OAuthConstants.WEB_APP_CALLBACKURL));
		app.setDescription(ParamUtil.getString(
				actionRequest, OAuthConstants.WEB_APP_DESCRIPTION));
		app.setName(ParamUtil.getString(
				actionRequest, OAuthConstants.WEB_APP_NAME));
		app.setWebsite(ParamUtil.getString(
				actionRequest, OAuthConstants.WEB_APP_WEBSITE));
	}
}
