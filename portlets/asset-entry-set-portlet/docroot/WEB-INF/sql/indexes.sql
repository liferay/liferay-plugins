create index IX_7641117E on AssetEntrySet (createTime, parentAssetEntrySetId);
create index IX_26CA2F3B on AssetEntrySet (parentAssetEntrySetId);
create index IX_116E481 on AssetEntrySet (parentAssetEntrySetId, creatorClassNameId);
create index IX_D6AFB9DC on AssetEntrySet (parentAssetEntrySetId, creatorClassNameId, creatorClassPK);

create index IX_E40D43AE on AssetEntrySetLike (assetEntrySetId);