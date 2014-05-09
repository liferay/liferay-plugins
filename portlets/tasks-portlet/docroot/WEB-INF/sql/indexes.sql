create index IX_399C699E on TMS_TasksEntry (assigneeUserId, status);
create index IX_CB93FC0A on TMS_TasksEntry (groupId, assigneeUserId, status);
create index IX_EB40A85B on TMS_TasksEntry (groupId, resolverUserId);
create index IX_DE60B51B on TMS_TasksEntry (groupId, userId, status);
create index IX_9A89E9EF on TMS_TasksEntry (resolverUserId);
create index IX_8DA9F6AF on TMS_TasksEntry (userId, status);