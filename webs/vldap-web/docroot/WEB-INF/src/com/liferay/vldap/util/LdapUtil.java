package com.liferay.vldap.util;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.Company;
import com.liferay.vldap.server.directory.ldap.Directory;

import org.apache.directory.shared.ldap.model.name.Dn;
import org.apache.directory.shared.ldap.model.name.Rdn;


public class LdapUtil {

	public static String createName(
		String top, Company company, String... organizationUnits) {

		StringBundler sb = new StringBundler(
			3 + (organizationUnits.length * 3));

		for (int i = organizationUnits.length - 1; i >= 0; --i) {
			String organizationUnit = organizationUnits[i];

			if (!organizationUnit.startsWith("cn=") &&
				!organizationUnit.startsWith("ou=")) {

				sb.append("ou=");
			}

			sb.append(Directory.escape(organizationUnit));
			sb.append(",");
		}

		sb.append("ou=");
		sb.append(Directory.escape(company.getWebId()));
		sb.append(",o=");
		sb.append(Directory.escape(top));

		return sb.toString();
	}

	public static String getRdnValue(Dn dn, int index) {
		try {
			index = dn.size() - 1 - index;

			if (index < dn.size()) {
				Rdn rdn = dn.getRdn(index);

				return rdn.getValue(rdn.getNormType()).toString();
			}
		}
		catch (Exception e) {
		}

		return null;
	}

}
