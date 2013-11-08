create table TB_BlobEntry (
	uuid_ VARCHAR(75) null,
	blobEntryId LONG not null primary key,
	blobField BLOB
);