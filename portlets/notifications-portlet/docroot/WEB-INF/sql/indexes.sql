create index IX_93C52776 on Notifications_UserNotificationEvent (userId, actionRequired);
create index IX_36E5AE4C on Notifications_UserNotificationEvent (userId, actionRequired, archived);
create index IX_73C065F0 on Notifications_UserNotificationEvent (userId, delivered, actionRequired);
create unique index IX_DC9FCEDC on Notifications_UserNotificationEvent (userNotificationEventId);