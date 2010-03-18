create index IX_B2A93512 on Kaleo_KaleoAction (kaleoNodeId, executionType);

create index IX_2B7BEC2E on Kaleo_KaleoDefinition (companyId);
create index IX_2F7F569B on Kaleo_KaleoDefinition (companyId, active_);
create index IX_1827061A on Kaleo_KaleoDefinition (companyId, active_, name);
create index IX_87D3E16D on Kaleo_KaleoDefinition (companyId, name);
create index IX_CC4EDF3B on Kaleo_KaleoDefinition (companyId, name, version);

create index IX_EC4CB399 on Kaleo_KaleoInstance (companyId, kaleoDefinitionName, kaleoDefinitionVersion, completionDate);

create index IX_45F8175C on Kaleo_KaleoInstanceToken (companyId, parentKaleoInstanceTokenId);
create index IX_D5BA57FA on Kaleo_KaleoInstanceToken (companyId, parentKaleoInstanceTokenId, completionDate);

create index IX_37870D24 on Kaleo_KaleoLog (kaleoInstanceTokenId, kaleoNodeId, type_);
create index IX_5FD03F99 on Kaleo_KaleoLog (kaleoInstanceTokenId, type_);
create index IX_B0413637 on Kaleo_KaleoLog (kaleoTaskInstanceTokenId);

create index IX_61E22421 on Kaleo_KaleoTask (kaleoNodeId);

create index IX_34EC9969 on Kaleo_KaleoTaskAssignment (assigneeClassName, assigneeClassPK);
create index IX_5EDD8F17 on Kaleo_KaleoTaskAssignment (kaleoTaskId);
create index IX_787D75A7 on Kaleo_KaleoTaskAssignment (kaleoTaskId, defaultAssignment);

create index IX_57B35FBC on Kaleo_KaleoTaskInstanceAssignment (kaleoTaskInstanceTokenId);
create index IX_ABD2B74C on Kaleo_KaleoTaskInstanceAssignment (kaleoTaskInstanceTokenId, assigneeClassName, assigneeClassPK);

create index IX_E90AF711 on Kaleo_KaleoTransition (kaleoNodeId);
create index IX_462C6BF5 on Kaleo_KaleoTransition (kaleoNodeId, defaultTransition);
create index IX_8499F610 on Kaleo_KaleoTransition (kaleoNodeId, name);