create table SAMVC_Asset (
	assetId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	definitionId LONG,
	serialNumber VARCHAR(75) null,
	inactiveDate DATE null,
	active_ BOOLEAN
);

create table SAMVC_Checkout (
	checkoutId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	assetId LONG,
	checkOutDate DATE null,
	expectedCheckInDate DATE null,
	actualCheckInDate DATE null
);

create table SAMVC_Definition (
	definitionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	typeId LONG,
	manufacturer VARCHAR(75) null,
	model VARCHAR(75) null,
	orderDate DATE null,
	quantity INTEGER,
	price DOUBLE
);

create table SAMVC_Type (
	typeId LONG not null primary key,
	groupId LONG,
	name VARCHAR(75) null
);