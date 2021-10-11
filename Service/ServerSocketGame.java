package Service;

import Interfaces.CRClient;
import Interfaces.CRServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

class ServerSocketGame extends UnicastRemoteObject implements CRServer {
    private static ArrayList<Player> clients;

    public ServerSocketGame() throws RemoteException {
        super();
        System.out.println("[SERVER] SERVER INVOCATED");
    }

    public static void main(String[] args) {
        try {
            clients = new ArrayList<Player>();
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.setProperty("java.security.policy", "security.policy");
            Registry rgsty = LocateRegistry.createRegistry(1099);
            rgsty.rebind("mainServer", new ServerSocketGame());
            System.out.println("[SERVER] Servidor ativo e operante...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void connect(Player player) throws RemoteException {
        clients.add(player);
        try {
            CRClient crClient = (CRClient) Naming.lookup("//127.0.0.1/" + player.getNome());
            player.setConn(crClient);
        } catch(NotBoundException e) {
            System.out.println(e.getMessage());
            System.out.println("[SERVER] CATCH BOUND");
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            System.out.println("[SERVER] CATCH MALFORMED");
        }

        System.out.println("[CLIENT] Cliente conectado!!!");
    }

    @Override
    public void sendMessage(Player player, String msg) throws RemoteException {
        for(Player p: clients) {
            System.out.println("[SERVER] SENDMESSAGE INSIDE SERVER");
            System.out.println("[SERVER] a mensagem :"+msg);
            p.getConn().receiveMessage(msg + "\r\n");
        }
    }

    @Override
    public void disconnect(Player player) throws RemoteException {
        clients.remove(player);
    }
}
