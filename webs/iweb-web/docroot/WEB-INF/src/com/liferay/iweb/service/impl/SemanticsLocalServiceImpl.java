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

/**
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.sun.com/cddl/cddl.html and
 * legal/CDDLv1.0.txt. See the License for the specific language governing
 * permission and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at legal/CDDLv1.0.txt.
 *
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Copyright 2008 Sun Microsystems Inc. All rights reserved.
 */

package com.liferay.iweb.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Set;
import java.util.TreeSet;

import org.mindswap.pellet.owlapi.Reasoner;
import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.io.StringInputSource;
import org.semanticweb.owl.model.OWLClass;
import org.semanticweb.owl.model.OWLDescription;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyCreationException;
import org.semanticweb.owl.model.OWLOntologyManager;

import com.liferay.iweb.IWebException;
import com.liferay.iweb.model.Community;
import com.liferay.iweb.model.Semantics;
import com.liferay.iweb.model.SemanticsElement;
import com.liferay.iweb.model.impl.SemanticsElementImpl;
import com.liferay.iweb.service.IWebCallBackLocalServiceUtil;
import com.liferay.iweb.service.base.SemanticsLocalServiceBaseImpl;
import com.liferay.portal.SystemException;

/**
 * <a href="SemanticsLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 * @author Prakash Radhakrishnan
 *
 */

public class SemanticsLocalServiceImpl extends SemanticsLocalServiceBaseImpl {

	public void createNewSemantics() throws IWebException {
		throw new IWebException("Operation not supported yet.");
	}

	public void deleteSemantics() throws IWebException {
		throw new IWebException("Operation not supported yet.");
	}

	public Set<Semantics> getAllSemanticElements(Set<Semantics> semantics)
		throws IWebException {
		Set<Semantics> updatedSemantics = new TreeSet();
		Set<OWLClass> owlclasses;
		for (Semantics sem : semantics) {
			String semuri = sem.getSemanticsURI();
			Set<SemanticsElement> semelements = new TreeSet();
			try {
				OWLOntology ont = _loadOntology(semuri);
				owlclasses = ont.getReferencedClasses();
				for (OWLClass owlclass : owlclasses) {
					SemanticsElement element = new SemanticsElementImpl();
					String elementuri = owlclass.getURI().toString();
					element.setElementURI(elementuri);
					if (elementuri.indexOf("#") != -1) {
						element.setSemanticsURI(
							elementuri.substring(0,elementuri.indexOf("#")));

					}
					semelements.add(element);
				}
				sem.setSemanticselements(semelements);
				updatedSemantics.add(sem);
			}
			catch (OWLOntologyCreationException e) {
				throw new IWebException(e);
			}
		}
		return updatedSemantics;
	}

