create table KBArticle (
	uuid_ VARCHAR(75) null,
	kbArticleId LONG not null primary key,
	resourcePrimKey LONG,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	rootResourcePrimKey LONG,
	parentResourceClassNameId LONG,
	parentResourcePrimKey LONG,
	kbFolderId LONG,
	version INTEGER,
	title STRING null,
	urlTitle VARCHAR(75) null,
	content TEXT null,
	description STRING null,
	priority DOUBLE,
	sections STRING null,
	viewCount INTEGER,
	latest BOOLEAN,
	main BOOLEAN,
	sourceURL STRING null,
	lastPublishDate DATE null,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null
);

create table KBComment (
	uuid_ VARCHAR(75) null,
	kbCommentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	content STRING null,
	userRating INTEGER,
	lastPublishDate DATE null,
	status INTEGER
);

create table KBFolder (
	uuid_ VARCHAR(75) null,
	kbFolderId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	parentKBFolderId LONG,
	name VARCHAR(75) null,
	urlTitle VARCHAR(75) null,
	description STRING null,
	lastPublishDate DATE null
);

create table KBTemplate (
	uuid_ VARCHAR(75) null,
	kbTemplateId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	title STRING null,
	content TEXT null,
	lastPublishDate DATE null
);