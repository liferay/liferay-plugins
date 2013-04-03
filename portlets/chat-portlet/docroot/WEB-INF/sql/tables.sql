create table Chat_Entry (
	entryId LONG not null primary key,
	createDate LONG,
	fromUserId LONG,
	toUserId LONG,
	content VARCHAR(1000) null,
	flag INTEGER
);

create table Chat_Status (
	statusId LONG not null primary key,
	userId LONG,
	modifiedDate LONG,
	online_ BOOLEAN,
	awake BOOLEAN,
	activePanelIds STRING null,
	message STRING null,
	playSound BOOLEAN
);