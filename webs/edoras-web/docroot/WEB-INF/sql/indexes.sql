create index IX_A68925A5 on Edoras_WorkflowDefinition (companyId);
create index IX_9F57E5A4 on Edoras_WorkflowDefinition (companyId, name);
create unique index IX_8AD6A8E4 on Edoras_WorkflowDefinition (companyId, name, version);

create index IX_AD6EBE49 on Edoras_WorkflowInstance (companyId, finished);
create index IX_A0E81A10 on Edoras_WorkflowInstance (companyId, parentWorkflowInstanceId);
create index IX_49CE9600 on Edoras_WorkflowInstance (companyId, parentWorkflowInstanceId, relatedElementName);
create index IX_88AB9CE6 on Edoras_WorkflowInstance (companyId, parentWorkflowInstanceId, relatedElementName, finished);
create index IX_528A388B on Edoras_WorkflowInstance (finished);
create index IX_6F9AD052 on Edoras_WorkflowInstance (parentWorkflowInstanceId);
create index IX_FBA55015 on Edoras_WorkflowInstance (relationClassName, relationClassPK);
create index IX_2BC18F63 on Edoras_WorkflowInstance (setupId);
create index IX_9AD28E06 on Edoras_WorkflowInstance (workflowDefinitionId);

create index IX_B772508F on Edoras_WorkflowJob (setupId);
create index IX_9442145A on Edoras_WorkflowJob (workflowDefinitionId);
create index IX_463D711C on Edoras_WorkflowJob (workflowInstanceId);

create index IX_C1960963 on Edoras_WorkflowLog (workflowInstanceId);
create index IX_A97BD0CE on Edoras_WorkflowLog (workflowInstanceId, logEntityType);
create index IX_E700B547 on Edoras_WorkflowLog (workflowInstanceId, logEntityType, type_);
create index IX_8F74AAD3 on Edoras_WorkflowLog (workflowTaskId);

create index IX_C799F228 on Edoras_WorkflowTask (companyId, completed);
create index IX_CF060A46 on Edoras_WorkflowTask (completed);
create index IX_FCB927B8 on Edoras_WorkflowTask (workflowInstanceId);
create index IX_3FCEB449 on Edoras_WorkflowTask (workflowInstanceId, state);