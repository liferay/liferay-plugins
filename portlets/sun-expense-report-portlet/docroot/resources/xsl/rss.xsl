<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
  xmlns:dc="http://purl.org/dc/elements/1.1/"
  xmlns:content="http://purl.org/rss/1.0/modules/content/"
  xmlns:rss="http://purl.org/rss/1.0/"
  xmlns:oa="http://purl.org/atom/ns#"
  xmlns:atom="http://www.w3.org/2005/Atom"
  exclude-result-prefixes="rss rdf dc">
    
  <!-- "number" parameter can be passed. default = 5 enteries -->
  <xsl:param name="count">10</xsl:param>
  
  <xsl:output method="text" encoding="utf-8"/>

  
  <!-- process for top channel -->
  <xsl:template match="/">
      <xsl:apply-templates select="rss" />
      <xsl:apply-templates select="rdf:RDF" />
      <xsl:apply-templates select="atom:feed" />
      <xsl:apply-templates select="oa:feed" />
  </xsl:template>
  
    <!-- atom 0.3 -->
  <xsl:template match="oa:feed">
      <xsl:variable name="currentTitle" select="//oa:title" />
       <xsl:variable name="currentLink" select="//oa:link[@rel='alternate']/@href" />
      <xsl:variable name="currentDate" select="//oa:issued" />
      <xsl:variable name="currentDesc" select="//oa:summary" />

          { "dataType" : "jmakiRSS",
            "channel": {
            "title" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$currentTitle"/></xsl:call-template>",
            "link" : "<xsl:value-of select="$currentLink" />",
            "date" : "<xsl:value-of select="$currentDate" />",
            "description" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$currentDesc"/></xsl:call-template>",
            "items" : [
            <xsl:apply-templates select="oa:entry" />
            ]}}
  </xsl:template>
  <!-- process each element item -->
  <xsl:template match="oa:entry">
      <xsl:if test="($count = 0) or (position() &lt;= $count)">
          
          <xsl:call-template name="itemJson">
              <xsl:with-param name="link"><xsl:value-of select="oa:link[@rel='alternate']/@href"/></xsl:with-param>
              <xsl:with-param name="title"><xsl:value-of select="oa:title"/></xsl:with-param>
              <xsl:with-param name="date"><xsl:value-of select="oa:issued"/></xsl:with-param>
              <xsl:with-param name="description"><xsl:value-of select="oa:summary"/></xsl:with-param>
          </xsl:call-template>
      </xsl:if>
  </xsl:template>
  
  <!-- RSS 1.0 -->
  <xsl:template match="rdf:RDF">
      <xsl:variable name="currentTitle" select="//rss:title" />
      <xsl:variable name="currentLink" select="//rss:link" />
      <xsl:variable name="currentDate" select="//dc:date" />
      <xsl:variable name="currentDesc" select="//rss:description" />

          { "dataType" : "jmakiRSS",
            "channel": {
           "title" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$currentTitle"/></xsl:call-template>",
            "link" : "<xsl:value-of select="$currentLink" />",
            "date" : "<xsl:value-of select="$currentDate" />",
            "description" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$currentDesc"/></xsl:call-template>",
            "items" : [
            <xsl:apply-templates select="rss:item" />
            ]}}
  </xsl:template>
  <!-- process each element item -->
  <xsl:template match="rss:item">
      <xsl:if test="($count = 0) or (position() &lt;= $count)">
          
          <xsl:call-template name="itemJson">
              <xsl:with-param name="link"><xsl:value-of select="rss:link"/></xsl:with-param>
              <xsl:with-param name="title"><xsl:value-of select="rss:title"/></xsl:with-param>
              <xsl:with-param name="date"><xsl:value-of select="dc:date"/></xsl:with-param>
              <xsl:with-param name="description"><xsl:value-of select="rss:description"/></xsl:with-param>
