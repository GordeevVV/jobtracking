# Пояснения

**@Component** - аннотация для того чтобы понять 
спрингу что этот компонент должен быть доступен пока приложения запущено,
то есть все что имеет отношения к функционалу должна быть эта аннотация 
либо ее вариации **@Listener**, **@Service**, **@Handler**...

Линковка к таблице:
**@Table(name= "persons")**

**@Entity** - аннотация для того чтобы понять что используется Hibernate 
и это сущность идентична таблице созданной под нее

**Магия спринга для запуска приложения**
```java
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
```
**Файл для конфигурации подключения к БД, вам нужно лишь поменять на свой логин пароль и создать базу данных с таким же названием**

[Application.yaml](src/main/resources/application.yaml)

**Скрипты для создания таблиц в базе данных, но они больше не нужны, нужно просто запустить в maven терминале mvn spring-boot:run и если у вас есть pgAdmin автоматом создадутся БД**

[persons table](src/main/resources/migration/persons.sql)

[tasks table](src/main/resources/migration/tasks.sql)

***Теперь самое интересное***

[WebSecurityConfig](src/main/java/com/job_tracking_system/configuration/WebSecurityConfig.java)
Класс конфигурации аутентификации, то есть тут происходит конфигурация всего что отвечает за security

[AuthController](src/main/java/com/job_tracking_system/controllers/AuthController.java)
Эндпоинты регистрации и аутентификации, а так же выхода из системы

[MessageController](src/main/java/com/job_tracking_system/controllers/MessageController.java)
Эндпоинты для всего остального функционала то есть получения из таблиц записи в таблицы

[Security](src/main/java/com/job_tracking_system/security)
Все что связано с генерацией аутентификационного токена и сервисами аутентификации

[Mappers](src/main/java/com/job_tracking_system/mappers)
Маппинги из приходящих в запросе json сущностей в используемые в проекте

[PersonService](src/main/java/com/job_tracking_system/services/PersonService.java)
Конечная сверка пришедшего пользователя с базой данных посредством Spring Security