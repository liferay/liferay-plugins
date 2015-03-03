create table KaleoAction (
	kaleoActionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoClassName VARCHAR(200) null,
	kaleoClassPK LONG,
	kaleoDefinitionId LONG,
	kaleoNodeName VARCHAR(200) null,
	name VARCHAR(200) null,
	description STRING null,
	executionType VARCHAR(20) null,
	script TEXT null,
	scriptLanguage VARCHAR(75) null,
	scriptRequiredContexts STRING null,
	priority INTEGER
);

create table KaleoCondition (
	kaleoConditionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNodeId LONG,
	script TEXT null,
	scriptLanguage VARCHAR(75) null,
	scriptRequiredContexts STRING null
);

create table KaleoDefinition (
	kaleoDefinitionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(200) null,
	title STRING null,
	description STRING null,
	content TEXT null,
	version INTEGER,
	active_ BOOLEAN,
	startKaleoNodeId LONG
);

create table KaleoInstance (
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

create table KaleoInstanceToken (
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
	className VARCHAR(200) null,
	classPK LONG,
	completed BOOLEAN,
	completionDate DATE null
);

create table KaleoLog (
	kaleoLogId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoClassName VARCHAR(200) null,
	kaleoClassPK LONG,
	kaleoDefinitionId LONG,
	kaleoInstanceId LONG,
	kaleoInstanceTokenId LONG,
	kaleoTaskInstanceTokenId LONG,
	kaleoNodeName VARCHAR(200) null,
	terminalKaleoNode BOOLEAN,
	kaleoActionId LONG,
	kaleoActionName VARCHAR(200) null,
	kaleoActionDescription STRING null,
	previousKaleoNodeId LONG,
	previousKaleoNodeName VARCHAR(200) null,
	previousAssigneeClassName VARCHAR(200) null,
	previousAssigneeClassPK LONG,
	currentAssigneeClassName VARCHAR(200) null,
	currentAssigneeClassPK LONG,
	type_ VARCHAR(50) null,
	comment_ TEXT null,
	startDate DATE null,
	endDate DATE null,
	duration LONG,
	workflowContext TEXT null
);

create table KaleoNode (
	kaleoNodeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	name VARCHAR(200) null,
	metadata STRING null,
	description STRING null,
	type_ VARCHAR(20) null,
	initial_ BOOLEAN,
	terminal BOOLEAN
);

create table KaleoNotification (
	kaleoNotificationId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoClassName VARCHAR(200) null,
	kaleoClassPK LONG,
	kaleoDefinitionId LONG,
	kaleoNodeName VARCHAR(200) null,
	name VARCHAR(200) null,
	description STRING null,
	executionType VARCHAR(20) null,
	template TEXT null,
	templateLanguage VARCHAR(75) null,
	notificationTypes VARCHAR(25) null
);

create table KaleoNotificationRecipient (
	kaleoNotificationRecipientId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNotificationId LONG,
	recipientClassName VARCHAR(200) null,
	recipientClassPK LONG,
	recipientRoleType INTEGER,
	recipientScript TEXT null,
	recipientScriptLanguage VARCHAR(75) null,
	recipientScriptRequiredContexts STRING null,
	address VARCHAR(255) null,
	notificationReceptionType VARCHAR(3) null
);

create table KaleoTask (
	kaleoTaskId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoDefinitionId LONG,
	kaleoNodeId LONG,
	name VARCHAR(200) null,
	description STRING null
);

create table KaleoTaskAssignment (
	kaleoTaskAssignmentId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoClassName VARCHAR(200) null,
	kaleoClassPK LONG,
	kaleoDefinitionId LONG,
	kaleoNodeId LONG,
	assigneeClassName VARCHAR(200) null,
	assigneeClassPK LONG,
	assigneeActionId VARCHAR(75) null,
	assigneeScript TEXT null,
	assigneeScriptLanguage VARCHAR(75) null,
	assigneeScriptRequiredContexts STRING null
);

create table KaleoTaskAssignmentInstance (
	kaleoTaskAssignmentInstanceId LONG not null primary key,
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
	kaleoTaskId LONG,
	kaleoTaskName VARCHAR(200) null,
	assigneeClassName VARCHAR(200) null,
	assigneeClassPK LONG,
	completed BOOLEAN,
	completionDate DATE null
);

create table KaleoTaskInstanceToken (
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
	className VARCHAR(200) null,
	classPK LONG,
	completionUserId LONG,
	completed BOOLEAN,
	completionDate DATE null,
	dueDate DATE null,
	workflowContext TEXT null
);

create table KaleoTimer (
	kaleoTimerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoClassName VARCHAR(200) null,
	kaleoClassPK LONG,
	kaleoDefinitionId LONG,
	name VARCHAR(75) null,
	blocking BOOLEAN,
	description STRING null,
	duration DOUBLE,
	scale VARCHAR(75) null,
	recurrenceDuration DOUBLE,
	recurrenceScale VARCHAR(75) null
);

create table KaleoTimerInstanceToken (
	kaleoTimerInstanceTokenId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(200) null,
	createDate DATE null,
	modifiedDate DATE null,
	kaleoClassName VARCHAR(200) null,
	kaleoClassPK LONG,
	kaleoDefinitionId LONG,
	kaleoInstanceId LONG,
	kaleoInstanceTokenId LONG,
	kaleoTaskInstanceTokenId LONG,
	kaleoTimerId LONG,
	kaleoTimerName VARCHAR(200) null,
	blocking BOOLEAN,
	completionUserId LONG,
	completed BOOLEAN,
	completionDate DATE null,
	workflowContext TEXT null
);

create table KaleoTransition (
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
	description STRING null,
	sourceKaleoNodeId LONG,
	sourceKaleoNodeName VARCHAR(200) null,
	targetKaleoNodeId LONG,
	targetKaleoNodeName VARCHAR(200) null,
	defaultTransition BOOLEAN
);