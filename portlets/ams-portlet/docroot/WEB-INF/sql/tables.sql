
create table AMS_AssetCheckout (
	assetCheckoutId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	assetEntryId LONG,
	checkOutDate DATE null,
	expectedCheckInDate DATE null,
	actualCheckInDate DATE null
);

create table AMS_AssetDefinition (
	assetDefinitionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	assetTypeId LONG,
	manufacturer VARCHAR(75) null,
	model VARCHAR(75) null,
	orderDate DATE null,
	quantity INTEGER,
	price DOUBLE
);

create table AMS_AssetEntry (
	assetEntryId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	assetDefinitionId LONG,
	serialNumber VARCHAR(75) null,
	inactiveDate DATE null,
	active_ BOOLEAN
);

create table AMS_AssetType (
	assetTypeId LONG not null primary key,
	groupId LONG,
	name VARCHAR(75) null
);