<xsl:stylesheet version='1.0'
    xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
	xmlns:yn="urn:yahoo:maps" >

<xsl:output method="text" encoding="UTF-8"/>
<xsl:template match="text()"/>
<xsl:template match="@*"/>
<xsl:param name="callback"></xsl:param>

<xsl:template match="yn:ResultSet">
  <xsl:if test="($callback)">
    <xsl:value-of select="$callback"/>(
  </xsl:if>
        {"coordinates": [
        <xsl:apply-templates/>
        ]}
      <xsl:if test="($callback)">);</xsl:if>
     </xsl:template>

  <xsl:template match="yn:Result">
           {
            "address" : "<xsl:value-of select="yn:Address"/>",
            "city" : "<xsl:value-of select="yn:City"/>",
            "state" : "<xsl:value-of select="yn:State"/>",
            "zip" : "<xsl:value-of select="yn:Zip"/>",
            "latitude" : "<xsl:value-of select="yn:Latitude"/>",
            "longitude" : "<xsl:value-of select="yn:Longitude" />"
	   }
           <xsl:if test="position() &lt; last()">,</xsl:if>
  </xsl:template>
</xsl:stylesheet>