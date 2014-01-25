create index IX_A56E51DD on SN_MeetupsEntry (companyId);
create index IX_6EA9EEA5 on SN_MeetupsEntry (userId);

create index IX_BCEB16E2 on SN_MeetupsRegistration (meetupsEntryId, status);
create index IX_3CBE4C36 on SN_MeetupsRegistration (userId, meetupsEntryId);

create index IX_F2F6C19A on SN_WallEntry (groupId, userId);
create index IX_C46194C4 on SN_WallEntry (userId);