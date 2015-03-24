@echo off

if "" == "%1" goto errorCreate

if "" == "%2" goto errorCreate

call ..\tools\gradle\gradlew.bat --build-file=..\sdk.gradle -PextName=%1 -PextDisplayName=%2 createExt

rem call ant deploy

goto end

:errorCreate
	echo.
	echo Usage: create.bat hello-world "Hello World"
	echo.
	echo The first hello-world is your extension environment name. A new directory will
	echo be created based on the extension environment name.
	echo.
	echo The second "Hello World" is the extension environment's display name. The
	echo quotation marks are only needed because there is a space in the display name.

	goto end

:end