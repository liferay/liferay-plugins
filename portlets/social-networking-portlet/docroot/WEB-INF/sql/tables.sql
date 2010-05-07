create table SN_MeetupsEntry (
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

create table SN_MeetupsRegistration (
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

create table SN_WallEntry (
	wallEntryId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	comments STRING null
);