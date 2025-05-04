import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

interface HotelServerInterface extends Remote {
    boolean bookRoom(String guestName, int roomNumber) throws RemoteException;
    boolean cancelBooking(String guestName, int roomNumber) throws RemoteException;
}

public class HotelServer extends UnicastRemoteObject implements HotelServerInterface {
    private final Map<Integer, String> roomBookings = new HashMap<>();

    public HotelServer() throws RemoteException {}

    @Override
    public synchronized boolean bookRoom(String guestName, int roomNumber) throws RemoteException {
        if (roomNumber < 1 || roomNumber > 10 || roomBookings.containsKey(roomNumber)) return false;
        roomBookings.put(roomNumber, guestName);
        return true;
    }

    @Override
    public synchronized boolean cancelBooking(String guestName, int roomNumber) throws RemoteException {
        return roomBookings.remove(roomNumber, guestName);
    }

    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("rmi://localhost/HotelServer", new HotelServer());
            System.out.println("HotelServer is running.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}