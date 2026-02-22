# ExpenseTracker

Приложение для ведения заметок, поддерживающее закрепление записей в списке и интеграцию графических файлов.

## Скриншоты
<img width="399" height="853" alt="Снимок экрана 2026-02-22 063315" src="https://github.com/user-attachments/assets/4689cd76-7bfc-4cdc-b5e7-0cad2dbec2fd" />
<img width="397" height="851" alt="Снимок экрана 2026-02-22 063328" src="https://github.com/user-attachments/assets/efe3d1c9-c4af-43ab-a91a-f1aeff98577f" />
<img width="398" height="851" alt="Снимок экрана 2026-02-22 063454" src="https://github.com/user-attachments/assets/e3545866-7ceb-454e-82c0-daf2f01e3aa9" />

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
