FOR /F "eol=# tokens=*" %%i IN (%~dp0.env) DO SET %%i
.\mvnw package
