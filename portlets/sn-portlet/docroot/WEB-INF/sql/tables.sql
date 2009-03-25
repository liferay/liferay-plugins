
create table WOL_MeetupsEntry (
	meetupsEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title VARCHAR(75) null,
	description STRING null,
	startDate DATE null,
	endDate DATE null,
	totalAttendees INTEGER,
	maxAttendees INTEGER,
	price DOUBLE,
	thumbnailId LONG
);

create table WOL_MeetupsRegistration (
	meetupsRegistrationId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	meetupsEntryId LONG,
	status INTEGER,
	comments STRING null
);

create table WOL_SVNRepository (
	svnRepositoryId LONG not null primary key,
	url VARCHAR(200) null,
	revisionNumber LONG
);

create table WOL_SVNRevision (
	svnRevisionId LONG not null primary key,
	svnUserId VARCHAR(75) null,
	createDate DATE null,
	svnRepositoryId LONG,
	revisionNumber LONG,
	comments STRING null
);

create table WOL_WallEntry (
	wallEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	comments STRING null
);