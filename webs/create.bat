@echo off

if "" == "%1" goto errorCreate

if "" == "%2" goto errorCreate

call ant -Dweb.name=%1 -Dweb.display.name=%2 create

rem call ant deploy

goto end

:errorCreate
	echo.
	echo Usage: create.bat hello-world "Hello World"
	echo.
	echo The first hello-world is your web id. A new directory will be created based on
	echo the web id.
	echo.
	echo The second "Hello World" is the web's display name. The quotation marks are only
	echo needed because there is a space in the display name.

	goto end

:end