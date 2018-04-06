create table WebEx_WebExAccount (
	uuid_ VARCHAR(75) null,
	webExAccountId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	webExSiteId LONG,
	login VARCHAR(300) null,
	password_ VARCHAR(75) null
);

create table WebEx_WebExSite (
	uuid_ VARCHAR(75) null,
	webExSiteId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	apiURL VARCHAR(300) null,
	login VARCHAR(300) null,
	password_ VARCHAR(75) null,
	partnerKey VARCHAR(300) null,
	siteKey LONG
);