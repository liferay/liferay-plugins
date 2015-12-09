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

package com.liferay.sync.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.sync.service.SyncDLObjectServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SyncDLObjectServiceClpInvoker {
	public SyncDLObjectServiceClpInvoker() {
		_methodName68 = "getOSGiServiceIdentifier";

		_methodParameterTypes68 = new String[] {  };

		_methodName73 = "addFileEntry";

		_methodParameterTypes73 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.io.File", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName74 = "addFolder";

		_methodParameterTypes74 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName75 = "cancelCheckOut";

		_methodParameterTypes75 = new String[] { "long" };

		_methodName76 = "checkInFileEntry";

		_methodParameterTypes76 = new String[] {
				"long", "boolean", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName77 = "checkOutFileEntry";

		_methodParameterTypes77 = new String[] {
				"long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName78 = "checkOutFileEntry";

		_methodParameterTypes78 = new String[] {
				"long", "java.lang.String", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName79 = "copyFileEntry";

		_methodParameterTypes79 = new String[] {
				"long", "long", "long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName80 = "getAllFolderSyncDLObjects";

		_methodParameterTypes80 = new String[] { "long" };

		_methodName81 = "getFileEntrySyncDLObject";

		_methodParameterTypes81 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName82 = "getFileEntrySyncDLObjects";

		_methodParameterTypes82 = new String[] { "long", "long" };

		_methodName83 = "getFolderSyncDLObject";

		_methodParameterTypes83 = new String[] { "long" };

		_methodName84 = "getFolderSyncDLObject";

		_methodParameterTypes84 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName85 = "getFolderSyncDLObjects";

		_methodParameterTypes85 = new String[] { "long", "long" };

		_methodName86 = "getGroup";

		_methodParameterTypes86 = new String[] { "long" };

		_methodName87 = "getLatestModifiedTime";

		_methodParameterTypes87 = new String[] {  };

		_methodName88 = "getSyncContext";

		_methodParameterTypes88 = new String[] {  };

		_methodName89 = "getSyncDLObjectUpdate";

		_methodParameterTypes89 = new String[] { "long", "long", "int" };

		_methodName90 = "getSyncDLObjectUpdate";

		_methodParameterTypes90 = new String[] { "long", "long", "int", "boolean" };

		_methodName91 = "getSyncDLObjectUpdate";

		_methodParameterTypes91 = new String[] { "long", "long", "long" };

		_methodName92 = "getUserSitesGroups";

		_methodParameterTypes92 = new String[] {  };

		_methodName93 = "moveFileEntry";

		_methodParameterTypes93 = new String[] {
				"long", "long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName94 = "moveFileEntryToTrash";

		_methodParameterTypes94 = new String[] { "long" };

		_methodName95 = "moveFolder";

		_methodParameterTypes95 = new String[] {
				"long", "long", "com.liferay.portal.service.ServiceContext"
			};

		_methodName96 = "moveFolderToTrash";

		_methodParameterTypes96 = new String[] { "long" };

		_methodName97 = "patchFileEntry";

		_methodParameterTypes97 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "java.io.File", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName98 = "restoreFileEntryFromTrash";

		_methodParameterTypes98 = new String[] { "long" };

		_methodName99 = "restoreFolderFromTrash";

		_methodParameterTypes99 = new String[] { "long" };

		_methodName100 = "updateFileEntries";

		_methodParameterTypes100 = new String[] { "java.io.File" };

		_methodName101 = "updateFileEntry";

		_methodParameterTypes101 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "java.io.File", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName102 = "updateFolder";

		_methodParameterTypes102 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return SyncDLObjectServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return SyncDLObjectServiceUtil.addFileEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.io.File)arguments[7],
				(java.lang.String)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return SyncDLObjectServiceUtil.addFolder(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return SyncDLObjectServiceUtil.cancelCheckOut(((Long)arguments[0]).longValue());
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return SyncDLObjectServiceUtil.checkInFileEntry(((Long)arguments[0]).longValue(),
				((Boolean)arguments[1]).booleanValue(),
				(java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return SyncDLObjectServiceUtil.checkOutFileEntry(((Long)arguments[0]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[1]);
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return SyncDLObjectServiceUtil.checkOutFileEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return SyncDLObjectServiceUtil.copyFileEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return SyncDLObjectServiceUtil.getAllFolderSyncDLObjects(((Long)arguments[0]).longValue());
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFileEntrySyncDLObject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFileEntrySyncDLObjects(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFolderSyncDLObject(((Long)arguments[0]).longValue());
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFolderSyncDLObject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return SyncDLObjectServiceUtil.getFolderSyncDLObjects(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return SyncDLObjectServiceUtil.getGroup(((Long)arguments[0]).longValue());
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return SyncDLObjectServiceUtil.getLatestModifiedTime();
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return SyncDLObjectServiceUtil.getSyncContext();
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return SyncDLObjectServiceUtil.getSyncDLObjectUpdate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return SyncDLObjectServiceUtil.getSyncDLObjectUpdate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			return SyncDLObjectServiceUtil.getSyncDLObjectUpdate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return SyncDLObjectServiceUtil.getUserSitesGroups();
		}

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return SyncDLObjectServiceUtil.moveFileEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return SyncDLObjectServiceUtil.moveFileEntryToTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return SyncDLObjectServiceUtil.moveFolder(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[2]);
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return SyncDLObjectServiceUtil.moveFolderToTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return SyncDLObjectServiceUtil.patchFileEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				(java.io.File)arguments[8], (java.lang.String)arguments[9],
				(com.liferay.portal.service.ServiceContext)arguments[10]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return SyncDLObjectServiceUtil.restoreFileEntryFromTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return SyncDLObjectServiceUtil.restoreFolderFromTrash(((Long)arguments[0]).longValue());
		}

		if (_methodName100.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes100, parameterTypes)) {
			return SyncDLObjectServiceUtil.updateFileEntries((java.io.File)arguments[0]);
		}

		if (_methodName101.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes101, parameterTypes)) {
			return SyncDLObjectServiceUtil.updateFileEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				(java.io.File)arguments[7], (java.lang.String)arguments[8],
				(com.liferay.portal.service.ServiceContext)arguments[9]);
		}

		if (_methodName102.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes102, parameterTypes)) {
			return SyncDLObjectServiceUtil.updateFolder(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
	private String _methodName97;
	private String[] _methodParameterTypes97;
	private String _methodName98;
	private String[] _methodParameterTypes98;
	private String _methodName99;
	private String[] _methodParameterTypes99;
	private String _methodName100;
	private String[] _methodParameterTypes100;
	private String _methodName101;
	private String[] _methodParameterTypes101;
	private String _methodName102;
	private String[] _methodParameterTypes102;
}