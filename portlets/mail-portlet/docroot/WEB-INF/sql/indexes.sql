create index IX_6B92F85F on Mail_Account (userId, address[$COLUMN_LENGTH:75$]);

create index IX_F661D061 on Mail_Attachment (messageId);

create index IX_310E554A on Mail_Folder (accountId, fullName[$COLUMN_LENGTH:75$]);

create index IX_163EBD83 on Mail_Message (companyId);
create index IX_200D262A on Mail_Message (folderId, remoteMessageId);