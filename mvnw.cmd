@REM ----------------------------------------------------------------------------
@REM CampusGig Maven Wrapper
@REM ----------------------------------------------------------------------------
@ECHO OFF
SETLOCAL

IF EXIST "%USERPROFILE%\.maven\apache-maven-3.9.9\bin\mvn.cmd" (
    CALL "%USERPROFILE%\.maven\apache-maven-3.9.9\bin\mvn.cmd" %*
) ELSE (
    mvn %*
)

ENDLOCAL
