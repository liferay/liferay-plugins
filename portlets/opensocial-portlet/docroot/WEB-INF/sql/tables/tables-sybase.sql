create table OpenSocial_Gadget (
	uuid_ varchar(75) null,
	gadgetId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	modifiedDate datetime null,
	name varchar(75) null,
	url varchar(1000) null
)
go

create table OpenSocial_OAuthConsumer (
	oAuthConsumerId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	modifiedDate datetime null,
	gadgetId decimal(20,0),
	serviceName varchar(75) null,
	consumerKey varchar(75) null,
	consumerSecret text null,
	keyType varchar(75) null,
	callbackUrl varchar(75) null,
	keyName varchar(75) null
)
go

create table OpenSocial_OAuthToken (
	oAuthTokenId decimal(20,0) not null primary key,
	companyId decimal(20,0),
	createDate datetime null,
	modifiedDate datetime null,
	userId decimal(20,0),
	gadgetId decimal(20,0),
	moduleId decimal(20,0),
	serviceName varchar(75) null,
	tokenName varchar(75) null,
	accessToken varchar(75) null,
	tokenSecret varchar(75) null,
	sessionHandle varchar(75) null,
	tokenExpireMillis decimal(20,0)
)
go
