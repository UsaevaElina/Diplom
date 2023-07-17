# Процедура запуска автотестов
1. Для возможности поднять вспомогательные сервисы нужно выполнить в терминале команду `docker-compose up`
2. Запустить SUT с помощью команды в терминале java `-jar artifacts/aqa-shop.jar`
3. Запустить тесты с помощью команды в терминале `./gradlew clean test --info`
4. Сформировать отчет в репорт-системе Allure с помощью команды в терминале `./gradlew allureserve`
5. Для подключения СУБД PostgreSQL в терминале остановить работу -jar файла (`Ctrl+C`), далее ввести команду в терминал java `-jar artifacts/aqa-shop.jar -P:jdbc.url=jdbc:postgresql://localhost:5432/app`
6. Для запуска тестов и формирования отчетов выполните команды в п. 3 и 4