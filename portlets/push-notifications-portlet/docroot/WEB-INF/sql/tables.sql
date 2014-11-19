create table PushNotificationsDevice (
	pushNotificationsDeviceId LONG not null primary key,
	userId LONG,
	createDate DATE null,
	platform VARCHAR(75) null,
	token STRING null
);

create table PushNotificationsEntry (
	pushNotificationsEntryId LONG not null primary key,
	userId LONG,
	createTime LONG,
	parentPushNotificationsEntryId LONG,
	childrenPushNotificationsEntriesCount INTEGER,
	payload STRING null,
	ratingsTotalScore LONG
);