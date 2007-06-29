@echo off

if "" == "%1" goto errorCreate
if "" == "%2" goto errorCreate

call ant -Dtheme.name=%1 -Dtheme.display.name=%2 create

goto end

:errorCreate
	echo.
	echo Usage: create.bat hello-world "Hello World"
	echo.
	echo The first hello-world will tell the script to put your theme in a newly created
	echo hello-world-theme directory.
	echo.
	echo The second "Hello World" is the theme's display name. The quotation marks are
	echo only needed because there is a space in the display name.

	goto end

:end