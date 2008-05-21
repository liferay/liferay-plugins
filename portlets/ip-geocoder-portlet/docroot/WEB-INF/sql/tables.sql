
create table IPGeocoder_IPInfo (
	ipInfoId LONG not null primary key,
	createDate DATE null,
	ipAddress VARCHAR(75) null,
	latitude DOUBLE,
	longitude DOUBLE,
	city VARCHAR(75) null,
	country VARCHAR(75) null
);