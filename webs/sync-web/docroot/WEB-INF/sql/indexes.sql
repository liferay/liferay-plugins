create index IX_A9B43C55 on SyncDLFileVersionDiff (expirationDate);
create unique index IX_AC4C7667 on SyncDLFileVersionDiff (fileEntryId, sourceFileVersionId, targetFileVersionId);

create index IX_6E126C47 on SyncDLObject (companyId, modifiedTime, repositoryId, event);
create index IX_D38D70CA on SyncDLObject (companyId, modifiedTime, repositoryId, parentFolderId);
create index IX_3EA3BEBC on SyncDLObject (companyId, repositoryId, type_);
create index IX_5D8CBE8F on SyncDLObject (parentFolderId);
create unique index IX_E3F57BD6 on SyncDLObject (type_, typePK);
create index IX_28CD54BB on SyncDLObject (type_, version);
create index IX_1CCA3B5 on SyncDLObject (version, type_);