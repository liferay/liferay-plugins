create table MicroblogsEntry (
	microblogsEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	content STRING null,
	type_ INTEGER,
	receiverUserId LONG,
	receiverMicroblogsEntryId LONG,
	socialRelationType INTEGER
);

create table Microblogs_MicroblogsEntry (
	microblogsEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	content STRING null,
	type_ INTEGER,
	receiverUserId LONG,
	receiverEntryId LONG,
	socialRelationType INTEGER
);