create table PM_UserThread (
	userThreadId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	mbThreadId LONG,
	topMBMessageId LONG,
	read_ BOOLEAN,
	deleted BOOLEAN
);