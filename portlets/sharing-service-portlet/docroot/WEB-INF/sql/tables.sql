create table Sharing_SharingEntry (
	classNameId LONG not null,
	classPK LONG not null,
	sharingClassNameId LONG not null,
	sharingClassPK LONG not null,
	primary key (classNameId, classPK, sharingClassNameId, sharingClassPK)
);