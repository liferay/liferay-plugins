package com.liferay.portal.oauth;

import java.io.Console;
import java.io.IOException;
import java.net.URL;

import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.ExecutionTestListeners;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.util.TestPropsValues;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;

/**
 * @author Ivica Cardic
 */
@ExecutionTestListeners(listeners = {EnvironmentExecutionTestListener.class})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class OAuthProviderTest {

	public static void main(String args[]) throws IOException {
		OAuthProviderTest t = new OAuthProviderTest();

		t.setup();
		try {
			t.testRequestToken();
		}
		catch (Exception e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	@Before
	public void setup() {
		_service = new ServiceBuilder()
			.provider(LiferayApi.class)
			.apiKey("key11")
			.apiSecret("secret11")
			.build();
	}

	@Test
	public void testRequestToken() throws Exception{
		////DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

		//URL url = new URL(TestPropsValues.PORTAL_URL);

		//HttpHost httpHost = new HttpHost(
		//	url.getHost(), url.getPort(), url.getProtocol());


		Token requestToken = _service.getRequestToken();

		//Assert.assertNotNull(requestToken);

		String authorizationUrl = LiferayApi.AUTHORIZE_URL.replace(
			"%s", requestToken.getToken());

		System.out.println(authorizationUrl);

		Console c = System.console();

		String verifierString = c.readLine("Enter verifier: ");

		Verifier verifier = new Verifier(verifierString);

		Token accessToken = _service.getAccessToken(requestToken, verifier);

		System.out.println(accessToken.getToken());

		//OAuthRequest request = new OAuthRequest(Verb.GET,
		// "http://api.twitter.com/1/account/verify_credentials.xml");
		//_service.signRequest(accessToken, request); // the access token
		// from step 4
		//Response response = request.send();
		//System.out.println(response.getBody());
	}

	private OAuthService _service;

}