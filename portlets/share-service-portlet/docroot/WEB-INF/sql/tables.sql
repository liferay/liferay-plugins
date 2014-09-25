create table share_EntitySocialFeed (
	classNameId LONG not null,
	classPK LONG not null,
	feedClassNameId LONG not null,
	feedClassPK LONG not null,
	primary key (classNameId, classPK, feedClassNameId, feedClassPK)
);