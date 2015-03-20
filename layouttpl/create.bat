@echo off

if "" == "%1" goto errorCreate
if "" == "%2" goto errorCreate

call ..\tools\gradle\gradlew.bat -b ..\sdk.gradle -PlayouttplName=%1 -PlayouttplDisplayName=%2 createLayouttpl

cd %1-layouttpl

rem call ant deploy

cd ..

goto end

:errorCreate
	echo.
	echo Usage: create.bat hello-world "Hello World"
	echo.
	echo The first hello-world is your layouttpl id. A new directory will be created
	echo based on the layouttpl id.
	echo.
	echo The second "Hello World" is the layouttpl's display name. The quotation marks
	echo are only needed because there is a space in the display name.

	goto end

:end