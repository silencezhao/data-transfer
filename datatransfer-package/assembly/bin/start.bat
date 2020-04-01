@echo off & setlocal enabledelayedexpansion
set BIN_PATH=%CD%
set LIB_JARS=""
cd ..
set LIB_PATH=%CD%\lib\*
cd ..
set COM_LIB_PATH=%CD%\lib\*
set LIB_JARS=%LIB_PATH%;%COM_LIB_PATH%
cd %BIN_PATH%
java -Xms1024m -Xmx3096m -XX:MaxPermSize=256M -classpath ..\conf;%LIB_JARS% com.nsfocus.core.Boot
pause