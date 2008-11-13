
create table Ruon_Network (
	networkId LONG not null primary key,
	name VARCHAR(75) null,
	ttl LONG
);

create table Ruon_Presence (
	presenceId LONG not null primary key,
	userId LONG,
	modifiedDate LONG,
	networkId LONG,
	online_ BOOLEAN
);
