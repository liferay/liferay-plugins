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
	payload STRING null,
	childAssetEntrySetsCount INTEGER,
	ratingsStatsTotalScore INTEGER,
	privateAssetEntrySet BOOLEAN
);