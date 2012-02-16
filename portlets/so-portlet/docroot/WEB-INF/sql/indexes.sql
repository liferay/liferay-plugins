create index IX_DE2F16A6 on SO_FavoriteSite (userId);
create unique index IX_8E78C4 on SO_FavoriteSite (userId, groupId);

create index IX_D34593C1 on SO_MemberRequest (groupId, receiverUserId, status);
create index IX_212FA0EC on SO_MemberRequest (key_);
create index IX_D6810661 on SO_MemberRequest (receiverUserId);
create index IX_16475447 on SO_MemberRequest (receiverUserId, status);

create index IX_3371C715 on SO_ProjectsEntry (userId);