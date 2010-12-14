create index IX_729869EE on OpenSocial_Gadget (companyId);
create unique index IX_A6A89EB1 on OpenSocial_Gadget (companyId, url);
create index IX_E1F8627A on OpenSocial_Gadget (uuid_);

create index IX_C87BF466 on OpenSocial_OAuthConsumer (gadgetId);
create index IX_305FFC6A on OpenSocial_OAuthConsumer (gadgetId, serviceName);

create index IX_A69E2384 on OpenSocial_OAuthToken (gadgetId, moduleId, serviceName, tokenName, userId);
create index IX_9AA2EF85 on OpenSocial_OAuthToken (gadgetId, serviceName);
create index IX_1B874128 on OpenSocial_OAuthToken (oauthConsumerId);
create index IX_B78A6E84 on OpenSocial_OAuthToken (userId, gadgetId, moduleId, serviceName, tokenName);
create index IX_21DFD8EE on OpenSocial_OAuthToken (userId, gadgetId, serviceName, moduleId, tokenName);
create index IX_52BC4C1 on OpenSocial_OAuthToken (userId, moduleId, oauthConsumerId, tokenName);