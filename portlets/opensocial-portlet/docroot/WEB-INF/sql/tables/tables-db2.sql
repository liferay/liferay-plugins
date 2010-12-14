create table OpenSocial_Gadget (
	uuid_ varchar(75),
	gadgetId bigint not null primary key,
	companyId bigint,
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	url varchar(500)
);

create table OpenSocial_OAuthConsumer (
	oAuthConsumerId bigint not null primary key,
	companyId bigint,
	createDate timestamp,
	modifiedDate timestamp,
	gadgetId bigint,
	serviceName varchar(75),
	consumerKey varchar(75),
	consumerSecret clob,
	keyType varchar(75),
	callbackUrl varchar(75),
	keyName varchar(75)
);

create table OpenSocial_OAuthToken (
	oAuthTokenId bigint not null primary key,
	companyId bigint,
	createDate timestamp,
	modifiedDate timestamp,
	userId bigint,
	gadgetId bigint,
	moduleId bigint,
	serviceName varchar(75),
	tokenName varchar(75),
	accessToken varchar(75),
	tokenSecret varchar(75),
	sessionHandle varchar(75),
	tokenExpireMillis bigint
);
