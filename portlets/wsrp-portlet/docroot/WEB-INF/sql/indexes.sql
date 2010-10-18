create index IX_8F3348D on WSRP_WSRPConsumer (companyId);
create index IX_8B675499 on WSRP_WSRPConsumer (uuid_);

create index IX_82D5F2C9 on WSRP_WSRPConsumerPortlet (uuid_);
create index IX_1CEEF2AA on WSRP_WSRPConsumerPortlet (wsrpConsumerId);
create index IX_D5F95908 on WSRP_WSRPConsumerPortlet (wsrpConsumerId, portletHandle);

create index IX_DD08A671 on WSRP_WSRPProducer (companyId);
create index IX_19A9587D on WSRP_WSRPProducer (uuid_);
create unique index IX_DDBB58CD on WSRP_WSRPProducer (uuid_, groupId);