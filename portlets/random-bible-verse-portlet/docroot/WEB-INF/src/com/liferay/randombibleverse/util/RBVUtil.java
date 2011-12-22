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

package com.liferay.randombibleverse.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Randomizer;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.randombibleverse.model.Bible;
import com.liferay.randombibleverse.model.Verse;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 */
public class RBVUtil {

	public static Bible getBible(
		PortletPreferences preferences, Locale locale) {

		return _instance._getBible(preferences, locale);
	}

	public static Map<String, Bible> getBibles() {
		return _instance._bibles;
	}

	public static Verse getVerse(
		PortletPreferences preferences, Locale locale) {

		return _instance._getVerse(preferences, locale);
	}

	private RBVUtil() {
		Document doc = null;

		try {
			ClassLoader classLoader = getClass().getClassLoader();

			URL url = classLoader.getResource(
				"com/liferay/randombibleverse/dependencies/" +
					"random_bible_verse.xml");

			doc = SAXReaderUtil.read(url);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_bibles = new LinkedHashMap<String, Bible>();
		_verses = new ArrayList<String>();

		Element root = doc.getRootElement();

		Iterator<Element> itr = root.element("bibles").elements(
			"bible").iterator();

		while (itr.hasNext()) {
			Element bible = itr.next();

			_bibles.put(
				bible.attributeValue("language"),
				new Bible(
					bible.attributeValue("language"),
					bible.attributeValue("language-name"),
					bible.attributeValue("version-id")));
		}

		_bibles = Collections.unmodifiableMap(_bibles);

		itr = root.element("verses").elements("verse").iterator();

		while (itr.hasNext()) {
			Element verse = itr.next();

			_verses.add(verse.attributeValue("location"));
		}

		_verses = new UnmodifiableList<String>(_verses);
	}

	private Bible _getBible(PortletPreferences preferences, Locale locale) {
		Bible bible = _bibles.get(
			preferences.getValue("language", StringPool.BLANK));

		if (bible == null) {
			bible = _bibles.get(locale.getLanguage());
		}

		if (bible == null) {
			bible = _bibles.get("en");
		}

		return bible;
	}

	private Verse _getVerse(PortletPreferences preferences, Locale locale) {
		Bible bible = _getBible(preferences, locale);

		int i = Randomizer.getInstance().nextInt(_verses.size());

		return _getVerse(
			_verses.get(i), bible.getVersionId(), bible.getLanguage());
	}

	private Verse _getVerse(
		String location, String versionId, String language) {

		WebCacheItem webCacheItem = new VerseWebCacheItem(
			location, versionId, language);

		return (Verse)WebCachePoolUtil.get(
			RBVUtil.class.getName() + StringPool.PERIOD + location +
				StringPool.PERIOD + versionId,
			webCacheItem);
	}

	private static Log _log = LogFactoryUtil.getLog(RBVUtil.class);

	private static RBVUtil _instance = new RBVUtil();

	private Map<String, Bible> _bibles;
	private List<String> _verses;

}