import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ControleurAllumettes extends Application implements Initializable{

	@FXML private ImageView al1;
	@FXML private ImageView al2;
	@FXML private ImageView al3;
	@FXML private ImageView al4;
	@FXML private ImageView al5;
	
	@FXML private ImageView al6;
	@FXML private ImageView al7;
	@FXML private ImageView al8;
	@FXML private ImageView al9;
	@FXML private ImageView al10;
	
	@FXML private ImageView al11;
	@FXML private ImageView al12;
	@FXML private ImageView al13;
	

	@FXML private Label lbl_joueur;
	
	
	@FXML private Button btn_1;
	@FXML private Button btn_2;
	@FXML private Button btn_3;
	
	ArrayList<ImageView> listAllu; 
	
	private int idJoueur;
	private int idPartie;
	private int choix;
	AllumettesInterface jeu;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listAllu = new ArrayList<ImageView>();
		listAllu.add(al1);
		listAllu.add(al2);
		listAllu.add(al3);
		listAllu.add(al4);
		listAllu.add(al5);

		listAllu.add(al6);
		listAllu.add(al7);
		listAllu.add(al8);
		listAllu.add(al9);
		listAllu.add(al10);
		
		listAllu.add(al11);
		listAllu.add(al12);
		listAllu.add(al13);
		
		try {
			   int port = 8000;
			   jeu = (AllumettesInterface) Naming.lookup("rmi://localhost:8000/allu");
			   int test = jeu.getGagnant(0);
			   int []info = jeu.joinPartie();
			   // on rÈcupËre les infos
			   idPartie = info[0];
			   idJoueur = info[1];
			   
		} catch (Exception e) {
			   System.out.println("Client exception: " + e);
		}
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		URL fxmlURL = getClass().getResource("/view_allumettes.fxml"); 
		System.out.println(fxmlURL);
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL); 
		try {
			Node node = fxmlLoader.load();
			Scene scene = new Scene((HBox) node, 848, 432);
			arg0.setScene(scene);
			arg0.show();
			
		} catch (IOException e) {
			// TODO Bloc catch g√©n√©r√© automatiquement
			e.printStackTrace();
		}
		
	}

	
	public void retirer1() {
		try {
			jeu.retirer(1, idPartie);
			updateTour();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateNbAllumettes();
	}	
	
	public void retirer2() {
		try {
			jeu.retirer(2, idPartie);
			updateTour();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateNbAllumettes();
	}	
	
	public void retirer3() {
		try {
			jeu.retirer(3, idPartie);
			updateTour();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateNbAllumettes();
	}
	
	public void updateNbAllumettes() {
		int nbAlu;
		try {
			nbAlu = jeu.getNbAllumettes(idPartie);
			
			for(int i = 1; i <= nbAlu; i++) {
				listAllu.get(i-1).setVisible(true);
			}
			for(int i = nbAlu; i <= 13; i++) {
				listAllu.get(i-1).setVisible(false);
			}
			
			// On teste si le joueur a gagn√©
			if(nbAlu <= 0) {
				
				if(jeu.EstMonTour(idJoueur, idPartie)){
					lbl_joueur.setText("Vous avez gagnÈ!");
				}else{
					lbl_joueur.setText("Vous avez perdu!");
				}
				
			}else {
				updateTour();
			}
			
			//on d√©sactive les boutons selon le nombre d'allumettes restantes
			if(nbAlu < 3) {
				btn_3.setDisable(true);
			}
			if(nbAlu < 2) {
				btn_2.setDisable(true);
			}
			if(nbAlu < 1) {
				btn_1.setDisable(true);
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateTour() {
		try {
			lbl_joueur.setText("En attente de l'adversaire...");
			btn_1.setDisable(true);
			btn_2.setDisable(true);
			btn_3.setDisable(true);
			while(jeu.EstMonTour(idJoueur, idPartie)){
				Thread.sleep(100);
			}
			btn_1.setDisable(false);
			btn_2.setDisable(false);
			btn_3.setDisable(false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
