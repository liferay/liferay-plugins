create index IX_DC27633B on WSRP_WSRPConfiguredProducer (portalId, namespace);

create index IX_A2034709 on WSRP_WSRPConsumerRegistration (producerKey);
create index IX_555F06B4 on WSRP_WSRPConsumerRegistration (registrationHandle, producerKey);

create index IX_BAE7F7E0 on WSRP_WSRPPortlet (name);
create index IX_CBAD75A5 on WSRP_WSRPPortlet (producerEntityId);
create index IX_502B20AD on WSRP_WSRPPortlet (producerEntityId, portletHandle);

create index IX_8984B7CB on WSRP_WSRPProducer (instanceName);
create index IX_94E69299 on WSRP_WSRPProducer (portalId, namespace);