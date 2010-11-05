create table OpenSocial_Gadget (
	uuid_ VARCHAR(75) null,
	gadgetId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url STRING null
);