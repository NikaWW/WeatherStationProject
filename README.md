# WeatherStationProject
Celem projektu było napisanie aplikacji z graficznym interfejsem użytkownika (JavaFX), która 
umożliwi rejestrowanie warunków pogodowych w wybranej przez użytkownika miejscowości z 
określoną przez niego częstotliwością. Minimalne wymagania projektu:
• użytkownik ma możliwość uruchomienia, czasowego wstrzymania i przerwania 
pobierania danych z serwisu openweather;
• komunikacja z serwerem musi odbywać się w osobnym wątku;
• podczas rejestracji dane są na bieżąco wizualizowane za pomocą wykresów;
• podczas rejestracji wyświetlana jest statystka opisowa (liczba pomiarów, wartości 
minimalne i maksymalne, odchylenie standardowe);
• zebrane dane zostają zapisane w formacie JSON;
• program ma umożliwić wczytanie utrwalonych danych i ich wizualizację.
Oprócz spełnienia minimalnych wymagań wzbogacono aplikaje o wyświetlanie flag państwa do 
którego należy miasto z którego pobierana jest pogoda. Dodano zdjęcie zrobione samodzeilnie 
na tło aplikacji, dodano pole wyświetlające zegarek z aktualnym czasem, pole z czasem 
ostatniej aktualizacji pogody oraz pole z temperaturą przedstawioną w Fahrenheita’ch. W 
momencie gdy użytkownik nie wprowadzi częstotliwości czasu rejsetracji warunków 
pogodowych zostanie ona automatycznie ustawiona na jedną minutę oraz ustawiono 
minimalny czas częstotliwość rejestracji na jedną minutę. Ustationo również odpowiednią 
blokadę przycisków aby użytkownik mógł łatwiej posługiwać się aplikacją oraz zapobiec 
możliwym błędom.
