create table AssetEntrySet (
	assetEntrySetId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createTime LONG,
	modifiedTime LONG,
	assetEntryId LONG,
	parentAssetEntrySetId LONG,
	creatorClassNameId LONG,
	creatorClassPK LONG,
	creatorName VARCHAR(75) null,
	payload STRING null,
	childAssetEntrySetsCount INTEGER,
	assetEntrySetLikesCount INTEGER,
	privateAssetEntrySet BOOLEAN
);

create table AssetEntrySetLike (
	assetEntrySetId LONG not null,
	classNameId LONG not null,
	classPK LONG not null,
	primary key (assetEntrySetId, classNameId, classPK)
);