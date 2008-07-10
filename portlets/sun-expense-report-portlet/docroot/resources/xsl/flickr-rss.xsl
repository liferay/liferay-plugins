<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:dc="http://purl.org/dc/elements/1.1/"
  xmlns:atom="http://www.w3.org/2005/Atom"
  exclude-result-prefixes="atom">
      
  <xsl:output method="text" encoding="utf-8"/>

  <xsl:template match="text()"/>
  <xsl:template match="@*"/>
  
  <!-- process the feed -->
  <xsl:template match="atom:feed">
     {"items" : [<xsl:apply-templates/>]}
  </xsl:template>
  
  
 <xsl:template match="atom:entry">
        <xsl:variable name="id"><xsl:value-of select="atom:id"/></xsl:variable>
        <xsl:variable name="title"><xsl:value-of select="atom:title"/></xsl:variable>
        <xsl:variable name="date"><xsl:value-of select="dc:date.Taken"/></xsl:variable>
        <xsl:variable name="published"><xsl:value-of select="atom:published"/></xsl:variable>
        <xsl:variable name="link"><xsl:value-of select="atom:link/@href"/></xsl:variable>
        <xsl:variable name="author"><xsl:value-of select="atom:author/atom:name"/></xsl:variable>
        <xsl:variable name="authorURI"><xsl:value-of select="atom:author/atom:uri"/></xsl:variable>
        <xsl:variable name="category"><xsl:value-of select="atom:category/@term"/></xsl:variable>
        <xsl:variable name="content"><xsl:value-of select="normalize-space(atom:content)"/></xsl:variable>
        {
        "id" : "<xsl:value-of select="$id"/>",
        "title" : "<xsl:value-of select="$title"/>",
        "link" : "<xsl:value-of select="$link" />",
        "author" : "<xsl:value-of select="$author" />",
        "authorURI" : "<xsl:value-of select="$authorURI" />",
        "date" : "<xsl:value-of select="$date" />",
        "published" : "<xsl:value-of select="$published" />",
        "content" : "<xsl:value-of select="$content" />",
        "category" : "<xsl:value-of select="$category" />"
        }
        <xsl:if test="position() &lt; last() -1">, </xsl:if>
  </xsl:template>

</xsl:stylesheet>
