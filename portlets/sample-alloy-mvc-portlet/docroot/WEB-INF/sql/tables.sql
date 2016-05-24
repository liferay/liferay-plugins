create table SAM_SAMTodoItem (
	samTodoItemId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	samTodoListId LONG,
	description VARCHAR(75) null,
	priority INTEGER,
	status INTEGER
);

create table SAM_SAMTodoList (
	samTodoListId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null
);