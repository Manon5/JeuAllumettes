import java.rmi.Remote;
import java.rmi.RemoteException;
public interface AllumettesInterface extends Remote{
	
	public int getNbAllumettes(int idPartie) throws RemoteException;
	public boolean EstMonTour(int id, int idPartie) throws RemoteException;
	public void retirer(int nbAl, int idPartie) throws RemoteException;
	public boolean partieFinie(int idPartie) throws RemoteException;
	public int getGagnant(int idPartie) throws RemoteException;
	public int[] joinPartie() throws RemoteException;
	public boolean enAttente(int idP) throws RemoteException;

}
