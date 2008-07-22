
create table KB_KBArticle (
	uuid_ VARCHAR(75) null,
	articleId LONG not null primary key,
	groupId LONG,
	resourcePrimKey LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	modifiedDate DATE null,
	title VARCHAR(100) null,
	version DOUBLE,
	content TEXT null,
	description STRING null,
	head BOOLEAN,
	parentTitle VARCHAR(75) null
);

create table KB_KBArticleResource (
	resourcePrimKey LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	title VARCHAR(75) null
);