	public Set<Semantics> getAvailablePublicSemantics() throws IWebException {
		try {
			Set<Semantics> semanticsSet = new TreeSet(semanticsPersistence
				.findAll());
			Set<Semantics> privateSemantics = new TreeSet();
			for (Semantics semantics : semanticsSet) {
				long communityId = semantics.getCreatedInCommunity();
				int createdInCommunityType = 0;
				Community comm = communityPersistence.fetchByPrimaryKey(
					communityId);

				if (comm != null) {
					createdInCommunityType = comm.getType();
				}
				if (createdInCommunityType == 0) {
					createdInCommunityType =
						IWebCallBackLocalServiceUtil.getCommunityType(
							communityId);

				}
				if (createdInCommunityType == 3) {
					privateSemantics.add(semantics);
				}
			}
			semanticsSet.removeAll(privateSemantics);
			return semanticsSet;
		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	public Set<SemanticsElement> getRelatedSemanticsElements(
		Set<SemanticsElement> elements, boolean reason) throws IWebException {

		try {
			Set<SemanticsElement> relatedSemanticsElements = new TreeSet();
			for (SemanticsElement element : elements) {
				String semanticsuri = element.getSemanticsURI();
				String elementuri = element.getElementURI();
				if (semanticsuri == null || semanticsuri.equals("")) {
					if (elementuri.indexOf("#") != -1) {
						semanticsuri = elementuri.substring(
							0, elementuri.indexOf("#"));

					}
				}
				OWLOntology ontology = _loadOntology(semanticsuri);
				Set<OWLClass> ontologyClasses = ontology.getReferencedClasses();
				for (OWLClass owl : ontologyClasses) {
					if (reason) {
						Reasoner reasoner = new Reasoner(_manager);
						reasoner.loadOntology(ontology);
						if (owl.getURI().toString().equals(elementuri)) {
							Set<Set<OWLClass>> subclassSet =
								reasoner.getSubClasses(owl);

							for (Set<OWLClass> owlClasses : subclassSet) {
								for (OWLClass owlClass : owlClasses) {
									SemanticsElement semanticsElement =
										new SemanticsElementImpl();

									String uri = owlClass.getURI().toString();
									semanticsElement.setElementURI(uri);
									if (uri.indexOf("#") != -1) {
										semanticsElement.setSemanticsURI(
											uri.substring(
												0, uri.indexOf("#")));

									}
									relatedSemanticsElements.add(element);
									relatedSemanticsElements.add(
										semanticsElement);

								}
							}
						}
					}
					else {
						if (owl.getURI().toString().equals(elementuri)) {
							Set<OWLDescription> owlSubClasses =
								owl.getSubClasses(ontology);

							for (OWLDescription desc : owlSubClasses) {
								SemanticsElement semanticsElement =
									new SemanticsElementImpl();

								String uri =
									desc.asOWLClass().getURI().toString();

								semanticsElement.setElementURI(uri);
								if (uri.indexOf("#") != -1) {
									semanticsElement.setSemanticsURI(
										uri.substring(0, uri.indexOf("#")));

								}
								relatedSemanticsElements.add(element);
								relatedSemanticsElements.add(semanticsElement);
							}
						}
					}
				}
			}
			return relatedSemanticsElements;
		}
		catch (OWLOntologyCreationException ex) {
			throw new IWebException(ex);
		}
	}

	public Set<Semantics> listAllSemantics() throws IWebException {
		try {
			return new TreeSet(semanticsPersistence.findAll());
		}
		catch (SystemException ex) {
			throw new IWebException(ex);
		}
	}

	public void loadSemanticReasoner() throws IWebException{
		throw new IWebException("Operation not supported yet.");
	}

	/*
	 * For updating an existing semantics, we need to load the file first.
	 *
	 */
	public Semantics loadSemantics(String uri, String name)
		throws IWebException {

		throw new IWebException("Operation not supported yet.");
	}

	public void removeCachedSemantics(String semanticsURI) throws IWebException{
		Set<OWLOntology> loadedontologies = _manager.getOntologies();
		for (OWLOntology ontology : loadedontologies) {
			if (semanticsURI.equals(ontology.getURI().toString())) {
				_manager.removeOntology(URI.create(semanticsURI));
			}
		}
	}

	public void updateSemantics(String uri, String name) throws IWebException {
		try {
			Semantics sem = semanticsPersistence.create(uri);
			sem.setSemanticsName(name);
			semanticsPersistence.update(sem, false);
		}
		catch (SystemException e) {
			throw new IWebException(e);
		}
	}

	public boolean validateSemantics(Semantics semantics) throws IWebException {
		try {
			String semuri = semantics.getPrimaryKey();
			OWLOntology ontology = _loadOntology(semuri);
			Reasoner reasoner = new Reasoner(_manager);
			reasoner.loadOntology(ontology);
			return reasoner.isConsistent();
		}
		catch (OWLOntologyCreationException ex) {
			throw new IWebException(ex);
		}
	}

	/*
	 * This method is used to load an ontology file given a input stream
	 * for the ontology.
	 */

	private OWLOntology _loadOntology(InputStream stream)
		throws OWLOntologyCreationException, IOException {

		byte[] tempBuffer = new byte[4096];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int read;
		while ((read = stream.read(tempBuffer)) != -1) {
			bos.write(tempBuffer, 0, read);
		}
		byte[] buffer = bos.toByteArray();

		String s = new String(buffer);
		OWLOntology ontology = _manager.loadOntology(new StringInputSource(s));
		return ontology;
	}

	private OWLOntology _loadOntology(String uri)
		throws OWLOntologyCreationException {
		Set<OWLOntology> loadedontologies = _manager.getOntologies();
		for (OWLOntology ontology : loadedontologies) {
			if (uri.equals(ontology.getURI().toString())) {
				return ontology;
			}
		}
		return _manager.loadOntology(URI.create(uri));
	}

	private static OWLOntologyManager _manager =
		OWLManager.createOWLOntologyManager();

}