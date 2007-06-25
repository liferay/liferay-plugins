/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
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

package com.sample.struts.struts.form;

import com.sample.struts.model.Book;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * <a href="NestedForm.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 *
 */
public class NestedForm extends ActionForm {

	public Collection getBooks() {
		return _books;
	}

	public void reset(ActionMapping mapping, HttpServletRequest req) {
		_books = new ArrayList();

		_books.add(new Book("1", "Genesis"));
		_books.add(new Book("2", "Exodus"));
		_books.add(new Book("3", "Leviticus"));
		_books.add(new Book("4", "Numbers"));
		_books.add(new Book("5", "Deuteronomy"));
	}

	public ActionErrors validate(
		ActionMapping mapping, HttpServletRequest req) {

		ActionErrors errors = new ActionErrors();

		return errors;
	}

	public String toString() {
		return _books.toString();
	}

	private Collection _books;

}