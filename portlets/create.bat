@echo off

if "" == "%1" goto errorCreate

if "" == "%2" goto errorCreate

if "" == "%3" set PORTLET_FRAMEWORK=mvc
if not "" == "%3" set PORTLET_FRAMEWORK=%3

call ant -Dportlet.name=%1 -Dportlet.display.name=%2 -Dportlet.framework=%PORTLET_FRAMEWORK% create

rem call ant deploy

goto end

:errorCreate
	echo.
	echo Usage: create.bat hello-world "Hello World"
	echo.
	echo The first hello-world is your portlet id. A new directory will be created based
	echo on the portlet id.
	echo.
	echo The second "Hello World" is the portlet's display name. The quotation marks are
	echo only needed because there is a space in the display name.
	echo.
	echo A third value can be passed to specify the portlet framework to use. Valid
	echo values are "jsf", "icefaces", "liferay_faces_alloy", "mvc", "primefaces",
	echo "richfaces", "spring_mvc", or "vaadin". The default value is "mvc". The
	echo quotation marks are not needed.

	goto end

:end