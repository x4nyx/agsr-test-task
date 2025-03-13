<!-- ABOUT THE PROJECT -->
## About The Project
Проект представляет собой небольшое Spring Boot REST-API приложение для ведения учёта пациентов. 

### Built With
* [![SpringBoot]][Spring-url]
* [![MySQL]][MySQL-url]
* [Keycloak](https://www.keycloak.org/)

### Structure
* `agsrdemo` - Каталог с основным Spring Boot приложением
* `agsrdemoconsole` - Каталог с консольным приложением для генерации 100 случайных сущностей
* `AGSR-API-collection.postman_collection.json` - Конфигурационный файл postman-коллекции

<!-- GETTING STARTED -->
## Getting Started
Для запуска REST-API приложения необходимо перейти в `agsrdemo` и выполнить docker compose:
```sh
docker compose up -d
```
Для запуска консольного приложения необходимо перейти в `agsrdemoconsole` и выполнить скрипт run.sh:
```sh
sh run.sh
```
Для теста запросов, в postman необходимо импортировать `AGSR-API-collection.postman_collection.json` и запустить коллекцию

### Authorization
Авторизация выполняется по протоколу OAuth2, с помощью SSO-провайдера [Keycloak](https://www.keycloak.org/). 
Пользователи для тестов предустановлены в конфигурационном файле `agsrdemo/keycloak/realm.json`.
Логин и пароль для пользователя с ролью Practitioner: "**admin**", с ролью Patient: "**user**".

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
[Spring-url]: https://spring.io/projects/spring-boot
[MySQL-url]: https://www.mysql.com/

[SpringBoot]: https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white
[Swagger]: https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white
[MySQL]: https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white
