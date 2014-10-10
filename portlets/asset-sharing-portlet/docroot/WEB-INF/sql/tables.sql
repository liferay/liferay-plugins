create table AssetSharing_AssetSharingEntry (
	classNameId LONG not null,
	classPK LONG not null,
	sharedToClassNameId LONG not null,
	sharedToClassPK LONG not null,
	primary key (classNameId, classPK, sharedToClassNameId, sharedToClassPK)
);