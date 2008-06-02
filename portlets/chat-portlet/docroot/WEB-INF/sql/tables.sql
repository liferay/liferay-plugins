
create table Chat_Entry (
	entryId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	content VARCHAR(75) null,
	receiverUserId LONG
);