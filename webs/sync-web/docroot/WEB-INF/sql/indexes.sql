create index IX_A9B43C55 on SyncDLFileVersionDiff (expirationDate);
create index IX_F832A75D on SyncDLFileVersionDiff (fileEntryId);
create unique index IX_AC4C7667 on SyncDLFileVersionDiff (fileEntryId, sourceFileVersionId, targetFileVersionId);

create index IX_980323CB on SyncDLObject (modifiedTime, repositoryId);
create index IX_8D4FDC9F on SyncDLObject (modifiedTime, repositoryId, event);
create index IX_A3ACE372 on SyncDLObject (modifiedTime, repositoryId, parentFolderId);
create index IX_F174AD48 on SyncDLObject (repositoryId, parentFolderId);
create index IX_3BE7BB8D on SyncDLObject (repositoryId, parentFolderId, type_);
create index IX_57F62914 on SyncDLObject (repositoryId, type_);
create unique index IX_E3F57BD6 on SyncDLObject (type_, typePK);
create index IX_28CD54BB on SyncDLObject (type_, version);
create index IX_1CCA3B5 on SyncDLObject (version, type_);