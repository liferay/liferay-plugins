
create table WSRP_Producer (
	uuid_ VARCHAR(75) null,
	producerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	wsdlURL VARCHAR(300) null,
	markupEndpoint VARCHAR(300) null,
	portletManagementEndpoint VARCHAR(300) null,
	registrationEndpoint VARCHAR(300) null,
	serviceDescriptionEndpoint VARCHAR(300) null,
	registrationContext TEXT null,
	serviceDescription TEXT null
);