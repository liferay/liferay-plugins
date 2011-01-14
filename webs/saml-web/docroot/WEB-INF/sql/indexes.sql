create index IX_1A77E1E3 on SAML_SPSession (ssoSessionId);
create index IX_B4FF9AD0 on SAML_SPSession (ssoSessionId, issuer);

create index IX_1C8AF4D3 on SAML_SSOSession (key_);