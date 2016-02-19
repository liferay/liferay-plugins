create index IX_A9B43C55 on SyncDLFileVersionDiff (expirationDate);
create unique index IX_AC4C7667 on SyncDLFileVersionDiff (fileEntryId, sourceFileVersionId, targetFileVersionId);

create index IX_8D4FDC9F on SyncDLObject (modifiedTime, repositoryId, event[$COLUMN_LENGTH:75$]);
create index IX_A3ACE372 on SyncDLObject (modifiedTime, repositoryId, parentFolderId);
create index IX_38C38A09 on SyncDLObject (repositoryId, event[$COLUMN_LENGTH:75$]);
create index IX_3BE7BB8D on SyncDLObject (repositoryId, parentFolderId, type_[$COLUMN_LENGTH:75$]);
create index IX_57F62914 on SyncDLObject (repositoryId, type_[$COLUMN_LENGTH:75$]);
create index IX_EE41CBEB on SyncDLObject (treePath[$COLUMN_LENGTH:4000$], event[$COLUMN_LENGTH:75$]);
create unique index IX_E3F57BD6 on SyncDLObject (type_[$COLUMN_LENGTH:75$], typePK);
create index IX_28CD54BB on SyncDLObject (type_[$COLUMN_LENGTH:75$], version[$COLUMN_LENGTH:75$]);
create index IX_1CCA3B5 on SyncDLObject (version[$COLUMN_LENGTH:75$], type_[$COLUMN_LENGTH:75$]);

create index IX_176DF87B on SyncDevice (companyId, userName[$COLUMN_LENGTH:75$]);
create index IX_AE38DEAB on SyncDevice (uuid_[$COLUMN_LENGTH:75$], companyId);