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
	parentResourcePrimKey LONG,
	version INTEGER,
	title STRING null,
	content TEXT null,
	description STRING null,
	priority DOUBLE,
	sections STRING null,
	viewCount INTEGER,
	latest BOOLEAN,
	main BOOLEAN,
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
	helpful BOOLEAN
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
	content TEXT null
);