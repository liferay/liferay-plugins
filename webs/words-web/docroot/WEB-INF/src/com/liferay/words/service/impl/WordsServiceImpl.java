/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.words.service.impl;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.util.SerializableUtil;
import com.liferay.util.jazzy.InvalidWord;
import com.liferay.words.service.base.WordsServiceBaseImpl;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ken Boyer
 * @author Eric Chin
 */
public class WordsServiceImpl extends WordsServiceBaseImpl {

	@Override
	public List<String> checkSpelling(String text) throws Exception {
		List<String> invalidWords = new ArrayList<String>();

		for (InvalidWord invalidWord : getInvalidWords(text)) {
			invalidWords.add(invalidWord.getInvalidWord());
		}

		return invalidWords;
	}

	@Override
	public List<String> getSuggestions(String word) throws Exception {
		List<InvalidWord> invalidWords = getInvalidWords(word);

		if (invalidWords.isEmpty()) {
			return Collections.emptyList();
		}

		InvalidWord invalidWord = invalidWords.get(0);

		return invalidWord.getSuggestions();
	}

	protected List<InvalidWord> getInvalidWords(String text) throws Exception {
		ClassLoader portalClassLoader = PortalClassLoaderUtil.getClassLoader();

		Class<?> wordsUtilClass = portalClassLoader.loadClass(
			"com.liferay.portal.words.WordsUtil");

		Method method = ReflectionUtil.getDeclaredMethod(
			wordsUtilClass, "checkSpelling", String.class);

		return (List<InvalidWord>)SerializableUtil.clone(
			method.invoke(null, text));
	}

}