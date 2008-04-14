
create table WOL_SVNRepository (
	svnRepositoryId LONG not null primary key,
	url VARCHAR(200) null,
	revisionNumber LONG
);

create table WOL_SVNRevision (
	svnRevisionId LONG not null primary key,
	svnRepositoryId LONG,
	revisionNumber LONG,
	date_ DATE null,
	author VARCHAR(75) null,
	comments TEXT null
);