create index IX_571C019E on KBArticle (companyId, latest);
create index IX_5A381890 on KBArticle (companyId, main);
create index IX_FBC2D349 on KBArticle (companyId, status);
create index IX_CFB8C81F on KBArticle (groupId, kbFolderId, status);
create index IX_379FD6BC on KBArticle (groupId, kbFolderId, urlTitle, status);
create index IX_694EA2E0 on KBArticle (groupId, latest);
create index IX_97C62252 on KBArticle (groupId, main);
create index IX_B0FCBB47 on KBArticle (groupId, parentResourcePrimKey, latest);
create index IX_D91D2879 on KBArticle (groupId, parentResourcePrimKey, main);
create index IX_55A38CF2 on KBArticle (groupId, parentResourcePrimKey, status);
create index IX_DF5748B on KBArticle (groupId, status);
create index IX_86BA3247 on KBArticle (parentResourcePrimKey, latest);
create index IX_1DCC5F79 on KBArticle (parentResourcePrimKey, main);
create index IX_2B6103F2 on KBArticle (parentResourcePrimKey, status);
create index IX_5FEF5F4F on KBArticle (resourcePrimKey, groupId, latest);
create index IX_8EF92E81 on KBArticle (resourcePrimKey, groupId, main);
create index IX_49630FA on KBArticle (resourcePrimKey, groupId, status);
create index IX_A9E2C691 on KBArticle (resourcePrimKey, latest);
create index IX_69C17E43 on KBArticle (resourcePrimKey, main);
create index IX_4E89983C on KBArticle (resourcePrimKey, status);
create unique index IX_AA304772 on KBArticle (resourcePrimKey, version);
create index IX_4E87D659 on KBArticle (uuid_, companyId);
create unique index IX_5C941F1B on KBArticle (uuid_, groupId);

create index IX_47D3AE89 on KBComment (classNameId, classPK, status);
create index IX_E8D43932 on KBComment (groupId, classNameId);
create index IX_828BA082 on KBComment (groupId, status);
create index IX_FD56A55D on KBComment (userId, classNameId, classPK);
create index IX_6CB72942 on KBComment (uuid_, companyId);
create unique index IX_791D1844 on KBComment (uuid_, groupId);

create index IX_3FA4415C on KBFolder (groupId, parentKBFolderId, name);
create index IX_729A89FA on KBFolder (groupId, parentKBFolderId, urlTitle);
create index IX_32D1105F on KBFolder (uuid_, companyId);
create unique index IX_1FD022A1 on KBFolder (uuid_, groupId);

create index IX_83D9CC13 on KBTemplate (groupId);
create index IX_853770AB on KBTemplate (uuid_, companyId);
create unique index IX_40AA25ED on KBTemplate (uuid_, groupId);