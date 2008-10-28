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

package com.liferay.portlet.wikinavigation;

import java.util.LinkedList;
import java.util.List;

/**
 * <a href="EntryDisplay.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Thiago Moreira
 * 
 */
public class EntryDisplay {

	public void addChild(EntryDisplay child) {
		_children.add(child);
	}

	public void addChildren(List<EntryDisplay> children) {
		_children.addAll(children);
	}

	public List<EntryDisplay> getChildren() {
		return _children;
	}

	public boolean getExternalURL() {
		return _externalURL;
	}

	public String getLabel() {
		return _label;
	}

	public String getUrl() {
		return _url;
	}

	public void setChildren(List<EntryDisplay> children) {
		_children = children;
	}

	public void setExternalURL(boolean externalurl) {
		_externalURL = externalurl;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setUrl(String url) {
		_url = url;
	}

	private List<EntryDisplay> _children = new LinkedList<EntryDisplay>();
	private boolean _externalURL;
	private String _label;
	private String _url;

}