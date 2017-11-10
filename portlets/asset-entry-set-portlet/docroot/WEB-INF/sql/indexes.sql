create index IX_6ABC038D on AssetEntrySet (classNameId, classPK, title);
create index IX_85CDEA5A on AssetEntrySet (classNameId, classPK, type_);
create index IX_7641117E on AssetEntrySet (createTime, parentAssetEntrySetId);
create index IX_D6AFB9DC on AssetEntrySet (parentAssetEntrySetId, creatorClassNameId, creatorClassPK);

create index IX_E40D43AE on AssetEntrySetLike (assetEntrySetId);