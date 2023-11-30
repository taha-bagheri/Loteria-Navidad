package clases;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class LotteryClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Conecta al servidor

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Ingresa tu número (o 0 para salir): ");
                int userNumber = scanner.nextInt();

                outputStream.write(String.valueOf(userNumber).getBytes());
                if (userNumber == 0) {
                    // Cerrar conexión y salir del bucle
                    socket.close();
                    System.exit(0);  // Terminar la ejecución completa del programa
                    break; // Aunque no debería llegar aquí, por precaución
                }

                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);
                if (bytesRead == -1) {
                    break; // Si la lectura es -1, la conexión se cerró
                }

                String resultMessage = new String(buffer, 0, bytesRead);
                System.out.println("Resultado: " + resultMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
