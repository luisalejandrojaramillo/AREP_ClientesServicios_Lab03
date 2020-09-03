# AREP_ClientesServicios_Lab03
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

## Reto 1
Escriba un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor debe retornar todos los archivos solicitados, incluyendo páginas html e imágenes. Construya un sitio web con javascript para probar su servidor. Despliegue su solución en Heroku. NO use frameworks web como Spark o Spring use solo Java y las librerías para manejo de la red.

## Reto 2 (Avanzado)
Usando su  servidor y java (NO use frameworks web como Spark o Spring). Escriba un framework similar a Spark que le permita publicar servicios web "get" con funciones lambda y le permita acceder a recursoso estáticos como páginas, javascripts, imágenes, y CSSs. Cree una aplicación que conecte con una base de datos desde el servidor para probar su solución. Despliegue su solución en Heroku.

## License
[MIT License ](/LICENSE)
## Autor
Luis Alejandro Jaramillo Rincon
