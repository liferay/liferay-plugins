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

package com.liferay.kb.util;

import com.liferay.portal.kernel.zip.ZipReader;

import java.io.File;
import java.io.StringReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * <a href="DocBookEntityResolver.java.html"><b><i>View Source</i></b></a>
 *
 * @author Alvaro del Castillo
 * @author Bruno Farache
 *
 */
public class DocBookEntityResolver  implements EntityResolver {

	public DocBookEntityResolver(String rootPath, ZipReader zipReader) {
		_rootDirZip = rootPath;
		_zipReader = zipReader;
	}

	public InputSource resolveEntity(String publicId, String systemId) {
		if (_log.isDebugEnabled()) {
			_log.debug("Found Entity: " + systemId);
		}

		if (systemId.contains("file:///")) {
			File zipEntity = new File(systemId);

			String zipPath = _rootDirZip + File.separator +
				zipEntity.getParentFile().getName() + File.separator +
					zipEntity.getName();

			String contents = _zipReader.getEntryAsString(zipPath);

			if (contents != null) {
				return new InputSource(new StringReader(contents.substring(1)));
			}

			return null;
		}
		// For testing from command line
		else if (systemId.endsWith("xml")) {
			String zipPath = _rootDirZip + File.separator +
				systemId.replace("\\", "/");

			String contents = _zipReader.getEntryAsString(zipPath);

			if (contents != null) {
				if (_log.isDebugEnabled()) {
					_log.debug("Testing Entity: " + zipPath);
				}

				return new InputSource(new StringReader(contents.substring(1)));
			}

			return null;
		}
		else {
			// use the default behavior and return null to bypass inet connetion
			// for DTDs

			return new InputSource(new StringReader(""));
		}
	}

	private static Log _log = LogFactory.getLog(DocBookEntityResolver.class);

	private ZipReader _zipReader;

	private String _rootDirZip;

}