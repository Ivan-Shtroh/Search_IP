# Search_IP
Данный проект используется отделом технической поддержки для решения следующей задачи:
Происходит поиск заданых пользователем совпадений в конфигурационных файлах операционной системы Wtware. 
Путь для поиска задается пользователем.
При нахождении совпадений, директория, в которой находится указанный файл, полностью копируется со всеми вложениями в директорию назначения,
также заданную пользователем. Если указанный путь не существует, данная директория создается программой.
В папках, в которых были найдены совпадения, программа находит файл под названием ip,
копирует из этого файла ip-адрес и записывает его в текстовый файл, который создается программой в директории назначения.

Таким образом, данная программа имеет следующее практическое применение:
она позволяет находить компьютеры с определенной конфиругацией, заданной в строке поиска, 
например, компьютеры, которые подключаются к определенному терминальному серверу или имеют определенное разрешение экрана.
Это позволяет быстро находить и изменять конфигурацию конкретных компьютеров, избежав ручной проверки множества директорий.
