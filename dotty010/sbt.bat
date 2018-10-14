set SBT_OPTS=-Xmx3072M -Dinput.encoding=Cp1252 -Dfile.encoding=SJIS -XX:+CMSClassUnloadingEnabled

java %SBT_OPTS% -jar sbt-launch.jar %*
