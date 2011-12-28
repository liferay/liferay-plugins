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

package com.liferay.mail.imap;

import com.liferay.mail.util.DefaultAttachmentHandler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.InputStream;

import javax.mail.Folder;
import javax.mail.MessagingException;

/**
 * @author Ryan Park
 */
public class IMAPAttachmentHandler extends DefaultAttachmentHandler {

	public IMAPAttachmentHandler(InputStream inputStream, Folder folder) {
		super(inputStream, folder);
	}

	@Override
	public void cleanUp() {
		try {
			if ((_folder == null) || !_folder.isOpen()) {
				return;
			}

			_folder.close(false);
		}
		catch (MessagingException me) {
			_log.error(me, me);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		IMAPAttachmentHandler.class);

}