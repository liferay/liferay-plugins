create index IX_330AA4A7 on KB_Article (resourcePrimKey);
create index IX_A1094C8D on KB_Article (resourcePrimKey, status);
create unique index IX_A7A71D41 on KB_Article (resourcePrimKey, version);
create index IX_3D477900 on KB_Article (uuid_);
create unique index IX_1B961BAA on KB_Article (uuid_, groupId);

create index IX_B8FC1FE2 on KB_Template (groupId);
create index IX_7EFC42EC on KB_Template (uuid_);
create unique index IX_61E7BB3E on KB_Template (uuid_, groupId);