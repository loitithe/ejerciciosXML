###############################################################################################
Ejercicio 601
Se pide desarrollar una aplicación Java que permita:

Conectarse a una base de datos XML gestionada por BaseX denominada ejercicio601
El usuario introducirá datos en el sistema de forma que complete un XML con la siguiente estructura:
<alumno>
  <nombre></nombre>
  <apellidos></apellidos>
  <edad></edad>
  <correo></correo>
</alumno>
El sistema pedirá valores de forma indefinida hasta que el usuario indique lo contrario.
Cada vez que se cree un fichero XML se añadirá a la base de datos.
Una vez finalizada la introducción de datos el sistema mostrará un menú con las siguientes opciones:

Número total de documentos en el sistema
Media de edad de los alumnos.
Mostrar un XML con la edad del alumno mayor y del alumno menor (todo en el mismo documento XML).
Mostrar el nombre de los alumnos ordenado por edad de mayor a menor.
Mostrar el nombre de un alumno de forma aleatoria.
##########################################################################################
Ejercicio 602
1.Almacenar un único fichero muy grande que contiene todos los elementos principales.

2.Almacenar muchos ficheros que contenga un único elemento a almacenar.
Crear dos bases de datos: ejercicio602FicheroGrande y ejercicio602FicherosPequeños.
Se crearán 10000 elementos libro que tengan la estructura del ejemplo anterior.
Los datos de cada uno de los elementos anteriores se generarán de forma aleatoria. Para ello se seguirán las siguientes directrices:
Los elementos que sean cadenas de texto estarán formadas de la siguiente forma: Nombre18, Editorial12, etc
Publicación: entre 2000 y 2014.
Edición: entre 1 y 20
Titulo: entre 1 y 20000.
Nombre: entre 0 y 19
Apellidos: formado por dos apellidos con posibles valores entre 0 y 19, ejemplo: Apellido2 Apellido17.
Editorial: entre 0 y 100
Paginas: entre 150 y 850
ediciónElectrónica: verdadero o falso
Para evitar distorsionar los resultados el contenido de todos los elementos de fichero grande y los ficheros pequeños tendrá que ser el mismo.
Una vez creadas las dos base de datos se ejecutarán las siguientes consultas:

Número total de documentos.
Número de libros publicados antes de 2009
Número de libros que escribió un determinado autor
Número medio de palabras de todos los libros
Número medio de palabras de los libros publicados después de 2008 y escritos por un determinado autor.
Al final, se deberá completar la siguiente tabla con el tiempo que tarda cada una de las consultas, de forma que nos permita inferir cual de las dos opciones es mejor.

################################################################################################
Ejercicio 603
Se pide desarrollar una aplicación Java que permita gestionar bases de datos. Para ello el sistema debe permitir:

Gestión de bases de datos:

Crear, si no existe, una base de datos con el nombre que elija el usuario.
Utiliza una base de datos ya existente en el caso de que exista.
Eliminar una base de datos.
Gestionar los documentos de las bases de datos:

Crear una estructura XML para los documentos de esa base de datos.
Añadir nuevos documentos siguiendo la estructura XML.
Modificar los valores de un documento.
Eliminar un documento.
Para cada base de datos se debe poder:

Consultar el número de documentos.
Listar la información por cada campo que tenga la estructura XML.

######################################################################################################################################################################
Ejercicio 604
Consideremos un documento XML con las siguientes características:

El elemento raíz es biblioteca. Contiene un elemento libros.
El elemento libros contiene varios elementos libro.
Un elemento libro tiene los siguientes elementos:
titulo
autor: puede haber más de uno. Contiene los elementos nombre y apellidos.
editorial
paginas
edicionElectronica: elemento opcional para indicar si hay edición electrónica.
Además, contiene los atributos publicacion y edicion (opcional).
Escribe las consultas XQuery que permitan obtener la siguiente información así como el código Java que permita ejecutarlas:

Título y editorial de todos los libros.
Los datos de cada libro deben estar dentro de un elemento <libro>.
El título y la editorial de cada libro deben estar separados por un guión medio (-).
El título de todos los libros de menos de 400 páginas.
Se debe obtener únicamente los datos, sin etiquetas.
La cantidad de libros de más de 400 páginas.
Una lista HTML con el título de los libros de la editorial O'Reilly Media ordenados por título.
Título y editorial de los libros de 2018 y 2019.
Los datos de cada libro deben estar dentro de un elemento <libro>.
El título y la editorial deben ir dentro de los elementos <titulo> y <editorial> respectivamente.
Título y editorial de los libros con más de un autor.
Los datos de cada libro deben estar dentro de un elemento <libro>.
El título y la editorial deben ir dentro de los elementos <titulo> y <editorial> respectivamente.
Título y año de publicación de los libros que tienen versión electrónica.
Los datos de cada libro deben estar dentro de un elemento <libro>.
El título y el año de publicación deben ir dentro de los elementos <titulo> y <fecha-publicacion> respectivamente.
Título de los libros que no tienen versión electrónica.
Se debe obtener únicamente los datos, sin etiquetas
