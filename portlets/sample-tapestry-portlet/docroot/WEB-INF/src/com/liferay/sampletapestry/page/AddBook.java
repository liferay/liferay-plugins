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

package com.liferay.sampletapestry.page;

import com.liferay.sampletapestry.bean.Book;
import com.liferay.sampletapestry.bean.BookList;

import org.apache.tapestry.IPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.engine.IEngineService;
import org.apache.tapestry.event.PageBeginRenderListener;
import org.apache.tapestry.event.PageEvent;
import org.apache.tapestry.html.BasePage;

/**
 * @author Joseph Shum
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