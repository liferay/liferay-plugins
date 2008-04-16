
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
	comments TEXT null
);