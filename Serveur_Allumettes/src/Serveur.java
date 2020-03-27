import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class Serveur{
	
	public static void main(String[] argv) {
		try{
			
			int port = 8000;
			   LocateRegistry.createRegistry(port);
			   Naming.rebind("rmi://localhost:8000/allu", new AllumettesImpl());
			   System.out.println("Serveur allumettes en attente de connexions...");
		}catch(Exception e){
			System.out.println("Server exception: " + e);
		}
	}

}
