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

package com.liferay.chat.video.scheduler;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.chat.video.WebRTCManager;

/**
 * WebRTC connection state scheduler
 *
 * This class is instantiated by Liferay and checks at a regular
 * interval all the connection states of all the WebRTC managers.
 *
 * @author Philippe Proulx <philippe.proulx@savoirfairelinux.com>
 */
public class WebRTCConnectionStateScheduler extends BaseMessageListener {
    @Override
    protected void doReceive(Message message) throws Exception {
        WebRTCManager.checkAllManagersConnectionsStates();
    }
}
