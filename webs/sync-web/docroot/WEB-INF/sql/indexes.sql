create index IX_A9B43C55 on SyncDLFileVersionDiff (expirationDate);
create unique index IX_CA006E0C on SyncDLFileVersionDiff (fileEntryId, sourceFileVersionId, destinationFileVersionId);

create index IX_7F996123 on SyncDLObject (companyId, modifiedTime, repositoryId);
create unique index IX_69ADEDD1 on SyncDLObject (typePK);