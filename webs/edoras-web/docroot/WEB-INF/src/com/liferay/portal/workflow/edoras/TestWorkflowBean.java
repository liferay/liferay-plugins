/**
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

package com.liferay.portal.workflow.edoras;

import javax.annotation.PostConstruct;

import org.edorasframework.process.api.Process;
import org.edorasframework.process.api.annotation.ProcessConversation;
import org.edorasframework.process.api.model.ProcessModel;
import org.edorasframework.process.api.session.ProcessSession;
import org.edorasframework.process.api.setup.Default;
import org.springframework.util.Assert;


/**
 * <a href="TestWorkflowBean.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * TODO: add Class-Description here ...
 * </p>
 *
 * @author Micha Kiener
 *
 */
public class TestWorkflowBean {
	private String processModel;
	
	@PostConstruct
	public void init() {
		System.out.println(System.getProperty("default.liferay.home"));
		System.out.println(System.getProperty("liferay.home"));
		testLoadedModels();
	}
	
	@ProcessConversation
	public void testLoadedModels() {
		boolean sessionCreated = false;
		ProcessSession session = Process.getCurrentSession(Default.class);
		if (session == null) {
			session = Process.createSession(Default.class);
			sessionCreated = true;
		}
		try {
			Assert.notNull(session, "The session must not be null");

			ProcessModel model =
				session.getService().getProcessModel(
					"com.liferay.workflow.edoras.Test1", null);
			Assert.notNull(model, "The process model was not properly deployed");
			System.out.println("*** EDORAS WORKFLOW IS UP ***");
		}
		finally {
			if (sessionCreated && session != null) {
				session.close();
			}
		}
	}
	
	public void setProcessModel(String processModel) {
		this.processModel = processModel;
	}

	public String getProcessModel() {
		return processModel;
	}
}
