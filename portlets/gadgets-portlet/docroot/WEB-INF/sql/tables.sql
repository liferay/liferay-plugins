
create table Gadgets_GadgetsEntry (
	gadgetsEntryId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url VARCHAR(75) null,
	xml VARCHAR(75) null
);