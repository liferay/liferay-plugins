create unique index IX_A6A89EB1 on OpenSocial_Gadget (companyId, url[$COLUMN_LENGTH:4000$]);
create index IX_3C79316E on OpenSocial_Gadget (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_8E715BF8 on OpenSocial_OAuthConsumer (gadgetKey[$COLUMN_LENGTH:75$], serviceName[$COLUMN_LENGTH:75$]);

create index IX_6C8CCC3D on OpenSocial_OAuthToken (gadgetKey[$COLUMN_LENGTH:75$], serviceName[$COLUMN_LENGTH:75$]);
create index IX_CDD35402 on OpenSocial_OAuthToken (userId, gadgetKey[$COLUMN_LENGTH:75$], serviceName[$COLUMN_LENGTH:75$], moduleId, tokenName[$COLUMN_LENGTH:75$]);