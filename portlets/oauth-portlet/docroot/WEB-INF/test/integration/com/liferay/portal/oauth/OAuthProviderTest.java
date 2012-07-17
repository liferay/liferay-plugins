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

package com.liferay.portal.oauth;

import java.io.InputStream;

import java.sql.*;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * To run this test you need to have installed one of Firefox versions:
 * 3.0, 3.6, 5, 6, 7, or 8. If your installation is not in default installation
 * directory(depends on OS) you can set VM option "webdriver.firefox.bin" to
 * set alternative path to Firefox while running this test
 *
 * @author Ivica Cardic
 */
public class OAuthProviderTest {

	public static String getString(String key) {
		return (String)_properties.get(key);
	}

	@After
	public void after() throws Exception {
		_driver.quit();

		clearData(dataWithoutCallback);
		clearData(dataWithCallback);

		_connection.close();
	}

	@Before
	public void before() throws Exception {
		loadProperties();

		openConnection();

		clearData(dataWithoutCallback);
		clearData(dataWithCallback);

		prepareData(dataWithoutCallback);
		prepareData(dataWithCallback);

		_driver = new FirefoxDriver();
		_driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void testAlreadyAuthorized() throws Exception {
		for (int i = 0; i < dataWithCallback.length; i++) {
			OAuthService service = buildOAuthService(dataWithCallback, i);

			Token requestToken = service.getRequestToken();

			goToAuthorizationPage(requestToken);

			if (i == 0) {
				redirectedToLoginPage();
			}

			WebElement element = _driver.findElement(By.id(OAUTH_PORTLET_ID));
			element.submit();

			goToAuthorizationPage(requestToken);

			List<WebElement> elements = _driver.findElements(
				By.className("portlet-msg-error"));

			for (WebElement webElement : elements) {
				if (webElement.getText().
						contains("You are already authorized.")) {
					return;
				}
			}

			Assert.fail();
		}
	}

	@Test
	public void testGetAccessTokenWithCallbackUrl() throws Exception {
		for (int i = 0; i < dataWithCallback.length; i++) {
			OAuthService service = buildOAuthService(dataWithCallback, i);

			Token requestToken = service.getRequestToken();

			Assert.assertNotNull(requestToken.getToken());

			goToAuthorizationPage(requestToken);

			if (i == 0) {
				redirectedToLoginPage();
			}

			WebElement element = _driver.findElement(
					By.id("_4_WAR_oauthportlet_authorize"));
			element.submit();

			String[] params = extractParams(_driver.getCurrentUrl());

			Assert.assertNotNull(params[0]);
			Assert.assertNotNull(params[1]);

			Verifier verifier = new Verifier(params[1]);

			Token accessToken = service.getAccessToken(requestToken, verifier);

			Assert.assertNotNull(accessToken.getToken());
		}
	}

	@Test
	public void testGetAccessTokenWithoutCallback() throws Exception {
		for (int i = 0; i < dataWithoutCallback.length; i++) {
			OAuthService service = buildOAuthService(dataWithoutCallback, i);

			Token requestToken = service.getRequestToken();

			Assert.assertNotNull(requestToken.getToken());

			goToAuthorizationPage(requestToken);

			if (i == 0) {
				redirectedToLoginPage();
			}

			WebElement element = _driver.findElement(
					By.id("_4_WAR_oauthportlet_authorize"));
			element.submit();

			element = _driver.findElement(By.className("portlet-msg-info"));

			String text = element.getText();

			String verifierString = text.substring(
					text.lastIndexOf(":") + 1, text.length());

			Assert.assertNotNull(verifierString);

			Verifier verifier = new Verifier(verifierString);

			Token accessToken = service.getAccessToken(requestToken, verifier);

			Assert.assertNotNull(accessToken.getToken());
		}
	}

	@Test
	public void testTokenExpired() throws Exception {
		for (int i = 0; i < dataWithCallback.length; i++) {
			OAuthService service = buildOAuthService(dataWithCallback, i);

			Token requestToken = service.getRequestToken();

			goToAuthorizationPage(requestToken);

			if (i == 0) {
				redirectedToLoginPage();
			}

			WebElement element = _driver.findElement(By.id(OAUTH_PORTLET_ID));
			element.submit();

			String[] params = extractParams(_driver.getCurrentUrl());

			Verifier verifier = new Verifier(params[1]);

			service.getAccessToken(requestToken, verifier);

			goToAuthorizationPage(requestToken);

			List<WebElement> elements = _driver.findElements(
				By.className("portlet-msg-error"));

			for (WebElement webElement : elements) {
				if (webElement.getText().contains(
					"Your token has been expired.")) {

					return;
				}
			}

			Assert.fail();
		}
	}

	private OAuthService buildOAuthService(Object[][] data, int index) {
		OAuthService service = new ServiceBuilder()
			.provider(LiferayApi.class)
			.apiKey((String) data[index][8])
			.apiSecret((String) data[index][9])
			.build();

		return service;
	}

	private void clearData(Object[][] data) throws Exception {
		for (int i = 0; i< data.length; i++) {
			PreparedStatement pstmt = _connection.prepareStatement(
				DELETE_STATEMENT_OAUTHAPPLICATION);

			pstmt.setInt(1, (Integer) data[i][0]);

			pstmt.executeUpdate();

			pstmt.close();

			pstmt = _connection.prepareStatement(
				DELETE_STATEMENT_OAUTHAPPLICATIONS_USERS);

			pstmt.setInt(1, (Integer) data[i][0]);

			pstmt.executeUpdate();

			pstmt.close();
		}
	}

	private String[] extractParams(String url) {
		int beginIndex = url.indexOf("oauth_token");
		int endIndex = url.length();

		url = url.substring(beginIndex, endIndex);

		beginIndex = "oauth_token=".length();
		endIndex = url.indexOf("&oauth_verifier");

		String oauthToken =url.substring(beginIndex, endIndex);

		beginIndex = url.indexOf("&oauth_verifier");
		endIndex = url.length();

		url = url.substring(beginIndex, endIndex);

		beginIndex = "oauth_verifier=".length();
		endIndex = url.length();

		String oauthVerifier =url.substring(beginIndex, endIndex);

		return new String[]{oauthToken, oauthVerifier};
	}

	private void goToAuthorizationPage(Token requestToken) {
		String authorizationUrl = LiferayApi.AUTHORIZE_URL.replace(
			"%s", requestToken.getToken());

		_driver.get(authorizationUrl);
	}

	private void loadProperties() throws Exception {
		_properties = new Properties();
		InputStream in = this.getClass().getResourceAsStream(
			"/portal-test.properties");
		_properties.load(in);
	}

	private void openConnection() throws Exception {
		String className = (String)_properties.get(
			"jdbc.default.driverClassName");
		String url = (String)_properties.get("jdbc.default.url");
		String username =  (String)_properties.get("jdbc.default.username");
		String password = (String)_properties.get("jdbc.default.password");

		Class.forName(className);

		_connection = DriverManager.getConnection(url, username, password);
	}

	private void prepareData(Object[][] data) throws Exception {
		for (int i = 0; i< data.length; i++) {
			PreparedStatement pstmt = _connection.prepareStatement(
				INSERT_STATEMENT_OAUTHAPPLICATION);

			pstmt.setInt(1, (Integer) data[i][0]);
			pstmt.setInt(2, (Integer) data[i][1]);
			pstmt.setInt(3, (Integer) data[i][2]);
			pstmt.setString(4, (String) data[i][3]);
			pstmt.setDate(5, new Date(System.currentTimeMillis()));
			pstmt.setDate(6, new Date(System.currentTimeMillis()));
			pstmt.setInt(7, (Integer) data[i][4]);
			pstmt.setString(8, (String) data[i][5]);
			pstmt.setString(9, (String) data[i][6]);
			pstmt.setString(10, (String) data[i][7]);
			pstmt.setString(11, (String) data[i][8]);
			pstmt.setString(12, (String) data[i][9]);
			pstmt.setString(13, (String) data[i][10]);
			pstmt.setInt(14, (Integer) data[i][11]);

			pstmt.executeUpdate();

			pstmt.close();
		}
	}

	private void redirectedToLoginPage() {
		//String url = (String)_properties.get("portal.default.url");
		//_driver.get(url);

		WebElement element = _driver.findElement(By.name("_58_login"));
		element.clear();

		String login = (String)_properties.get("portal.default.login");
		element.sendKeys(login);

		element = _driver.findElement(By.name("_58_password"));

		String password = (String)_properties.get("portal.default.password");
		element.sendKeys(password);

		element.submit();
	}

	private static final String DELETE_STATEMENT_OAUTHAPPLICATION =
			"delete from oauthapplication where applicationId=?";
	private static final String DELETE_STATEMENT_OAUTHAPPLICATIONS_USERS =
			"delete from oauthapplications_users where applicationId=?";
	private static final String INSERT_STATEMENT_OAUTHAPPLICATION =
		"INSERT INTO oauthapplication VALUES " +
			"(? ,? ,? ,? ,? ,? , ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String OAUTH_PORTLET_ID =
		"_4_WAR_oauthportlet_authorize";

	Object[][] dataWithoutCallback = {
		{
			20000, 10152, 10194, "test@liferay.com", 10194, "application1",
			"this is a test application1", "http://liferay.com",
			"af91876b105c6834ec", "521b0a6b31a3f8", null, 0
		},
		{
			20001, 10152, 10194, "test@liferay.com", 10194, "application2",
			"this is a test application2", "http://liferay.com",
			"13a795dcbd0acd97816", "a5385e50e6812", null, 0
		}
	};

	Object[][] dataWithCallback = {
		{
			20002, 10152, 10194, "test@liferay.com", 10194, "application3",
			"this is a test application3", "http://liferay.com",
			"af91876uubfc6834ec", "521b0poj31a3f8", "http://liferay.com", 0
		},
		{
			20003, 10152, 10194, "test@liferay.com", 10194, "application4",
			"this is a test application4", "http://liferay.com",
			"13a795dvcd0acd97816", "a5385jbde6812", "http://liferay.com", 0
		}
	};

	private static Properties _properties;

	private Connection _connection;
	private WebDriver _driver;

}