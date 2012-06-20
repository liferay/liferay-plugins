package com.liferay.sample.authverifier;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.security.auth.verifier.AuthVerifier;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthenticationContext;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.security.auth.verifier.AuthVerifier;
import com.liferay.portal.security.auth.verifier.VerificationResult;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

import java.util.Properties;

/**
 * {@link AuthVerifier}s are used for obtaining user from request based on
 * a provided token, regardless of a nature of the token.<br />
 * <br />
 * This sample verifier returns admin account if request contains secret key,
 * configurable from hook's portal.properties file.<br />
 * <br />
 * <strong>Don't use in production, only for education purpose, security
 * of this solution is weak!</strong>
 *
 * @author Tomas Polesovsky
 */
public class SampleAuthVerifier implements AuthVerifier {

	public VerificationResult verify(
			AuthenticationContext authenticationContext,
			Properties configuration)
		throws AuthException {

		VerificationResult result = new VerificationResult();

		String keyFromConfiguration = configuration.getProperty("key");

		String keyFromRequest = ParamUtil.get(
			authenticationContext.getHttpServletRequest(), "key",
			StringPool.BLANK);

		if(!keyFromConfiguration.equals(keyFromRequest)){
			return result;
		}

		long companyId = PortalUtil.getCompanyId(authenticationContext.
			getHttpServletRequest());
		try {
			Role adminRole = RoleLocalServiceUtil.getRole(companyId,
				"Administrator");
			long[] adminIds = UserLocalServiceUtil.getRoleUserIds(
				adminRole.getRoleId());

			long adminId = adminIds.length > 0 ? adminIds[0] : 0;
			result.setState(VerificationResult.State.SUCCESS);
			result.setUserId(adminId);

		} catch (Exception e) {
			throw new AuthException(e.getMessage(), e);
		}

		return result;
	}
}
