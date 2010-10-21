create table Twitter_Feed (
	feedId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	twitterUserId LONG,
	twitterScreenName VARCHAR(75) null,
	lastStatusId LONG
);