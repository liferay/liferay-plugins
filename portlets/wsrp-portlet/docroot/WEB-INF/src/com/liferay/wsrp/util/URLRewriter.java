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

package com.liferay.wsrp.util;

import com.liferay.portal.kernel.util.StringPool;
import com.sun.portal.wsrp.common.WSRPSpecKeys;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletModeException;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;
import javax.portlet.WindowStateException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="URLRewriter.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class URLRewriter {	
	public static String rewrite(String markup, RenderResponse res) {
		StringBuffer resultMarkup = new StringBuffer("");
		int markupIndex = 0;
		int rewriteStartPos = -1;
		int rewriteEndPos = -1;
		int currentPos = 0;
		String exprType = null;

		// Loop through the markup, find rewrite expressions, rewrite them 

		while (markupIndex < markup.length()) {

			rewriteStartPos = -1;
			rewriteEndPos = -1;

			// Get first occurrence of wsrp rewrite expression

			rewriteStartPos = markup.indexOf(WSRPSpecKeys.COMMON,
					markupIndex);

			// Found a rewrite start token, and token is not at the end of 
			// markup so we can determine the rewrite type, i.e. there is at 
			// least 1 char after the rewrite start token
			
			boolean atEnd = rewriteStartPos + 
				WSRPSpecKeys.COMMON.length() - 1 > markup.length() - 2;
			
			if (!(rewriteStartPos == -1 || atEnd)) {

				// Namespace or URL? The single char string after the token 
				// decides

				exprType = markup.substring(rewriteStartPos
						+ WSRPSpecKeys.COMMON.length(),
						rewriteStartPos + WSRPSpecKeys.COMMON.length() + 1);

				if (exprType.equals(StringPool.UNDERLINE)) {

					// Ignore namespace rewrite						

					rewriteEndPos = rewriteStartPos
							+ WSRPSpecKeys.COMMON.length()
							+ StringPool.UNDERLINE.length() - 1;
				}
				else if (exprType.equals(StringPool.QUESTION)) {

					// We have a URL rewrite here
					// Get the position of the end token

					rewriteEndPos = markup.indexOf(WSRPSpecKeys.URL_REWRITE_END,
							markupIndex);
					
					if (rewriteEndPos != -1) {

						// See if we find a rewrite start token nearer to the 
						// end token

						currentPos = rewriteStartPos;

						while ((currentPos != -1)
								&& (currentPos < rewriteEndPos)) {

							// Update rewriteStartPos with position of found 
							// rewrite begin token being 'nearer' 
						
							rewriteStartPos = currentPos;
							
							// Look for next URL rewrite start expression
							
							currentPos = markup.indexOf(WSRPSpecKeys.COMMON
									+ StringPool.QUESTION, rewriteStartPos
									+ WSRPSpecKeys.COMMON.length()
									+ StringPool.QUESTION.length());
						}
						
						rewriteEndPos = rewriteEndPos
								+ WSRPSpecKeys.URL_REWRITE_END.length() - 1;
					}
				}
			}

			if ((rewriteStartPos != -1) && (rewriteEndPos != -1)) {
				
				// Append markup before rewrite expression
				
				resultMarkup.append(markup.substring(markupIndex,
						rewriteStartPos));
				
				// Append rewritten expression
				
				_rewriteURL(resultMarkup, markup.substring(rewriteStartPos,
						rewriteEndPos + 1), res);
				
				// Set markup index after the last char of the rewriteExpression
				
				markupIndex = rewriteEndPos + 1;
			}
			else {
				
				// Append rest of markup

				resultMarkup.append(markup.substring(markupIndex, markup
						.length()));
				
				markupIndex = markup.length();
			}
		}

		return resultMarkup.toString();
	}	

	private static void _rewriteURL(
		StringBuffer markup, String rewriteURL, RenderResponse res) {

		String replacement = StringPool.BLANK;
		
		if (rewriteURL.startsWith(WSRPSpecKeys.COMMON + StringPool.QUESTION)) {
			
			Map<String, String> params = _parseURL(rewriteURL);
			
			if (rewriteURL.indexOf(
				WSRPSpecKeys.URL_TYPE_BLOCKING_ACTION) != -1) {
				
				replacement = _getPortletURL(res.createActionURL(), params);
			}
			else if (rewriteURL.indexOf(WSRPSpecKeys.URL_TYPE_RENDER) != -1) {
				replacement = _getPortletURL(res.createActionURL(), params);
			}
			else if (rewriteURL.indexOf(WSRPSpecKeys.URL_TYPE_RESOURCE) != -1) {
				replacement = _getResourceURL(res.createResourceURL(), params);
			}
		}
		else if (rewriteURL.startsWith(WSRPSpecKeys.COMMON
				+ StringPool.UNDERLINE)) {

			// Handle namespace rewriting

			replacement = res.getNamespace();
		}
		else {
			_log.error("No valid rewrite expression found in: " + rewriteURL);
		}
		
		markup.append(replacement);
	}

	private static Map<String, String> _parseURL(String url) {
		Map<String, String> params = new HashMap<String, String>();

		if (url.indexOf(WSRPSpecKeys.URL_TYPE_BLOCKING_ACTION) != -1) {
			params.put(
				WSRPSpecKeys.URL_TYPE, WSRPSpecKeys.URL_TYPE_BLOCKING_ACTION);
		}
		else if (url.indexOf(WSRPSpecKeys.URL_TYPE_RENDER) != -1) {
			params.put(WSRPSpecKeys.URL_TYPE, WSRPSpecKeys.URL_TYPE_RENDER);
		}
		else if (url.indexOf(WSRPSpecKeys.URL_TYPE_RESOURCE) != -1) {
			params.put(WSRPSpecKeys.URL_TYPE, WSRPSpecKeys.URL_TYPE_RESOURCE);

		}

		int equals = 0;
		int next = 0;
		int end = url.indexOf(WSRPSpecKeys.URL_REWRITE_END);
		int index = url.indexOf(StringPool.AMPERSAND);
		int lengthNext = 0;
		String subNext = null;

		while (index != -1) {

			// Support "&amp;" as parameter separator

			subNext = url.substring(index, 
				index + StringPool.AMPERSAND_ENCODED.length());

			if (subNext.equals(StringPool.AMPERSAND_ENCODED)) {
				lengthNext = StringPool.AMPERSAND_ENCODED.length();
			}
			else {
				lengthNext = StringPool.AMPERSAND.length();
			}

			equals = url.indexOf(StringPool.EQUAL, index + lengthNext);
			next = url.indexOf(StringPool.AMPERSAND, equals);

			if (equals != -1) {
				if (next != -1) {
					params.put(url.substring(index + lengthNext, equals),
						url.substring(equals + 1, next));
				}
				else {
					params.put(url.substring(index + lengthNext, equals),
						url.substring(equals + 1, end));
				}
			}

			index = next;
		}

		Set<String> keys = params.keySet();
		
		for (String key : keys) {
			String value = params.get(key);

			try {
				params.put(key, URLDecoder.decode(value, StringPool.UTF8));
			} 
			catch (Exception e) {
			}
		}
		
		return params;		
	}
	
	private static String _getPortletURL(
		PortletURL url, Map<String, String> params) {
		
		for (String key : params.keySet()) {
			String value = params.get(key);

			if (key.equalsIgnoreCase(WSRPSpecKeys.WINDOW_STATE)) {
				try {
					url.setWindowState(WSRPState.fromWSRP(value).getPortlet());
				}
				catch (WindowStateException e) {
					_log.error(e.getMessage(), e);
				}
			}
			else if (key.equalsIgnoreCase(WSRPSpecKeys.MODE)) {
				try {
					url.setPortletMode(WSRPMode.fromWSRP(value).getPortlet());
				}
				catch (PortletModeException e) {
					_log.error(e.getMessage(), e);
				}
			}
			else if (key.equals(WSRPSpecKeys.NAVIGATIONAL_STATE) ||
				key.equals(WSRPSpecKeys.INTERACTION_STATE)) {
				
				url.setParameter(key, value);
			}
		}
		
		return url.toString();
	}

	private static String _getResourceURL(
		ResourceURL url, Map<String, String> params) {

		for (String key : params.keySet()) {
			String value = params.get(key);

			if (key.equalsIgnoreCase(WSRPSpecKeys.WINDOW_STATE) ||
				key.equalsIgnoreCase(WSRPSpecKeys.MODE) ||
				key.equalsIgnoreCase(WSRPSpecKeys.NAVIGATIONAL_STATE) ||
				key.equalsIgnoreCase(WSRPSpecKeys.INTERACTION_STATE)) {
				
				url.setParameter(key, value);
			}
			else if (key.equalsIgnoreCase(WSRPSpecKeys.URL)) {
				url.setResourceID(value);
			}
		}	
		
		return url.toString() + "&compress=0&strip=0";
	}
	
	private static Log _log = LogFactory.getLog(URLRewriter.class);

}
