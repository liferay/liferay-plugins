<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
      
  <xsl:output method="text" encoding="utf-8"/>

  <xsl:template match="text()"/>
  <xsl:template match="@*"/>
  
  <!-- process the rest response -->
  <xsl:template match="rsp">
     {"photos" : {<xsl:apply-templates select="photos" />}}
  </xsl:template>
  
 <xsl:template match="photos">"page" : "<xsl:value-of select="@page" />" , "pages" : "<xsl:value-of select="@pages" />", "perpage" : "<xsl:value-of select="@perpage" />" , "total": "<xsl:value-of select="@total" />", "items": [<xsl:apply-templates select="photo" />]</xsl:template>
   
 <xsl:template match="photo">
    <xsl:variable name="id" select="@id" />
      <xsl:variable name="secret" select="@secret" />
      <xsl:variable name="server" select="@server" />
      <xsl:variable name="owner" select="@owner" />
      <xsl:variable name="quot">"</xsl:variable>
      <xsl:variable name="apos">'</xsl:variable>
        {
        "id" : "<xsl:value-of select="$id"/>",
        "title" : "<xsl:value-of select="translate(@title, $quot , $apos)" />",
        "owner" : "<xsl:value-of select="@owner" />",
        "url" : "http://www.flickr.com/photos/<xsl:value-of select="@owner" />/<xsl:value-of select="$id"/>",
        "smallURL" : "http://static.flickr.com/<xsl:value-of select="$server" />/<xsl:value-of select="$id" />_<xsl:value-of select="$secret" />_s.jpg",
        "mediumURL" : "http://static.flickr.com/<xsl:value-of select="$server" />/<xsl:value-of select="$id" />_<xsl:value-of select="$secret" />_m.jpg",
        "largeURL" : "http://static.flickr.com/<xsl:value-of select="$server" />/<xsl:value-of select="$id" />_<xsl:value-of select="$secret" />_b.jpg",
        "originalURL" : "http://static.flickr.com/<xsl:value-of select="$server" />/<xsl:value-of select="$id" />_<xsl:value-of select="$secret" />_o.jpg"
        }
        <xsl:if test="position() &lt; last()">, </xsl:if>
  </xsl:template>
</xsl:stylesheet>