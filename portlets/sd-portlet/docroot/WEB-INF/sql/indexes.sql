create index IX_6E577F72 on sd_SVNRepository (url);

create index IX_CC0964E2 on sd_SVNRevision (svnRepositoryId);
create index IX_EB029923 on sd_SVNRevision (svnUserId);
create index IX_80A1027D on sd_SVNRevision (svnUserId, svnRepositoryId);