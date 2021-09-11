package Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ServerSocketGame extends Thread {
    private static ArrayList<BufferedWriter> clients;
    private static ServerSocket server;
    private Socket socket;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public static void main(String[] args) {
        try {
            server = new ServerSocket(2208);
            clients = new ArrayList<BufferedWriter>();

            while(true) {
                System.out.println("Aguardando conexão...");
                Socket socket = server.accept();
                System.out.println("Jogador conectado.");
                Thread thread = new ServerSocketGame(socket);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    ServerSocketGame(Socket socket) {
        this.socket = socket;

        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        try {
            String message;
            OutputStream outputStream = this.socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            clients.add(bufferedWriter);
            message = bufferedReader.readLine();

            while (!"Sair".equalsIgnoreCase(message) && message != null) {
                propagateMessage(bufferedWriter, message);
                message = bufferedReader.readLine();
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void propagateMessage(BufferedWriter bufferedWriterOut, String message) throws IOException {
        BufferedWriter bufferedWriterOutTmp;

        for(BufferedWriter bw: clients) {
            bufferedWriterOutTmp = (BufferedWriter)bw;
            if(!(bufferedWriterOut == bufferedWriterOutTmp)) {
                bw.write(message + "\r\n");
                bw.flush();
            }
        }
    }
}
