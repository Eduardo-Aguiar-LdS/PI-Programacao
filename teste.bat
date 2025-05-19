@echo off
cd /d %~dp0

echo ====================================
echo COMPILANDO O PROJETO COM MAVEN...
echo ====================================
call mvn clean compile

if "%ERRORLEVEL%"=="0" (
    echo ====================================
    echo EXECUTANDO SHOW DO MILHÃO...
    echo ====================================
    
    call mvn exec:java -Dexec.mainClass="show_milhao.Jogo"
    
    if errorlevel 1 (
        echo ====================================
        echo ERRO NA EXECUÇÃO!
        echo ====================================
    )
) else (
    echo ====================================
    echo ERRO NA COMPILAÇÃO! CÓDIGO: %ERRORLEVEL%
    echo ====================================
)

echo.
echo ====================================
echo FIM DA EXECUÇÃO
echo ====================================
pause
