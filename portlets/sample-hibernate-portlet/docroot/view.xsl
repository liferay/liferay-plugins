<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
	<xsl:output method="html" omit-xml-declaration="yes" />

	<xsl:template match="/">
		<table border="1" cellpadding="4" cellspacing="0" width="100%">
		<tr>
			<td>
				<b>Food Item ID</b>
			</td>
			<td>
				<b>Name</b>
			</td>
			<td>
				<b>Points</b>
			</td>
		</tr>

		<xsl:for-each select="result/food-item">
			<tr>
				<td>
					<xsl:value-of select="food-item-id" />
				</td>
				<td>
					<xsl:value-of select="name" />
				</td>
				<td>
					<xsl:value-of select="points" />
				</td>
			</tr>
		</xsl:for-each>

		</table>
	</xsl:template>
</xsl:stylesheet>