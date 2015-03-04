/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.runtime.notification;

/**
 * @author Val Nagy
 */
public abstract class NotificationConstants {

	public enum EMAIL_RECIPIENT_TYPE {

		NOT_APPLICABLE(0), TO(1), CC(2), BCC(3);

		public final int type;

		private EMAIL_RECIPIENT_TYPE(int type) {
			this.type = type;
		}

	}

	public enum RECIPIENT_SCRIPT_LANGUAGE {

		BEANSHELL("beanshell"), DRL("drl"), GROOVY("groovy"),
		JAVASCRIPT("javascript"), PYTHON("python"), RUBY("ruby");

		public static boolean hasScriptingLanguage(String scriptingLanguage) {
			for (RECIPIENT_SCRIPT_LANGUAGE value : values()) {
				if (value.scriptingLanguage.equals(scriptingLanguage)) {
					return true;
				}
			}

			return false;
		}

		public final String scriptingLanguage;

		private RECIPIENT_SCRIPT_LANGUAGE(String scriptingLanguage) {
			this.scriptingLanguage = scriptingLanguage;
		}

	}

}