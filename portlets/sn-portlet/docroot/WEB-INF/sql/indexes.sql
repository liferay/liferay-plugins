create index IX_E9B155DD on sn_MeetupsEntry (companyId);

create index IX_AB6F97FC on sn_MeetupsRegistration (meetupsEntryId);
create index IX_A5841AE2 on sn_MeetupsRegistration (meetupsEntryId, status);
create index IX_25575036 on sn_MeetupsRegistration (userId, meetupsEntryId);

create index IX_73534560 on sn_WallEntry (groupId);
create index IX_A5A53D9A on sn_WallEntry (groupId, userId);
create index IX_283798C4 on sn_WallEntry (userId);