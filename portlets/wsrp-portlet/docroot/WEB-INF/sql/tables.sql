
create table WSRP_WSRPConfiguredProducer (
	configuredProducerId LONG not null primary key,
	name VARCHAR(75) null,
	portalId VARCHAR(75) null,
	namespace VARCHAR(75) null,
	producerURL VARCHAR(256) null,
	producerVersion VARCHAR(75) null,
	producerMarkupURL VARCHAR(256) null,
	status INTEGER,
	registrationData TEXT null,
	registrationContext TEXT null,
	serviceDescription TEXT null,
	userCategoryMapping TEXT null,
	customUserProfile TEXT null,
	identityPropagationType VARCHAR(75) null,
	lifetimeTerminationTime VARCHAR(75) null,
	sdLastModified LONG,
	entityVersion INTEGER
);

create table WSRP_WSRPConsumerRegistration (
	consumerRegistrationId LONG not null primary key,
	consumerName VARCHAR(100) null,
	status BOOLEAN,
	registrationHandle VARCHAR(75) null,
	registrationData TEXT null,
	lifetimeTerminationTime VARCHAR(75) null,
	producerKey VARCHAR(75) null
);

create table WSRP_WSRPPortlet (
	portletId LONG not null primary key,
	name VARCHAR(75) null,
	channelName VARCHAR(75) null,
	title VARCHAR(75) null,
	shortTitle VARCHAR(75) null,
	displayName VARCHAR(75) null,
	keywords VARCHAR(75) null,
	status INTEGER,
	producerEntityId VARCHAR(75) null,
	consumerId VARCHAR(75) null,
	portletHandle VARCHAR(75) null,
	mimeTypes STRING null
);

create table WSRP_WSRPProducer (
	producerId LONG not null primary key,
	portalId VARCHAR(75) null,
	status BOOLEAN,
	namespace VARCHAR(75) null,
	instanceName VARCHAR(75) null,
	requiresRegistration BOOLEAN,
	supportsInbandRegistration BOOLEAN,
	version VARCHAR(75) null,
	offeredPortlets STRING null,
	producerProfileMap VARCHAR(75) null,
	registrationProperties STRING null,
	registrationValidatorClass VARCHAR(200) null
);