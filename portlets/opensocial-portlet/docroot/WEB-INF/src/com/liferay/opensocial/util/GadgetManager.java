/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.util;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.portlet.GadgetPortlet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Node;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="GadgetManager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 */
public class GadgetManager {

	public GadgetManager(Gadget gadget) throws Exception {
		String xml = gadget.getXml();

		try {
			Document document = SAXReaderUtil.read(xml);

			Element moduleElement = document.getRootElement();

			_readGadgetXml(moduleElement);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			throw e;
		}
	}

	public String getContent(String view) {
		String content = _contents.get(view);

		if (content == null) {
			content = _contents.get("default");
		}

		return content;
	}

	private void _readGadgetXml(Element moduleElement) {
		List<Node> contentNodes  = moduleElement.selectNodes("//Content");

		String content = null;

		for (Node contentNode : contentNodes) {
			Element contentElement = (Element)contentNode;

			Attribute viewAttribute = contentElement.attribute("view");

			content = contentElement.getText();

			if (viewAttribute == null) {
				_contents.put(GadgetPortlet.VIEW_DEFAULT, content);
			}
			else {
				String views = viewAttribute.getText();

				String[] viewArray = views.split(StringPool.COMMA);

				for (String view : viewArray) {
					view = view.toLowerCase().trim();

					_contents.put(view, content);
				}
			}
		}

		// Make sure we have content for default view

		content = getContent(GadgetPortlet.VIEW_DEFAULT);

		if (content == null) {
			content = getContent(GadgetPortlet.VIEW_PROFILE);
		}

		if (content == null) {
			content = getContent(GadgetPortlet.VIEW_CANVAS);
		}

		if (content == null) {
			content = getContent(GadgetPortlet.VIEW_HOME);
		}

		if (content != null) {
			_contents.put(GadgetPortlet.VIEW_DEFAULT, content);
		}

		// Fall back for home and profile views

		content = getContent(GadgetPortlet.VIEW_HOME);

		if (content == null) {
			content = getContent(GadgetPortlet.VIEW_PROFILE);
		}

		if (content != null) {
			_contents.put(GadgetPortlet.VIEW_HOME, content);
		}

		content = getContent(GadgetPortlet.VIEW_PROFILE);

		if (content == null) {
			content = getContent(GadgetPortlet.VIEW_HOME);
		}

		if (content != null) {
			_contents.put(GadgetPortlet.VIEW_PROFILE, content);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(GadgetManager.class);

	private Map<String, String> _contents = new HashMap<String, String>();

}