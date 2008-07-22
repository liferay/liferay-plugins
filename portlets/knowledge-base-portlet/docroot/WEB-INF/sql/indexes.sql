create index IX_9799EB4B on KB_KBArticle (companyId);
create index IX_50AAC88D on KB_KBArticle (groupId);
create index IX_4D38CA1 on KB_KBArticle (groupId, head);
create index IX_B2B097D on KB_KBArticle (groupId, head, parentTitle);
create index IX_4AA18D11 on KB_KBArticle (groupId, parentTitle);
create index IX_184C71DB on KB_KBArticle (groupId, title);
create index IX_EAF8836F on KB_KBArticle (groupId, title, head);
create index IX_8A06EA8D on KB_KBArticle (groupId, title, version);
create index IX_584F10D1 on KB_KBArticle (head);
create index IX_2D1BD34D on KB_KBArticle (head, parentTitle);
create index IX_344173AB on KB_KBArticle (title);
create index IX_5C78E93F on KB_KBArticle (title, head);
create index IX_D6CEF2BD on KB_KBArticle (title, version);
create index IX_7D031A57 on KB_KBArticle (uuid_);
create index IX_93550C33 on KB_KBArticle (uuid_, groupId);

create index IX_5EFF4389 on KB_KBArticleResource (groupId, title);
create index IX_1B41663D on KB_KBArticleResource (title);