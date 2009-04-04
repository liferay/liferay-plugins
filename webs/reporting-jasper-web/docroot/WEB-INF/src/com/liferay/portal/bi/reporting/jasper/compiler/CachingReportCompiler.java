/*
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

package com.liferay.portal.bi.reporting.jasper.compiler;

import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;

import java.util.Collections;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

import org.apache.commons.collections.map.LRUMap;

/**
 * <a href="CachingReportCompiler.java.html"><b><i>View Source</i></b></a>
 *
 * Decorates a normal report compiler to introducing caching capabilities as
 * report compilations can be expensive.  Thus, this caching compiler will
 * cache a configurable number of compiled reports to increase performance.
 *
 * To avoid compiled reports consuming excessive memory, you should configure
 * the cache with the appropriate cache size. 
 *
 * @author Michael C. Han
 */
public class CachingReportCompiler implements ReportCompiler {

	/**
	 * Creates a caching compiler with a max size of 100
	 *
	 * @param delegate
	 */
	public CachingReportCompiler(ReportCompiler delegate) {
		this(delegate, _DEFAULT_CACHE_SIZE);
	}

	/**
	 * Creates a caching compiler with a configurable max size
	 *
	 * @param delegate
	 */
	public CachingReportCompiler(ReportCompiler delegate, int maxSize) {
		_delegate = delegate;
		_cache = Collections.synchronizedMap(new LRUMap(maxSize));
	}

	public JasperReport compile(ReportDesignRetriever retriever)
		throws JRException {
		String reportName = retriever.getReportName();
		JasperReport report =
			(JasperReport) _cache.get(reportName);
		if (report == null) {
			report = _delegate.compile(retriever);
			_cache.put(reportName, report);
		}
		return report;
	}

	private static final int _DEFAULT_CACHE_SIZE = 25;
	private ReportCompiler _delegate;
	private Map _cache;
}
