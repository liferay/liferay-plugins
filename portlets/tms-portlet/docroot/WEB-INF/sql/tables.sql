
create table TMS_ProjectEntry (
	projectEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	finishDate DATE null
);

create table TMS_ProjectMilestone (
	projectMilestoneId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	projectEntryId LONG,
	dueDate DATE null,
	finishDate DATE null
);

create table TMS_TaskEntry (
	taskEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	projectEntryId LONG,
	projectMilestoneId LONG,
	title VARCHAR(75) null,
	description VARCHAR(75) null,
	assigneeUserId LONG,
	resolverUserId LONG,
	dueDate DATE null,
	finishDate DATE null,
	status INTEGER
);

create table TMS_TaskView (
	taskViewId LONG not null primary key,
	groupId LONG,
	companyId VARCHAR(75) null,
	userId VARCHAR(75) null,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	tags VARCHAR(75) null,
	notTags VARCHAR(75) null,
	assigneeUserId LONG,
	reporterUserId LONG,
	includeProjectTasks INTEGER,
	isPrivate BOOLEAN
);