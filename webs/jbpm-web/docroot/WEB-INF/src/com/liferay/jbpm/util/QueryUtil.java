/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.jbpm.util;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Randomizer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.dialect.Dialect;

/**
 * <a href="QueryUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class QueryUtil {

	public static final int ALL_POS = -1;

	public static Iterator<?> iterate(
		Query query, Dialect dialect, int begin, int end) {

		return list(query, dialect, begin, end).iterator();
	}

	public static List<?> list(
		Query query, Dialect dialect, int begin, int end) {

		if ((begin == ALL_POS) && (end == ALL_POS)) {
			return query.list();
		}
		else {
			if (dialect.supportsLimit()) {
				query.setMaxResults(end - begin);
				query.setFirstResult(begin);

				return query.list();
			}
			else {
				List<Object> list = new ArrayList<Object>();

				ScrollableResults sr = query.scroll();

				if (sr.first() && sr.scroll(begin)) {
					for (int i = begin; i < end; i++) {
						Object obj = sr.get(0);

						list.add(obj);

						if (!sr.next()) {
							break;
						}
					}
				}

				return list;
			}
		}
	}

	public static List<?> randomList(
		Query query, Dialect dialect, int total, int num) {

		if ((total == 0) || (num == 0)) {
			return new ArrayList<Object>();
		}

		if (num >= total) {
			return list(query, dialect, ALL_POS, ALL_POS);
		}

		int[] scrollIds = Randomizer.getInstance().nextInt(total, num);

		List<Object> list = new ArrayList<Object>();

		ScrollableResults sr = query.scroll();

		for (int i = 0; i < scrollIds.length; i++) {
			if (sr.scroll(scrollIds[i])) {
				Object obj = sr.get(0);

				list.add(obj);

				sr.first();
			}
		}

		return list;
	}

	public static Comparable<?>[] getPrevAndNext(
		Query query, int count, OrderByComparator obc,
		Comparable<?> comparable) {

		int pos = count;
		int boundary = 0;

		Comparable<?>[] array = new Comparable[3];

		ScrollableResults sr = query.scroll();

		if (sr.first()) {
			while (true) {
				Object obj = sr.get(0);

				if (obj == null) {
					if (_log.isWarnEnabled()) {
						_log.warn("Object is null");
					}

					break;
				}

				Comparable<?> curComparable = (Comparable<?>)obj;

				int value = obc.compare(comparable, curComparable);

				if (_log.isDebugEnabled()) {
					_log.debug("Comparison result is " + value);
				}

				if (value == 0) {
					if (!comparable.equals(curComparable)) {
						break;
					}

					array[1] = curComparable;

					if (sr.previous()) {
						array[0] = (Comparable<?>)sr.get(0);
					}

					sr.next();

					if (sr.next()) {
						array[2] = (Comparable<?>)sr.get(0);
					}

					break;
				}

				if (pos == 1) {
					break;
				}

				pos = (int)Math.ceil(pos / 2.0);

				int scrollPos = pos;

				if (value < 0) {
					scrollPos = scrollPos * -1;
				}

				boundary += scrollPos;

				if (boundary < 0) {
					scrollPos = scrollPos + (boundary * -1) + 1;

					boundary = 0;
				}

				if (boundary > count) {
					scrollPos = scrollPos - (boundary - count);

					boundary = scrollPos;
				}

				if (_log.isDebugEnabled()) {
					_log.debug("Scroll " + scrollPos);
				}

				if (!sr.scroll(scrollPos)) {
					if (value < 0) {
						if (!sr.next()) {
							break;
						}
					}
					else {
						if (!sr.previous()) {
							break;
						}
					}
				}
			}
		}

		return array;
	}

	private static Log _log = LogFactory.getLog(QueryUtil.class);

}