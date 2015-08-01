create table WSRP_WSRPConsumer (
	uuid_ VARCHAR(75) null,
	wsrpConsumerId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	url STRING null,
	wsdl TEXT null,
	registrationContextString TEXT null,
	registrationPropertiesString STRING null,
	forwardCookies VARCHAR(255) null,
	forwardHeaders VARCHAR(255) null,
	markupCharacterSets VARCHAR(255) null,
	lastPublishDate DATE null
);

create table WSRP_WSRPConsumerPortlet (
	uuid_ VARCHAR(75) null,
	wsrpConsumerPortletId LONG not null primary key,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	wsrpConsumerId LONG,
	name VARCHAR(75) null,
	portletHandle VARCHAR(255) null,
	lastPublishDate DATE null
);

create table WSRP_WSRPProducer (
	uuid_ VARCHAR(75) null,
	wsrpProducerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	version VARCHAR(75) null,
	portletIds STRING null,
	lastPublishDate DATE null
);