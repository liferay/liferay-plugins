create table TMS_TasksEntry (
	tasksEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	priority INTEGER,
	assigneeUserId LONG,
	resolverUserId LONG,
	dueDate DATE null,
	finishDate DATE null,
	status INTEGER
);