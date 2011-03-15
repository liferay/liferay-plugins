create index IX_837C013D on MicroblogsEntry (companyId);
create index IX_36CA3D37 on MicroblogsEntry (type_, receiverMicroblogsEntryId);
create index IX_7ABB0AB3 on MicroblogsEntry (type_, receiverUserId);
create index IX_6C297B45 on MicroblogsEntry (userId);
create index IX_92BA6F0 on MicroblogsEntry (userId, type_);