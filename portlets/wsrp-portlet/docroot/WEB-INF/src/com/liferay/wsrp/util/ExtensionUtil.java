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

package com.liferay.wsrp.util;

import java.util.ArrayList;
import java.util.List;

import oasis.names.tc.wsrp.v2.types.Extension;

import org.apache.axis.message.MessageElement;

/**
 * <a href="ExtensionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael Young
 *
 */
public class ExtensionUtil {

	public static void addMessageElement(
		List<MessageElement> messageElements, String localPart, String value) {

		MessageElement messageElement = new MessageElement(
			"liferay", localPart);

		messageElement.setValue(value);

		messageElements.add(messageElement);
	}

	public static Extension getExtension(List<MessageElement> messageElements) {
		MessageElement[] messageElementsArray = messageElements.toArray(
			new MessageElement[messageElements.size()]);

		return new Extension(messageElementsArray);
	}

	public static Extension[] getExtensions(
		List<MessageElement> messageElements) {

		List<Extension> extensions = new ArrayList<Extension>();

		extensions.add(getExtension(messageElements));

		return extensions.toArray(new Extension[extensions.size()]);
	}

	public static MessageElement[] getMessageElements(
		Extension[] extensions) {

		MessageElement[] messageElements = null;

		if ((extensions != null) && (extensions.length == 1)) {
			Extension extension = extensions[0];

			messageElements = extension.get_any();
		}

		return messageElements;
	}

}