create index IX_E59D4815 on Polls_PollsChoice (pollsQuestionId);
create unique index IX_F8047414 on Polls_PollsChoice (pollsQuestionId, name);
create index IX_3A6EE84 on Polls_PollsChoice (uuid_);

create index IX_687C1055 on Polls_PollsQuestion (groupId);
create index IX_B693B41F on Polls_PollsQuestion (uuid_);
create index IX_33D0D6A9 on Polls_PollsQuestion (uuid_, companyId);
create unique index IX_D9C0F36B on Polls_PollsQuestion (uuid_, groupId);

create index IX_E3B7C67 on Polls_PollsVote (pollsChoiceId);
create index IX_A3712DEC on Polls_PollsVote (pollsQuestionId);
create unique index IX_8C7A79F2 on Polls_PollsVote (userId, pollsQuestionId);