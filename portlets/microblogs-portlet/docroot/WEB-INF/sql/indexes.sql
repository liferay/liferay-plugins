create index IX_6FA9334F on Microblogs_MicroblogsEntry (companyId);
create index IX_E4FC2CF2 on Microblogs_MicroblogsEntry (type_, receiverEntryId);
create index IX_E7E3B1C5 on Microblogs_MicroblogsEntry (type_, receiverUserId);
create index IX_D31DD273 on Microblogs_MicroblogsEntry (userId);
create index IX_D86D8002 on Microblogs_MicroblogsEntry (userId, type_);