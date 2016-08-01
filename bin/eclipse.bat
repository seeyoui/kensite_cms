@echo off

title %cd%

rem pause
rem echo.

cd %~dp0
cd..

call mvn -Declipse.workspace=%cd% eclipse:clean eclipse:eclipse

cd bin
pause