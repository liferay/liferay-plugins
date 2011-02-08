create index IX_729869EE on OpenSocial_Gadget (companyId);
create unique index IX_A6A89EB1 on OpenSocial_Gadget (companyId, url);
create unique index IX_EB9A754E on OpenSocial_Gadget (companyId, url, categoryName);
create index IX_E1F8627A on OpenSocial_Gadget (uuid_);

create index IX_C87BF466 on OpenSocial_OAuthConsumer (gadgetId);
create index IX_305FFC6A on OpenSocial_OAuthConsumer (gadgetId, serviceName);

create index IX_9AA2EF85 on OpenSocial_OAuthToken (gadgetId, serviceName);
create index IX_21DFD8EE on OpenSocial_OAuthToken (userId, gadgetId, serviceName, moduleId, tokenName);