create index IX_A9B43C55 on SyncDLFileVersionDiff (expirationDate);
create unique index IX_AC4C7667 on SyncDLFileVersionDiff (fileEntryId, sourceFileVersionId, targetFileVersionId);

create index IX_8D4FDC9F on SyncDLObject (modifiedTime, repositoryId, event);
create index IX_38C38A09 on SyncDLObject (repositoryId, event);
create index IX_3BE7BB8D on SyncDLObject (repositoryId, parentFolderId, type_);
create index IX_57F62914 on SyncDLObject (repositoryId, type_);
create unique index IX_E3F57BD6 on SyncDLObject (type_, typePK);
create index IX_1CCA3B5 on SyncDLObject (version, type_);

create index IX_176DF87B on SyncDevice (companyId, userName);
create index IX_A18EDDB1 on SyncDevice (userId);
create index IX_AE38DEAB on SyncDevice (uuid_, companyId);