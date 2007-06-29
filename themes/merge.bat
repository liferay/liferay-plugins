@echo off

if "" == "%1" goto errorMerge

call ant -Dtheme.name=%1 merge

goto end

:errorMerge
	echo.
	echo Usage: merge.bat hello-world

	goto end

:end