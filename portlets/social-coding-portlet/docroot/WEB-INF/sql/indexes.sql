create index IX_D0E748D3 on SC_SVNRepository (url[$COLUMN_LENGTH:200$]);

create index IX_82D9A903 on SC_SVNRevision (svnRepositoryId);
create index IX_95A48FBC on SC_SVNRevision (svnUserId[$COLUMN_LENGTH:75$], svnRepositoryId);