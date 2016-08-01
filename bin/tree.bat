@echo off

title %cd%

rem pause
rem echo.

cd %~dp0
cd..

call mvn dependency:tree

cd bin
pause