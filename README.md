# ExpenseTracker

Приложение для ведения заметок, поддерживающее закрепление записей в списке и интеграцию графических файлов.

## Скриншоты
[Вставьте 3-4 скриншота]

## Функционал
- Реализовал UI на Jetpack Compose
- Спроектировал локальную БД (Room) с 2 таблицами и связями
- Добавил возможность использовать изображения при написании заметок
- Настроил работу с Internal и External Storage

## Технологии
- Kotlin
- Jetpack Compose (UI)
- Jetpack Compose Navigation
- MVVM + Clean Architecture
- Room (локальная БД)
- Hilt (DI)
- Coroutines + Flow
- Splash Screen API
- External Storage + Internal Storage
- Intent

## Архитектура
Проект следует Clean Architecture с разделением на слои:
- Presentation (UI + ViewModel)
- Domain (Use Cases)
- Data (Repository + DataSource)

## Установка
`git clone https://github.com/SliNeYuBe/Notes.git`
Откройте проект в Android Studio Arctic Fox или новее.
