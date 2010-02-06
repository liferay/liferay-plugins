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

package com.liferay.portal.workflow.jbpm.bundle;

import com.liferay.portal.kernel.resource.ResourceRetriever;
import com.liferay.portal.kernel.resource.StringResourceRetriever;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/**
 * <a href="Bundle.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class Bundle {

	public void addBundleEntry(BundleEntry bundleEntry) {
		_bundleEntries.add(bundleEntry);
	}

	public void addBundleEntry(
		String name, ResourceRetriever resourceRetriever) {

		_bundleEntries.add(new BundleEntry(name, resourceRetriever));
	}

	public void addBundleEntry(String name, String bundleEntryContent) {
		addBundleEntry(name, new StringResourceRetriever(bundleEntryContent));
	}

	public void addDefinitionFile(
		ResourceRetriever definitionFileResourceRetriever) {

		addBundleEntry(_DEFINITION_FILE_NAME, definitionFileResourceRetriever);
	}

	public void addDefinitionFile(String definitionFileContent) {
		addBundleEntry(_DEFINITION_FILE_NAME, definitionFileContent);
	}

	public ResourceRetriever getBundleRetriever() throws IOException {
		final byte[] content = getContent();

		return new ResourceRetriever() {

			public InputStream getInputStream() {
				return new ByteArrayInputStream(content);
			}

		};
	}

	public byte[] getContent() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JarOutputStream jos = new JarOutputStream(baos);

		List<BundleEntry> bundleEntries =
			new ArrayList<BundleEntry>(_bundleEntries);

		for (BundleEntry bundleEntry : bundleEntries) {
			JarEntry jarEntry = new JarEntry(bundleEntry.getName());

			jos.putNextEntry(jarEntry);

			BufferedInputStream bis = new BufferedInputStream(
				bundleEntry.getResourceRetriever().getInputStream());

			int i = -1;

			while ((i = bis.read()) != -1) {
				jos.write(i);
			}

			bis.close();
			jos.closeEntry();
		}

		jos.close();
		baos.close();

		return baos.toByteArray();
	}

	private static String _DEFINITION_FILE_NAME = "processdefinition.xml";

	private List<BundleEntry> _bundleEntries = new ArrayList<BundleEntry>();

}