create table AssetEntrySet (
	assetEntrySetId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createTime LONG,
	modifiedTime LONG,
	assetEntryId VARCHAR(75) null,
	parentAssetEntrySetId LONG,
	creatorClassNameId LONG,
	creatorClassPK LONG,
	content VARCHAR(75) null,
	data_ VARCHAR(75) null
);