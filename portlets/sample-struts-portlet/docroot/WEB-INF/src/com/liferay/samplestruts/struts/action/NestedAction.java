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

package com.liferay.samplestruts.struts.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.samplestruts.model.Book;
import com.liferay.samplestruts.struts.form.NestedForm;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Scott Lee
 */
public class NestedAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
		throws Exception {

		NestedForm nestedForm = (NestedForm)form;

		Iterator itr = nestedForm.getBooks().iterator();

		while (itr.hasNext()) {
			Book book = (Book)itr.next();

			if (_log.isInfoEnabled()) {
				_log.info(
					"Book " + book.getIsbn() + " " + book.getTitle() + " " +
						book.getCover());
			}
		}

		return mapping.findForward("/sample_struts_portlet/nested_success");
	}

	private static Log _log = LogFactoryUtil.getLog(NestedAction.class);

}