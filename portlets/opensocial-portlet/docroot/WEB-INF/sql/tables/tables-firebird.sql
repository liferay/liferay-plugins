create table OpenSocial_Gadget (
	uuid_ varchar(75),
	gadgetId int64 not null primary key,
	companyId int64,
	createDate timestamp,
	modifiedDate timestamp,
	name varchar(75),
	url varchar(4000)
);

create table OpenSocial_OAuthConsumer (
	oAuthConsumerId int64 not null primary key,
	companyId int64,
	createDate timestamp,
	modifiedDate timestamp,
	gadgetId int64,
	serviceName varchar(75),
	consumerKey varchar(75),
	consumerSecret blob,
	keyType varchar(75),
	callbackUrl varchar(75),
	keyName varchar(75)
);

create table OpenSocial_OAuthToken (
	oAuthTokenId int64 not null primary key,
	companyId int64,
	createDate timestamp,
	modifiedDate timestamp,
	userId int64,
	gadgetId int64,
	moduleId int64,
	serviceName varchar(75),
	tokenName varchar(75),
	accessToken varchar(75),
	tokenSecret varchar(75),
	sessionHandle varchar(75),
	tokenExpireMillis int64
);
