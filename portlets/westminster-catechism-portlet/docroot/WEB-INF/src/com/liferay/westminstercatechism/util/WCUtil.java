/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.westminstercatechism.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.westminstercatechism.model.WCEntry;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="WCUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WCUtil {

	public static List<WCEntry> getLarger() {
		return _instance._getLarger();
	}

	public static List<WCEntry> getShorter() {
		return _instance._getShorter();
	}

	public static String translate(String text) {
		return StringUtil.replace(
			text,
			new String[] {
				" doth ", " hath "
			},
			new String[] {
				" does ", " has "
			}
		);
	}

	private WCUtil() {
		Document doc = null;

		try {
			ClassLoader classLoader = getClass().getClassLoader();

			URL url = classLoader.getResource(
				"com/liferay/westminstercatechism/dependencies/" +
					"westminster_catechmism.xml");

			doc = SAXReaderUtil.read(url);
		}
		catch (DocumentException de) {
			_log.error(de, de);
		}

		_shorter = new ArrayList<WCEntry>();

		Element root = doc.getRootElement();

		Iterator<Element> itr1 = root.element("shorter").elements(
			"entry").iterator();

		while (itr1.hasNext()) {
			Element entry = itr1.next();

			List<String[]> proofs = new ArrayList<String[]>();

			Iterator<Element> itr2 = entry.element(
				"proofs").elements("scriptures").iterator();

			while (itr2.hasNext()) {
				Element scriptures = itr2.next();

				proofs.add(StringUtil.split(
					scriptures.getText(), StringPool.SEMICOLON));
			}

			_shorter.add(
				new WCEntry(
					entry.elementText("question"),
					entry.elementText("answer"),
					proofs.toArray(new String[0][0])));
		}

		_shorter = Collections.unmodifiableList(_shorter);

		_larger = new ArrayList<WCEntry>();

		itr1 = root.element("larger").elements("entry").iterator();

		while (itr1.hasNext()) {
			Element entry = itr1.next();

			List<String[]> proofs = new ArrayList<String[]>();

			Iterator<Element> itr2 = entry.element(
				"proofs").elements("scriptures").iterator();

			while (itr2.hasNext()) {
				Element scriptures = itr2.next();

				proofs.add(StringUtil.split(
					scriptures.getText(), StringPool.SEMICOLON));
			}

			_larger.add(
				new WCEntry(
					entry.elementText("question"),
					entry.elementText("answer"),
					proofs.toArray(new String[0][0])));
		}

		_larger = Collections.unmodifiableList(_larger);
	}

	private List<WCEntry> _getLarger() {
		return _larger;
	}

	private List<WCEntry> _getShorter() {
		return _shorter;
	}

	private static Log _log = LogFactoryUtil.getLog(WCUtil.class);

	private static WCUtil _instance = new WCUtil();

	private List<WCEntry> _larger;
	private List<WCEntry> _shorter;

}