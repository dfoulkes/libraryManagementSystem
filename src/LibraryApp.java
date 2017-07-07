
/**
 * This will host as the runtime for the new app.
 *
 * @author mr-mou

 *
 */
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;


public class LibraryApp extends Application implements EventHandler<javafx.event.ActionEvent> {

	private static Library library;


	@FXML
	public Button login_btn;
	@FXML
	public Button listbooks_btn;
	@FXML
	public TextField usernameTF;
	@FXML
	public PasswordField passwordPF;
	@FXML
	public ListView<String> listView;

	@FXML private static Stage pStage;
	@FXML public Button addnewbook_btn;
	@FXML public TextField newbook_tf;

	@FXML public ChoiceBox<String> reg_role_chb;
	@FXML public PasswordField reg_password_tf;
	@FXML public TextField reg_username_tf;
	public Button reg_register_btn;
	public Button printreport_btn;
	public Button printinfo_btn;
	public Button checkoutbook_btn;
	public Button checkinbook_btn;
	public Button getuserinfo_btn;
	@FXML
	private Button logout_btn;


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
		System.out.println("LibraryApp started...\n type 'help' to show list of commands");
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {

		pStage = primaryStage;
		listView = new ListView<>();
		Parent login = FXMLLoader.load(getClass().getResource("res/login_ui.fxml"));
		primaryStage.setTitle("Library app");
		primaryStage.setScene(new Scene(login));
		primaryStage.setResizable(false);
		login_btn = new Button();
		addnewbook_btn= new Button();
		newbook_tf = new TextField();
		reg_username_tf = new TextField();
		reg_password_tf = new PasswordField();
		reg_role_chb = new ChoiceBox<String>();
		reg_register_btn = new Button();
		printinfo_btn = new Button();
		checkoutbook_btn = new Button();
		checkinbook_btn = new Button();
		library = new Library();
		primaryStage.show();
	}
	@Override
	public void handle(javafx.event.ActionEvent event) {

		if (event.getSource() == login_btn) {
			library.login(usernameTF.getCharacters().toString(), passwordPF.getCharacters().toString());
		}else if (event.getSource() == listbooks_btn) {
			listLibraryBooks();
		}else if (event.getSource() == addnewbook_btn) {
			library.addNewBook(newbook_tf.getText());
			newbook_tf.setText("");
			listLibraryBooks();
		}else if (event.getSource() == reg_register_btn) {
			library.register(reg_username_tf.getText(), reg_password_tf.getText(), reg_role_chb.getValue(), true);
			reg_password_tf.setText("");
		}else if(event.getSource() == printreport_btn){
			library.printReport();
		}else if(event.getSource() == logout_btn){
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to logout?", ButtonType.YES, ButtonType.CANCEL);
			alert.setGraphic(null);
			alert.setHeaderText(null);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				try {
                    Parent adminInterface = FXMLLoader.load(getClass().getResource("res/login_ui.fxml"));
                    LibraryApp.getpStage().setScene(new Scene(adminInterface));} catch (IOException e) {e.printStackTrace();}
			}
		}else if(event.getSource() == checkoutbook_btn){
			String bookName =listView.getSelectionModel().getSelectedItem();
			if (bookName!=null) {
				Alert alert= new Alert(Alert.AlertType.CONFIRMATION , "Are you sure you want to check out <"+bookName+"> ?", ButtonType.YES, ButtonType.CANCEL);
				alert.setGraphic(null);
				alert.setHeaderText(null);
				alert.setTitle("Checkout");
				alert.showAndWait();
				if(alert.getResult() == ButtonType.YES ){
					library.checkOutBook(bookName);
					listLibraryBooks();
				}
			} else {
				Alert alert= new Alert(Alert.AlertType.INFORMATION , "Please, choose a book from the list.");
				alert.setGraphic(null);
				alert.setHeaderText(null);
				alert.setTitle("Checkout");
				alert.show();
			}
		}else if(event.getSource() == checkinbook_btn){
			library.checkInBook();
		}else if(event.getSource() == printinfo_btn){
			library.printMyInfo();
		}else if(event.getSource() == getuserinfo_btn){
			TextInputDialog dialog = new TextInputDialog();
			Optional<String> result = dialog.showAndWait();
			dialog.setTitle("Get information");
			dialog.setGraphic(null);
			dialog.setHeaderText("Get user info.");
			dialog.setContentText("Username");

			result.ifPresent(name -> library.printUserInfo(result.get()));
		}
	}
	private void listLibraryBooks(){
			listView.getItems().removeAll(listView.getItems());
		for (int i = 0; i < library.listBooks().getItems().size(); i++) {
			listView.getItems().add(library.listBooks().getItems().get(i).toString());
		}
		listView.refresh();

	}

	public static Stage getpStage() {
		return pStage;
	}

}