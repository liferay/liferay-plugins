<!--
    XSL Stylesheet for converting Yahoo! Weather RSS feed
    into a JSON data structure.
    
    @author Ahmad M. Zawawi <ahmad.zawawi@gmail.com>
-->
<xsl:stylesheet version='1.0'
                xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
                xmlns:yweather="http://xml.weather.yahoo.com/ns/rss/1.0" 
                xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#"
                xmlns:fn="http://www.w3.org/2005/02/xpath-functions">
    <xsl:output method="text" encoding="UTF-8"/>
    <xsl:template match="channel">
        {"data": {
        <xsl:param name="title" select="title"/>
        
        <xsl:if test="title = 'Yahoo! Weather - Error'">
            ok: false
        </xsl:if>
        <xsl:if test="title != 'Yahoo! Weather - Error'">
            ok: true,
            lastUpdated: "<xsl:value-of select="lastBuildDate"/>",
            city: "<xsl:value-of select="yweather:location/@city"/>",
            region: "<xsl:value-of select="yweather:location/@region"/>",
            country: "<xsl:value-of select="yweather:location/@country"/>",
            temparature_unit: "<xsl:value-of select="yweather:units/@temperature"/>",
            distance_unit: "<xsl:value-of select="yweather:units/@distance"/>",
            pressure_unit: "<xsl:value-of select="yweather:units/@pressure"/>",
            speed_unit: "<xsl:value-of select="yweather:units/@speed"/>",
            chill: <xsl:value-of select="yweather:wind/@chill"/>,
            direction: <xsl:value-of select="yweather:wind/@direction"/>,
            speed: <xsl:value-of select="yweather:wind/@speed"/>,
            humidity: <xsl:value-of select="yweather:atmosphere/@humidity"/>,
            visibility: <xsl:value-of select="yweather:atmosphere/@visibility"/>,
            pressure: <xsl:value-of select="yweather:atmosphere/@pressure"/>,
            rising: <xsl:value-of select="yweather:atmosphere/@rising"/>,
            sunrise: "<xsl:value-of select="yweather:astronomy/@sunrise"/>",
            sunset: "<xsl:value-of select="yweather:astronomy/@sunset"/>",
            latitude:    <xsl:value-of select="item/geo:lat"/>,
            longitude:    <xsl:value-of select="item/geo:long"/>,
                            
            <xsl:for-each select="item">forecast: [{
                date: "<xsl:value-of select="yweather:condition/@date"/>",
                text: "<xsl:value-of select="yweather:condition/@text"/>",
                code: <xsl:value-of select="yweather:condition/@code"/>,
                temparature: "<xsl:value-of select="yweather:condition/@temp"/>",
                <!-- Ugly hack to escape ' into " & remove newlines -->
                <xsl:variable name="quot">"</xsl:variable>
                <xsl:variable name="apos">'</xsl:variable> 
                <xsl:variable name="d1" select="translate(description, $quot, $apos )"/>
                <xsl:variable name="d2" select="translate($d1,'&#xA;','')"/>
                description: "<xsl:value-of select="$d2"/>"}, 
                
                <xsl:for-each select="yweather:forecast">{
                    day: "<xsl:value-of select="@day"/>",
                    date: "<xsl:value-of select="@date"/>",
                    text: "<xsl:value-of select="@text"/>",
                    code: <xsl:value-of select="@code"/>, 
                    low: <xsl:value-of select="@low"/>,
                    high: <xsl:value-of select="@high"/>
                    }<xsl:if test="position() &lt; last()">,</xsl:if>
                </xsl:for-each>
                ]
            </xsl:for-each>
            
        </xsl:if>
        
        }}
        
    </xsl:template>
</xsl:stylesheet>
