## Цель задания

Создать небольшое приложение для отображения списка фильмов с архитектурой по принципам Clean Architecture + MVVM, с использованием Hilt для DI и Navigation Compose с type-safe навигацией.

## Функциональные требования

1. **Экран списка фильмов**
    * Отображает список заранее заготовленных фильмов.
    * Каждый элемент содержит, например, название и описание.
    * При нажатии открывается экран с деталями фильма.
2. **Экран деталей фильма**
    * Отображает полную информацию: название, описание, год выхода, жанр.
3. **Нижняя навигация**
    * В приложении есть нижнее меню с минимум двумя пунктами (например: «Фильмы» и «Избранное» (пустой экран или просто заглушка)).

## Структура проекта (Clean Architecture)

* **domain**
    * Модели
    * Интерфейсы репозиториев
* **data**
    * Реализация репозиториев (реализация методов получения данных из статического списка фильмов)
* **presentation**
    * viewmodels (Вью модели)
    * screens (Composable-экраны)
    * navigation (type-safe навигация)
* **di**
    * DI модули

## Технические требования

* Использовать Jetpack Compose для UI.
* Разделить логику UI и бизнес-логику (в ViewModel хранить состояние, UI — чисто отображает).
* Использовать Navigation Compose с type-safe аргументами (например, передавать movieId в экран деталей).
* Внедрение зависимостей через Hilt.

## Ссылки
* Clean Architecture - https://habr.com/ru/companies/bsl/articles/788940/
* MVVM - https://medium.com/@sks727633/mvvm-with-jetpack-compose-structuring-your-app-for-clean-architecture-42f4bad4c99e
* ViewModel - https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ru
* Hilt - https://developer.android.com/training/dependency-injection/hilt-android?hl=ru
* Навигация - https://developer.android.com/develop/ui/compose/navigation?hl=ru#bottom-nav; https://proandroiddev.com/type-safe-bottom-navigation-in-jetpack-compose-d18c4d76ac1a
