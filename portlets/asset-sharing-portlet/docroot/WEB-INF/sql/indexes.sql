create index IX_B3378B40 on AssetSharing_AssetSharingEntry (classNameId, classPK, sharedToClassNameId);
create index IX_2241A12A on AssetSharing_AssetSharingEntry (classNameId, sharedToClassNameId, sharedToClassPK);
create index IX_ED6D16EE on AssetSharing_AssetSharingEntry (sharedToClassNameId, sharedToClassPK);