# Hulk | Store

La aplicación fue desarrollada con Java 8, Spring Framework, Thymeleaf, JavaScript, Junit 5 y in-memory HsqlDb.

###Features 

* Tiene funcionalidades generales para añadir productos, y realizar operaciones de venta y compra de los mismos.
* Quedo pendiente eliminar y/o editar los prodcutos (con un usuario Administrativo), al igual que añadir, editar y/o eliminar Empleados.
* Se implementó Unit test para los controladores, no se han cubierto todos los métodos.
* Cuenta con un ExceptionHander global para manejar las Excepciones por errores en los datos.

###[Link a la applicación corriendo en Heroku](https://hulk-store-kardex.herokuapp.com/)

###Para ejecutar localmente

1 Clonar el repositorio

```
	git clone https://github.com/BalthazRBlake/hulk-store.git
```

2 Build maven project

```
	mvn clean install
```

3 Ejecutar el jar

```
	java -jar target/hulk-store-0.0.1-SNAPSHOT.jar
```

4 Navegar a la dirección

>	http://localhost:5000/