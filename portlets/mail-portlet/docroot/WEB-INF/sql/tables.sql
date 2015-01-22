create table Mail_Account (
	accountId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	address VARCHAR(75) null,
	personalName VARCHAR(75) null,
	protocol VARCHAR(75) null,
	incomingHostName VARCHAR(75) null,
	incomingPort INTEGER,
	incomingSecure BOOLEAN,
	outgoingHostName VARCHAR(75) null,
	outgoingPort INTEGER,
	outgoingSecure BOOLEAN,
	login VARCHAR(75) null,
	password_ VARCHAR(75) null,
	savePassword BOOLEAN,
	signature VARCHAR(75) null,
	useSignature BOOLEAN,
	folderPrefix VARCHAR(75) null,
	inboxFolderId LONG,
	draftFolderId LONG,
	sentFolderId LONG,
	trashFolderId LONG,
	defaultSender BOOLEAN
);

create table Mail_Attachment (
	attachmentId LONG not null primary key,
	companyId LONG,
	userId LONG,
	accountId LONG,
	folderId LONG,
	messageId LONG,
	contentPath VARCHAR(75) null,
	fileName VARCHAR(75) null,
	size_ LONG
);

create table Mail_Folder (
	folderId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountId LONG,
	fullName VARCHAR(75) null,
	displayName VARCHAR(75) null,
	remoteMessageCount INTEGER
);

create table Mail_Message (
	messageId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	accountId LONG,
	folderId LONG,
	sender STRING null,
	to_ TEXT null,
	cc TEXT null,
	bcc TEXT null,
	sentDate DATE null,
	subject STRING null,
	preview VARCHAR(75) null,
	body TEXT null,
	flags VARCHAR(75) null,
	size_ LONG,
	remoteMessageId LONG,
	contentType VARCHAR(75) null
);