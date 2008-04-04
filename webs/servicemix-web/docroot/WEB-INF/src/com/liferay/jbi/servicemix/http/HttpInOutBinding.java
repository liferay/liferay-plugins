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

package com.liferay.jbi.servicemix.http;

import java.io.IOException;

import java.util.Enumeration;

import javax.jbi.JBIException;
import javax.jbi.messaging.ExchangeStatus;
import javax.jbi.messaging.InOut;
import javax.jbi.messaging.NormalizedMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.transform.TransformerException;

/**
 * <a href="HttpInOutBinding.java.html"><b><i>View Source</i></b></a>
 *
 * @author Charles May
 *
 */
public class HttpInOutBinding
	extends org.apache.servicemix.components.http.HttpInOutBinding {

	public void processInOut(HttpServletRequest req, HttpServletResponse res)
		throws IOException, JBIException, ServletException {

		InOut exchange = getExchangeFactory().createInOutExchange();

		NormalizedMessage in = exchange.createMessage();

		try {
			getMarshaler().toNMS(exchange, in, req);

			Enumeration enu = req.getParameterNames();

			while (enu.hasMoreElements()) {
				try {
					String name = (String)enu.nextElement();

					String value = req.getParameter(name);

					in.setProperty(name, value);
				}
				catch (Exception e) {
				}
			}

			exchange.setInMessage(in);

			boolean result = getDeliveryChannel().sendSync(exchange);

			if (result) {
				if (exchange.getStatus() == ExchangeStatus.ERROR) {
					if (exchange.getError() != null) {
						throw new ServletException(exchange.getError());
					}
					else {
						throw new ServletException("Exchange status is ERROR");
					}
				}

				getMarshaler().toResponse(
					exchange, exchange.getOutMessage(), res);
			}

			done(exchange);

			res.setStatus(HttpServletResponse.SC_OK);
		}
		catch (IOException ioe) {
			fail(exchange, ioe);
			outputException(res, ioe);
		}
		catch (TransformerException te) {
			fail(exchange, te);
			outputException(res, te);
		}
	}

}