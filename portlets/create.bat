@echo off

if "" == "%1" goto errorCreate
if "" == "%2" goto errorCreate

call ant -Dportlet.name=%1 -Dportlet.display.name=%2 create

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

	goto end

:end