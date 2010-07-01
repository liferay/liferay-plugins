create index IX_3B1743AB on Kaleo_KaleoAction (companyId);
create index IX_D764E9A1 on Kaleo_KaleoAction (kaleoDefinitionId);
create index IX_B2A93512 on Kaleo_KaleoAction (kaleoNodeId, executionType);

create index IX_2B7BEC2E on Kaleo_KaleoDefinition (companyId);
create index IX_2F7F569B on Kaleo_KaleoDefinition (companyId, active_);
create index IX_87D3E16D on Kaleo_KaleoDefinition (companyId, name);
create index IX_2C5DD83C on Kaleo_KaleoDefinition (companyId, name, active_);
create index IX_CC4EDF3B on Kaleo_KaleoDefinition (companyId, name, version);

create index IX_769B5BEC on Kaleo_KaleoInstance (companyId);
create index IX_EC4CB399 on Kaleo_KaleoInstance (companyId, kaleoDefinitionName, kaleoDefinitionVersion, completionDate);
create index IX_5EB130E2 on Kaleo_KaleoInstance (kaleoDefinitionId);
create index IX_664E0519 on Kaleo_KaleoInstance (kaleoDefinitionId, completed);

create index IX_7EC1F25F on Kaleo_KaleoInstanceToken (companyId);
create index IX_45F8175C on Kaleo_KaleoInstanceToken (companyId, parentKaleoInstanceTokenId);
create index IX_D5BA57FA on Kaleo_KaleoInstanceToken (companyId, parentKaleoInstanceTokenId, completionDate);
create index IX_949FA455 on Kaleo_KaleoInstanceToken (kaleoDefinitionId);
create index IX_E324C3D7 on Kaleo_KaleoInstanceToken (kaleoInstanceId);

create index IX_39EDEABF on Kaleo_KaleoLog (companyId);
create index IX_D9FD3CB5 on Kaleo_KaleoLog (kaleoDefinitionId);
create index IX_31CCF437 on Kaleo_KaleoLog (kaleoInstanceId);
create index IX_37870D24 on Kaleo_KaleoLog (kaleoInstanceTokenId, kaleoNodeId, type_);
create index IX_5FD03F99 on Kaleo_KaleoLog (kaleoInstanceTokenId, type_);
create index IX_B0413637 on Kaleo_KaleoLog (kaleoTaskInstanceTokenId);

create index IX_C5B0731F on Kaleo_KaleoNode (companyId);
create index IX_ECA77F9F on Kaleo_KaleoNode (companyId, kaleoDefinitionId);
create index IX_78616515 on Kaleo_KaleoNode (kaleoDefinitionId);

create index IX_7DFAABD6 on Kaleo_KaleoNotification (companyId);
create index IX_3BDEF6CC on Kaleo_KaleoNotification (kaleoDefinitionId);
create index IX_7A83C847 on Kaleo_KaleoNotification (kaleoNodeId, executionType);

create index IX_4550EA95 on Kaleo_KaleoNotificationRecipient (companyId);
create index IX_7F26068B on Kaleo_KaleoNotificationRecipient (kaleoDefinitionId);
create index IX_21EE3763 on Kaleo_KaleoNotificationRecipient (kaleoNotificationId);

create index IX_E2BF787C on Kaleo_KaleoTask (companyId);
create index IX_4977BD72 on Kaleo_KaleoTask (kaleoDefinitionId);
create index IX_61E22421 on Kaleo_KaleoTask (kaleoNodeId);

create index IX_BCE28DE5 on Kaleo_KaleoTaskAssignment (assigneeClassName, kaleoTaskId);
create index IX_28E6762F on Kaleo_KaleoTaskAssignment (companyId);
create index IX_572B5825 on Kaleo_KaleoTaskAssignment (kaleoDefinitionId);
create index IX_5EDD8F17 on Kaleo_KaleoTaskAssignment (kaleoTaskId);

create index IX_6E0C2E9A on Kaleo_KaleoTaskAssignmentInstance (companyId);
create index IX_CFB37590 on Kaleo_KaleoTaskAssignmentInstance (kaleoDefinitionId);
create index IX_D9E1A7D2 on Kaleo_KaleoTaskAssignmentInstance (kaleoInstanceId);
create index IX_213D60FC on Kaleo_KaleoTaskAssignmentInstance (kaleoTaskInstanceTokenId);

create index IX_A9FF7F44 on Kaleo_KaleoTaskInstanceToken (companyId);
create index IX_40C87C3A on Kaleo_KaleoTaskInstanceToken (kaleoDefinitionId);
create index IX_26FC50FC on Kaleo_KaleoTaskInstanceToken (kaleoInstanceId);

create index IX_EEE0476C on Kaleo_KaleoTransition (companyId);
create index IX_47129C62 on Kaleo_KaleoTransition (kaleoDefinitionId);
create index IX_E90AF711 on Kaleo_KaleoTransition (kaleoNodeId);
create index IX_462C6BF5 on Kaleo_KaleoTransition (kaleoNodeId, defaultTransition);
create index IX_8499F610 on Kaleo_KaleoTransition (kaleoNodeId, name);