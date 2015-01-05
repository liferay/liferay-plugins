create index IX_8F03D381 on AssetSharingEntry (classNameId, classPK);
create index IX_24D9088D on AssetSharingEntry (classNameId, classPK, sharedToClassNameId);
create index IX_65AB8177 on AssetSharingEntry (classNameId, sharedToClassNameId, sharedToClassPK);
create index IX_FDBBD81 on AssetSharingEntry (sharedToClassNameId, sharedToClassPK);