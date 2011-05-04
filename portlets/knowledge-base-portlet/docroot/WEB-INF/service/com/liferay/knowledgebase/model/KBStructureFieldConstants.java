/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.knowledgebase.model;

/**
 * @author Peter Shin
 */
public class KBStructureFieldConstants {

	public static final String DEFAULT_TYPE =
		KBStructureFieldConstants.TYPE_TEXT;

	public static final String TYPE_CHECKBOX = "checkbox";

	public static final String TYPE_SELECT = "select";

	public static final String TYPE_SELECT_MULTIPLE = "select-multiple";

	public static final String TYPE_TEXT = "text";

	public static final String TYPE_TEXTAREA = "textarea";

	public static final String TYPE_WYSIWYG_EDITOR = "wysiwyg-editor";

	public static final String[] TYPES = {
		TYPE_CHECKBOX, TYPE_SELECT, TYPE_SELECT_MULTIPLE, TYPE_TEXT,
		TYPE_TEXTAREA, TYPE_WYSIWYG_EDITOR
	};

}