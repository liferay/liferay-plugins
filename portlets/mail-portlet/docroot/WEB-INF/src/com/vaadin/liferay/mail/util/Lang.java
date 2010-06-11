
package com.vaadin.liferay.mail.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.vaadin.liferay.mail.Controller;

public class Lang {

	public static String get(String key) {

		return LanguageUtil.get(Controller.get().getUserLocale(), key);
	}

	public static String get(String key, String... arguments) {

		return LanguageUtil.format(
			Controller.get().getUserLocale(), key, arguments);
	}
}
