
create table KB_KBArticle (
	uuid_ VARCHAR(75) null,
	articleId LONG not null primary key,
	groupId LONG,
	resourcePrimKey LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	modifiedDate DATE null,
	title VARCHAR(100) null,
	version DOUBLE,
	minorEdit BOOLEAN,
	content TEXT null,
	description STRING null,
	head BOOLEAN,
	template BOOLEAN,
	draft BOOLEAN,
	parentResourcePrimKey LONG
);

create table KB_KBArticleResource (
	resourcePrimKey LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	title VARCHAR(75) null
);

create table KB_KBFeedbackEntry (
	feedbackEntryId LONG not null primary key,
	articleResourcePrimKey LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	comments STRING null,
	score INTEGER,
	vote INTEGER
);

create table KB_KBFeedbackStats (
	feedbackStatsId LONG not null primary key,
	articleResourcePrimKey LONG,
	averageScore DOUBLE,
	totalScoreEntries INTEGER,
	totalVotes INTEGER,
	yesVotes INTEGER
);