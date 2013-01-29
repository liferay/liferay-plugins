create table Akismet_AkismetData (
	akismetDataId LONG not null primary key,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	type_ VARCHAR(75) null,
	permalink STRING null,
	referrer STRING null,
	userAgent STRING null,
	userIP VARCHAR(75) null,
	userURL STRING null
);