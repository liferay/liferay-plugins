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

package com.liferay.google.apps.connector;

import com.liferay.portal.kernel.xml.Element;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class GetNextItems {

	public GetNextItems(String url, Element atomFeedElement)
		throws GoogleAppsException {

		List<Element> atomLinkElements = atomFeedElement.elements(
			GHelperUtil.getAtomQName("link"));

		for (Element atomLinkElement : atomLinkElements) {
			String rel = atomLinkElement.attributeValue("rel");

			if (rel.equals("next")) {
				String href = atomLinkElement.attributeValue("href");

				if (!href.equals(url)) {
					getNextItems(href);
				}

				break;
			}
		}
	}

	public abstract void getNextItems(String nextURL)
		throws GoogleAppsException;

}