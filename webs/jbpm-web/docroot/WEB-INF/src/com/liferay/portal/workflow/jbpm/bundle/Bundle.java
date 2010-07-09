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