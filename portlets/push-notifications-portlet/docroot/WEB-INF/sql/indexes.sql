create index IX_7758D8B1 on PushNotificationsDevice (platform);
create unique index IX_2F3EDC9F on PushNotificationsDevice (token);
create index IX_2FBF066B on PushNotificationsDevice (userId, platform);

create index IX_91964A84 on PushNotificationsEntry (createTime, parentPushNotificationsEntryId);
create index IX_15A45767 on PushNotificationsEntry (parentPushNotificationsEntryId);