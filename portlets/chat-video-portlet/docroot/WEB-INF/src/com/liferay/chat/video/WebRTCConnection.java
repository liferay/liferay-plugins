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
package com.liferay.chat.video;

/**
 * WebRTC connection (thread safe)
 *
 * This represents a connection between two clients. It is owned by
 * both clients connected together, but still holds a reference to
 * the original caller so that we know it later.
 *
 * @author Philippe Proulx <philippe.proulx@savoirfairelinux.com>
 */
public class WebRtcConnection {
	public enum State {
		INITIATED,
		CONNECTED,
		DISCONNECTED
	}
	private State currentState = State.DISCONNECTED;
	private final WebRtcClient caller;
    private long initiatedTsMs = -1;

	public WebRtcConnection(WebRtcClient caller) {
		this.caller = caller;
	}
	
	public synchronized State getState() {
		return this.currentState;
	}
	
	public synchronized void setState(State state) {
		this.currentState = state;
        if (state == State.INITIATED) {
            this.initiatedTsMs = System.currentTimeMillis();
        } else {
            this.initiatedTsMs = -1;
        }
	}
	
	public WebRtcClient getCaller() {
		return this.caller;
	}

    public long getInitatedTsMs() {
        if (this.initiatedTsMs == -1) {
            return -1;
        }
        return System.currentTimeMillis() - this.initiatedTsMs;
    }
}
