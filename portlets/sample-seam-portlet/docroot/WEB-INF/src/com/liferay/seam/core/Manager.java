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

package com.liferay.seam.core;

import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.portlet.PortletRequest;

import static org.jboss.seam.annotations.Install.DEPLOYMENT;
import static org.jboss.seam.InterceptionType.NEVER;
import org.jboss.seam.annotations.Install;
import org.jboss.seam.annotations.Intercept;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.ScopeType;

/**
 * <a href="Manager.java.html"><b><i>View Source</i></b></a>
 *
 * @author Neil Griffin
 *
 * This class is necessary because of a bug in JBoss Seam 1.2.1GA:
 *      http://jira.jboss.org/jira/browse/JBSEAM-1557
 *
 * Note that the precedence of the @Install annotation is DEPLOYMENT, which
 * has a higher precedence value that BUILTIN (the value given to the class
 * that is being extended). This higher precedence will cause this class
 * to be the "winning" Seam component.
 */
@Install(precedence = DEPLOYMENT)
@Intercept(NEVER)
@Name("org.jboss.seam.core.manager")
@Scope(ScopeType.EVENT)
public class Manager extends org.jboss.seam.core.Manager {

	@Override public void redirect(
		String viewId, Map<String, Object> parameters,
		boolean includeConversationId) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();

		// If this Seam application is running as a portlet, then force the
		// ViewHandler to create the view associated with the new viewId,
		// and let the portlet lifecycle happen gracefully, so that the
		// portlet doView() method will be executed, and render the new
		// view on the portal page (within the portlet) accordingly.
		if (externalContext.getRequest() instanceof PortletRequest) {

			UIViewRoot uiViewRoot =
				facesContext.getApplication().getViewHandler().createView(
					facesContext, viewId);
			facesContext.setViewRoot(uiViewRoot);
			facesContext.renderResponse();
		}

		// Otherwise, must be running as a servlet, and so let the original
		// superclass handle this redirect request.
		else {
			super.redirect(viewId, parameters, includeConversationId);
		}
	}

}