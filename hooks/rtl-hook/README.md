# RTL Hook

## Motivation

Middle eastern languages such as Hebrew and Arabic are written predominantly right-to-left. Languages written in left-to-right scripts are often mixed in, so the complete document is bidirectional in nature, a mix of both right-to-left (RTL) and left-to-right (LTR) writing. Thus, sites written in the Hebrew and Arabic languages are often referred to as bidirectional sites. 

Since the html tag contains information [about the language and the direction of the content](http://www.w3.org/TR/html401/struct/dirlang.html), browsers automatically adapt the page alignment. However there may be some elements on the page which are absolutely positioned by style and won't be automatically mirrored by the browser. In this case, you must adapt your style sheets to both directions. Usually style sheets are design for LTR languages, so the challenge is to create an alternative  version of the CSS for RTL languages.

The RTL Hook is a Liferay Plugin that automatically adapts Liferay Portal styles for RTL languages. Just deploy it and check how the your site is mirrored for RTL languages.

<img title="Site Administration view for LTR language" src="https://github.com/liferay-plugins/hooks/rtl-hook/raw/6.2.x/images/rtl-hook-001.png" />
<img title="Site Administration view for RTL language" src="https://github.com/liferay-plugins/hooks/rtl-hook/raw/6.2.x/images/rtl-hook-002.png" />

## Support for plugins

You can use the RTL Hook to provide support for RTL languages in your plugins, too. Just follow these steps:

### Setup your plugin source project

* Make sure the RTL Hook is deployed in your application server
* Copy all .jar files from WEB-INF/lib folder of the rtl-hook webapp of your application server to the docroot/WEB-INF/lib folder of your plugin source project.
* Add or edit the WEB-INF/web.xml file of your plugin source project and add the following lines before the `</web-app>` closing tag:

```xml
<filter>
	<filter-name>Dynamic CSS Filter</filter-name>
	<filter-class>com.liferay.rtl.servlet.filters.dynamiccss.DynamicCSSFilter</filter-class>
</filter>
<filter-mapping>
	<filter-name>Dynamic CSS Filter</filter-name>
	<url-pattern>*.css</url-pattern>
</filter-mapping>
```

* Finally, edit the build.xml file of your plugin source project and add the following lines before the `</project>` closing tag:

```xml
<target name="build-css" depends="clean-rtl-css, build-common-theme.build-css, build-rtl-css" />

<target name="build-rtl-css">
	<java
		classname="com.liferay.rtl.tools.RtlCssBuilder"
		classpathref="plugin.classpath"
		fork="true"
		newenvironment="true"
	>
		<jvmarg value="-Dliferay.lib.portal.dir=${app.server.lib.portal.dir}" />
		<arg value="sass.dir=/" />
		<arg value="sass.docroot.dir=${basedir}/docroot" />
	</java>
</target>

<target name="clean-rtl-css">
	<delete failonerror="false" includeemptydirs="true">
		<fileset dir="${basedir}/docroot" includes="**/.sass-cache/*_rtl.*" />
	</delete>
</target>
```

### Generate your RTL CSS files

* Deploy your plugin through the `ant deploy` command in the root folder of your plugin source project.
* Check the log messages and notice that it now includes some `Generated RTL cache for ...` lines.
* Check the `css/.sass-cache` folder of your plugin. It now contains a *_rtl.css version for each *.css file.

### Define custom CSS for RTL languages 

Through the steps described in the previous section, the RTL Hook automatically generates the RTL version of your CSS files by appliying some rules (e.g. change from `margin-left` to `margin-right`). However, you may want to extend this generated RTL version to define some custom styles for RTL languages. You can achieve this by going through the following steps:

* Add a CSS file in the same location and with the same name as the original one but with the suffix `_rtl`. E.g. for a `main.css` file, create a `main_rtl.css` file.
* Edit the _rtl file and add **only** the lines that define your custom styles for RTL languages.
* Deploy your plugin as explained in the previous section.
* Check the `css/.sass-cache` folder of your plugin. Notice that the generated _rtl.css file in this folder contains not only the automatically generated CSS from the original file, but also your custom CSS code at the end. 

### Validate

* Browse your plugin (e.g. add your portlet to a page or apply your theme to a site or page and go to it).
* Add the Language portlet to the page and change the current language to an RTL language (e.g. hebrew).
* Your plugin styles are automatically adapted, as well as any other portal element.
* In case you have defined any custom styles as described in the previous section, they are also applied.