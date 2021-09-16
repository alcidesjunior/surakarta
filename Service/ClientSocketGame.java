package Service;

import View.*;

import java.io.*;
import java.net.Socket;
import com.google.gson.*;

public class ClientSocketGame {
    private Socket socket;
    private OutputStream outputStream;
    private Writer writer;
    private BufferedWriter bufferedWriter;
    private SurakartaView surakartaView;
    private String name;

    public static void main(String[] args) throws IOException {
        ClientSocketGame clientSocketGame = new ClientSocketGame();
        clientSocketGame.connect();
        clientSocketGame.listenMessage();
    }

    ClientSocketGame() {
        surakartaView = new SurakartaView(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void connect() throws IOException {
        socket = new Socket("127.0.0.1",2208);
        outputStream = socket.getOutputStream();
        writer = new OutputStreamWriter(outputStream);
        bufferedWriter = new BufferedWriter(writer);
    }

    public void sendMessage(String message) throws IOException {
        if(message.equals("Sair")) {
            String payload = "{'communicationType': 'message', 'message': 'O jogador ["+name + "] Desistiu do jogo, você venceu!', 'moveTo': null, 'dot': null, 'youTurn': null}";
            bufferedWriter.write(payload);
        } else {
            bufferedWriter.write(message+"\r\n");
        }
        bufferedWriter.flush();
    }

    public void listenMessage() throws IOException {
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReaderTmp = new BufferedReader(inputStreamReader);
        String message = "";

        while(!"Sair".equalsIgnoreCase(message)) {
            if(inputStreamReader.ready()) {
                message = bufferedReaderTmp.readLine();

                if(message.equals("Sair")) {
                    surakartaView.appendMessageToTextArea("Servidor caiu! ");
                } else {
                    CommunicationModel communicationModel = new Gson().fromJson(message, CommunicationModel.class);

                    if(communicationModel.getCommunicationType().equals("moviment")) {
                        surakartaView.movePlayer(communicationModel.getDot(), communicationModel.getMoveTo());
                        surakartaView.setTurno(communicationModel.getYouTurn());
                    } else if(communicationModel.getCommunicationType().equals("message")) {
                        surakartaView.appendMessageToTextArea(communicationModel.getMessage());
                    }
                }
            }
        }
    }

    public void exit() throws IOException {
        sendMessage("Sair");
        bufferedWriter.close();
        outputStream.close();
        writer.close();
        socket.close();
    }
}
