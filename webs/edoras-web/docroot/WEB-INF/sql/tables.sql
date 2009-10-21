
create table Edoras_WorkflowDefinition (
	workflowDefinitionId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	version INTEGER,
	designerVersion VARCHAR(75) null,
	modelXml VARCHAR(75) null,
	graphicalXml VARCHAR(75) null,
	persistent BOOLEAN
);

create table Edoras_WorkflowInstance (
	workflowInstanceId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	setupId VARCHAR(75) null,
	friendlyId VARCHAR(75) null,
	workflowDefinitionId LONG,
	workflowDefinitionName VARCHAR(75) null,
	workflowDefinitionVersion INTEGER,
	parentWorkflowInstanceId LONG,
	relationClassName VARCHAR(75) null,
	relationClassPK LONG,
	attributes VARCHAR(75) null,
	nestedWorkflowDefinitionIds VARCHAR(75) null,
	nestedWorkflowDefinitionVersions VARCHAR(75) null,
	nestedRelatedElements VARCHAR(75) null,
	currentElementName VARCHAR(75) null,
	relatedElementName VARCHAR(75) null,
	finished BOOLEAN,
	finishedDated DATE null,
	active_ BOOLEAN
);

create table Edoras_WorkflowJob (
	workflowJobId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	setupId VARCHAR(75) null,
	workflowDefinitionId LONG,
	workflowInstanceId LONG,
	elementName VARCHAR(75) null,
	cause VARCHAR(75) null,
	dueDate DATE null,
	notBeforeDate DATE null,
	exceptionCount INTEGER
);

create table Edoras_WorkflowLog (
	workflowLogId LONG not null primary key,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	workflowDefinitionId LONG,
	workflowInstanceId LONG,
	workflowTaskId LONG,
	logEntityType INTEGER,
	description VARCHAR(75) null,
	activityName VARCHAR(75) null,
	oldState VARCHAR(75) null,
	newState VARCHAR(75) null,
	type_ INTEGER,
	comment VARCHAR(75) null
);

create table Edoras_WorkflowTask (
	workflowTaskId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	friendlyId VARCHAR(75) null,
	workflowDefinitionId LONG,
	workflowDefinitionName VARCHAR(75) null,
	workflowDefinitionVersion INTEGER,
	workflowInstanceId LONG,
	metaName VARCHAR(75) null,
	relation VARCHAR(75) null,
	dueDate DATE null,
	completionDate DATE null,
	completed BOOLEAN,
	state INTEGER,
	priority INTEGER,
	asynchronous BOOLEAN,
	taskName VARCHAR(75) null,
	description VARCHAR(75) null,
	assigneeUserId LONG,
	assigneeUserName VARCHAR(75) null,
	assigneeGroupId LONG,
	assigneeGroupName VARCHAR(75) null,
	assigneeRoleId LONG,
	assigneeRoleName VARCHAR(75) null
);