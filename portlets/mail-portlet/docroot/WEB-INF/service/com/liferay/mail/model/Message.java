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

package com.liferay.mail.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the Message service. Represents a row in the &quot;Mail_Message&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MessageModel
 * @see com.liferay.mail.model.impl.MessageImpl
 * @see com.liferay.mail.model.impl.MessageModelImpl
 * @generated
 */
@ProviderType
public interface Message extends MessageModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.mail.model.impl.MessageImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Message, Long> MESSAGE_ID_ACCESSOR = new Accessor<Message, Long>() {
			@Override
			public Long get(Message message) {
				return message.getMessageId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Message> getTypeClass() {
				return Message.class;
			}
		};

	public long getGroupId()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean hasAttachments();

	public boolean hasFlag(int flag);
}