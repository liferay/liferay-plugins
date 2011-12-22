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

package com.liferay.samplestruts.struts.form;

import com.liferay.samplestruts.model.Book;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author Scott Lee
 */
public class NestedForm extends ActionForm {

	public Collection getBooks() {
		return _books;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest req) {
		_books = new ArrayList();

		_books.add(new Book("1", "Genesis"));
		_books.add(new Book("2", "Exodus"));
		_books.add(new Book("3", "Leviticus"));
		_books.add(new Book("4", "Numbers"));
		_books.add(new Book("5", "Deuteronomy"));
	}

	@Override
	public ActionErrors validate(
		ActionMapping mapping, HttpServletRequest req) {

		ActionErrors errors = new ActionErrors();

		return errors;
	}

	@Override
	public String toString() {
		return _books.toString();
	}

	private Collection _books;

}