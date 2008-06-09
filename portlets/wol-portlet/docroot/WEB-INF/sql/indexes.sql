create index IX_79AEBAB6 on WOL_MeetupsEntry (companyId);

create index IX_83877F55 on WOL_MeetupsRegistration (meetupsEntryId);
create index IX_C28F993B on WOL_MeetupsRegistration (meetupsEntryId, status);
create index IX_4262CE8F on WOL_MeetupsRegistration (userId, meetupsEntryId);

create index IX_75C012F on WOL_SVNRepository (url);

create index IX_1AF89E5F on WOL_SVNRevision (svnRepositoryId);
create index IX_8645F460 on WOL_SVNRevision (svnUserId);
create index IX_25E1E8E0 on WOL_SVNRevision (svnUserId, svnRepositoryId);

create index IX_CB1E7CE7 on WOL_WallEntry (groupId);
create index IX_E65FFE21 on WOL_WallEntry (groupId, userId);
create index IX_C7F3D45D on WOL_WallEntry (userId);