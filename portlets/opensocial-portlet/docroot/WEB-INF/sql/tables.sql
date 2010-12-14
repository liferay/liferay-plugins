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
	consumerSecret TEXT null,
	keyType VARCHAR(75) null,
	callbackUrl VARCHAR(75) null,
	keyName VARCHAR(75) null
);

create table OpenSocial_OAuthToken (
	oauthTokenId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	userId LONG,
	gadgetId LONG,
	moduleId LONG,
	serviceName VARCHAR(75) null,
	tokenName VARCHAR(75) null,
	accessToken VARCHAR(75) null,
	tokenSecret VARCHAR(75) null,
	sessionHandle VARCHAR(75) null,
	tokenExpireMillis LONG
);