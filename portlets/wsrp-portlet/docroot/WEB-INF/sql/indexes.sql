create index IX_8F3348D on WSRP_WSRPConsumer (companyId);
create index IX_DF2205EF on WSRP_WSRPConsumer (uuid_, companyId);

create index IX_AC53A9BF on WSRP_WSRPConsumerPortlet (uuid_, companyId);
create index IX_D5F95908 on WSRP_WSRPConsumerPortlet (wsrpConsumerId, portletHandle);

create index IX_DD08A671 on WSRP_WSRPProducer (companyId);
create index IX_22C76B8B on WSRP_WSRPProducer (uuid_, companyId);
create unique index IX_DDBB58CD on WSRP_WSRPProducer (uuid_, groupId);