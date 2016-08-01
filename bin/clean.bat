@echo off

title %cd%

rem pause
rem echo.

cd %~dp0
cd..

call mvn clean

cd bin
pause