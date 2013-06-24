/* @generated */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with this
 * work for additional information regarding copyright ownership. The ASF
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.shindig.gadgets.oauth;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthProblemException;
import net.oauth.OAuth.Parameter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shindig.auth.OAuthConstants;
import org.apache.shindig.auth.OAuthUtil;
import org.apache.shindig.common.crypto.Crypto;
import org.apache.shindig.common.uri.Uri;
import org.apache.shindig.common.uri.UriBuilder;
import org.apache.shindig.common.util.CharsetUtil;
import org.apache.shindig.gadgets.GadgetException;
import org.apache.shindig.gadgets.http.HttpFetcher;
import org.apache.shindig.gadgets.http.HttpRequest;
import org.apache.shindig.gadgets.http.HttpResponse;
import org.apache.shindig.gadgets.http.HttpResponseBuilder;
import org.apache.shindig.gadgets.oauth.AccessorInfo.HttpMethod;
import org.apache.shindig.gadgets.oauth.AccessorInfo.OAuthParamLocation;
import org.apache.shindig.gadgets.oauth.OAuthStore.TokenInfo;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * Implements both signed fetch and full OAuth for gadgets, as well as a combination of the two that
 * is necessary to build OAuth enabled gadgets for social sites.
 *
 * Signed fetch sticks identity information in the query string, signed either with the container's
 * private key, or else with a secret shared between the container and the gadget.
 *
 * Full OAuth redirects the user to the OAuth service provider site to obtain the user's permission
 * to access their data.  Read the example in the appendix to the OAuth spec for a summary of how
 * this works (The spec is at http://oauth.net/core/1.0/).
 *
 * The combination protocol works by sending identity information in all requests, and allows the
 * OAuth dance to happen as well when owner == viewer (by default) or for any viewer when the
 * OAuthFetcherConfig#isViewerAccessTokensEnabled parameter is true. This lets OAuth service providers build up
 * an identity mapping from ids on social network sites to their own local ids.
 */
public class OAuthRequest {

  // Maximum number of attempts at the protocol before giving up.
  private static final int MAX_ATTEMPTS = 2;

  // names of additional OAuth parameters we include in outgoing requests
  // TODO(beaton): can we do away with this bit in favor of the opensocial param?
  public static final String XOAUTH_APP_URL = "xoauth_app_url";

  protected static final String OPENSOCIAL_OWNERID = "opensocial_owner_id";

  protected static final String OPENSOCIAL_VIEWERID = "opensocial_viewer_id";

  protected static final String OPENSOCIAL_APPID = "opensocial_app_id";

  // TODO(beaton): figure out if this is the name in the 0.8 spec.
  protected static final String OPENSOCIAL_APPURL = "opensocial_app_url";

  protected static final String OPENSOCIAL_PROXIED_CONTENT = "opensocial_proxied_content";

  // old and new parameters for the public key
  // TODO remove OLD in a far future release
  protected static final String XOAUTH_PUBLIC_KEY_OLD = "xoauth_signature_publickey";
  protected static final String XOAUTH_PUBLIC_KEY_NEW = "xoauth_public_key";

  protected static final Pattern ALLOWED_PARAM_NAME = Pattern.compile("[-:\\w~!@$*()_\\[\\]:,./ ]+");

  private static final long ACCESS_TOKEN_EXPIRE_UNKNOWN = 0;
  private static final long ACCESS_TOKEN_FORCE_EXPIRE = -1;


  /**
   * Configuration options for the fetcher.
   */
  protected final OAuthFetcherConfig fetcherConfig;

  /**
   * Next fetcher to use in chain.
   */
  private final HttpFetcher fetcher;

  /**
   * Additional trusted parameters to be included in the OAuth request.
   */
  private final List<Parameter> trustedParams;

  /**
   * State information from client
   */
  protected OAuthClientState clientState;

  /**
   * OAuth specific stuff to include in the response.
   */
  protected OAuthResponseParams responseParams;

  /**
   * The accessor we use for signing messages. This also holds metadata about
   * the service provider, such as their URLs and the keys we use to access
   * those URLs.
   */
  protected AccessorInfo accessorInfo;

  /**
   * The request the client really wants to make.
   */
  protected HttpRequest realRequest;

  /**
   * Data returned along with OAuth access token, null if this is not an access token request
   */
  protected Map<String, String> accessTokenData;

  /**
   * @param fetcherConfig configuration options for the fetcher
   * @param fetcher fetcher to use for actually making requests
   */
  public OAuthRequest(OAuthFetcherConfig fetcherConfig, HttpFetcher fetcher) {
    this(fetcherConfig, fetcher, null);
  }

  /**
   * @param fetcherConfig configuration options for the fetcher
   * @param fetcher fetcher to use for actually making requests
   * @param trustedParams additional parameters to include in all outgoing OAuth requests, useful
   *     for client data that can't be pulled from the security token but is still trustworthy.
   */
  public OAuthRequest(OAuthFetcherConfig fetcherConfig, HttpFetcher fetcher,
      List<Parameter> trustedParams) {
    this.fetcherConfig = fetcherConfig;
    this.fetcher = fetcher;
    this.trustedParams = trustedParams;
  }

  /**
   * OAuth authenticated fetch.
   */
  public HttpResponse fetch(HttpRequest request) {
    realRequest = request;
    clientState = new OAuthClientState(
        fetcherConfig.getStateCrypter(),
        request.getOAuthArguments().getOrigClientState());
    responseParams = new OAuthResponseParams(request.getSecurityToken(), request,
        fetcherConfig.getStateCrypter());
    try {
      return fetchNoThrow();
    } catch (RuntimeException e) {
      // We log here to record the request/response pairs that created the failure.
      responseParams.logDetailedWarning("OAuth fetch unexpected fatal error", e);
      throw e;
    }
  }

  /**
   * Fetch data and build a response to return to the client.  We try to always return something
   * reasonable to the calling app no matter what kind of madness happens along the way.  If an
   * unchecked exception occurs, well, then the client is out of luck.
   */
  private HttpResponse fetchNoThrow() {
    HttpResponseBuilder response = null;
    try {
      accessorInfo = fetcherConfig.getTokenStore().getOAuthAccessor(
          realRequest.getSecurityToken(), realRequest.getOAuthArguments(), clientState,
          responseParams, fetcherConfig);
      response = fetchWithRetry();
    } catch (OAuthRequestException e) {
      // No data for us.
      if (OAuthError.UNAUTHENTICATED.name().equals(e.getError())) {
        responseParams.logDetailedInfo("Unauthenticated OAuth fetch", e);
      } else if (OAuthError.BAD_OAUTH_TOKEN_URL.name().equals(e.getError())) {
        responseParams.logDetailedInfo("Invalid OAuth fetch request", e);
      } else {
        responseParams.logDetailedWarning("OAuth fetch fatal error", e);
      }
      responseParams.setSendTraceToClient(true);
      response = new HttpResponseBuilder()
          .setHttpStatusCode(HttpResponse.SC_FORBIDDEN)
          .setStrictNoCache();
      responseParams.addToResponse(response, e);
      return response.create();
    }

    // OK, got some data back, annotate it as necessary.
    if (response.getHttpStatusCode() >= 400) {
      responseParams.logDetailedWarning("OAuth fetch fatal error");
      responseParams.setSendTraceToClient(true);
    } else if (responseParams.getAznUrl() != null && responseParams.sawErrorResponse()) {
      responseParams.logDetailedWarning("OAuth fetch error, reprompting for user approval");
      responseParams.setSendTraceToClient(true);
    }

    responseParams.addToResponse(response, null);
    return response.create();
  }

  /**
   * Fetch data, retrying in the event that that the service provider returns an error and we think
   * we can recover by restarting the protocol flow.
   */
  private HttpResponseBuilder fetchWithRetry() throws OAuthRequestException {
    int attempts = 0;
    boolean retry;
    HttpResponseBuilder response = null;
    do {
      retry = false;
      ++attempts;
      try {
        response = attemptFetch();
      } catch (OAuthProtocolException pe) {
        retry = handleProtocolException(pe, attempts);
        if (!retry) {
          if (pe.getProblemCode() != null) {
            throw new OAuthRequestException(pe.getProblemCode(),
                "Service provider rejected request", pe);
          } else {
            throw new OAuthRequestException(OAuthError.UNKNOWN_PROBLEM,
                "Service provider rejected request", pe);
          }
        }
      }
    } while (retry);
    return response;
  }

  private boolean handleProtocolException(OAuthProtocolException pe, int attempts)
      throws OAuthRequestException {
    if (pe.canExtend()) {
      accessorInfo.setTokenExpireMillis(ACCESS_TOKEN_FORCE_EXPIRE);
    } else if (pe.startFromScratch()) {
      fetcherConfig.getTokenStore().removeToken(realRequest.getSecurityToken(),
          accessorInfo.getConsumer(), realRequest.getOAuthArguments(), responseParams);
      accessorInfo.getAccessor().accessToken = null;
      accessorInfo.getAccessor().requestToken = null;
      accessorInfo.getAccessor().tokenSecret = null;
      accessorInfo.setSessionHandle(null);
      accessorInfo.setTokenExpireMillis(ACCESS_TOKEN_EXPIRE_UNKNOWN);
    }
    return (attempts < MAX_ATTEMPTS && pe.canRetry());
  }

  /**
   * Does one of the following:
   * 1) Sends a request token request, and returns an approval URL to the calling app.
   * 2) Sends an access token request to swap a request token for an access token, and then asks
   *    for data from the service provider.
   * 3) Asks for data from the service provider.
   */
  private HttpResponseBuilder attemptFetch() throws OAuthRequestException, OAuthProtocolException {
    if (needApproval()) {
      // This is section 6.1 of the OAuth spec.
      checkCanApprove();
      fetchRequestToken();
      // This is section 6.2 of the OAuth spec.
      buildClientApprovalState();
      buildAznUrl();
      // break out of the content fetching chain, we need permission from
      // the user to do this
      return new HttpResponseBuilder()
         .setHttpStatusCode(HttpResponse.SC_OK)
         .setStrictNoCache();
    } else if (needAccessToken()) {
      // This is section 6.3 of the OAuth spec
      checkCanApprove();
      exchangeRequestToken();
      saveAccessToken();
      buildClientAccessState();
    }
    return fetchData();
  }

  /**
   * Do we need to get the user's approval to access the data?
   */
  private boolean needApproval() {
    return (realRequest.getOAuthArguments().mustUseToken()
            && accessorInfo.getAccessor().requestToken == null
            && accessorInfo.getAccessor().accessToken == null);
  }

  /**
   * Make sure the user is authorized to approve access tokens.  At the moment
   * we restrict this to page owner's viewing their own pages.
   */
  private void checkCanApprove() throws OAuthRequestException {
    String pageOwner = realRequest.getSecurityToken().getOwnerId();
    String pageViewer = realRequest.getSecurityToken().getViewerId();
    String stateOwner = clientState.getOwner();
    if (pageOwner == null || pageViewer == null) {
      throw new OAuthRequestException(OAuthError.UNAUTHENTICATED);
    }
    if (!fetcherConfig.isViewerAccessTokensEnabled() && !pageOwner.equals(pageViewer)) {
      throw new OAuthRequestException(OAuthError.NOT_OWNER);
    }
    // if (stateOwner != null && !stateOwner.equals(pageViewer)) {
    //   throw new OAuthRequestException(OAuthError.UNKNOWN_PROBLEM,
    //       "Client state belongs to a different person " +
    //       "(state owner=" + stateOwner + ", pageViewer=" + pageViewer + ')');
    // }
  }

  private void fetchRequestToken() throws OAuthRequestException, OAuthProtocolException {
    OAuthAccessor accessor = accessorInfo.getAccessor();
    HttpRequest request = createRequestTokenRequest(accessor);

    List<Parameter> requestTokenParams = Lists.newArrayList();

    addCallback(requestTokenParams);

    HttpRequest signed = sanitizeAndSign(request, requestTokenParams, true);

    OAuthMessage reply = sendOAuthMessage(signed);

    accessor.requestToken = OAuthUtil.getParameter(reply, OAuth.OAUTH_TOKEN);
    accessor.tokenSecret = OAuthUtil.getParameter(reply, OAuth.OAUTH_TOKEN_SECRET);
  }

  private HttpRequest createRequestTokenRequest(OAuthAccessor accessor)
      throws OAuthRequestException {
    if (accessor.consumer.serviceProvider.requestTokenURL == null) {
      throw new OAuthRequestException(OAuthError.BAD_OAUTH_TOKEN_URL, "request token");
    }
    HttpRequest request = new HttpRequest(
        Uri.parse(accessor.consumer.serviceProvider.requestTokenURL));
    request.setMethod(accessorInfo.getHttpMethod().toString());
    if (accessorInfo.getHttpMethod() == HttpMethod.POST) {
      request.setHeader("Content-Type", OAuth.FORM_ENCODED);
    }
    return request;
  }

  private void addCallback(List<Parameter> requestTokenParams) throws OAuthRequestException {
    // This will be either the consumer key callback URL or the global callback URL.
    String baseCallback = StringUtils.trimToNull(accessorInfo.getConsumer().getCallbackUrl());
    if (baseCallback != null) {
      String callbackUrl = fetcherConfig.getOAuthCallbackGenerator().generateCallback(
          fetcherConfig, baseCallback, realRequest, responseParams);
      if (callbackUrl != null) {
        requestTokenParams.add(new Parameter(OAuth.OAUTH_CALLBACK, callbackUrl));
      }
    }
  }

  /**
   * Strip out any owner or viewer identity information passed by the client.
   */
  private List<Parameter> sanitize(List<Parameter> params) throws OAuthRequestException {
    ArrayList<Parameter> list = Lists.newArrayList();
    for (Parameter p : params) {
      String name = p.getKey();
      if (allowParam(name)) {
        list.add(p);
      } else {
        throw new OAuthRequestException(OAuthError.INVALID_PARAMETER, name);
      }
    }
    return list;
  }

  protected boolean allowParam(String paramName) {
    String canonParamName = paramName.toLowerCase();
    return (!(canonParamName.startsWith("oauth") ||
        canonParamName.startsWith("xoauth") ||
        canonParamName.startsWith("opensocial")) &&
        ALLOWED_PARAM_NAME.matcher(canonParamName).matches());
  }

  /**
   * This gives a chance to override parameters by passing trusted parameters.
   *
   */
  private void overrideParameters(List<Parameter> authParams)
    throws OAuthRequestException {
    if (trustedParams == null) {
      return;
    }

    Map<String, String> paramMap = Maps.newLinkedHashMap();
    for (Parameter param : authParams) {
      paramMap.put(param.getKey(), param.getValue());
    }
    for (Parameter param : trustedParams) {
      if (!isContainerInjectedParameter(param.getKey())) {
        throw new OAuthRequestException(OAuthError.INVALID_TRUSTED_PARAMETER, param.getKey());
      }
      paramMap.put(param.getKey(), param.getValue());
    }

    authParams.clear();
    for (Entry<String, String> entry : paramMap.entrySet()) {
      authParams.add(new Parameter(entry.getKey(), entry.getValue()));
    }
  }

  /**
   * Add identity information, such as owner/viewer/gadget.
   */
  private void addIdentityParams(List<Parameter> params) {
    // If no owner or viewer information is required, don't add any identity params.  This lets
    // us be compatible with strict OAuth service providers that reject extra parameters on
    // requests.
    if (!realRequest.getOAuthArguments().getSignOwner() &&
        !realRequest.getOAuthArguments().getSignViewer()) {
      return;
    }

    String owner = realRequest.getSecurityToken().getOwnerId();
    if (owner != null && realRequest.getOAuthArguments().getSignOwner()) {
      params.add(new Parameter(OPENSOCIAL_OWNERID, owner));
    }

    String viewer = realRequest.getSecurityToken().getViewerId();
    if (viewer != null && realRequest.getOAuthArguments().getSignViewer()) {
      params.add(new Parameter(OPENSOCIAL_VIEWERID, viewer));
    }

    String app = realRequest.getSecurityToken().getAppId();
    if (app != null) {
      params.add(new Parameter(OPENSOCIAL_APPID, app));
    }

    String appUrl = realRequest.getSecurityToken().getAppUrl();
    if (appUrl != null) {
      params.add(new Parameter(OPENSOCIAL_APPURL, appUrl));
    }

    if (realRequest.getOAuthArguments().isProxiedContentRequest()) {
      params.add(new Parameter(OPENSOCIAL_PROXIED_CONTENT, "1"));
    }
  }

  /**
   * Add signature type to the message.
   */
  private void addSignatureParams(List<Parameter> params) {
    if (accessorInfo.getConsumer().getConsumer().consumerKey == null) {
      params.add(
          new Parameter(OAuth.OAUTH_CONSUMER_KEY, realRequest.getSecurityToken().getDomain()));
    }
    if (accessorInfo.getConsumer().getKeyName() != null) {
      params.add(new Parameter(XOAUTH_PUBLIC_KEY_OLD, accessorInfo.getConsumer().getKeyName()));
      params.add(new Parameter(XOAUTH_PUBLIC_KEY_NEW, accessorInfo.getConsumer().getKeyName()));
    }
    params.add(new Parameter(OAuth.OAUTH_VERSION, OAuth.VERSION_1_0));
    params.add(new Parameter(OAuth.OAUTH_TIMESTAMP,
        Long.toString(fetcherConfig.getClock().currentTimeMillis() / 1000L)));
    // the oauth.net java code uses a clock to generate nonces, which causes nonce collisions
    // under heavy load.  A random nonce is more reliable.
    params.add(new Parameter(OAuth.OAUTH_NONCE, String.valueOf(Math.abs(Crypto.RAND.nextLong()))));
  }

  static String getAuthorizationHeader(List<Map.Entry<String, String>> oauthParams) {
    StringBuilder result = new StringBuilder("OAuth ");

    boolean first = true;
    for (Map.Entry<String, String> parameter : oauthParams) {
      if (!first) {
        result.append(", ");
      } else {
        first = false;
      }
      result.append(OAuth.percentEncode(parameter.getKey()))
            .append("=\"")
            .append(OAuth.percentEncode(parameter.getValue()))
            .append('"');
    }
    return result.toString();
  }


  /**
   * Start with an HttpRequest.
   * Throw if there are any attacks in the query.
   * Throw if there are any attacks in the post body.
   * Build up OAuth parameter list.
   * Sign it.
   * Add OAuth parameters to new request.
   * Send it.
   */
  public HttpRequest sanitizeAndSign(HttpRequest base, List<Parameter> params,
      boolean tokenEndpoint) throws OAuthRequestException {
    if (params == null) {
      params = Lists.newArrayList();
    }
    UriBuilder target = new UriBuilder(base.getUri());
    String query = target.getQuery();
    target.setQuery(null);
    params.addAll(sanitize(OAuth.decodeForm(query)));

    switch(OAuthUtil.getSignatureType(tokenEndpoint, base.getHeader("Content-Type"))) {
      case URL_ONLY:
        break;
      case URL_AND_FORM_PARAMS:
        try {
          params.addAll(sanitize(OAuth.decodeForm(base.getPostBodyAsString())));
        } catch (IllegalArgumentException e) {
          // Occurs if OAuth.decodeForm finds an invalid URL to decode.
          throw new OAuthRequestException(OAuthError.INVALID_REQUEST,
              "Could not decode body", e);
        }
        break;
      case URL_AND_BODY_HASH:
        try {
          byte[] body = IOUtils.toByteArray(base.getPostBody());
          byte[] hash = DigestUtils.sha(body);
          String b64 = new String(Base64.encodeBase64(hash), Charsets.UTF_8.name());
          params.add(new Parameter(OAuthConstants.OAUTH_BODY_HASH, b64));
        } catch (IOException e) {
          throw new OAuthRequestException(OAuthError.UNKNOWN_PROBLEM,
              "Error taking body hash", e);
        }
        break;
    }

    // authParams are parameters prefixed with 'xoauth' 'oauth' or 'opensocial',
    // trusted parameters have ability to override these parameters.
    List<Parameter> authParams = Lists.newArrayList();

    addIdentityParams(authParams);

    addSignatureParams(authParams);

    overrideParameters(authParams);

    params.addAll(authParams);

    try {
      OAuthMessage signed = OAuthUtil.newRequestMessage(accessorInfo.getAccessor(),
          base.getMethod(), target.toString(), params);
      HttpRequest oauthHttpRequest = createHttpRequest(base, selectOAuthParams(signed));
      // Following 302s on OAuth responses is unlikely to be productive.
      oauthHttpRequest.setFollowRedirects(false);
      return oauthHttpRequest;
    } catch (OAuthException e) {
      throw new OAuthRequestException(OAuthError.UNKNOWN_PROBLEM,
          "Error signing message", e);
    }
  }

  private HttpRequest createHttpRequest(HttpRequest base,
      List<Map.Entry<String, String>> oauthParams) throws OAuthRequestException {

    OAuthParamLocation paramLocation = accessorInfo.getParamLocation();

    // paramLocation could be overriden by a run-time parameter to fetchRequest

    HttpRequest result = new HttpRequest(base);

    // If someone specifies that OAuth parameters go in the body, but then sends a request for
    // data using GET, we've got a choice.  We can throw some type of error, since a GET request
    // can't have a body, or we can stick the parameters somewhere else, like, say, the header.
    // We opt to put them in the header, since that stands some chance of working with some
    // OAuth service providers.
    if (paramLocation == OAuthParamLocation.POST_BODY &&
        !result.getMethod().equals("POST")) {
      paramLocation = OAuthParamLocation.AUTH_HEADER;
    }

    switch (paramLocation) {
      case AUTH_HEADER:
        result.addHeader("Authorization", getAuthorizationHeader(oauthParams));
        break;

      case POST_BODY:
        String contentType = result.getHeader("Content-Type");
        if (!OAuth.isFormEncoded(contentType)) {
          throw new OAuthRequestException(OAuthError.INVALID_REQUEST,
              "OAuth param location can only be post_body if it is of " +
              "type x-www-form-urlencoded");
        }
        String oauthData = OAuthUtil.formEncode(oauthParams);
        if (result.getPostBodyLength() == 0) {
          result.setPostBody(CharsetUtil.getUtf8Bytes(oauthData));
        } else {
          StringBuilder postBody = new StringBuilder();
          postBody.append(result.getPostBodyAsString());

          if (!result.getPostBodyAsString().endsWith("&")) {
            postBody.append('&');
          }

          postBody.append(oauthData);
          result.setPostBody(postBody.toString().getBytes());
        }
        break;

      case URI_QUERY:
        result.setUri(Uri.parse(OAuthUtil.addParameters(result.getUri().toString(), oauthParams)));
        break;
    }

    return result;
  }

  /**
   * Sends OAuth request token and access token messages.
   */
  private OAuthMessage sendOAuthMessage(HttpRequest request)
      throws OAuthRequestException, OAuthProtocolException {
    HttpResponse response = fetchFromServer(request);
    checkForProtocolProblem(response);
    OAuthMessage reply = new OAuthMessage(null, null, null);

    reply.addParameters(OAuth.decodeForm(response.getResponseAsString()));
    reply = parseAuthHeader(reply, response);
    if (OAuthUtil.getParameter(reply, OAuth.OAUTH_TOKEN) == null) {
      throw new OAuthRequestException(OAuthError.MISSING_OAUTH_PARAMETER,
          OAuth.OAUTH_TOKEN);
    }
    if (OAuthUtil.getParameter(reply, OAuth.OAUTH_TOKEN_SECRET) == null) {
      throw new OAuthRequestException(OAuthError.MISSING_OAUTH_PARAMETER,
          OAuth.OAUTH_TOKEN_SECRET);
    }
    return reply;
  }

  /**
   * Parse OAuth WWW-Authenticate header and either add them to an existing
   * message or create a new message.
   *
   * @param msg
   * @param resp
   * @return the updated message.
   */
  private OAuthMessage parseAuthHeader(OAuthMessage msg, HttpResponse resp) {
    if (msg == null) {
      msg = new OAuthMessage(null, null, null);
    }

    for (String auth : resp.getHeaders("WWW-Authenticate")) {
      msg.addParameters(OAuthMessage.decodeAuthorization(auth));
    }

    return msg;
  }

  /**
   * Builds the data we'll cache on the client while we wait for approval.
   */
  private void buildClientApprovalState() {
    OAuthAccessor accessor = accessorInfo.getAccessor();
    responseParams.getNewClientState().setRequestToken(accessor.requestToken);
    responseParams.getNewClientState().setRequestTokenSecret(accessor.tokenSecret);
    responseParams.getNewClientState().setOwner(realRequest.getSecurityToken().getOwnerId());
  }

  /**
   * Builds the URL the client needs to visit to approve access.
   */
  private void buildAznUrl() throws OAuthRequestException {
    // We add the token, gadget is responsible for the callback URL.
    OAuthAccessor accessor = accessorInfo.getAccessor();
    if (accessor.consumer.serviceProvider.userAuthorizationURL == null) {
      throw new OAuthRequestException(OAuthError.BAD_OAUTH_TOKEN_URL,
          "authorization");
    }
    StringBuilder azn = new StringBuilder(
        accessor.consumer.serviceProvider.userAuthorizationURL);
    if (azn.indexOf("?") == -1) {
      azn.append('?');
    } else {
      azn.append('&');
    }
    azn.append(OAuth.OAUTH_TOKEN);
    azn.append('=');
    azn.append(OAuth.percentEncode(accessor.requestToken));
    responseParams.setAznUrl(azn.toString());
  }

  /**
   * Do we need to exchange a request token for an access token?
   */
  private boolean needAccessToken() {
    if (realRequest.getOAuthArguments().mustUseToken()
        && accessorInfo.getAccessor().requestToken != null
        && accessorInfo.getAccessor().accessToken == null) {
      return true;
    }
    return realRequest.getOAuthArguments().mayUseToken() && accessTokenExpired();
  }

  private boolean accessTokenExpired() {
    return (accessorInfo.getTokenExpireMillis() != ACCESS_TOKEN_EXPIRE_UNKNOWN
        && accessorInfo.getTokenExpireMillis() < fetcherConfig.getClock().currentTimeMillis());
  }

  /**
   * Implements section 6.3 of the OAuth spec.
   */
  private void exchangeRequestToken() throws OAuthRequestException, OAuthProtocolException {
    if (accessorInfo.getAccessor().accessToken != null) {
      // session extension per
      // http://oauth.googlecode.com/svn/spec/ext/session/1.0/drafts/1/spec.html
      accessorInfo.getAccessor().requestToken = accessorInfo.getAccessor().accessToken;
      accessorInfo.getAccessor().accessToken = null;
    }
    OAuthAccessor accessor = accessorInfo.getAccessor();

    if (accessor.consumer.serviceProvider.accessTokenURL == null) {
      throw new OAuthRequestException(OAuthError.BAD_OAUTH_TOKEN_URL, "access token");
    }
    Uri accessTokenUri = Uri.parse(accessor.consumer.serviceProvider.accessTokenURL);
    HttpRequest request = new HttpRequest(accessTokenUri);
    request.setMethod(accessorInfo.getHttpMethod().toString());
    if (accessorInfo.getHttpMethod() == HttpMethod.POST) {
      request.setHeader("Content-Type", OAuth.FORM_ENCODED);
    }

    List<Parameter> msgParams = Lists.newArrayList();
    msgParams.add(new Parameter(OAuth.OAUTH_TOKEN, accessor.requestToken));
    if (accessorInfo.getSessionHandle() != null) {
      msgParams.add(new Parameter(OAuthConstants.OAUTH_SESSION_HANDLE,
          accessorInfo.getSessionHandle()));
    }
    String receivedCallback = realRequest.getOAuthArguments().getReceivedCallbackUrl();
    if (!StringUtils.isBlank(receivedCallback)) {
      try {
        Uri parsed = Uri.parse(receivedCallback);
        String verifier = parsed.getQueryParameter(OAuth.OAUTH_VERIFIER);
        if (verifier != null) {
          msgParams.add(new Parameter(OAuth.OAUTH_VERIFIER, verifier));
        }
      } catch (IllegalArgumentException e) {
        throw new OAuthRequestException(OAuthError.INVALID_REQUEST,
            "Invalid received callback URL: " + receivedCallback, e);
      }
    }

    HttpRequest signed = sanitizeAndSign(request, msgParams, true);

    OAuthMessage reply = sendOAuthMessage(signed);

    accessor.accessToken = OAuthUtil.getParameter(reply, OAuth.OAUTH_TOKEN);
    accessor.tokenSecret = OAuthUtil.getParameter(reply, OAuth.OAUTH_TOKEN_SECRET);
    accessorInfo.setSessionHandle(OAuthUtil.getParameter(reply,
        OAuthConstants.OAUTH_SESSION_HANDLE));
    accessorInfo.setTokenExpireMillis(ACCESS_TOKEN_EXPIRE_UNKNOWN);
    if (OAuthUtil.getParameter(reply, OAuthConstants.OAUTH_EXPIRES_IN) != null) {
      try {
        int expireSecs = Integer.parseInt(OAuthUtil.getParameter(reply,
            OAuthConstants.OAUTH_EXPIRES_IN));
        long expireMillis = fetcherConfig.getClock().currentTimeMillis() + expireSecs * 1000L;
        accessorInfo.setTokenExpireMillis(expireMillis);
      } catch (NumberFormatException e) {
        // Hrm.  Bogus server.  We can safely ignore this, we'll just wait for the server to
        // tell us when the access token has expired.
        responseParams.logDetailedWarning("server returned bogus expiration");
      }
    }

    // Clients may want to retrieve extra information returned with the access token.  Several
    // OAuth service providers (e.g. Yahoo, NetFlix) return a user id along with the access
    // token, and the user id is required to use their APIs.  Clients signal that they need this
    // extra data by sending a fetch request for the access token URL.
    //
    // We don't return oauth* parameters from the response, because we know how to handle those
    // ourselves and some of them (such as oauth_token_secret) aren't supposed to be sent to the
    // client.
    //
    // Note that this data is not stored server-side.  Clients need to cache these user-ids or
    // other data themselves, probably in user prefs, if they expect to need the data in the
    // future.
    if (accessTokenUri.equals(realRequest.getUri())) {
      accessTokenData = Maps.newHashMap();
      for (Entry<String, String> param : OAuthUtil.getParameters(reply)) {
        if (!param.getKey().startsWith("oauth")) {
          accessTokenData.put(param.getKey(), param.getValue());
        }
      }
    }
  }

  /**
   * Save off our new token and secret to the persistent store.
   */
  private void saveAccessToken() throws OAuthRequestException {
    OAuthAccessor accessor = accessorInfo.getAccessor();
    TokenInfo tokenInfo = new TokenInfo(accessor.accessToken, accessor.tokenSecret,
        accessorInfo.getSessionHandle(), accessorInfo.getTokenExpireMillis());
    fetcherConfig.getTokenStore().storeTokenKeyAndSecret(realRequest.getSecurityToken(),
        accessorInfo.getConsumer(), realRequest.getOAuthArguments(), tokenInfo, responseParams);
  }

  /**
   * Builds the data we'll cache on the client while we make requests.
   */
  private void buildClientAccessState() {
    OAuthAccessor accessor = accessorInfo.getAccessor();
    responseParams.getNewClientState().setAccessToken(accessor.accessToken);
    responseParams.getNewClientState().setAccessTokenSecret(accessor.tokenSecret);
    responseParams.getNewClientState().setOwner(realRequest.getSecurityToken().getOwnerId());
    responseParams.getNewClientState().setSessionHandle(accessorInfo.getSessionHandle());
    responseParams.getNewClientState().setTokenExpireMillis(accessorInfo.getTokenExpireMillis());
  }

  /**
   * Get honest-to-goodness user data.
   *
   * @throws OAuthProtocolException if the service provider returns an OAuth
   * related error instead of user data.
   */
  private HttpResponseBuilder fetchData() throws OAuthRequestException, OAuthProtocolException {
    HttpResponseBuilder builder = null;
    if (accessTokenData != null) {
      // This is a request for access token data, return it.
      builder = formatAccessTokenData();
    } else {
      HttpRequest signed = sanitizeAndSign(realRequest, null, false);

      HttpResponse response = fetchFromServer(signed);

      checkForProtocolProblem(response);
      builder = new HttpResponseBuilder(response);
    }
    return builder;
  }

  private HttpResponse fetchFromServer(HttpRequest request) throws OAuthRequestException {
    HttpResponse response = null;
    try {
      response = fetcher.fetch(request);
      if (response == null) {
        throw new OAuthRequestException(OAuthError.MISSING_SERVER_RESPONSE);
      }
      return response;
    } catch (GadgetException e) {
      throw new OAuthRequestException(OAuthError.MISSING_SERVER_RESPONSE, "", e);
    } finally {
      responseParams.addRequestTrace(request, response);
    }
  }

  /**
   * Access token data is returned to the gadget as json key/value pairs:
   *
   *    { "user_id": "12345678" }
   */
  private HttpResponseBuilder formatAccessTokenData() {
    HttpResponseBuilder builder = new HttpResponseBuilder();
    builder.addHeader("Content-Type", "application/json; charset=utf-8");
    builder.setHttpStatusCode(HttpResponse.SC_OK);
    // no need to cache this, these requests should be fairly rare, and the results should be
    // cached in gadget.
    builder.setStrictNoCache();
    JSONObject json = new JSONObject(accessTokenData);
    builder.setResponseString(json.toString());
    return builder;
  }

  /**
   * Look for an OAuth protocol problem.  For cases where no access token is in play
   * @param response
   * @throws OAuthProtocolException
   */
  private void checkForProtocolProblem(HttpResponse response) throws OAuthProtocolException {
    if (couldBeFullOAuthError(response)) {
      // OK, might be OAuth related.
      OAuthMessage message = parseAuthHeader(null, response);
      if (OAuthUtil.getParameter(message, OAuthProblemException.OAUTH_PROBLEM) != null) {
        // SP reported extended error information
        throw new OAuthProtocolException(response.getHttpStatusCode(), message);
      }
      // No extended information, guess based on HTTP response code.
      if (response.getHttpStatusCode() == HttpResponse.SC_UNAUTHORIZED) {
        throw new OAuthProtocolException(response.getHttpStatusCode());
      }
    }
  }

  /**
   * Check if a response might be due to an OAuth protocol error.  We don't want to intercept
   * errors for signed fetch, we only care about places where we are dealing with OAuth request
   * and/or access tokens.
   */
  private boolean couldBeFullOAuthError(HttpResponse response) {
    // 400, 401 and 403 are likely to be authentication errors.  Unfortunately there is
    // significant overlap with other types of server errors as well, so we can't just assume
    // that the root cause of these errors is a bad token or a bad consumer key.
    if (response.getHttpStatusCode() != HttpResponse.SC_BAD_REQUEST
        && response.getHttpStatusCode() != HttpResponse.SC_UNAUTHORIZED
        && response.getHttpStatusCode() != HttpResponse.SC_FORBIDDEN) {
      return false;
    }
    // If the client forced us to use full OAuth, this might be OAuth related.
    if (realRequest.getOAuthArguments().mustUseToken()) {
      return true;
    }
    // If we're using an access token, this might be OAuth related.
    if (accessorInfo.getAccessor().accessToken != null) {
      return true;
    }
    // Not OAuth related.
    return false;
  }

  /**
   * Extracts only those parameters from an OAuthMessage that are OAuth-related.
   * An OAuthMessage may hold a whole bunch of non-OAuth-related parameters
   * because they were all needed for signing. But when constructing a request
   * we need to be able to extract just the OAuth-related parameters because
   * they, and only they, may have to be put into an Authorization: header or
   * some such thing.
   *
   * @param message the OAuthMessage object, which holds non-OAuth parameters
   * such as foo=bar (which may have been in the original URI query part, or
   * perhaps in the POST body), as well as OAuth-related parameters (such as
   * oauth_timestamp or oauth_signature).
   *
   * @return a list that contains only the oauth_related parameters.
   */
  static List<Map.Entry<String, String>> selectOAuthParams(OAuthMessage message) {
    List<Map.Entry<String, String>> result = Lists.newArrayList();
    for (Map.Entry<String, String> param : OAuthUtil.getParameters(message)) {
      if (isContainerInjectedParameter(param.getKey())) {
        result.add(param);
      }
    }
    return result;
  }

  protected static boolean isContainerInjectedParameter(String key) {
    key = key.toLowerCase();
    return key.startsWith("oauth") || key.startsWith("xoauth") || key.startsWith("opensocial");
  }
}
