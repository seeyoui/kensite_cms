@echo off

title %cd%

rem pause
rem echo.

cd %~dp0
cd..

call mvn clean package -Dmaven.test.skip=true

cd bin
pause