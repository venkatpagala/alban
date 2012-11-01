rem r�cup�re le r�pertoire courant sous windows
rem set POC_HOME=%~dp0

set VERSION=10

set HOME=C:\workspace\users\albandri%VERSION%
set THIRDPARTY_ROOT=C:\thirdparty

set JAVA_HOME=C:\SUN\SDK\jdk1.6.0_33
set JAVA=%JAVA_HOME%\bin
set ANT_HOME=C:\apache-ant-1.7.1
set ANT=%ANT_HOME%\bin
set M2_HOME=C:\apache-maven-3.0.4
set M2=%M2_HOME%\bin
set M2_REPO=C:\repo

set MAVEN_OPTS=-Xms256m -Xmx512m -XX:PermSize=80M -XX:MaxPermSize=256M

set GRAPHVIZ_HOME=C:\Graphviz2.22
set CYGWIN_HOME=C:\cygwin

REM set ACE_ROOT=%THIRDPARTY_ROOT%/corba/tao/1_3_x86Linux/ACE_wrappers
REM set TAO_ROOT=%THIRDPARTY_ROOT%/corba/tao/1_3_x86Linux/ACE_wrappers/TAO
REM set QTDIR=C:\cygwin\lib\qt3

REM set DISPLAY=:0.0
REM set MACHINE=winnt

set PATH=%M2_HOME%/bin;%JAVA_HOME%/bin;%GRAPHVIZ_HOME%\bin
set CLASSPATH=.;%JAVA_HOME%\bin;%M2_HOME%\bin;%ANT_HOME%\bin;%GRAPHVIZ_HOME%\bin
echo %CLASSPATH%
