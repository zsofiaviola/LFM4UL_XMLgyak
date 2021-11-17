<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		
		<html>
			<body>
				<h2>Hallgatók adatai</h2>
					<table border="1">
						<tr>
					        <th>ID</th>
					        <th>Keresztnev</th>
					        <th>Vezeteknev</th>
					        <th>Becenev</th>
					        <th>Kor</th>
					     </tr>
					      <xsl:for-each select="class/student">
						        <tr>
						       	 <td><xsl:value-of select="@id"></xsl:value-of></td>
						          <td><xsl:value-of select="keresztnev"></xsl:value-of></td>
						          <td><xsl:value-of select="vezeteknev"></xsl:value-of></td>
						          <td><xsl:value-of select="becenev"></xsl:value-of></td>
						          <td><xsl:value-of select="kor"></xsl:value-of></td>
						        </tr>
						      </xsl:for-each>
      					</table>
			</body>
		</html>
	
	</xsl:template>
</xsl:stylesheet>