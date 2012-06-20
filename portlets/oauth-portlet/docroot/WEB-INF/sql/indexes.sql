create index IX_3E994709 on OAuthApplication (companyId);
create index IX_42FA6C08 on OAuthApplication (companyId, name);
create unique index IX_BF52271A on OAuthApplication (consumerKey);
create index IX_5583043E on OAuthApplication (name);
create index IX_D41BBDFF on OAuthApplication (ownerId);
create index IX_2389527E on OAuthApplication (ownerId, name);
create index IX_C517DC2C on OAuthApplication (website);

create index IX_F9E482BC on OAuthApplications_Users (accessToken);
create unique index IX_F9E482BC on OAuthApplications_Users (accessToken, applicationId, userId);
create index IX_FE417B92 on OAuthApplications_Users (applicationId);
create index IX_54885DE1 on OAuthApplications_Users (applicationId, authorized);
create unique index IX_C75B21CC on OAuthApplications_Users (applicationId, userId);
create index IX_87135503 on OAuthApplications_Users (userId);

create index IX_78A82761 on OAuth_OAuthApplication (companyId);
create index IX_DD85AA60 on OAuth_OAuthApplication (companyId, name);
create unique index IX_B12A5172 on OAuth_OAuthApplication (consumerKey);
create index IX_DE8F4CE6 on OAuth_OAuthApplication (name);
create index IX_4096D457 on OAuth_OAuthApplication (ownerId);
create index IX_46D6 on OAuth_OAuthApplication (ownerId, name);
create index IX_3192F284 on OAuth_OAuthApplication (website);

create index IX_6BD08564 on OAuth_OAuthApplications_Users (accessToken);
create index IX_A537743A on OAuth_OAuthApplications_Users (applicationId);
create index IX_A8469A89 on OAuth_OAuthApplications_Users (applicationId, authorized);
create unique index IX_41C1F274 on OAuth_OAuthApplications_Users (applicationId, userId);
create index IX_638A495B on OAuth_OAuthApplications_Users (userId);