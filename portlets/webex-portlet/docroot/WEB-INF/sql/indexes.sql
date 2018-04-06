create index IX_FE09B2F3 on WebEx_WebExAccount (groupId, webExSiteId);
create index IX_5A891098 on WebEx_WebExAccount (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_53CCDD1A on WebEx_WebExAccount (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_E3C3AE2E on WebEx_WebExSite (groupId);
create index IX_1B43904C on WebEx_WebExSite (siteKey);
create index IX_950ABCF0 on WebEx_WebExSite (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_96FDBF72 on WebEx_WebExSite (uuid_[$COLUMN_LENGTH:75$], groupId);