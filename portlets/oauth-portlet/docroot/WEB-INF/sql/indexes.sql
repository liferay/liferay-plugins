create index IX_26C5921A on OAuth_Application (companyId);
create index IX_50CC6659 on OAuth_Application (companyId, name);
create unique index IX_4D97F1EB on OAuth_Application (consumerKey);
create index IX_C233DF88 on OAuth_Application (userId);
create index IX_1C7A6947 on OAuth_Application (userId, name);

create index IX_CB0B40C on OAuth_ApplicationUser (accessToken);
create index IX_8EC698E2 on OAuth_ApplicationUser (applicationId);
create index IX_A63C0331 on OAuth_ApplicationUser (applicationId, authorized);
create index IX_FC2191B3 on OAuth_ApplicationUser (userId);
create unique index IX_52A72E68 on OAuth_ApplicationUser (userId, applicationId);
create index IX_83DB4A82 on OAuth_ApplicationUser (userId, authorized);
