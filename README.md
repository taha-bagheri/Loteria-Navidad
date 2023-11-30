# Proyecto Lotería de Navidad

Este proyecto simula una lotería de Navidad mediante una conexión de sockets entre un servidor y un cliente implementados en Java. El servidor tiene un número ganador fijo y clasifica los números enviados por el cliente en diferentes categorías de premios.

## Funcionalidad

El servidor genera un número ganador al inicio del juego (en este caso, se establece como 55555). Luego, espera la conexión de clientes.

El cliente puede enviar números al servidor para comprobar si ganan algún premio. El servidor clasifica los números en las siguientes categorías de premios:

1. **Premio Gordo**
2. **Número Anterior**
3. **Número Posterior**
4. **Centenas**
5. **Dos últimas cifras**
6. **Reintegro**
7. **Sin premio**

El cliente puede enviar múltiples números para verificar premios hasta que decide salir ingresando 0.

## Instrucciones de Uso

1. **Ejecutar el Servidor:**
   - Compile y ejecute `LotteryServer.java`.
   - El servidor esperará conexiones en el puerto 12345.

2. **Ejecutar el Cliente:**
   - Compile y ejecute `LotteryClient.java`.
   - Ingrese números para comprobar premios.
   - Ingrese 0 para salir y cerrar la conexión.

## Requisitos

- Java JDK instalado.

