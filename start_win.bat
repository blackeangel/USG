@echo off
cd "%~dp0"
IF EXIST "%~dp0\bin\jre_win\bin" SET PATH=%PATH%;"%~dp0\bin\jre_win\bin"
Setlocal EnableDelayedExpansion
javaw.exe -jar USG.jar
