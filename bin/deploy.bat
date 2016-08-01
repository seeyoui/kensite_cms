@echo off

title %cd%

rem pause
rem echo.

cd %~dp0
cd..

call mvn deploy

cd bin
pause