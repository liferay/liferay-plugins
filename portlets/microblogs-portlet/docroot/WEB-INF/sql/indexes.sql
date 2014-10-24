create index IX_837C013D on MicroblogsEntry (companyId);
create index IX_14ACFA9 on MicroblogsEntry (creatorClassNameId, creatorClassPK, type_);
create index IX_6AA6B164 on MicroblogsEntry (creatorClassNameId, type_);
create index IX_36CA3D37 on MicroblogsEntry (type_, receiverMicroblogsEntryId);
create index IX_7ABB0AB3 on MicroblogsEntry (type_, receiverUserId);
create index IX_8F04FC09 on MicroblogsEntry (userId, createDate, type_, socialRelationType);
create index IX_92BA6F0 on MicroblogsEntry (userId, type_);