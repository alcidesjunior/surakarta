package Service;

import Interfaces.CRClient;
import Interfaces.CRServer;
import View.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;

import com.google.gson.*;

public class ClientSocketGame extends UnicastRemoteObject implements CRClient {
    private static SurakartaView surakartaView;
    private String name;
    private CRServer conn;
    static Player player = new Player();

    ClientSocketGame() throws RemoteException {
        super();
    }

    public void buildView(ClientSocketGame clientSocketGame) {
        surakartaView = new SurakartaView(clientSocketGame);
    }

    public static void main(String[] args) {
        try {
            ClientSocketGame clientSocketGame = new ClientSocketGame();
            clientSocketGame.conn = (CRServer) Naming.lookup("//127.0.0.1/mainServer");
            clientSocketGame.buildView(clientSocketGame);

            try {
                Naming.rebind(clientSocketGame.getName(), new ClientSocketGame());
                player.setNome(clientSocketGame.getName());
                clientSocketGame.conn.connect(player);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("[CLIENT] ERROR 1");
                e.printStackTrace();
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            System.out.println("[CLIENT] ERROR 2");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void sendMessage(String message) throws RemoteException {
        if(message.equals("Sair")) {
            String payload = "{'communicationType': 'message', 'message': '\n====================================================\n🤡O jogador ["+name + "] Desistiu do jogo, você venceu!\n====================================================\n', 'moveTo': null, 'dot': null, 'youTurn': null}";
            conn.sendMessage(player, payload);
        }
        try {
            conn.sendMessage(player, message);
            System.out.println("[CLIENT] Mensagem..."+message);
            conn.disconnect(player);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("[CLIENT] SENDMESSAGE EXCEPTION");
        }
    }

    @Override
    public void receiveMessage(String msg) throws RemoteException {
        System.out.println("[CLIENT] RECEIVEMESSAGE");
        CommunicationModel communicationModel = new Gson().fromJson(
                msg,
                CommunicationModel.class
        );

        if (communicationModel.getCommunicationType().equals("moviment")) {
            surakartaView.movePlayer(communicationModel.getDot(), communicationModel.getMoveTo());
            surakartaView.setTurno(communicationModel.getYouTurn());
        } else if (communicationModel.getCommunicationType().equals("message")) {
            System.out.println("[CLIENT] A MENSAGEM DEVERIA SER APENDADA");
            System.out.println("[CLIENT]"+communicationModel.getMessage());
            surakartaView.appendMessageToTextArea(communicationModel.getMessage());
        }
    }

    public void exit() throws RemoteException {
        this.sendMessage("Sair");
    }
}
