create index IX_D0E748D3 on SC_SVNRepository (url);

create index IX_82D9A903 on SC_SVNRevision (svnRepositoryId);
create index IX_95A48FBC on SC_SVNRevision (svnUserId, svnRepositoryId);