/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.handler;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class DispatchHandler implements IoHandler {

	public void exceptionCaught(IoSession ioSession, Throwable cause) {
		System.out.println("## here exceptionCaught");
	}

	public void messageReceived(IoSession ioSession, Object message) {
		System.out.println("## here messageReceived");
	}

	public void messageSent(IoSession ioSession, Object message) {
		System.out.println("## here messageSent");
	}

	public void sessionClosed(IoSession ioSession) {
		System.out.println("## here sessionClosed");
	}

	public void sessionCreated(IoSession ioSession) {
		System.out.println("## here sessionCreated");
	}

	public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) {
		System.out.println("## here sessionIdle");
	}

	public void sessionOpened(IoSession ioSession) {
		System.out.println("## here sessionOpened");
	}

}