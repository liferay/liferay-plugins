
create table Report_ReportDefinition (
	uuid_ VARCHAR(75) null,
	definitionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedBy LONG,
	modifiedDate DATE null,
	definitionName VARCHAR(75) null,
	description VARCHAR(75) null,
	reportName VARCHAR(75) null,
	reportParameters VARCHAR(75) null,
	reportFormat VARCHAR(75) null,
	dataSourceName VARCHAR(75) null
);

create table Report_RequestedReport (
	uuid_ VARCHAR(75) null,
	requestId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	definitionId LONG,
	requestStatus VARCHAR(75) null,
	scheduleRequest BOOLEAN
);