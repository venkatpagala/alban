<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:fn="http://www.w3.org/2005/02/xpath-functions"
				xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
				xmlns:nabla="http://nabla.mobi/nabla"
				extension-element-prefixes="nabla">

  	<xsl:import href="filesGenerator.xsl" />

	<xsl:function name="nabla:filepath_to_uri" as="xs:string">
	  <xsl:param name="generationDirectory" as="xs:string"/>
	  <!-- Conversion steps:
	       1) replace backslashes with slashes
	       2) add three slashes before drive letter
	       3) prefix result with "file:" -->
	  <xsl:value-of
	    select="concat(
	              'file:',
	              replace(
	                  replace(
	                    replace(
	                      replace($generationDirectory, '\\', '/'),
	                      '%',
	                      '%25'),
	                    '#',
	                    '%23'),
	                '^([A-Za-z]):',
	                '///$1:'))"/>
	</xsl:function>

</xsl:stylesheet>
