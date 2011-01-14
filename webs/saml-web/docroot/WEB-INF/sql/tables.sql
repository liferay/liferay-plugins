create table SAML_SPSession (
	spSessionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	ssoSessionId LONG,
	issuer VARCHAR(75) null
);

create table SAML_SSOSession (
	ssoSessionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	lastActiveDate DATE null,
	key_ VARCHAR(75) null
);