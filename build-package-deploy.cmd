call mvn package
copy /Y .\target\questionnaire-1.0.0-SNAPSHOT.war %CATALINA_HOME%\webapps\ROOT.war
%CATALINA_HOME%\bin\startup.bat

