# AREP_ClientesServicios_Lab03

## Para probarlo con Heroku
* [![CircleCI](https://circleci.com/gh/luisalejandrojaramillo/AREP_ClientesServicios_Lab03.svg?style=svg)](https://circleci.com/gh/luisalejandrojaramillo/AREP_ClientesServicios_Lab03)

* [![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://clientserverarep.herokuapp.com/)

## Requisitos
* Git
* Java 8
* Maven

## Instalación
1. Abrimos una terminal
2. Clonamos el repositorio
```
git clone https://github.com/luisalejandrojaramillo/AREP_ClientesServicios_Lab03
```
3. Entramos al directorio
```
cd AREP_ClientesServicios_Lab03
```
4. Empaquetamos
```
mvn package
```
5. Ejecutamos
```
java -cp target/AREP_ClientesServicios_Lab03-1.0-SNAPSHOT.jar edu.escuelaing.arep.App
```
## Arquitectura
![Arquitectura](/img/modelAREP.PNG)

## Prueba DB
![Database](/img/database.PNG)

## Otros Ejemplos
* https://clientserverarep.herokuapp.com/arbol.jpg
* https://clientserverarep.herokuapp.com/libro.png
* https://clientserverarep.herokuapp.com/html.html (Contiene el JS)
* https://clientserverarep.herokuapp.com/text.txt

## Reto 1
Escriba un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos los archivos solicitados, incluyendo páginas html e imágenes. Construya un sitio web con javascript para probar su servidor. Despliegue su solución en Heroku. NO use frameworks web como Spark o Spring use solo Java y las librerías para manejo de la red.

## Reto 2 (Avanzado)
Usando su  servidor y java (NO use frameworks web como Spark o Spring). Escriba un framework similar a Spark que le permita publicar servicios web "get" con funciones lambda y le permita acceder a recursoso estáticos como páginas, javascripts, imágenes, y CSSs. Cree una aplicación que conecte con una base de datos desde el servidor para probar su solución. Despliegue su solución en Heroku.

## Informe
[Informe](/InformeLab03AREP.pdf)

## License
[MIT License ](/LICENSE)
## Autor
Luis Alejandro Jaramillo Rincon
