create table BBBMeeting (
	bbbMeetingId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bbbServerId LONG,
	name VARCHAR(75) null,
	description STRING null,
	attendeePassword VARCHAR(75) null,
	moderatorPassword VARCHAR(75) null,
	status INTEGER
);

create table BBBParticipant (
	bbbParticipantId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	bbbMeetingId LONG,
	name VARCHAR(75) null,
	emailAddress VARCHAR(75) null,
	type_ INTEGER,
	status INTEGER
);

create table BBBServer (
	bbbServerId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url STRING null,
	secret VARCHAR(75) null,
	active_ BOOLEAN
);