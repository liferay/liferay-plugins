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

package com.liferay.googlegadget.model;

/**
 * <a href="GGPagination.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class GGPagination {

	public static final int DEFAULT_DELTA = 24;

	public GGPagination(int delta, int start, int end, int total) {
		_delta = delta;
		_start = start;
		_end = end;
		_total = total;
	}

	public int getDelta() {
		return _delta;
	}

	public void setDelta(int delta) {
		_delta = delta;
	}

	public int getStart() {
		return _start;
	}

	public void setStart(int start) {
		_start = start;
	}

	public int getEnd() {
		return _end;
	}

	public void setEnd(int end) {
		_end = end;
	}

	public int getTotal() {
		return _total;
	}

	public void setTotal(int total) {
		_total = total;
	}

	private int _delta;
	private int _start;
	private int _end;
	private int _total;

}