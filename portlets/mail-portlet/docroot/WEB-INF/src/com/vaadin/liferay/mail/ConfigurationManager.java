
package com.vaadin.liferay.mail;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.util.portlet.PortletProps;

/**
 * Manager for configuration information such as port lists. This is based on
 * ConfigurationActionImpl.
 * 
 * @author Henri Sara
 */
public class ConfigurationManager {

	public List<Integer> getIncomingPorts() {

		return getIntegerList(PortletProps.get("incoming.ports"));
	}

	public List<Integer> getOutgoingPorts() {

		return getIntegerList(PortletProps.get("outgoing.ports"));
	}

	private List<Integer> getIntegerList(String propertyValue) {

		List<Integer> result = new ArrayList<Integer>();
		int[] values = GetterUtil.getIntegerValues(propertyValue.split(","));
		for (int value : values) {
			result.add(value);
		}
		return result;
	}

}
