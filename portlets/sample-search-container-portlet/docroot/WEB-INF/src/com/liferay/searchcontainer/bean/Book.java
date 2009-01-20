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

package com.liferay.searchcontainer.bean;

import java.util.Date;

/**
 * <a href="Book.java.html"><b><i>View Source</i></b></a>
 *
 * @author Raymond Augé
 *
 */
public class Book {

	public Book() {
	}

	public Book(
			long bookId, String title, String author, String publisher,
			Date publicationDate) {

		_bookId = bookId;
		_title = title;
		_author = author;
		_publisher = publisher;
		_publicationDate = publicationDate;
	}

    public String getAuthor() {
    	return _author;
    }

    public void setAuthor(String author) {
    	_author = author;
    }

    public long getBookId() {
    	return _bookId;
    }

    public void setBookId(long bookId) {
    	_bookId = bookId;
    }

	public String getPublisher() {
    	return _publisher;
    }

    public void setPublisher(String publisher) {
    	_publisher = publisher;
    }

    public Date getPublicationDate() {
    	return _publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
    	_publicationDate = publicationDate;
    }

    public String getTitle() {
    	return _title;
    }

    public void setTitle(String title) {
    	_title = title;
    }

	private String _author;
	private long _bookId;
	private String _publisher;
	private Date _publicationDate;
	private String _title;

}