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

package com.liferay.westminstercatechism.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class WCEntry implements Serializable {

	public WCEntry(String question, String answer, String[][] proofs) {
		_question = question;
		_answer = answer;
		_proofs = proofs;
	}

	public String getQuestion() {
		return _question;
	}

	public String getAnswer() {
		return _answer;
	}

	public String[][] getProofs() {
		return _proofs;
	}

	public void setProofs(String[][] proofs) {
		_proofs = proofs;
	}

	private String _question;
	private String _answer;
	private String[][] _proofs;

}