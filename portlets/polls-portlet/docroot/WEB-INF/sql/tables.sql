create table Polls_PollsChoice (
	uuid_ VARCHAR(75) null,
	pollsChoiceId LONG not null primary key,
	pollsQuestionId LONG,
	name VARCHAR(75) null,
	description STRING null
);

create table Polls_PollsQuestion (
	uuid_ VARCHAR(75) null,
	pollsQuestionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	description STRING null,
	expirationDate DATE null,
	lastVoteDate DATE null
);

create table Polls_PollsVote (
	pollsVoteId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	pollsQuestionId LONG,
	pollsChoiceId LONG,
	voteDate DATE null
);