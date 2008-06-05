
create table Chat_Entry (
	entryId LONG not null primary key,
	createDate LONG,
	fromUserId LONG,
	toUserId LONG,
	content STRING null
);