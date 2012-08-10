<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet
	version="2.0"
	xmlns:str="http://xsltsl.org/string"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
>
	<xsl:import href="http://xsltsl.sourceforge.net/modules/stdlib.xsl" />

	<xsl:template match="/">
		<html>
			<head>
				<script src="http://yui.yahooapis.com/3.4.1/build/yui/yui-min.js" type="text/javascript"></script>
				<script type="text/javascript">
					YUI().use(
						'datatable',
						function (Y) {
							var pluginsCols = [
								{
									key: 'Artifact ID',
									sortable: true
								},
								{
									key: 'Name',
									sortable: true
								},
								{
									key: 'Type',
									sortable: true
								},
								{
									key: 'Category',
									sortable: true
								},
								{
									key: 'Tags',
									sortable: true
								},
								{
									key: 'Short Description',
									sortable: true
								},
								{
									key: 'Long Description',
									sortable: true
								},
								{
									key: 'Change Log',
									sortable: true
								},
								{
									key: 'Page URL',
									sortable: true
								},
								{
									key: 'Demo URL',
									sortable: true
								},
								{
									key: 'Support URL',
									sortable: true
								},
								{
									key: 'Author',
									sortable: true
								},
								{
									key: 'Licenses',
									sortable: true
								},
								{
									key: 'Liferay Versions',
									sortable: true
								},
								{
									key: 'Bundle',
									sortable: true
								},
								{
									key: 'Dependent Apps',
									sortable: true
								},
								{
									key: 'Labs',
									sortable: true
								},
								{
									key: 'Marketplace',
									sortable: true
								},
								{
									key: 'Public',
									sortable: true
								},
								{
									key: 'Supported',
									sortable: true
								},
								{
									key: 'Icon',
									sortable: false
								},
								{
									key: 'Screenshots',
									sortable: false
								}
							];

							var pluginsData = [
								<xsl:apply-templates select="plugins-summary/plugin" />
							];

							var pluginsDataTable = new Y.DataTable.Base(
								{
									columnset: pluginsCols,
									recordset: pluginsData,
									plugins: [Y.Plugin.DataTableSort]
								}
							);

							pluginsDataTable.render("#plugins");

							var authorsCols = [
								{
									key: 'Authors',
									sortable: true
								}
							];

							var authorsData = [
								<xsl:apply-templates select="plugins-summary/author" />
							];

							var authorsDataTable = new Y.DataTable.Base(
								{
									columnset: authorsCols,
									recordset: authorsData,
									authors: [Y.Plugin.DataTableSort]
								}
							);

							authorsDataTable.render("#authors");

							var licensesCols = [
								{
									key: 'Licenses',
									sortable: true
								}
							];

							var licensesData = [
								<xsl:apply-templates select="plugins-summary/license" />
							];

							var licensesDataTable = new Y.DataTable.Base(
								{
									columnset: licensesCols,
									recordset: licensesData,
									licenses: [Y.Plugin.DataTableSort]
								}
							);

							licensesDataTable.render("#licenses");
						}
					);
				</script>
			</head>

			<body class="yui3-skin-sam">
				<div id="plugins"></div>

				<br />

				<div id="authors"></div>

				<br />

				<div id="licenses"></div>
			</body>
		</html>
	</xsl:template>

	<xsl:template match="author">
		{
			'Authors': '<xsl:value-of select="current()" />'
		}

		<xsl:if test="position() != last()">
			,
		</xsl:if>
	</xsl:template>

	<xsl:template match="license">
		{
			'Licenses': '<xsl:value-of select="current()" />'
		}

		<xsl:if test="position() != last()">
			,
		</xsl:if>
	</xsl:template>

	<xsl:template match="plugin">
		{
			'Artifact ID': '<xsl:value-of select="artifact-id" />',
			'Name': '<xsl:value-of select="name" />',
			'Type': '<xsl:value-of select="type" />',
			'Category': '<xsl:value-of select="releng/category" />',
			'Tags': '<xsl:value-of select="tags" />',

			'Short Description':
				'<xsl:call-template name="str:subst">
					<xsl:with-param name="text" select="normalize-space(short-description) "/>
					<xsl:with-param name="replace">'</xsl:with-param>
					<xsl:with-param name="with">\'</xsl:with-param>
				</xsl:call-template>',

			'Long Description':
				'<xsl:call-template name="str:subst">
					<xsl:with-param name="text" select="normalize-space(long-description) "/>
					<xsl:with-param name="replace">'</xsl:with-param>
					<xsl:with-param name="with">\'</xsl:with-param>
				</xsl:call-template>',

			'Change Log': '<xsl:value-of select="change-log" />',
			'Page URL': '<xsl:value-of select="page-url" />',
			'Demo URL': '<xsl:value-of select="releng/demo-url" />',
			'Support URL': '<xsl:value-of select="releng/support-url" />',
			'Author': '<xsl:value-of select="author" />',
			'Licenses': '<xsl:value-of select="licenses" />',
			'Liferay Versions': '<xsl:value-of select="liferay-versions" />',

			<xsl:apply-templates select="releng" />
		}

		<xsl:if test="position() != last()">
			,
		</xsl:if>
	</xsl:template>

	<xsl:template match="releng">
		'Bundle':
			'<xsl:choose>
				<xsl:when test="bundle = 'true'">Yes</xsl:when>
				<xsl:otherwise>No</xsl:otherwise>
			</xsl:choose>',

		'Compatibility': '<xsl:value-of select="compatibility" />',

		'Dependent Apps': '<xsl:value-of select="dependent-apps" />',

		'Icon': '<xsl:if test="icon != ''"><img height="50"><xsl:attribute name="src"><xsl:value-of select="icon" /></xsl:attribute></img></xsl:if>',

		'Labs':
			'<xsl:choose>
				<xsl:when test="labs = 'true'">Yes</xsl:when>
				<xsl:otherwise>No</xsl:otherwise>
			</xsl:choose>',

		'Marketplace':
			'<xsl:choose>
				<xsl:when test="marketplace = 'true'">Yes</xsl:when>
				<xsl:otherwise>No</xsl:otherwise>
			</xsl:choose>',

		'Public':
			'<xsl:choose>
				<xsl:when test="public = 'true'">Yes</xsl:when>
				<xsl:otherwise>No</xsl:otherwise>
			</xsl:choose>',

		'Screenshots': '<nobr><xsl:for-each select="screenshot"><a target="_blank"><xsl:attribute name="href"><xsl:value-of select="current()" /></xsl:attribute><img height="50"><xsl:attribute name="src"><xsl:value-of select="current()" /></xsl:attribute></img></a></xsl:for-each></nobr>',

		'Supported':
			'<xsl:choose>
				<xsl:when test="supported = 'true'">Yes</xsl:when>
				<xsl:otherwise>No</xsl:otherwise>
			</xsl:choose>'
	</xsl:template>
</xsl:stylesheet>