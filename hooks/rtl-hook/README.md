# RTL Hook

Middle eastern languages such as Hebrew and Arabic are written predominantly right-to-left. Languages written in left-to-right scripts are often mixed in, so the complete document is bidirectional in nature, a mix of both right-to-left (RTL) and left-to-right (LTR) writing. Thus, sites written in the Hebrew and Arabic languages are often referred to as bidirectional sites. 

Since the html tag contains information [about the language and the direction of the content](http://www.w3.org/TR/html401/struct/dirlang.html), browsers automatically adapt the page alignment. However there may be some elements on the page which are absolutely positioned by style and won't be automatically mirrowed by the browser. In this case, you must adapt your style sheets to both directions. Usually style sheets are design for LTR languages, so the challenge is to create an alternative  version of the CSS for RTL languages. 

The RTL Hook is a Liferay Plugin that automatically adapts Liferay Portal styles for RTL languages. Just deploy it and check how the your site is mirrowed for RTL languages.

<img title="Site Administration view for LTR language" src="https://github.com/liferay-plugins/hooks/rtl-hook/raw/6.2.x/images/rtl-hook-001.png" />
<img title="Site Administration view for RTL language" src="https://github.com/liferay-plugins/hooks/rtl-hook/raw/6.2.x/images/rtl-hook-002.png" />