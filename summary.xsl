<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>

		<body>

		<table border="1">
		<tr>
			<th>
				Artifact ID
			</th>
			<th>
				Name
			</th>
			<th>
				Type
			</th>
			<th>
				Tags
			</th>
			<th>
				Short Description
			</th>
			<th>
				Change Log
			</th>
			<th>
				Page URL
			</th>
			<th>
				Author
			</th>
			<th>
				Licenses
			</th>
		</tr>

		<xsl:apply-templates select="plugins-summary/plugin" />

		</table>

		<br />

		<table cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">
				<table border="1">
				<tr>
					<th>
						Unique Authors
					</th>
				</tr>

				<xsl:apply-templates select="plugins-summary/author" />

				</table>
			</td>
			<td style="padding-left: 10px"></td>
			<td valign="top">
				<table border="1">
				<tr>
					<th>
						Unique Licenses
					</th>
				</tr>

				<xsl:apply-templates select="plugins-summary/license" />

				</table>
			</td>
		</tr>
		</table>

		</body>

		</html>
	</xsl:template>

	<xsl:template match="author">
		<tr>
			<td nowrap="nowrap">
				<xsl:value-of select="current()" />
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="license">
		<tr>
			<td nowrap="nowrap">
				<xsl:value-of select="current()" />
			</td>
		</tr>
	</xsl:template>

	<xsl:template match="plugin">
		<tr>
			<td nowrap="nowrap">
				<xsl:value-of select="artifact-id" />
			</td>
			<td nowrap="nowrap">
				<xsl:value-of select="name" />
			</td>
			<td nowrap="nowrap">
				<xsl:value-of select="type" />
			</td>
			<td nowrap="nowrap">
				<xsl:value-of select="tags" />
			</td>
			<td nowrap="nowrap">
				<xsl:value-of select="short-description" />
			</td>
			<td nowrap="nowrap">
				<xsl:value-of select="change-log" />
			</td>
			<td nowrap="nowrap">
				<xsl:value-of select="page-url" />
			</td>
			<td nowrap="nowrap">
				<xsl:value-of select="author" />
			</td>
			<td nowrap="nowrap">
				<xsl:value-of select="licenses" />
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>