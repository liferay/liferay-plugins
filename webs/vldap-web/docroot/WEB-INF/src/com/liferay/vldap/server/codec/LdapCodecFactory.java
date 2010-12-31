/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.vldap.server.codec;

import org.apache.directory.shared.asn1.codec.Asn1CodecDecoder;
import org.apache.directory.shared.asn1.codec.Asn1CodecEncoder;
import org.apache.directory.shared.asn1.codec.stateful.StatefulDecoder;
import org.apache.directory.shared.asn1.codec.stateful.StatefulEncoder;
import org.apache.directory.shared.ldap.message.MessageDecoder;
import org.apache.directory.shared.ldap.message.MessageEncoder;
import org.apache.directory.shared.ldap.message.spi.BinaryAttributeDetector;
import org.apache.directory.shared.ldap.schema.SchemaManager;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class LdapCodecFactory implements ProtocolCodecFactory {

	public LdapCodecFactory(SchemaManager schemaManager) {
		_binaryAttributeDetector = new DefaultBinaryAttributeDetector(
			schemaManager);
	}

	public ProtocolDecoder getDecoder(IoSession ioSession) {
		StatefulDecoder statefulDecoder = new MessageDecoder(
			_binaryAttributeDetector);

		return new Asn1CodecDecoder(statefulDecoder);
	}

	public ProtocolEncoder getEncoder(IoSession ioSession) {
		StatefulEncoder statefulEncoder = new MessageEncoder();

		return new Asn1CodecEncoder(statefulEncoder);
	}

	private BinaryAttributeDetector _binaryAttributeDetector;

}