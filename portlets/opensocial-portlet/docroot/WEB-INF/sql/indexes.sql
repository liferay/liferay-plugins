create unique index IX_A6A89EB1 on OpenSocial_Gadget (companyId, url);
create index IX_3C79316E on OpenSocial_Gadget (uuid_, companyId);

create index IX_8E715BF8 on OpenSocial_OAuthConsumer (gadgetKey, serviceName);

create index IX_6C8CCC3D on OpenSocial_OAuthToken (gadgetKey, serviceName);
create index IX_CDD35402 on OpenSocial_OAuthToken (userId, gadgetKey, serviceName, moduleId, tokenName);