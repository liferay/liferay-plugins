package com.liferay.portlet.oauth.mvc;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.oauth.model.OAuthApplication;
import com.liferay.portal.oauth.service.OAuthApplicationLocalServiceUtil;
import com.liferay.portlet.oauth.OAuthConstants;
import com.liferay.portlet.oauth.simulator.LiferayApi;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class OAuthSimulatorPortlet extends MVCPortlet {
	
	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws PortletException, IOException {
		super.render(request, response);
	}
	
	public void addOAuthorization(ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws IOException, PortletException {
		
		long applicationId = ParamUtil.getLong(actionRequest,
				OAuthConstants.WEB_APP_ID);
		
		if (0 != applicationId) {
			try {
				OAuthApplication app = OAuthApplicationLocalServiceUtil
										.fetchOAuthApplication(applicationId);
				
				OAuthService oAuthService = new ServiceBuilder()
					.provider(LiferayApi.class)
					.apiKey(app.getConsumerKey())
					.apiSecret(app.getConsumerSecret()).build();
				
				Token requestToken = oAuthService.getRequestToken();
				
				actionRequest.setAttribute("oauth-simulator-url",
						LiferayApi.AUTHORIZE_URL.replace(
						"%s", requestToken.getToken()));
				actionRequest.setAttribute("oauth-simulator-token",
						requestToken.getToken());
				actionRequest.setAttribute("oauth-simulator-secret",
						requestToken.getSecret());
				actionRequest.setAttribute("applicationId",
						Long.toString(applicationId));
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
	
	public void verifyOAuthorization(ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws IOException, PortletException {
		long applicationId = ParamUtil.getLong(actionRequest,
				OAuthConstants.WEB_APP_ID);
		
		if (0 != applicationId) {
			try {
				OAuthApplication app = OAuthApplicationLocalServiceUtil
										.fetchOAuthApplication(applicationId);
				
				OAuthService oAuthService = new ServiceBuilder()
					.provider(LiferayApi.class)
					.apiKey(app.getConsumerKey())
					.apiSecret(app.getConsumerSecret()).build();
				
				String tokenString = ParamUtil.getString(actionRequest, "oauth-simulator-token");
				String secretString = ParamUtil.getString(actionRequest, "oauth-simulator-secret");
				String verifierString = ParamUtil.getString(actionRequest, "oauth-simulator-verifier");
				
				Verifier verifier = new Verifier(verifierString);
				
				Token requestToken = new Token(tokenString, secretString);
				
				Token accessToken = oAuthService.getAccessToken(requestToken, verifier);
				
				System.out.println(accessToken.getToken());
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
	
	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {
		super.processAction(actionRequest, actionResponse);
	}
}
