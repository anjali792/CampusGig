@REM ----------------------------------------------------------------------------
@REM CampusGig Maven Wrapper
@REM ----------------------------------------------------------------------------
@ECHO OFF
SETLOCAL

IF "%JAVA_HOME%" == "" (
    SET "JAVA_HOME=C:\Users\ANJALI SINGH SENGAR\.jdk\jdk-17"
)

IF EXIST "%USERPROFILE%\.maven\apache-maven-3.9.9\bin\mvn.cmd" (
    CALL "%USERPROFILE%\.maven\apache-maven-3.9.9\bin\mvn.cmd" %*
) ELSE (
    mvn %*
)

ENDLOCAL
