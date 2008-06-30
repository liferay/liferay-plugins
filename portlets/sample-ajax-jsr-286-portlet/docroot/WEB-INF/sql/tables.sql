
create table Sample_NotepadEntry (
	notepadEntryId LONG not null primary key,
	categoryId LONG,
	createDate DATE null,
	notes STRING null
);