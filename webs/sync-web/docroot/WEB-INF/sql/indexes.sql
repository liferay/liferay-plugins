create index IX_A9B43C55 on SyncDLFileVersionDiff (expirationDate);
create unique index IX_AC4C7667 on SyncDLFileVersionDiff (fileEntryId, sourceFileVersionId, targetFileVersionId);

create index IX_7F996123 on SyncDLObject (companyId, modifiedTime, repositoryId);
create unique index IX_69ADEDD1 on SyncDLObject (typePK);