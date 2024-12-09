@echo off
setlocal
for /f "tokens=2 delims=:." %%x in ('chcp') do set _codepage=%%x
chcp 65001>nul
cd D:\MCreatorWorkspaces\resourceFarm\run
"C:\Program Files\Java\jdk-21\bin\java.exe" @D:\MCreatorWorkspaces\resourceFarm\build\moddev\gameTestServerRunClasspath.txt @D:\MCreatorWorkspaces\resourceFarm\build\moddev\gameTestServerRunVmArgs.txt -Dfml.modFolders=resource_farms%%%%D:\MCreatorWorkspaces\resourceFarm\build\classes\java\main;resource_farms%%%%D:\MCreatorWorkspaces\resourceFarm\build\resources\main net.neoforged.devlaunch.Main @D:\MCreatorWorkspaces\resourceFarm\build\moddev\gameTestServerRunProgramArgs.txt
if not ERRORLEVEL 0 (  echo Minecraft failed with exit code %ERRORLEVEL%  pause)
chcp %_codepage%>nul
endlocal