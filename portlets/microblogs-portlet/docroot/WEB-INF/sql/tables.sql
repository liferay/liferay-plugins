create table MicroblogsEntry (
	microblogsEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	creatorClassNameId LONG,
	creatorClassPK LONG,
	content STRING null,
	type_ INTEGER,
	receiverUserId LONG,
	receiverMicroblogsEntryId LONG,
	socialRelationType INTEGER
);