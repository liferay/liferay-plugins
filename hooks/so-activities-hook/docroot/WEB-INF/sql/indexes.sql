create index IX_260A37D9 on SO_SocialActivity (activitySetId);

create index IX_4460FA14 on SocialActivitySet (classNameId, classPK, type_);
create index IX_9E13F2DE on SocialActivitySet (groupId);
create index IX_9BE30DDF on SocialActivitySet (groupId, userId, classNameId, type_);
create index IX_F71071BD on SocialActivitySet (groupId, userId, type_);
create index IX_F80C4386 on SocialActivitySet (userId);
create index IX_62AC101A on SocialActivitySet (userId, classNameId, classPK, type_);