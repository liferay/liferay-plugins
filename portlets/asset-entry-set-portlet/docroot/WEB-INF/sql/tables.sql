create table AssetEntrySet (
	assetEntrySetId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createTime LONG,
	modifiedTime LONG,
	assetEntryId LONG,
	parentAssetEntrySetId LONG,
	classNameId LONG,
	classPK LONG,
	creatorClassNameId LONG,
	creatorClassPK LONG,
	creatorName VARCHAR(75) null,
	assetEntrySetLikesCount INTEGER,
	childAssetEntrySetsCount INTEGER,
	level INTEGER,
	payload STRING null,
	privateAssetEntrySet BOOLEAN,
	stickyTime LONG,
	title VARCHAR(255) null,
	type_ INTEGER,
	status INTEGER
);

create table AssetEntrySetLike (
	assetEntrySetId LONG not null,
	classNameId LONG not null,
	classPK LONG not null,
	primary key (assetEntrySetId, classNameId, classPK)
);