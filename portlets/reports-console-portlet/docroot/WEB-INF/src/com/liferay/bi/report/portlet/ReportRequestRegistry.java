/*
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

package com.liferay.bi.report.portlet;

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.portal.kernel.bi.reporting.MemoryReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportDesignRetriever;
import com.liferay.portal.kernel.bi.reporting.ReportRequest;
import com.liferay.portal.kernel.bi.reporting.ReportResultContainer;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.sender.MessageSender;
import com.liferay.portal.kernel.uuid.PortalUUID;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <a href="ReportRequestSender.java.html"><b><i>View Source</i></b></a>
 *
 * TBD THIS IS ONLY FOR TESTING PURPOSES...
 *
 *
 * @author Michael C. Han
 */
public class ReportRequestRegistry implements MessageListener {

	public ReportRequestRegistry(
		PortalUUID generator, String requestDestination,
		String responseDestination, MessageSender sender) {
		_generator = generator;
		_messageSender = sender;
		_requestDestination = requestDestination;
		_responseDestination = responseDestination;
	}

	public RequestRegistryEntry getRequestEntry(String id) {
		RequestRegistryEntry entry = _entries.get(id);
		if (entry == null) {
			throw new IllegalArgumentException("No entry found for:" + id);
		}
		return entry;
	}

	public Collection<String> getRequestIds() {
		return Collections.unmodifiableCollection(_entries.keySet());
	}

	public void receive(Message message) {
		String requestId = message.getResponseId();
		RequestRegistryEntry entry = _entries.get(requestId);
		ReportResultContainer results =
			(ReportResultContainer)message.getPayload();
		entry.setResults(results);
	}

	public void remove(String id) {
		_entries.remove(id);
	}

	public String sendRequest(
		ReportDefinition definition, byte[] definitionFile) {
		String id = _generator.generate();
		ReportDesignRetriever retriever = new MemoryReportDesignRetriever(
			definition.getDefinitionName(), definitionFile);
		//TBD Need to configure the image path values...
		ReportRequest request = new ReportRequest(
			retriever, definition.getReportFormat(),
			null, null);

		Message message = new Message();
		message.setPayload(request);
		message.setResponseId(id);
		message.setResponseDestination(_responseDestination);
		_messageSender.send(_requestDestination, message);

		RequestRegistryEntry entry = new RequestRegistryEntry(id, request);
		_entries.put(id, entry);

		return id;
	}

	private Map<String, RequestRegistryEntry> _entries =
		new ConcurrentHashMap<String, RequestRegistryEntry>();
	private PortalUUID _generator;
	private String _responseDestination;
	private String _requestDestination;
	private MessageSender _messageSender;
}
