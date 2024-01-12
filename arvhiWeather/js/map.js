ymaps.ready(function () {
    var map = new ymaps.Map('map', {
    center: [0, 0],
    zoom: 8
    });
    
    // Получаем текущие координаты пользователя
    ymaps.geolocation.get().then(function (result) {
    var userCoordinates = result.geoObjects.position;
    
    // Устанавливаем текущие координаты пользователя в поле ввода и на карту
    var coordinatesInput = document.getElementById('coordinates');
    coordinatesInput.value = 'lat=' + userCoordinates[0].toFixed(6) + '&lon=' + userCoordinates[1].toFixed(6);
    
    var placemark = new ymaps.Placemark(userCoordinates, {}, {
    preset: 'islands#redDotIcon'
    });
    map.geoObjects.add(placemark);
    map.setCenter(userCoordinates);
    });
    
    // Обработчик клика на карту
    map.events.add('click', function (e) {
    // Получаем координаты клика
    var coordinates = e.get('coords');
    
    // Записываем координаты в поле ввода в нужном формате
    var coordinatesInput = document.getElementById('coordinates');
    coordinatesInput.value = 'lat=' + coordinates[0].toFixed(6) + '&lon=' + coordinates[1].toFixed(6);
    
    // Очищаем карту от предыдущих меток
    map.geoObjects.removeAll();
    
    // Создаем метку по указанным координатам
    var placemark = new ymaps.Placemark(coordinates, {}, {
    preset: 'islands#redDotIcon'
    });
    
    // Добавляем метку на карту
    map.geoObjects.add(placemark);
    
    // Центрируем карту по указанным координатам
    map.setCenter(coordinates);
    });
    // Создание экземпляра поисковой панели
    var searchControl = new ymaps.control.SearchControl({
    options: {
    // Замените "searchControlPlaceholder" на id элемента, в который будет встроен поисковый контрол
    size: 'large',
    float: 'none',
    placeholderContent: 'Введите название города'
    }
    });
    
    // Добавление поисковой панели на карту
    map.controls.add(searchControl);
    
    // Обработчик выбора результата поиска
    searchControl.events.add('resultselect', function (e) {
    var results = searchControl.getResultsArray(),
    selected = e.get('index'),
    point = results[selected].geometry.getCoordinates();
    
    // Записываем координаты в поле ввода в нужном формате
    var coordinatesInput = document.getElementById('coordinates');
    coordinatesInput.value = 'lat=' + point[0].toFixed(6) + '&lon=' + point[1].toFixed(6);
    
    // Очищаем карту от предыдущих меток
    map.geoObjects.removeAll();
    
    // Создаем метку по указанным координатам
    var placemark = new ymaps.Placemark(point, {}, {
    preset: 'islands#redDotIcon'
    });
    
    // Добавляем метку на карту
    map.geoObjects.add(placemark);
    
    // Центрируем карту по указанным координатам
    map.setCenter(point);
    });
    });