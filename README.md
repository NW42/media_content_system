# Зависимости проекта
Фреймворк:
1. `org.springframework.boot:spring-boot-starter-parent` - базовый POM для проектов на Spring.
2. `org.springframework.boot:spring-boot-starter-web` - cтартер для создания веб-приложений.
3. `org.springframework.boot:spring-boot-starter-data-jpa` - cтартер для взаимодействия с базой данных.
4. `org.postgresql:postgresql` - JDBC драйвер для СУБД PostgreSQL.

Swagger
1. `org.springdoc:springdoc-openapi-ui` - генерация Swagger UI.

Тестирование:
1. `org.junit.jupiter:junit-jupiter` - модульное тестирование.
2. `org.mockito:mockito-junit-jupiter` - объекты-заглушки для внешних зависимостей.
3. `org.assertj:assertj-core` - удобное тестирование и расширенный вывод информации об ошибках.

Плагины:
1. `org.apache.maven.plugins:maven-compiler-plugin` - компиляция проекта.
2. `org.apache.maven.plugins:maven-checkstyle-plugin` - проверка кода на соответствие [Google Java Code Style](https://google.github.io/styleguide/javaguide.html).
3. `org.apache.maven.plugins:maven-surefire-plugin` - запуск модульных тестов и генерация отчетов тестов.
4. `org.apache.maven.plugins:maven-failsafe-plugin` - запуск интеграционных тестов.
5. `org.springframework.boot:spring-boot-maven-plugin` - поддержка Spring Boot в Maven.

Разное:
1. `org.projectlombok:lombok` - сокращение кода и расширение функциональности языка Java.
2. `io.vavr:vavr` - примитивы функционального программирования.
3. `com.github.javafaker:javafaker` - генерация данных.
4. `commons-validator:commons-validator` - проверка значений.
4. `commons-codec:commons-codec` - кодирование и декодирование данных.

# Инструкции

## Сборка проекта

## Установка проекта
- В [application.yml](\src\main\resources\application.yml) установить:
  - 'spring.datasource.url' - ссылка на СУБД.
  - 'spring.datasource.username' - имя пользователя СУБД.
  - 'spring.datasource.password' - пароль пользователя СУБД.

## Запуск проекта

<!--
проверять доступность порта 8080
прописать требования на имя пользователя (User), см. Viki
<p> javadoc
на все failure прописать те два теста
описание, что id не может быть меньше нуля
написать тесты что ошибки реализуют Failure
на тесты ошибок добавить уникальность статуса
функция нахождения файлов, не прикрепленных файлов
протестить на непредвиденное разрастание списка ошибок в invalidvalues
возможно ли снова сделать имплементацию Failure на InvalidValueParam
fakeuserrepository - var usersResult = users.stream().filter(us -> us.getId().getValue() == id.getValue()).toList(); написать свой equals
тест на id = 234234ыва (public CompletableFuture<UserResponse> get(@PathVariable int id))

поискать везде 'new InvalidValues()' и обработать 
ошибки не дублировать, создавать и прокидывать наверх
файл - соответствие формата в имени и указанного формата

-->