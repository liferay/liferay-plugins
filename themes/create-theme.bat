@echo off

if "" == "%1" goto errorCreateTheme
if "" == "%2" goto errorCreateTheme

call ant -Dtheme.name=%1 -Dtheme.display.name=%2 create-theme

goto end

:errorCreateTheme
	echo.
	echo Usage: create-theme.bat hello-world "Hello World"
	echo.
	echo The first hello-world will tell the script to put your theme in a newly created
	echo hello-world-theme directory.
	echo.
	echo The second "Hello World" is the theme's display name. The quotation marks are
	echo only needed because there is a space in the display name.

	goto end

:end