<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<head>
				<script type="text/javascript">
					<![CDATA[
						window.onload = function() {
							var cellContents = {};

							function hideCell(rows, rowIndex, tagName, columnIndex) {
								var row = rows[rowIndex];

								var cells = row.getElementsByTagName(tagName);

								var cell = cells[columnIndex];

								if (cell) {
									if (cell.innerHTML == '-') {
										cell.innerHTML = cellContents[rowIndex + '_' + columnIndex];
									}
									else {
										cellContents[rowIndex + '_' + columnIndex] = cell.innerHTML;

										cell.innerHTML = '-';
									}
								}
							}

							function hideColumn(rows, columnIndex) {
								return function(e) {
									for (var i = 0; i < rows.length; i++) {
										hideCell(rows, i, 'th', columnIndex);
										hideCell(rows, i, 'td', columnIndex);
									}
								}
							}

							function initTable() {
								var table = document.getElementById('pluginsTable');

								var rows = table.getElementsByTagName('tr');

								var row = rows[0];

								var cells = row.getElementsByTagName('th');

								for (var i = 0; i < cells.length; i++) {
									var cell = cells[i];

									cell.onclick = hideColumn(rows, i);
								}
							}

							initTable();
						}
					]]>
				</script>
			</head>

			<body>
				<table border="1" cellpadding="4" cellspacing="0" frame="box" id="pluginsTable" rules="all">
				<tr>
					<th nowrap="nowrap">
						Artifact ID
					</th>
					<th nowrap="nowrap">
						Name
					</th>
					<th nowrap="nowrap">
						Type
					</th>
					<th nowrap="nowrap">
						Tags
					</th>
					<th>
						Short Description
					</th>
					<th nowrap="nowrap">
						Change Log
					</th>
					<th nowrap="nowrap">
						Page URL
					</th>
					<th nowrap="nowrap">
						Author
					</th>
					<th nowrap="nowrap">
						Licenses
					</th>
					<th nowrap="nowrap">
						Include in Bundle
					</th>
					<th nowrap="nowrap">
						Experimental
					</th>
					<th nowrap="nowrap">
						Marketplace
					</th>
					<th nowrap="nowrap">
						Parent App
					</th>
					<th nowrap="nowrap">
						Standalone App
					</th>
					<th nowrap="nowrap">
						Supported
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
		<tr onmouseout="this.bgColor = '#FFFFFF';" onmouseover="this.bgColor = '#FFFFCC';">
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
			<td>
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

			<xsl:apply-templates select="releng" />
		</tr>
	</xsl:template>

	<xsl:template match="releng">
		<td>
			<xsl:choose>
				<xsl:when test="bundle = 'true'">
					Yes
				</xsl:when>
				<xsl:otherwise>
					No
				</xsl:otherwise>
			</xsl:choose>
		</td>
		<td>
			<xsl:choose>
				<xsl:when test="experimental = 'true'">
					Yes
				</xsl:when>
				<xsl:otherwise>
					No
				</xsl:otherwise>
			</xsl:choose>
		</td>
		<td>
			<xsl:choose>
				<xsl:when test="marketplace = 'true'">
					Yes
				</xsl:when>
				<xsl:otherwise>
					No
				</xsl:otherwise>
			</xsl:choose>
		</td>
		<td>
			<xsl:choose>
				<xsl:when test="parent-app = 'true'">
					Yes
				</xsl:when>
				<xsl:otherwise>
					No
				</xsl:otherwise>
			</xsl:choose>
		</td>
		<td>
			<xsl:choose>
				<xsl:when test="standalone-app = 'true'">
					Yes
				</xsl:when>
				<xsl:otherwise>
					No
				</xsl:otherwise>
			</xsl:choose>
		</td>
		<td>
			<xsl:choose>
				<xsl:when test="supported = 'true'">
					Yes
				</xsl:when>
				<xsl:otherwise>
					No
				</xsl:otherwise>
			</xsl:choose>
		</td>
	</xsl:template>
</xsl:stylesheet>