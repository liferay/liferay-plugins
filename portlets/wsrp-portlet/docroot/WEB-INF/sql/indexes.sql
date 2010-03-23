create index IX_8F3348D on WSRP_WSRPConsumer (companyId);

create index IX_1CEEF2AA on WSRP_WSRPConsumerPortlet (wsrpConsumerId);
create index IX_D5F95908 on WSRP_WSRPConsumerPortlet (wsrpConsumerId, portletHandle);

create index IX_DD08A671 on WSRP_WSRPProducer (companyId);