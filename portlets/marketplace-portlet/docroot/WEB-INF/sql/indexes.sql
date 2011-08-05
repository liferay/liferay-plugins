create index IX_865B7BD5 on Marketplace_App (companyId);
create index IX_652D4FF8 on Marketplace_App (marketplaceAppId);
create index IX_3E667FE1 on Marketplace_App (uuid_);

create index IX_7DC16D26 on Marketplace_Module (appId);
create index IX_C6938724 on Marketplace_Module (appId, contextName);
create index IX_F2F1E964 on Marketplace_Module (contextName);
create index IX_A7EFD80E on Marketplace_Module (uuid_);