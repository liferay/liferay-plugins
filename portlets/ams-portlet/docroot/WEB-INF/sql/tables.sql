create table AMS_AMSAsset (
	amsAssetId LONG not null primary key,
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

create table AMS_AMSCheckout (
	amsCheckoutId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	amsAssetId LONG,
	checkOutDate DATE null,
	expectedCheckInDate DATE null,
	actualCheckInDate DATE null
);

create table AMS_AMSDefinition (
	assetDefinitionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	amsTypeId LONG,
	manufacturer VARCHAR(75) null,
	model VARCHAR(75) null,
	orderDate DATE null,
	quantity INTEGER,
	price DOUBLE
);

create table AMS_AMSType (
	amsTypeId LONG not null primary key,
	groupId LONG,
	name VARCHAR(75) null
);