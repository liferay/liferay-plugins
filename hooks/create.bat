@echo off

if "" == "%1" goto errorCreate

if "" == "%2" goto errorCreate

call ..\tools\gradle\gradlew.bat --build-file=..\sdk.gradle -PhookName=%1 -PhookDisplayName=%2 createHook

rem call ant deploy

goto end

:errorCreate
	echo.
	echo Usage: create.bat hello-world "Hello World"
	echo.
	echo The first hello-world is your hook id. A new directory will be created based on
	echo the hook id.
	echo.
	echo The second "Hello World" is the hook's display name. The quotation marks are
	echo only needed because there is a space in the display name.

	goto end

:end