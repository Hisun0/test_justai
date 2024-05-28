# Тестовое задание для JustAI

Суть задания указана по этой [ссылке](https://docs.google.com/document/d/1x_EEtb1AbU83dlJHVnI4GYkoSnGmXYmzqoM6q_BceF0/edit).

### Быстрые ссылки

- [Требования для запуска проекта](#требования-для-запуска-проекта)
- [Как проверять работоспособность приложения?](#как-проверять-работоспособность-приложения)
- [Как запустить приложение?](#как-запустить-приложение)
  - [1. Docker](#1-docker)
  - [2. Запуск без использования Docker](#2-запуск-без-использования-docker)
  - [3. Запуск без использования Docker и make](#3-запуск-без-использования-docker-и-make)
- [Где указаны переменные окружения?](#где-указаны-переменные-окружения)

## Требования для запуска проекта
- Обязательно
  - Установленная утилита ngrok ([ссылка на скачивание](https://ngrok.com/download))
- Опционально
  - Установить утилиту `make` через команду `sudo apt-get install make`
  - Установить Docker Desktop ([ссылка на скачивание](https://www.docker.com/products/docker-desktop/))

## Как проверять работоспособность приложения?

Вам нужно перейти по [данной ссылке](https://vk.com/club226051575), где у вас откроется VK сообщество. После, откройте сообщения с сообществом и напишите любой текст. Бот вам ответит вашим же сообщением. Вот пример работы:

![пример работы](https://i.ibb.co/G2xxjy2/image.png)

## Как запустить приложение?

Если у вас не установлена утилита `make`, то вместо команд типа `make` можно использовать аналогичную. Выглядит она следующим образом:

`make docker-run` - `docker run -it -p 8080:8080 divaan/test_justai`

`make run-ngrok` - `ngrok start --config ngrok.yml test`

Перед началом запуска, нужно склонировать репозиторий с приложением в любое удобное вам место:

```bash
git clone здесь ссылка будет
```

Далее будут описаны варианты запуска приложения. Вы можете выбрать любой удобный вам

### 1. Docker

Для запуска docker контейнера потребуется запустить Docker Desktop ([ссылка на скачивание](https://www.docker.com/products/docker-desktop/))

Из корневой папки приложения нужно прописать следующую команду:

```bash
make docker-run
```

Далее, нужно создать второй терминал, и прописать следующую команду, которая запустит ngrok:

```bash
make run-ngrok
```

Образ докер контейнера запушен на докер хаб, поэтому проблем с запуском возникнуть не должно, но если вдруг докер не подтягивает образ с хаба, то пропишите build команду перед запуском `make docker-run`:

```bash
docker build -t nickname/dockerimage .
```

### 2. Запуск без использования Docker

Для запуска приложения без Docker потребуется прописать следующие команды:

```bash
./gradlew installDist
./build/install/test_JustAI/bin/test_JustAI
```

После, запустите ngrok, используя эти две команды:

```bash
make run-ngrok
```

### 3. Запуск без использования Docker и make

Для запуска без Docker и make пропишите следующие команды:

```bash
./gradlew installDist
./build/install/test_JustAI/bin/test_JustAI
```

Затем откройте второй терминал и пропишите команду для запуска ngrok:

```bash
ngrok start --config ngrok.yml test
```

## Где указаны переменные окружения?

1. Переменная окружения для VK API указана в файле `application/properties`
2. Переменная окружения для ngrok указана в `ngrok.yaml`

Оба этих файла нужно поместить в `.gitignore`, но так как проект тестовый, то их нужно показать, чтобы его можно было запустить
