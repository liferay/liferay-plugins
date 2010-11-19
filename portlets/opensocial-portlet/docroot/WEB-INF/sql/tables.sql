create table OpenSocial_Gadget (
	uuid_ VARCHAR(75) null,
	gadgetId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url STRING null
);

create table OpenSocial_OAuthConsumer (
	oauthConsumerId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	gadgetId LONG,
	serviceName VARCHAR(75) null,
	consumerKey VARCHAR(75) null,
	consumerSecret VARCHAR(75) null,
	keyType VARCHAR(75) null
);