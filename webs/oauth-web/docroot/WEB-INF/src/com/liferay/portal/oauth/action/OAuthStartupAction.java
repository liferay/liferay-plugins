package com.liferay.portal.oauth.action;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.NoSuchRoleException;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * @author Igor Beslic
 */
public class OAuthStartupAction extends SimpleAction {

	@Override
	public void run(String[] ids) throws ActionException {
		if (_log.isDebugEnabled()) {
			_log.debug("Checking new roles to support OAuth...");
		}
		
		try {
			List<Company> companies = CompanyLocalServiceUtil.getCompanies();
			

			for (Company company : companies) {
				User defaultUser = UserLocalServiceUtil.getDefaultUser(
						company.getCompanyId());
				for (int i=0; i < DEFAULT_OAUTH_ROLES[0].length; i++) {
					try {
						RoleLocalServiceUtil.getRole(company.getCompanyId(),
								DEFAULT_OAUTH_ROLES[0][i]);
					}
					catch (NoSuchRoleException nsre) {
						Map<Locale, String> descriptionMap =
							new HashMap<Locale, String>();

						descriptionMap.put(LocaleUtil.getDefault(),
								DEFAULT_OAUTH_ROLES[1][i]);

						RoleLocalServiceUtil.addRole(
							defaultUser.getUserId(), company.getCompanyId(),
							DEFAULT_OAUTH_ROLES[0][i], null, descriptionMap,
							RoleConstants.TYPE_REGULAR);
						
						if (_log.isInfoEnabled()) {
							_log.info(DEFAULT_OAUTH_ROLES[0][i]
									.concat(" role succesfuly added!"));
						}
					}
				}	
			}
		} catch (Exception e) {
			if (e instanceof SystemException ||
				e instanceof PortalException	) {
				throw new ActionException(e);
			}
			else {
				e.printStackTrace();
			}
		}
	}
	
	private static String [][] DEFAULT_OAUTH_ROLES = {
		{"OAuth User"},
		{"OAuth User Role is automaticaly added by OAuth web plugin"}
	};

	private static Log _log = LogFactoryUtil.getLog(OAuthStartupAction.class);
}
