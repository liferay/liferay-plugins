create index IX_729869EE on OpenSocial_Gadget (companyId);
create unique index IX_A6A89EB1 on OpenSocial_Gadget (companyId, url);
create index IX_E1F8627A on OpenSocial_Gadget (uuid_);

create index IX_C87BF466 on OpenSocial_OAuthConsumer (gadgetId);
create index IX_305FFC6A on OpenSocial_OAuthConsumer (gadgetId, serviceName);