# Процедура запуска автотестов
1. Для возможности поднять вспомогательные сервисы нужно выполнить в терминале команду `docker-compose up`
2. Запустить SUT с помощью команды в терминале `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
3. Запустить тесты с помощью команды в терминале `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
4. Сформировать отчет в репорт-системе Allure с помощью команды в терминале `./gradlew allureserve`
5. Для подключения СУБД PostgreSQL в терминале остановить работу -jar файла (`Ctrl+C`), далее ввести команду в терминал `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
6. Запустить тесты с помощью команды в терминале `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
7. Сформировать отчет в репорт-системе Allure с помощью команды в терминале `./gradlew allureserve`