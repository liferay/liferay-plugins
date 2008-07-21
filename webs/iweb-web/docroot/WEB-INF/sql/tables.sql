
create table Communities_Semantics (
	cid LONG not null,
	semanticsURI VARCHAR(300) not null,
	primary key (cid, semanticsURI)
);

create table IWEB_Community (
	cid LONG not null primary key,
	type_ INTEGER
);

create table IWEB_Post (
	uid LONG not null primary key,
	pid LONG,
	type_ VARCHAR(75) null,
	communityid LONG
);

create table IWEB_Semantics (
	semanticsName VARCHAR(75) null,
	semanticsURI VARCHAR(300) not null primary key,
	createdInCommunity LONG
);

create table IWEB_SemanticsElement (
	elementURI VARCHAR(300) not null primary key,
	semanticsURI VARCHAR(300) null
);

create table Posts_SemanticsElements (
	uid LONG not null,
	elementURI VARCHAR(300) not null,
	primary key (uid, elementURI)
);
