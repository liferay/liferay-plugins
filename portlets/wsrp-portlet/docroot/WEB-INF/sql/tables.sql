create table WSRP_WSRPConsumer (
	wsrpConsumerId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url STRING null,
	wsdl TEXT null,
	registrationContextString TEXT null,
	registrationPropertiesString STRING null
);

create table WSRP_WSRPConsumerPortlet (
	wsrpConsumerPortletId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	wsrpConsumerId LONG,
	name VARCHAR(75) null,
	portletHandle STRING null
);

create table WSRP_WSRPProducer (
	wsrpProducerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	version VARCHAR(75) null,
	portletIds STRING null
);