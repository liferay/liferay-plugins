create table Akismet_AkismetData (
	akismetDataId LONG not null primary key,
	modifiedDate DATE null,
	classNameId LONG,
	classPK LONG,
	type_ VARCHAR(75) null,
	permalink VARCHAR(75) null,
	referrer VARCHAR(75) null,
	userAgent VARCHAR(75) null,
	userIP VARCHAR(75) null,
	userURL VARCHAR(75) null
);