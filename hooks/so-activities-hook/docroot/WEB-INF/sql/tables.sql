create table SO_SocialActivity (
	activityId LONG not null primary key,
	activitySetId LONG
);

create table SocialActivitySet (
	activitySetId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	createDate LONG,
	modifiedDate LONG,
	classNameId LONG,
	classPK LONG,
	type_ INTEGER,
	activityCount INTEGER
);