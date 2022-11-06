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

**Скрипты для создания таблиц в базе данных**

[persons table](src/main/resources/migration/persons.sql)

[tasks table](src/main/resources/migration/tasks.sql)