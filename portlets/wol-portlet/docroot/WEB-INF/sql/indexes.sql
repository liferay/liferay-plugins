create index IX_58DD7FBD on WOL_MeetupEntry (companyId);

create index IX_239BF70F on WOL_MeetupRegistration (meetupEntryId);

create index IX_75C012F on WOL_SVNRepository (url);

create index IX_1AF89E5F on WOL_SVNRevision (svnRepositoryId);
create index IX_8645F460 on WOL_SVNRevision (svnUserId);
create index IX_25E1E8E0 on WOL_SVNRevision (svnUserId, svnRepositoryId);

create index IX_CB1E7CE7 on WOL_WallEntry (groupId);
create index IX_E65FFE21 on WOL_WallEntry (groupId, userId);
create index IX_C7F3D45D on WOL_WallEntry (userId);