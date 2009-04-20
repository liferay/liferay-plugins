create index IX_72919090 on Report_ReportDefinition (companyId);
create index IX_3C05861A on Report_ReportDefinition (companyId, groupId);
create index IX_F959D10A on Report_ReportDefinition (definitionName);
create index IX_7164FB12 on Report_ReportDefinition (groupId);
create index IX_71F64D2 on Report_ReportDefinition (userId);
create index IX_B1422A1C on Report_ReportDefinition (uuid_);
create unique index IX_1AEA0E on Report_ReportDefinition (uuid_, groupId);

create index IX_98644CB on Report_RequestedReport (companyId);
create index IX_2808643F on Report_RequestedReport (companyId, groupId);
create index IX_D7023CBF on Report_RequestedReport (definitionId);
create index IX_FCDF820D on Report_RequestedReport (groupId);
create index IX_6C60A1DD on Report_RequestedReport (requestId);
create index IX_B90A9277 on Report_RequestedReport (userId);
create index IX_1A1833D7 on Report_RequestedReport (uuid_);
create unique index IX_2EA4A2B3 on Report_RequestedReport (uuid_, groupId);