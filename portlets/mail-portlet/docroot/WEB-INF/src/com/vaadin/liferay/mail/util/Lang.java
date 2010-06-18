
package com.vaadin.liferay.mail.util;

import java.util.ResourceBundle;

import com.liferay.portal.kernel.language.LanguageUtil;

import com.vaadin.liferay.mail.Controller;

public class Lang {
	private static ResourceBundle langBundle = ResourceBundle.getBundle("content.Language", Controller.get().getUserLocale());
		
	public static String get(String key) {			
		if(langBundle.containsKey(key)){
			return langBundle.getString(key);
		} else {
			return LanguageUtil.get(Controller.get().getUserLocale(), key);
		}		
	}

	public static String get(String key, String... arguments) {						
		return LanguageUtil.format(
			Controller.get().getUserLocale(), Lang.get(key), arguments);
	}
}