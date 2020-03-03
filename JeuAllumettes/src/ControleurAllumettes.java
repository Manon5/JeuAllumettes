import java.io.IOException;
import java.net.URL;
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
	
	private int nbAllumettes;
	private int tourNb;
	
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
		
		nbAllumettes = 13;
	}

	@Override
	public void start(Stage arg0) throws Exception {
		tourNb = 1;
		URL fxmlURL = getClass().getResource("/view_allumettes.fxml"); 
		System.out.println(fxmlURL);
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL); 
		try {
			Node node = fxmlLoader.load();
			Scene scene = new Scene((HBox) node, 848, 432);
			arg0.setScene(scene);
			arg0.show();
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		launch(args);
	}

	
	public void retirer1() {
		updateNbAllumettes(1);
	}	
	
	public void retirer2() {
		updateNbAllumettes(2);
	}	
	
	public void retirer3() {
		updateNbAllumettes(3);
	}
	
	public void updateNbAllumettes(int nbRetirer) {
		for(int i = 1; i <= nbRetirer; i++) {
			listAllu.get(nbAllumettes-1).setVisible(false);
			nbAllumettes--;
		}
		
		// On teste si le joueur a gagné
		if(nbAllumettes <= 0) {
			lbl_joueur.setText("Victoire du joueur " + (this.tourNb%2 + 1) + " !");
		}else {
			// sinon on continue le tour
			tourNb++;
			updateTour();
		}
		
		//on désactive les boutons selon le nombre d'allumettes restantes
		if(nbAllumettes < 3) {
			btn_3.setDisable(true);
		}
		if(nbAllumettes < 2) {
			btn_2.setDisable(true);
		}
		if(nbAllumettes < 1) {
			btn_1.setDisable(true);
		}
		
	}
	
	public void updateTour() {
		lbl_joueur.setText("Tour du joueur " + (this.tourNb%2 + 1));
	}
	
	
	
	

}
