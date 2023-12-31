# План автоматизации тестирования web-сервиса покупки тура

## 1. Перечень автоматизируемых сценариев.
### Часть 1. Обычная оплата по дебетовой карте.
- **1.1a.Позитивный сценарий для одобренной (APPROOVED) карты:**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - нет сообщений об ошибках валидации, всплывающее сообщение "Операция одобрена Банком".  


- **1.1b.Негативный сценарий для отклоненной (DECLINED) карты:**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер отклоненной карты (5555 6666 7777 8888).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - нет сообщений об ошибках валидации, всплывающее сообщение "Ошибка! Банк отказал в проведении операции".  


- **1.1c.Негативный сценарий для незарегистрированной карты:**  
  Открыть страницу покупки тура (*localhost:8080*).  
  Нажать кнопку **Купить**.  
  В поле ввода **Номер карты** ввести произвольный номер карты из 16 цифр (не из списка зарегитрированных APPROOVED/DECLINED).  
  В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
  В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
  В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
  В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
  Нажать кнопку **Продолжить**.  
  Ожидаемый результат - нет сообщений об ошибках валидации, всплывающее сообщение "Ошибка! Банк отказал в проведении операции".  
---

- **1.2a.Негативный сценарий для номера карты (по незаполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ничего не вводить.  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем карты "Неверный формат".  


- **1.2b.Негативный сценарий для номера карты (по неполному заполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести произвольные цифры в количестве от 1 до 15.  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр. Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем карты "Неверный формат".  


- **1.2c.Негативный сценарий для номера карты (по перезаполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести произвольные цифры в количестве 17.  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - в поле номера карты введены только первые 16 цифр, сообщения об ошибке валидации нет, всплывающее сообщение "Операция одобрена Банком".  


- **1.2d.Негативный сценарий для номера карты (по неверным символам):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести произвольные буквы и спецсимволы в количестве от 1 до 16.  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - поле номера карты осталось не заполнено, сообщение об ошибке валидации под полем карты "Неверный формат".  
---

- **1.3a.Негативный сценарий для месяца (по незаполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
Поле ввода **Месяц** оставить пустым.  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем месяца "Неверный формат".  


- **1.3b.Негативный сценарий для месяца (по неполному заполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести любую цифру, кроме "0".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем месяца "Неверный формат".  


- **1.3c.Негативный сценарий для месяца (по граничному условию):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести "13".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем месяца "Неверно указан срок действия карты".  


- **1.3d.Негативный сценарий для месяца (по неверным символам):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести произвольные буквы и спецсимволы в количестве 2.  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - поле месяца осталось не заполнено, сообщение об ошибке валидации под полем месяца "Неверный формат".  
---

- **1.4a.Негативный сценарий для года (по незаполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
Поле ввода **Год** оставить пустым.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем года "Неверный формат".  


- **1.4b.Негативный сценарий для года (по неполному заполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести любую цифру, кроме "0".  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем года "Неверный формат".  


- **1.4c.Негативный сценарий для года (по граничному заполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год, больший текущего  на 6 лет, в формате "xx".  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем года "Неверно указан срок действия карты".  


- **1.4d.Негативный сценарий для года (по неверным символам):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести произвольные буквы и спецсимволы в количестве 2.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - поле года осталось не заполнено, сообщение об ошибке валидации под полем года "Неверный формат".  
---

- **1.5a.Негативный сценарий для владельца (по незаполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
Поле ввода **Владелец** оставить пустым.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем владельца "Поле обязательно для заполнения".  


- **1.5b.Негативный сценарий для владельца (по неверным символам):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести несколько цифр и спецсимволов.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем владельца "Неверный формат".  

  
- **1.5c.Негативный сценарий для владельца (по переполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести два слова на латинице с пробелом между ними, чтобы общая длина последовательности составляла >100 символов.  
В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем владельца "Слишком длинное имя".  


- **1.5d.Негативный сценарий для владельца (по кириллице):**  
  Открыть страницу покупки тура (*localhost:8080*).  
  Нажать кнопку **Купить**.  
  В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
  В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
  В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
  В поле ввода **Владелец** ввести имя (два слова) на кириллице с пробелом между ними.  
  В поле ввода **CVC/CVV** ввести код проверки из трех любых цифр.  
  Нажать кнопку **Продолжить**.  
  Ожидаемый результат - сообщение об ошибке валидации под полем владельца "Имя должно быть на латинице".
---

- **1.6a.Негативный сценарий для CVC/CVV (по незаполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
Поле ввода **CVC/CVV** оставить пустым.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем CVC/CVV "Неверный формат".  


- **1.6b.Негативный сценарий для CVC/CVV (по неполному заполнению):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести две любые цифры, кроме "0".  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщение об ошибке валидации под полем CVC/CVV "Неверный формат".  


- **1.6c.Негативный сценарий для CVC/CVV (по граничному условию):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести 4 цифры, кроме "0".  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - в поле CVC/CVV введены только первые 3 цифры, сообщения об ошибке валидации нет, всплывающее сообщение "Операция одобрена Банком".  


- **1.6d.Негативный сценарий для CVC/CVV (по неверным символам):**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
В поле ввода **Номер карты** ввести валидный номер одобренной карты (1111 2222 3333 4444).  
В поле ввода **Месяц** ввести валидный номер месяца от 1 до 12 в формате "xx".  
В поле ввода **Год** ввести год окончания действия карты в формате "xx", он не должен быть меньше текущего.  
В поле ввода **Владелец** ввести валидное имя - два слова на латинице.  
В поле ввода **CVC/CVV** ввести буквы и спецсимволы (всего 3 символа).  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - поле CVC/CVV осталось незаполнено, сообщение об ошибке валидации под полем CVC/CVV "Неверный формат".  
---

- **1.7.Негативный сценарий пустых полей:**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
Не заполняя ни одного поля, нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщения об ошибках валидации под всеми незаполненными полями.  


- **1.8.Негативный сценарий для обновления сообщений валидации:**  
Открыть страницу покупки тура (*localhost:8080*).  
Нажать кнопку **Купить**.  
Не заполняя ни одного поля, нажать кнопку **Продолжить**.  
Заполнить все поля валидными данными.  
Нажать кнопку **Продолжить**.  
Ожидаемый результат - сообщения об ошибках валидации под всеми полями не отображаются.  



### Часть 2. Выдача кредита по данным банковской карты.

Все сценарии аналогичны дебетовой карте.


## 2. Перечень используемых инструментов с обоснованием выбора.
  1. **IntelliJ IDEA Community Edition** (удобная среда, много подсказок, подключаемые многочисленные плагины, частое обновление функционала)
  2. **Язык программирования JAVA** (легок для освоения, четкая типизация данных, поддержка ООП, частые обновления)
  3. **Сборщик проектов Gradle** (проще настройка конфигурации, чем у других, мультипроектный подход)
  4. **Фреймворк JUNIT для Java** (основной инструмент для автоматизированного unit-тестирования)
  5. **Библиотеки Faker, Allure, Selenide, DBUtils** и пр. (очень ускоряют разработку и контроль тестов за счет шаблонизирования часто используемых операций)
  6. **Docker Desktop** (самая распространенная система контенеризации с бесплатными контейнерами с СУБД MySql и пр.)
  7. **Git, GitHub** (известнейшие системы контроля версий, бесплатные, удобные)

## 3. Перечень и описание возможных рисков при автоматизации.
- Риск не успеть выполнить проект из-за нехватки опыта автоматизатора (много инструментов изучены недавно и мало, медленное написание кода, множество багов при отладке, неэффективный код), например, долго не мог подключить приложение к базе.
- Риск не успеть из-за нехватки человеческих ресурсов (автоматизатор один на проекте)

## 4. Интервальная оценка с учётом рисков в часах.
От 20 до 40 часов, если работает один автоматизатор.

## 5 План сдачи работ: когда будут проведены автотесты, результаты их проведения и отчёт по автоматизации.
- Начало работы, ознакомление с задачей, написание тестовых сценариев - 02.09-05.09.2023
- Написание и отладка автотестов - 06.09-15.09.2023
- Отчетность по итогам тестирования и автоматизации - 16.09.2023
