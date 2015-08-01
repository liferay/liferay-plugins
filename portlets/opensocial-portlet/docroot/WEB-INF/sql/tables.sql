create table OpenSocial_Gadget (
	uuid_ VARCHAR(75) null,
	gadgetId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url STRING null,
	portletCategoryNames STRING null,
	lastPublishDate DATE null
);

create table OpenSocial_OAuthConsumer (
	oAuthConsumerId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	gadgetKey VARCHAR(75) null,
	serviceName VARCHAR(75) null,
	consumerKey VARCHAR(75) null,
	consumerSecret TEXT null,
	keyType VARCHAR(75) null
);

create table OpenSocial_OAuthToken (
	oAuthTokenId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	gadgetKey VARCHAR(75) null,
	serviceName VARCHAR(75) null,
	moduleId LONG,
	accessToken VARCHAR(75) null,
	tokenName VARCHAR(75) null,
	tokenSecret VARCHAR(75) null,
	sessionHandle VARCHAR(75) null,
	expiration LONG
);