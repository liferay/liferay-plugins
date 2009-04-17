create index IX_72919090 on Report_ReportDefinition (companyId);
create index IX_3C05861A on Report_ReportDefinition (companyId, groupId);
create index IX_F959D10A on Report_ReportDefinition (definitionName);
create index IX_7164FB12 on Report_ReportDefinition (groupId);
create index IX_71F64D2 on Report_ReportDefinition (userId);
create index IX_B1422A1C on Report_ReportDefinition (uuid_);
create unique index IX_1AEA0E on Report_ReportDefinition (uuid_, groupId);

create index IX_9E743BB2 on Report_ReportRequest (companyId);
create index IX_CEB44138 on Report_ReportRequest (companyId, groupId);
create index IX_EF318878 on Report_ReportRequest (definitionId);
create index IX_1EDC06B4 on Report_ReportRequest (groupId);
create index IX_14E98C4 on Report_ReportRequest (requestId);
create index IX_CAA75CF0 on Report_ReportRequest (userId);
create index IX_22EBB63E on Report_ReportRequest (uuid_);
create unique index IX_F82FE42C on Report_ReportRequest (uuid_, groupId);