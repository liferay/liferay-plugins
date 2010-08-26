create table PM_UserThread (
	userThreadId LONG not null primary key,
	userId LONG,
	mbThreadId LONG,
	topMBMessageId LONG,
	read_ BOOLEAN,
	deleted BOOLEAN
);