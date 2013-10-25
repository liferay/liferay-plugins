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

package com.liferay.sync.service.base;

import com.liferay.sync.service.SyncDLObjectServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SyncDLObjectServiceClpInvoker {
	public SyncDLObjectServiceClpInvoker() {
		_methodName36 = "getBeanIdentifier";

		_methodParameterTypes36 = new String[] {  };

		_methodName37 = "setBeanIdentifier";

		_methodParameterTypes37 = new String[] { "java.lang.String" };

		_methodName42 = "addFileEntry";

		_methodParameterTypes42 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.io.File", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName43 = "addFolder";

		_methodParameterTypes43 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName44 = "cancelCheckOut";

		_methodParameterTypes44 = new String[] { "long" };

		_methodName45 = "checkInFileEntry";

		_methodParameterTypes45 = new String[] {
				"long", "boolean", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName46 = "checkOutFileEntry";

		_methodParameterTypes46 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName47 = "checkOutFileEntry";

		_methodParameterTypes47 = new String[] {
				"long", "java.lang.String", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName48 = "getAllSyncDLObjects";

		_methodParameterTypes48 = new String[] { "long", "long" };

		_methodName49 = "getFileEntrySyncDLObject";

		_methodParameterTypes49 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName50 = "getFileEntrySyncDLObjects";

		_methodParameterTypes50 = new String[] { "long", "long" };

		_methodName51 = "getFolderSyncDLObject";

		_methodParameterTypes51 = new String[] { "long" };

		_methodName52 = "getFolderSyncDLObjects";

		_methodParameterTypes52 = new String[] { "long", "long" };

		_methodName53 = "getGroup";

		_methodParameterTypes53 = new String[] { "long" };

		_methodName54 = "getLatestModifiedTime";

		_methodParameterTypes54 = new String[] {  };

		_methodName55 = "getSyncContext";

		_methodParameterTypes55 = new String[] { "java.lang.String" };

		_methodName56 = "getSyncDLObjectUpdate";

		_methodParameterTypes56 = new String[] { "long", "long", "long" };

		_methodName57 = "getUserSitesGroups";

		_methodParameterTypes57 = new String[] {  };

		_methodName58 = "moveFileEntry";

		_methodParameterTypes58 = new String[] {
				"long", "long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName59 = "moveFileEntryToTrash";

		_methodParameterTypes59 = new String[] { "long" };

		_methodName60 = "moveFolder";

		_methodParameterTypes60 = new String[] {
				"long", "long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName61 = "moveFolderToTrash";

		_methodParameterTypes61 = new String[] { "long" };

		_methodName62 = "patchFileEntry";

		_methodParameterTypes62 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean", "java.io.File",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName63 = "restoreFileEntryFromTrash";

		_methodParameterTypes63 = new String[] { "long" };

		_methodName64 = "restoreFolderFromTrash";

		_methodParameterTypes64 = new String[] { "long" };

		_methodName65 = "updateFileEntry";

		_methodParameterTypes65 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "java.io.File", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName66 = "updateFolder";

		_methodParameterTypes66 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return SyncDLObjectServiceUtil.getBeanIdentifier();
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			SyncDLObjectServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return SyncDLObjectServiceUtil.addFileEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.io.File)arguments[7],
				(java.lang.String)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			return SyncDLObjectServiceUtil.addFolder(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return SyncDLObjectServiceUtil.cancelCheckOut(((Long)arguments[0]).longValue());
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return SyncDLObjectServiceUtil.checkInFileEntry(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				(java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return SyncDLObjectServiceUtil.checkOutFileEntry(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return SyncDLObjectServiceUtil.checkOutFileEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return SyncDLObjectServiceUtil.getAllSyncDLObjects(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFileEntrySyncDLObject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFileEntrySyncDLObjects(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFolderSyncDLObject(((Long)arguments[0]).longValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFolderSyncDLObjects(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return SyncDLObjectServiceUtil.getGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return SyncDLObjectServiceUtil.getLatestModifiedTime();
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return SyncDLObjectServiceUtil.getSyncContext((java.lang.String)arguments[0]);
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return SyncDLObjectServiceUtil.getSyncDLObjectUpdate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return SyncDLObjectServiceUtil.getUserSitesGroups();
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return SyncDLObjectServiceUtil.moveFileEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return SyncDLObjectServiceUtil.moveFileEntryToTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return SyncDLObjectServiceUtil.moveFolder(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return SyncDLObjectServiceUtil.moveFolderToTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return SyncDLObjectServiceUtil.patchFileEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				(java.io.File)arguments[8], (java.lang.String)arguments[9],
				(com.liferay.portal.service.ServiceContext)arguments[10]);
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return SyncDLObjectServiceUtil.restoreFileEntryFromTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return SyncDLObjectServiceUtil.restoreFolderFromTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return SyncDLObjectServiceUtil.updateFileEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				(java.io.File)arguments[7], (java.lang.String)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return SyncDLObjectServiceUtil.updateFolder(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName54;
	private String[] _methodParameterTypes54;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName60;
	private String[] _methodParameterTypes60;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
}