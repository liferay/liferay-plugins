
create table WSRP_WSRPConsumer (
	wsrpConsumerId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url STRING null,
	wsdl TEXT null
);

create table WSRP_WSRPConsumerPortlet (
	wsrpConsumerPortletId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	wsrpConsumerId LONG,
	name VARCHAR(75) null,
	portletHandle STRING null
);