create index IX_61F3E3E4 on Ntfctns_UserNotificationEvent (userId, actionRequired);
create index IX_911E393A on Ntfctns_UserNotificationEvent (userId, actionRequired, archived);
create index IX_5EC15E51 on Ntfctns_UserNotificationEvent (userId, archived);
create index IX_20DFA99 on Ntfctns_UserNotificationEvent (userId, delivered);
create index IX_609938C2 on Ntfctns_UserNotificationEvent (userId, delivered, actionRequired);
create unique index IX_D448A02E on Ntfctns_UserNotificationEvent (userNotificationEventId);