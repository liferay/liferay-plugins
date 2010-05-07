create table SC_SVNRepository (
	svnRepositoryId LONG not null primary key,
	url VARCHAR(200) null,
	revisionNumber LONG
);

create table SC_SVNRevision (
	svnRevisionId LONG not null primary key,
	svnUserId VARCHAR(75) null,
	createDate DATE null,
	svnRepositoryId LONG,
	revisionNumber LONG,
	comments STRING null
);