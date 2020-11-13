# Fiera: tracker

## Índice

+ [Patrones/Arquitectura utilizados de ser necesario](#Patrones/Arquitectura utilizados de ser necesario)
+ [Instalación](#instalacion)
+ [Como se optimizaria la implementacion y la base de datos para publicar en producción](#Como se optimizaria la implementacion y la base de datos para publicar en producción)

+ [Diagramas](#diagramas)

<a name="Patrones/Arquitectura utilizados de ser necesario"></a>
## Patrones/Arquitectura utilizados de ser necesario
El proyecto ***fiera tracker*** utiliza el patron de arquitectura MVC y el patron de diseño Proxy

<a name="instalacion"></a>
## Instalación

- se debe clonar el proyecto desde github : https://github.com/aguilarjuan/tracker.git
- desde el directorio donde se instalo el profecto debe ejecutar el comando : mvn spring boot run
- luego que la aplicacion levantada va a estar escuchando en la direccion: http://localhost:8080/
- la mejor forma para probar la API rest es utilizando la herramienta Postman, usted puede utilizar directamente
- una casuística ya configurada solo debe importar el archivo .json que se encuentra en la ruta interna: ***src/test/resources/postman*** e exportarlo a la herramienta Postman

- [acceso a la base de la aplicacion] la aplicacion utiliza una instancia gratuita en la nube de MongoDB
- si se quiere conectar a base con el cliente de mongo llamado ***mondoDB compass*** simpremente cree una nueva coneccion y agrege la url ***mongodb+srv://fiera:Kk8JDahjzn6Chb7M@cluster0.fl4m7.mongodb.net/fiera?retryWrites=true&w=majority***    

<a name="Como se optimizaria la implementacion y la base de datos para publicar en producción"></a>
## Como se optimizaria la implementacion y la base de datos para publicar en producción
- a nivel de implementacion seria buena una alterntiva pensar en contener sierta informacion en memoria cache
- el agregado de test de integracion mediante algunas herramientas como el ***framework Karate*** permitiria tener major calidad de codigo 
- en produccion la base de datos MongoDB que se utiliza, podria tener un sistema de distribucion de carga  

<a name="diagramas"></a>
## Diagramas
[Diagrama de clases] se realizo diagrama de clases simplificado para poder observas las clases mas importantes dentro del sistema
---

- el diagrama se encuentra de la ruta interna: ***src/main/resources/static***


