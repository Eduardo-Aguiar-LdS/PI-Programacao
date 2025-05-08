@echo off
cd /d %~dp0

echo ====================================
echo COMPILANDO O PROJETO COM MAVEN...
echo ====================================
call mvn clean compile

if "%ERRORLEVEL%"=="0" (
    echo ====================================
    echo EXECUTANDO testes.TesteConexao...
    echo ====================================
    
    call mvn exec:java -Dexec.mainClass=testes.TesteConexao
    
    if errorlevel 1 (
        echo ====================================
        echo ERRO NA EXECUCAO!
        echo ====================================
    )
) else (
    echo ====================================
    echo ERRO NA COMPILACAO! CODIGO: %ERRORLEVEL%
    echo ====================================
)

echo.
echo ====================================
echo FIM DA EXECUCAO
echo ====================================
pause