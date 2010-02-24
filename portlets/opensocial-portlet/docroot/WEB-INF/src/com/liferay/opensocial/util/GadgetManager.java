/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.opensocial.util;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.portlet.GadgetPortlet;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
 *
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

				String[] viewArray = views.split(",");

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