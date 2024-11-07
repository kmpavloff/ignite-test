# Конфигурация кластера Ignite

В этой папке находится файл docker-compose.yml, который поднимает трех-узловой кластер Ignite.

Для запуска кластера выполнить:

```bash
docker-compose up
```

Для активации кластера необходимо выполнить команду:

```bash
# заходим на первую ноду по bash
docker exec -it ignite-node1 bash
# активируем кластер
ignite-node1:/opt/ignite# ./apache-ignite/bin/control.sh --activate
```

Для остановки кластера Ctrl+C и для удаления контейнеров:

```bash
docker-compose down
```
