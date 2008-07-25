create index IX_9799EB4B on KB_KBArticle (companyId);
create index IX_50AAC88D on KB_KBArticle (groupId);
create index IX_BF58A84F on KB_KBArticle (groupId, head, template);
create index IX_184C71DB on KB_KBArticle (groupId, title);
create index IX_EAF8836F on KB_KBArticle (groupId, title, head);
create index IX_8A06EA8D on KB_KBArticle (groupId, title, version);
create index IX_BFF538F4 on KB_KBArticle (parentResourcePrimKey);
create index IX_572BD3C8 on KB_KBArticle (parentResourcePrimKey, head);
create index IX_B329C53E on KB_KBArticle (resourcePrimKey);
create index IX_46548092 on KB_KBArticle (resourcePrimKey, head);
create index IX_1FDDB68A on KB_KBArticle (resourcePrimKey, version);
create index IX_7D031A57 on KB_KBArticle (uuid_);
create index IX_93550C33 on KB_KBArticle (uuid_, groupId);

create index IX_5EFF4389 on KB_KBArticleResource (groupId, title);