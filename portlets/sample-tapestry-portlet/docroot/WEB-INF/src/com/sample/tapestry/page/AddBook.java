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

package com.sample.tapestry.page;

import com.sample.tapestry.bean.Book;
import com.sample.tapestry.bean.BookList;

import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.engine.IEngineService;
import org.apache.tapestry.event.PageBeginRenderListener;
import org.apache.tapestry.event.PageEvent;
import org.apache.tapestry.html.BasePage;

/**
 * <a href="AddBook.java.html"><b><i>View Source</i></b></a>
 *
 * @author Joseph Shum
 *
 */
public abstract class AddBook
	extends BasePage implements PageBeginRenderListener {

	public abstract Book getBook();

	public abstract void setBook(Book book);

	public abstract BookList getBookList();

	public abstract void setBookList(BookList bookList);

	public abstract View getView();

	public abstract IEngineService getPageService();

	public void pageBeginRender(PageEvent event) {
		Book book = new Book();

		setBook(book);
	}

	public IPage doSubmit(IRequestCycle cycle) {
		View view = getView();

		Book book = getBook();

		BookList bookList = getBookList();

		bookList.addBook(book);

		view.setBooks(bookList.getBooks());

		return view;

	}

	public IPage doCancel(IRequestCycle cycle) {
		View view = getView();

		return view;
	}

}