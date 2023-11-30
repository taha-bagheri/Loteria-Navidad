package clases;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LotteryServer {
    private static final int NUMERO_GANADOR = 55555;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Puerto de conexión

            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Espera a que un cliente se conecte
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

                handleClient(clientSocket);

                // Salir del bucle cuando se cierra la conexión del cliente
                break;
            }

            // Cerrar el servidor después de salir del bucle
            serverSocket.close();
            System.exit(0); // Terminar la ejecución completa del programa
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String receivedNumberString = new String(buffer, 0, bytesRead);
                int receivedNumber = Integer.parseInt(receivedNumberString);

                if (receivedNumber == 0) {
                    // Cerrar la conexión y salir del bucle
                    clientSocket.close();
                    break;
                }

                String resultMessage = checkPrize(receivedNumber, NUMERO_GANADOR);
                System.out.println("Número recibido: " + receivedNumber + ", Resultado: " + resultMessage);
                outputStream.write(resultMessage.getBytes());
            }

            // Cierre del socket movido fuera del bucle
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String checkPrize(int numero, int numeroGanador) {
        if (numero == numeroGanador) {
            return "Premio Gordo";
        } else if (numero == numeroGanador - 1) {
            return "Número Anterior";
        } else if (numero == numeroGanador + 1) {
            return "Número Posterior";
        } else if (numero / 1000 == numeroGanador / 1000) {
            return "Centenas";
        } else if (numero % 100 == numeroGanador % 100) {
            return "Dos últimas cifras";
        } else if (numero % 10 == numeroGanador % 10) {
            return "Reintegro";
        } else {
            return "Sin premio";
        }
    }
}
