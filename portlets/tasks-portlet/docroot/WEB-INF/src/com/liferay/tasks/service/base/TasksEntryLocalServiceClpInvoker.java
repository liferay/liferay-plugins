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

package com.liferay.tasks.service.base;

import com.liferay.tasks.service.TasksEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Ryan Park
 * @generated
 */
public class TasksEntryLocalServiceClpInvoker {
	public TasksEntryLocalServiceClpInvoker() {
		_methodName0 = "addTasksEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.tasks.model.TasksEntry"
			};

		_methodName1 = "createTasksEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTasksEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTasksEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.tasks.model.TasksEntry"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchTasksEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getTasksEntry";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getPersistedModel";

		_methodParameterTypes12 = new String[] { "java.io.Serializable" };

		_methodName13 = "getTasksEntries";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getTasksEntriesCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateTasksEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.tasks.model.TasksEntry"
			};

		_methodName42 = "getBeanIdentifier";

		_methodParameterTypes42 = new String[] {  };

		_methodName43 = "setBeanIdentifier";

		_methodParameterTypes43 = new String[] { "java.lang.String" };

		_methodName48 = "addTasksEntry";

		_methodParameterTypes48 = new String[] {
				"long", "java.lang.String", "int", "long", "int", "int", "int",
				"int", "int", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName49 = "deleteTasksEntry";

		_methodParameterTypes49 = new String[] { "long" };

		_methodName50 = "deleteTasksEntry";

		_methodParameterTypes50 = new String[] {
				"com.liferay.tasks.model.TasksEntry"
			};

		_methodName51 = "getAssigneeTasksEntries";

		_methodParameterTypes51 = new String[] { "long", "int", "int" };

		_methodName52 = "getAssigneeTasksEntriesCount";

		_methodParameterTypes52 = new String[] { "long" };

		_methodName53 = "getGroupAssigneeTasksEntries";

		_methodParameterTypes53 = new String[] { "long", "long", "int", "int" };

		_methodName54 = "getGroupAssigneeTasksEntriesCount";

		_methodParameterTypes54 = new String[] { "long", "long" };

		_methodName55 = "getGroupResolverTasksEntries";

		_methodParameterTypes55 = new String[] { "long", "long", "int", "int" };

		_methodName56 = "getGroupResolverTasksEntriesCount";

		_methodParameterTypes56 = new String[] { "long", "long" };

		_methodName57 = "getGroupUserTasksEntries";

		_methodParameterTypes57 = new String[] { "long", "long", "int", "int" };

		_methodName58 = "getGroupUserTasksEntriesCount";

		_methodParameterTypes58 = new String[] { "long", "long" };

		_methodName59 = "getResolverTasksEntries";

		_methodParameterTypes59 = new String[] { "long", "int", "int" };

		_methodName60 = "getResolverTasksEntriesCount";

		_methodParameterTypes60 = new String[] { "long" };

		_methodName61 = "getTasksEntries";

		_methodParameterTypes61 = new String[] { "long", "int", "int" };

		_methodName62 = "getTasksEntries";

		_methodParameterTypes62 = new String[] {
				"long", "int", "long", "long", "int", "long[][]", "long[][]",
				"int", "int"
			};

		_methodName63 = "getTasksEntriesCount";

		_methodParameterTypes63 = new String[] { "long" };

		_methodName64 = "getTasksEntriesCount";

		_methodParameterTypes64 = new String[] {
				"long", "int", "long", "long", "int", "long[][]", "long[][]"
			};

		_methodName65 = "getTasksEntry";

		_methodParameterTypes65 = new String[] { "long" };

		_methodName66 = "getUserTasksEntries";

		_methodParameterTypes66 = new String[] { "long", "int", "int" };

		_methodName67 = "getUserTasksEntriesCount";

		_methodParameterTypes67 = new String[] { "long" };

		_methodName68 = "updateAsset";

		_methodParameterTypes68 = new String[] {
				"long", "com.liferay.tasks.model.TasksEntry", "long[][]",
				"java.lang.String[][]"
			};

		_methodName69 = "updateTasksEntry";

		_methodParameterTypes69 = new String[] {
				"long", "java.lang.String", "int", "long", "long", "int", "int",
				"int", "int", "int", "boolean", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName70 = "updateTasksEntryStatus";

		_methodParameterTypes70 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TasksEntryLocalServiceUtil.addTasksEntry((com.liferay.tasks.model.TasksEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TasksEntryLocalServiceUtil.createTasksEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TasksEntryLocalServiceUtil.deleteTasksEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TasksEntryLocalServiceUtil.deleteTasksEntry((com.liferay.tasks.model.TasksEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TasksEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TasksEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TasksEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TasksEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TasksEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TasksEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TasksEntryLocalServiceUtil.fetchTasksEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getTasksEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getTasksEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getTasksEntriesCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TasksEntryLocalServiceUtil.updateTasksEntry((com.liferay.tasks.model.TasksEntry)arguments[0]);
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			TasksEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return TasksEntryLocalServiceUtil.addTasksEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Boolean)arguments[9]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[10]);
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return TasksEntryLocalServiceUtil.deleteTasksEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return TasksEntryLocalServiceUtil.deleteTasksEntry((com.liferay.tasks.model.TasksEntry)arguments[0]);
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getAssigneeTasksEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getAssigneeTasksEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getGroupAssigneeTasksEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName54.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes54, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getGroupAssigneeTasksEntriesCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getGroupResolverTasksEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getGroupResolverTasksEntriesCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getGroupUserTasksEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getGroupUserTasksEntriesCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getResolverTasksEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName60.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes60, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getResolverTasksEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getTasksEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getTasksEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Integer)arguments[4]).intValue(), (long[])arguments[5],
				(long[])arguments[6], ((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue());
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getTasksEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getTasksEntriesCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Integer)arguments[4]).intValue(), (long[])arguments[5],
				(long[])arguments[6]);
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getTasksEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getUserTasksEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return TasksEntryLocalServiceUtil.getUserTasksEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			TasksEntryLocalServiceUtil.updateAsset(((Long)arguments[0]).longValue(),
				(com.liferay.tasks.model.TasksEntry)arguments[1],
				(long[])arguments[2], (java.lang.String[])arguments[3]);

			return null;
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return TasksEntryLocalServiceUtil.updateTasksEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Boolean)arguments[10]).booleanValue(),
				((Integer)arguments[11]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[12]);
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return TasksEntryLocalServiceUtil.updateTasksEntryStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
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
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
}