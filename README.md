# Currency Exchanger

## Проект "Обменный пункт валюты"

**Описание проекта:**
Разработать консольное приложение для имитации работы обменного пункта валюты.
Пользователи могут регистрировать аккаунты, открывать счета в различных валютах, пополнять счета, снимать средства со счетов, осуществлять обмен валюты, а также просматривать историю своих операций.

**Техническое задание:**

1. **Многослойная архитектура приложения:**
    - ~~**Модель (Model):** Определить сущности, такие как пользователь, аккаунт, операция, валюта, курс валют и другие необходимые объекты.~~
    - ~~**Сервис (Service):** Разработать бизнес-логику приложения, включая управление данными пользователей, выполнение валютных операций и т.д.~~
    - ~~**Репозиторий (Repository):** Создать слой для взаимодействия с хранилищем данных (например, в памяти или файле(-ах)).~~

2. **Организация данных и управление аккаунтами:**
    ~~- Реализовать механизмы для управления данными пользователя, включая их аккаунты и ведение счетов.~~
    ~~- У пользователя могут быть счета минимум в 3 разных валютах.~~

3. **Управление валютными операциями:**
    - ~~Обеспечить функциональность для осуществления валютных операций, включая пополнение, обмен и просмотр истории операций.~~

4. **Валидация данных:**
    - ~~При регистрации нового пользователя реализовать валидацию введенного email и пароля. Валидация должна убедиться, что email соответствует стандартному формату электронной почты, а пароль соответствует заданным критериям безопасности (например, минимальная длина, наличие букв и цифр).~~

5. **Пользовательский интерфейс:**
    - ~~Реализовать консольное меню для доступа к функциональности приложения.~~

6. **Тестирование:**
    - ~~Разработать JUnit тесты для проверки всех аспектов функциональности приложения, особое внимание уделить тестированию сервисного слоя.~~

7. **Использование коллекции Map:**
    - ~~В процессе разработки приложения активно использовать коллекцию `Map` для организации и управления данными, такими как учетные записи пользователей, счета, курсы валют и другие подходящие сценарии.~~
    - Например:
        - ~~Для хранения списка аккаунтов пользователя, где ключом будет идентификатор пользователя, а значением - список его аккаунтов.~~
        - ~~Для хранения курсов валют, где ключом будет код валюты, а значением - текущий курс.~~

**Опционально:**
- В случае провала валидации, приложение должно выбрасывать исключение, которое затем должно быть корректно обработано. Это может включать информирование пользователя о причине ошибки и предложение повторить попытку ввода данных.
- Функционал для просмотра исторических курсов валют.


## Функционал пользователя:
- ~~Регистрация нового пользователя (с валидацией email и password)~~
- ~~Вход в аккаунт~~
- ~~просмотр баланса (остатка на всех счетах или каком-то конкретном счете)~~
- ~~Пополнение счета в выбранной валюте (проверка существования такого счета у пользователя. Если отсутствует - открыть соответствующий счет)~~
- ~~Снятие средств со счета (с соответствующими проверками возможности операции)~~
- ~~Открытие нового счета~~
- ~~Закрытие счета (с проверками: если на счету есть средства? что делать?)~~
- ~~Просмотр истории операций (по всем счетам / по конкретной валюте)~~
- ~~Обмен валют (перевод средств с одного счета на другой с соответствующим кросс-курсом)~~

## Функционал администратора
- ~~Изменение курса валюты~~
- ~~Возможность добавление или удаление валют из списка (При удаление проверка, есть ли открытые счета у пользователей в этой валюте? Если есть - что делать?)~~
- ~~Просмотр операций пользователя~~
- ~~Просмотр операций по валюте~~
- ~~Назначение другого пользователя администратором (модератором / кассиром)~~

## Организация хранения данных (списки пользователей, валют, курсов и т.д. и т.п.)

## Опционально
- История изменения курсов валюты
- Свои классы исключений и их обработка














