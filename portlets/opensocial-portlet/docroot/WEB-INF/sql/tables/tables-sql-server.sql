create table OpenSocial_Gadget (
	uuid_ nvarchar(75) null,
	gadgetId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	name nvarchar(75) null,
	url nvarchar(2000) null
);

create table OpenSocial_OAuthConsumer (
	oAuthConsumerId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	gadgetId bigint,
	serviceName nvarchar(75) null,
	consumerKey nvarchar(75) null,
	consumerSecret ntext null,
	keyType nvarchar(75) null,
	callbackUrl nvarchar(75) null,
	keyName nvarchar(75) null
);

create table OpenSocial_OAuthToken (
	oAuthTokenId bigint not null primary key,
	companyId bigint,
	createDate datetime null,
	modifiedDate datetime null,
	userId bigint,
	gadgetId bigint,
	moduleId bigint,
	serviceName nvarchar(75) null,
	tokenName nvarchar(75) null,
	accessToken nvarchar(75) null,
	tokenSecret nvarchar(75) null,
	sessionHandle nvarchar(75) null,
	tokenExpireMillis bigint
);
