create index IX_9DB062B8 on TMS_TasksEntry (assigneeUserId);
create index IX_DF694DFB on TMS_TasksEntry (groupId);
create index IX_EE672124 on TMS_TasksEntry (groupId, assigneeUserId);
create index IX_EB40A85B on TMS_TasksEntry (groupId, resolverUserId);
create index IX_84A27B35 on TMS_TasksEntry (groupId, userId);
create index IX_9A89E9EF on TMS_TasksEntry (resolverUserId);
create index IX_5D4090C9 on TMS_TasksEntry (userId);