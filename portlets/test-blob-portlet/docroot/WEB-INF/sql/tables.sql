create table TestBlob_BlobEntry (
	uuid_ VARCHAR(75) null,
	testBlobEntryId LONG not null primary key,
	blobField BLOB
);