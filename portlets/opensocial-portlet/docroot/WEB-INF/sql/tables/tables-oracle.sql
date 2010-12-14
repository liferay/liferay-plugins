create table OpenSocial_Gadget (
	uuid_ varchar2(300) null,
	gadgetId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	modifiedDate timestamp null,
	name varchar2(300) null,
	url varchar2(4000) null
);

create table OpenSocial_OAuthConsumer (
	oAuthConsumerId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	modifiedDate timestamp null,
	gadgetId number(30,0),
	serviceName varchar2(300) null,
	consumerKey varchar2(300) null,
	consumerSecret clob null,
	keyType varchar2(300) null,
	callbackUrl varchar2(300) null,
	keyName varchar2(300) null
);

create table OpenSocial_OAuthToken (
	oAuthTokenId number(30,0) not null primary key,
	companyId number(30,0),
	createDate timestamp null,
	modifiedDate timestamp null,
	userId number(30,0),
	gadgetId number(30,0),
	moduleId number(30,0),
	serviceName varchar2(300) null,
	tokenName varchar2(300) null,
	accessToken varchar2(300) null,
	tokenSecret varchar2(300) null,
	sessionHandle varchar2(300) null,
	tokenExpireMillis number(30,0)
);
