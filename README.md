# Presentación de app 1ra parte
## Descripción:
Se construye una app que reciba datos de articulos mediante API y sea posible para el usuario almacenar items
de esta información dentro de su cuenta

### A continuación se puede encontrar
* Sistema navegable
* integración de sistema de registro e inicio de sesión
* Descripción parcial de como se realizará integración del api (comentarios dispuestos dentro de las activities Main, UnitInfo y UserCards)
* Integración de base de datos, entidad usuario y dao de usuario
* Sistema simple de navegación
* Sistema de seguridad para hasheo, confirmación de clave de usuario y validación simple de email
* Singleton para recuperación global de datos de entidad usuario para posibles usos en la integración de cards.

Falta integración de API, ajustes de interfaz de usuario y construcción de sistema de persistencia para cartas (entidad y dao).

#### Quedo infinitamente agradecido por el tiempo y la atención y estoy atento a las correcciones pertinentes que deba ejecutar.


## Modo de navegación.
* Es necesario primero crear una cuenta, e la pantalla principal hay una opción que lleva a la pantalla de registro.
* Si se vuelve a la pantalla inicial se puede ingresar con las credenciales registradas.
* En la pantalla de la actividad principal que aparece una vez iniciada sesión:
  - se puede optar por oprimir el icono de usuario que lleva al UserCardActivity
  - Se puede optar por oprimir encima de algun item para ir a UnitInfoActivity
Tanto en UserCards como en UnitInfo existe una flecha en el appbar para devolverse
