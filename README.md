# Процедура запуска автотестов
1. Для возможности поднять вспомогательные сервисы нужно выполнить в терминале команду `docker-compose up`
2. Запустить SUT с помощью команды в терминале `java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app`
3. Запустить тесты с помощью команды в терминале `./gradlew clean test -P:jdbc.url=jdbc:mysql://localhost:3306/app`
4. Сформировать отчет в репорт-системе Allure с помощью команды в терминале `./gradlew allureserve`
5. Для подключения СУБД PostgreSQL в терминале остановить работу -jar файла (`Ctrl+C`), далее ввести команду в терминал `java -jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:postgresql://localhost:5432/app`
6. Запустить тесты с помощью команды в терминале `./gradlew clean test -P:jdbc.url=jdbc:postgresql://localhost:5432/app`
7. Сформировать отчет в репорт-системе Allure с помощью команды в терминале `./gradlew allureserve`