<xsl:with-param name="content"><xsl:value-of select="rss:content"/></xsl:with-param>              
          </xsl:call-template>
      </xsl:if>
  </xsl:template>
  
  <!-- Atom 1.0 -->
  <xsl:template match="atom:feed">
      <xsl:variable name="currentTitle" select="//atom:title" />
      <xsl:variable name="currentLink" select="//atom:link[@rel='alternate']/@href" />
      <xsl:variable name="currentDate" select="//atom:published" />
      <xsl:variable name="currentDesc" select="//rss:content" />      

          { "dataType" : "jmakiRSS",
            "channel": {
            "title" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$currentTitle"/></xsl:call-template>",
            "link" : "<xsl:value-of select="$currentLink" />",
            "date" : "<xsl:value-of select="$currentDate" />",
            "description" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$currentDesc"/></xsl:call-template>",
            "items" : [
            <xsl:apply-templates select="atom:entry" />
            ]}}
  </xsl:template>
  <!-- process each element item -->
  <xsl:template match="atom:entry">
      <xsl:if test="($count = 0) or (position() &lt;= $count)">
          
          <xsl:call-template name="itemJson">
              <xsl:with-param name="link"><xsl:value-of select="atom:link[@rel='alternate']/@href"/></xsl:with-param>
              <xsl:with-param name="title"><xsl:value-of select="atom:title"/></xsl:with-param>
              <xsl:with-param name="date"><xsl:value-of select="atom:published"/></xsl:with-param>
              <xsl:with-param name="description"><xsl:value-of select="atom:content"/></xsl:with-param>
          </xsl:call-template>
      </xsl:if>
  </xsl:template>  
  
  <!-- RSS 2.0 -->
  <xsl:template match="rss">
      <xsl:variable name="currentTitle" select="//title" />
      <xsl:variable name="currentLink" select="//link" />
      <xsl:variable name="currentDate" select="//pubDate" />
      <xsl:variable name="currentDesc" select="//description" />
      <xsl:variable name="currentCon" select="//content:encoded" />
   
      { "dataType" : "jmakiRSS",
        "channel": {
        "title" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$currentTitle"/></xsl:call-template>",
        "link" : "<xsl:value-of select="$currentLink" />",
        "date" : "<xsl:value-of select="$currentDate" />",
        "description" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$currentDesc"/></xsl:call-template>",
             
        "items" : [
        <xsl:apply-templates select="channel/item" />
        ]}}
  </xsl:template>
  
  <!-- process each element item -->
  <xsl:template match="item">
      <xsl:if test="($count = 0) or (position() &lt;= $count)">
      
          <xsl:call-template name="itemJson">
              <xsl:with-param name="link"><xsl:value-of select="link"/></xsl:with-param>
              <xsl:with-param name="title"><xsl:value-of select="title"/></xsl:with-param>
              <xsl:with-param name="date"><xsl:value-of select="pubDate"/></xsl:with-param>
              <xsl:with-param name="description"><xsl:value-of select="description"/></xsl:with-param>
              <xsl:with-param name="content"><xsl:value-of select="content:encoded"/></xsl:with-param>              
          </xsl:call-template>
      </xsl:if>
  </xsl:template>
  
  <xsl:template name="itemJson">
      <xsl:param name="link">defaultLink</xsl:param>
      <xsl:param name="title">defaultTitle</xsl:param>
      <xsl:param name="date">defaultDate</xsl:param>
      <xsl:param name="description">defaultDescription</xsl:param>
      <xsl:param name="content"></xsl:param>
      {
        "title" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$title"/></xsl:call-template>",
        "link" : "<xsl:value-of select="$link" />",
        "date" : "<xsl:value-of select="$date" />", 
        "description" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$description"/></xsl:call-template>",
        
        "content" : "<xsl:call-template name="escapeJson"><xsl:with-param name="string" select="$content"/></xsl:call-template>"       
      }

      <xsl:if test="($count != 0)  and (position()  &lt; last()) and (position() &lt; $count)">,</xsl:if>
  </xsl:template>

    <!-- for JSON, translate disallowed characters (quot, backslash) and clean white space.
    -->
   <xsl:template name="escapeJson">
       <xsl:variable name="quot">"</xsl:variable>
       <xsl:variable name="backslash">\</xsl:variable>
         <xsl:param name="string"/>
         <xsl:value-of select="normalize-space(translate(translate($string, $backslash, ''), $quot, ''))"/>
</xsl:template>
  
</xsl:stylesheet>
