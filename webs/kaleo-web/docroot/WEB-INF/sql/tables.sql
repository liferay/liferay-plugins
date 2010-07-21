create table Kaleo_KaleoAction (
	kaleoActionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNodeId LONG,
	kaleoNodeName VARCHAR(200) null,
	name VARCHAR(200) null,
	description VARCHAR(2000) null,
	executionType VARCHAR(20) null,
	script TEXT null,
	scriptLanguage VARCHAR(75) null,
	priority INTEGER
);

create table Kaleo_KaleoDefinition (
	kaleoDefinitionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(200) null,
	title STRING null,
	description VARCHAR(2000) null,
	version INTEGER,
	active_ BOOLEAN,
	startKaleoNodeId LONG
);

create table Kaleo_KaleoInstance (
	kaleoInstanceId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoDefinitionName VARCHAR(200) null,
	kaleoDefinitionVersion INTEGER,
	rootKaleoInstanceTokenId LONG,
	className VARCHAR(200) null,
	classPK LONG,
	completed BOOLEAN,
	completionDate DATE null,
	workflowContext TEXT null
);

create table Kaleo_KaleoInstanceToken (
	kaleoInstanceTokenId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoInstanceId LONG,
	parentKaleoInstanceTokenId LONG,
	currentKaleoNodeId LONG,
	currentKaleoNodeName VARCHAR(200) null,
	className VARCHAR(75) null,
	classPK LONG,
	completed BOOLEAN,
	completionDate DATE null
);

create table Kaleo_KaleoLog (
	kaleoLogId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoInstanceId LONG,
	kaleoInstanceTokenId LONG,
	kaleoTaskInstanceTokenId LONG,
	kaleoNodeId LONG,
	kaleoNodeName VARCHAR(200) null,
	terminalKaleoNode BOOLEAN,
	kaleoActionId LONG,
	kaleoActionName VARCHAR(200) null,
	kaleoActionDescription VARCHAR(2000) null,
	previousKaleoNodeId LONG,
	previousKaleoNodeName VARCHAR(200) null,
	previousAssigneeClassName VARCHAR(200) null,
	previousAssigneeClassPK LONG,
	currentAssigneeClassName VARCHAR(200) null,
	currentAssigneeClassPK LONG,
	type_ VARCHAR(50) null,
	comment_ VARCHAR(2000) null,
	startDate DATE null,
	endDate DATE null,
	duration LONG,
	workflowContext TEXT null
);

create table Kaleo_KaleoNode (
	kaleoNodeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	name VARCHAR(200) null,
	description VARCHAR(2000) null,
	type_ VARCHAR(20) null,
	initial_ BOOLEAN,
	terminal BOOLEAN
);

create table Kaleo_KaleoNotification (
	kaleoNotificationId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNodeId LONG,
	kaleoNodeName VARCHAR(200) null,
	name VARCHAR(200) null,
	description VARCHAR(2000) null,
	executionType VARCHAR(20) null,
	template TEXT null,
	templateLanguage VARCHAR(75) null,
	notificationTypes VARCHAR(25) null
);

create table Kaleo_KaleoNotificationRecipient (
	kaleoNotificationRecipientId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNotificationId LONG,
	recipientClassName VARCHAR(75) null,
	recipientClassPK LONG,
	recipientRoleType INTEGER,
	address VARCHAR(75) null
);

create table Kaleo_KaleoTask (
	kaleoTaskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNodeId LONG,
	name VARCHAR(75) null,
	description VARCHAR(75) null,
	dueDateDuration DOUBLE,
	dueDateScale VARCHAR(75) null
);

create table Kaleo_KaleoTaskAssignment (
	kaleoTaskAssignmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNodeId LONG,
	kaleoTaskId LONG,
	assigneeClassName VARCHAR(200) null,
	assigneeClassPK LONG
);

create table Kaleo_KaleoTaskAssignmentInstance (
	kaleoTaskAssignmentInstanceId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoInstanceId LONG,
	kaleoInstanceTokenId LONG,
	kaleoTaskInstanceTokenId LONG,
	kaleoTaskId LONG,
	kaleoTaskName VARCHAR(75) null,
	assigneeClassName VARCHAR(75) null,
	assigneeClassPK LONG,
	completed BOOLEAN,
	completionDate DATE null
);

create table Kaleo_KaleoTaskInstanceToken (
	kaleoTaskInstanceTokenId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoInstanceId LONG,
	kaleoInstanceTokenId LONG,
	kaleoTaskId LONG,
	kaleoTaskName VARCHAR(200) null,
	className VARCHAR(75) null,
	classPK LONG,
	completionUserId LONG,
	completed BOOLEAN,
	completionDate DATE null,
	dueDate DATE null,
	workflowContext TEXT null
);

create table Kaleo_KaleoTransition (
	kaleoTransitionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNodeId LONG,
	name VARCHAR(200) null,
	description VARCHAR(2000) null,
	sourceKaleoNodeId LONG,
	sourceKaleoNodeName VARCHAR(200) null,
	targetKaleoNodeId LONG,
	targetKaleoNodeName VARCHAR(200) null,
	defaultTransition BOOLEAN
);