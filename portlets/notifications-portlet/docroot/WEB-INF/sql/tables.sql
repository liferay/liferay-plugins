create table Notifications_UserNotificationEvent (
	notificationEventId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userNotificationEventId LONG,
	timestamp LONG,
	delivered BOOLEAN,
	actionRequired BOOLEAN,
	archived BOOLEAN
);