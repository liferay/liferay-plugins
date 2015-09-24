create index IX_8F3348D on WSRP_WSRPConsumer (companyId);
create index IX_DF2205EF on WSRP_WSRPConsumer (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_AC53A9BF on WSRP_WSRPConsumerPortlet (uuid_[$COLUMN_LENGTH:75$], companyId);
create index IX_D5F95908 on WSRP_WSRPConsumerPortlet (wsrpConsumerId, portletHandle[$COLUMN_LENGTH:255$]);

create index IX_DD08A671 on WSRP_WSRPProducer (companyId);
create index IX_22C76B8B on WSRP_WSRPProducer (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_DDBB58CD on WSRP_WSRPProducer (uuid_[$COLUMN_LENGTH:75$], groupId);