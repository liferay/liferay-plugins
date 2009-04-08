create index IX_72919090 on Report_ReportDefinition (companyId);
create index IX_3C05861A on Report_ReportDefinition (companyId, groupId);
create index IX_7164FB12 on Report_ReportDefinition (groupId);
create index IX_5BC6A257 on Report_ReportDefinition (name);
create index IX_71F64D2 on Report_ReportDefinition (userId);
create index IX_B1422A1C on Report_ReportDefinition (uuid_);
create unique index IX_1AEA0E on Report_ReportDefinition (uuid_, groupId);