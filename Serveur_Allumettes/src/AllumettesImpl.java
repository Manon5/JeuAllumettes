import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.server.UnicastRemoteObject;


public class AllumettesImpl extends UnicastRemoteObject implements AllumettesInterface{
	
	

	private ArrayList<Integer> tour;
	private ArrayList<Integer> idJoueur1;
	private ArrayList<Integer> idJoueur2;
	private ArrayList<Integer> nbAllumettes;
	private int idJeu;
	private ArrayList<Boolean> partieEnAttente;
	
	public AllumettesImpl() throws RemoteException {
		super();
		idJeu = 0;
		tour = new ArrayList<Integer>();
		idJoueur1 = new ArrayList<Integer>();
		idJoueur2 = new ArrayList<Integer>();
		nbAllumettes = new ArrayList<Integer>();
		partieEnAttente = new ArrayList<Boolean>();
		partieEnAttente.add(false);
	}
	public int getNbAllumettes(int idPartie) throws RemoteException {
		return nbAllumettes.get(idPartie);
	}
	
	public boolean EstMonTour(int id, int idPartie) throws RemoteException {
		if(tour.get(idPartie)%2 == 1){
			return (id == idJoueur1.get(idPartie));
		}else{
			return (id == idJoueur2.get(idPartie));
		}
	}
	
	public void retirer(int nbAl, int idPartie) throws RemoteException {
		nbAllumettes.set(idPartie,(nbAllumettes.get(idPartie) - nbAl));
		tour.set(idPartie, (tour.get(idPartie) + 1));
	}
	
	public boolean partieFinie(int idPartie) throws RemoteException {
		return (nbAllumettes.get(idPartie) == 0);
	}
	
	public int getGagnant(int idPartie) throws RemoteException {
		return 0;
	}

	public int[] joinPartie() throws RemoteException {
		if(partieEnAttente.get(idJeu)){
			int[] info = {idJeu, 2};
			partieEnAttente.set(idJeu, false);
			return info;
		}else{
			if(idJeu != 0){
				idJeu++;
			}
			tour.add(1);
			idJoueur1.add(1);
			idJoueur2.add(2);
			nbAllumettes.add(13);
			int[] info = {idJeu, 1};
			if(idJeu == 0){
				partieEnAttente.set(0, true);
			}else{
				partieEnAttente.add(true);
			}
			return info;
			
		}
		
	}

	public boolean enAttente(int idP) throws RemoteException {
		return partieEnAttente.get(idP);
	}




	

}
