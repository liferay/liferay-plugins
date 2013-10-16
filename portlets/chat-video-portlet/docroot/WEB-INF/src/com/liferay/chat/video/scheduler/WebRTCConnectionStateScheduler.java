/**
 * Copyright (c) 2013 Savoir-faire Linux Inc.
 *     Philippe Proulx <philippe.proulx@savoirfairelinux.com>.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms are permitted
 * provided that the above copyright notice and this paragraph are
 * duplicated in all such forms and that any documentation,
 * advertising materials, and other materials related to such
 * distribution and use acknowledge that the software was developed
 * by Savoir-faire Linux.  The name of Savoir-faire Linux
 * may not be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package com.liferay.chat.video.scheduler;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.chat.video.WebRtcManager;

/**
 * WebRTC connection state scheduler
 *
 * This class is instantiated by Liferay and checks at a regular
 * interval all the connection states of all the WebRTC managers.
 *
 * @author Philippe Proulx <philippe.proulx@savoirfairelinux.com>
 */
public class WebRtcConnectionStateScheduler extends BaseMessageListener {
    @Override
    protected void doReceive(Message message) throws Exception {
        WebRtcManager.checkAllManagersConnectionsStates();
    }
}
