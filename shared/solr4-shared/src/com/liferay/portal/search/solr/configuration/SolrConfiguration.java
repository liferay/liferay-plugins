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

package com.liferay.portal.search.solr.configuration;

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author Michael C. Han
 */
@Meta.OCD(id = "com.liferay.portal.search.solr.configuration.SolrConfiguration")
public interface SolrConfiguration {

	@Meta.AD(
		deflt = "BASIC", optionLabels = {"Basic", "Cert"},
		optionValues = {"BASIC", "CERT"},
		required = false
	)
	public String authenticationMode();

	@Meta.AD(deflt = "solr", required = false)
	public String basicAuthPassword();

	@Meta.AD(deflt = "solr", required = false)
	public String basicAuthUserName();

	@Meta.AD(deflt = "20", required = false)
	public int defaultMaxConnectionsPerRoute();

	@Meta.AD(deflt = "secret", required = false)
	public String keyStorePassword();

	@Meta.AD(deflt = "classpath:/keystore.jks", required = false)
	public String keyStorePath();

	@Meta.AD(deflt = "JKS", required = false)
	public String keyStoreType();

	@Meta.AD(
		deflt = "true",
		description = "Set to true to only log exceptions from Solr and not rethrow them.",
		required = false
	)
	public boolean logExceptionsOnly();

	@Meta.AD(deflt = "20", required = false)
	public int maxTotalConnections();

	@Meta.AD(deflt = "secret", required = false)
	public String trustStorePassword();

	@Meta.AD(deflt = "classpath:/truststore.jks", required = false)
	public String trustStorePath();

	@Meta.AD(deflt = "JKS", required = false)
	public String trustStoreType();

	@Meta.AD(deflt = "http://localhost:8080/solr", required = false)
	public String url();

	@Meta.AD(deflt = "true", required = false)
	public boolean verifyServerCertificate();

	@Meta.AD(deflt = "true", required = false)
	public boolean verifyServerName();

}