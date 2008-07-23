
create table IWEB_InterestGroup (
	cid LONG not null primary key,
	type_ INTEGER
);

create table IWEB_PostEntry (
	uid LONG not null primary key,
	interestGroupId LONG,
	pid LONG,
	type_ VARCHAR(75) null
);

create table IWEB_SemanticsElement (
	elementURI VARCHAR(75) not null primary key,
	semanticsURI VARCHAR(75) null
);

create table IWEB_SemanticsFile (
	semanticsName VARCHAR(75) null,
	semanticsURI VARCHAR(75) not null primary key,
	createdInInterestGroup LONG
);

create table InterestGroups_SemanticsFiles (
	cid LONG not null,
	semanticsURI VARCHAR(75) not null,
	primary key (cid, semanticsURI)
);

create table PostEntries_SemanticsElements (
	uid LONG not null,
	elementURI VARCHAR(75) not null,
	primary key (uid, elementURI)
);