# Использование Spring для разработки серверного приложения

## Доделать сервис управления книгами:

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/066ac326-827d-4d9a-b9c2-957065ab0a71)

### Реализовать контроллер по управлению книгами с ручками:

GET /book/{id} - получить описание книги

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/ec0e6dc7-cdd5-4e50-b805-5fa90aedda45)

DELETE /book/{id} - удалить книгу

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/b021d297-27d3-4399-89a3-1e1185fded35)

POST /book - создать книгу

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/ed8addbb-18ef-42e3-92d4-5a4c2c6aa866)

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/331dc15a-7eef-40c3-a062-ba343d59a109)


###  1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/df727d63-6c52-4210-a18a-6463ad31ee46)

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/d5e627ee-0dda-4595-9348-f86b7001fa70)


### 1.3 В контроллере IssueController добавить ресурс GET /issue/{id} - получить описание факта выдачи

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/1cf1834c-c137-46f9-9a15-4166a849d49a)

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/f9b54768-8afb-4856-a2b4-87fc0a6feed5)



## 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг.

* Если есть - не выдавать книгу (статус ответа - 409 Conflict)

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/e648baee-28ba-4f20-82fb-fa698157384f)

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/9c792ee5-5a75-4bb0-97fb-5723112a6419)

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/f67c6964-c3f3-4a67-95ea-97888d10cdff)

##  В сервис читателя добавить ручку
* GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/811a00da-f831-4552-9cb6-f10eaf01cfa2)

![image](https://github.com/Winniebob/HomeWorkSpring3/assets/131287620/04139f4c-e127-4157-be01-e2a645b7351a)
