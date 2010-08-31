create index IX_A821854B on PM_UserThread (mbThreadId);
create index IX_3084E97 on PM_UserThread (userId);
create index IX_434EE852 on PM_UserThread (userId, deleted);
create index IX_466F2985 on PM_UserThread (userId, mbThreadId);
create index IX_A16EF3C7 on PM_UserThread (userId, read_, deleted);