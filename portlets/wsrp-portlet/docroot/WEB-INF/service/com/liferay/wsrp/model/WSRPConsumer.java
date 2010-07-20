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

package com.liferay.wsrp.model;

/**
 * <p>
 * This interface is a model that represents the WSRP_WSRPConsumer table in the
 * database.
 * </p>
 *
 * <p>
 * Customize {@link com.liferay.wsrp.model.impl.WSRPConsumerImpl} and rerun the
 * ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WSRPConsumerModel
 * @see       com.liferay.wsrp.model.impl.WSRPConsumerImpl
 * @see       com.liferay.wsrp.model.impl.WSRPConsumerModelImpl
 * @generated
 */
public interface WSRPConsumer extends WSRPConsumerModel {
	public oasis.names.tc.wsrp.v2.types.RegistrationContext getRegistrationContext();

	public com.liferay.portal.kernel.util.UnicodeProperties getRegistrationProperties();

	public void setRegistrationContext(
		oasis.names.tc.wsrp.v2.types.RegistrationContext registrationContext);

	public void setRegistrationProperties(
		com.liferay.portal.kernel.util.UnicodeProperties registrationProperties);
}