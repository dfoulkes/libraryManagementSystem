import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import objects.Book;
import objects.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by mr-mou on 05/07/17.
 */
public class Library {



    private static String uUserName;
    private static String uPassword;
    private static ArrayList<User> users;
    private static ArrayList<Book> checkedOutBooks;
    private static ArrayList<Book> libraryBooks;
    private static String uRole;
    private static int uUserIndex;

    public Library(){
        System.out.println("LibraryApp started...\n type 'help' to show list of commands");
        libraryBooks = new ArrayList<Book>(0);
        users = new ArrayList<User>(0);
        checkedOutBooks = new ArrayList<Book>(0);

        for (int i=0; i<11; i++){
            Book book = new Book("book:"+i,null, null);
            libraryBooks.add(book);
        }

        register("u" ,"u", "User" , false);
        register("a" ,"a", "Admin", false);

    }

    public void register(String username, String password, String role, Boolean notify){
        Alert alert;

        Boolean userNameIsAvailable =true;

        for(User user0 : users){
            if(user0.getUsername().equals(username)){userNameIsAvailable=false; break;}
        }

        if(username.isEmpty() || password.isEmpty()){
            alert =new Alert(Alert.AlertType.ERROR, "These fields cannot be empty!");
            alert.setTitle("Register");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.show();
        }else if (userNameIsAvailable) {
            User o = new User(username, password, role, null);
            users.add(o);
            if (notify) {
                alert = new Alert(Alert.AlertType.INFORMATION, role + "<" + username + "> Registered successfully!");
                alert.setTitle("Register");
                alert.setGraphic(null);
                alert.setHeaderText(null);
                alert.show();
            }
        }else{
            alert =new Alert(Alert.AlertType.ERROR, "Username:<"+username+"> Already exists!");
            alert.setTitle("Register");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.show();}
    }

    public void login( String username, String password) {
        Alert alert;
        User user=null;
        for (User user0 : users) {
            if(user0.getUsername().equals(username) && user0.getPassword().equals(password))user=user0;
        }
        try {
            uRole = user.getRole();
            uUserName = username;
            uPassword = password;

            if (uRole.equals("Admin")) {
                Parent adminInterface = FXMLLoader.load(getClass().getResource("res/admin_interface.fxml"));
                LibraryApp.getpStage().setScene(new Scene(adminInterface));
            } else {
                Parent adminInterface = FXMLLoader.load(getClass().getResource("res/user_interface.fxml"));
                LibraryApp.getpStage().setScene(new Scene(adminInterface));
            }
        } catch (NullPointerException e) {
            alert = new Alert(Alert.AlertType.ERROR, "User name or Password is incorrect!");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ListView listBooks() {

        ListView<String> items = new ListView<String>();
        for (Book book : libraryBooks) {
            items.getItems().add(book.getBookName());

        }

        return items;
    }

    public void addNewBook(String bookName) {
        Alert alert;
        Boolean bookAlreadyExists = false;
        Book book=null;
        for(Book book1 : libraryBooks){
            if(bookName.equals(book1.getBookName())){
                bookAlreadyExists=true;
                break;
            } else book = new Book(bookName, null, null);
        }
        if (bookName.isEmpty() || bookName.equals("") ){
            alert =new Alert(Alert.AlertType.ERROR, "Please enter book name!");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.show();
        }else if (bookAlreadyExists){
            alert =new Alert(Alert.AlertType.ERROR, "Book already exists!");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.show();
        }else if(bookName.length()>21){
            alert =new Alert(Alert.AlertType.ERROR, "This book name is too long!");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.show();
        }else {
            alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to add <"+bookName+"> to the Library ?", ButtonType.YES,ButtonType.CANCEL);
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                libraryBooks.add(book);
                alert =new Alert(Alert.AlertType.INFORMATION,"<"+book.getBookName() + "> Successfully added to the library!");
                alert.setGraphic(null);
                alert.setHeaderText(null);
                alert.show();
            }
        }
    }

    public void printUserInfo(String username) {
        Integer index = null;
        for (int i=0; i<users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                index=i;
                break;
            }
        }
        try {
            StringBuilder checkedOutBooks= new StringBuilder();
            ArrayList<Book> backpack = users.get(index).getBackpack();
            if (backpack!=null) {
                for (int i = 0; i < backpack.size(); i++) {
                    checkedOutBooks.append(i + 1).append(") ").append(backpack.get(i).getBookName());
                    i++;
                }
            }else checkedOutBooks.append("<").append(username).append("> haven't checked out any books!");
            String content=
                    checkedOutBooks
                    +"\n Password: " + users.get(index).getPassword()
                    + "\n Role: " + users.get(index).getRole();

            Dialog reportDialog = new Dialog();
            Window window = reportDialog.getDialogPane().getScene().getWindow();
            reportDialog.setTitle("Report");
            TextArea textArea = new TextArea(content.toString());
            Button exitBtn = new Button("Exit");
            exitBtn.setOnAction(event -> window.hide());
            textArea.setEditable(false);
            textArea.setWrapText(false);
            GridPane container = new GridPane();
            container.add(textArea, 0, 0);
            container.add(exitBtn, 0, 1);
            exitBtn.setAlignment(Pos.BOTTOM_RIGHT);
            reportDialog.getDialogPane().setContent(container);
            reportDialog.show();

        } catch (NullPointerException e) {
            Alert alert= new Alert(Alert.AlertType.ERROR , "User! <"+username+"> doesn't exist!");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.setTitle("Get information");
            alert.show();
        }

    }

    public void printReport(){
        Alert alert;
        if(checkedOutBooks.size() != 0){
            StringBuilder content= new StringBuilder();
            content.append("Today is: ").append(currentTime());
            for (Book book : checkedOutBooks) {
            String details= "BookName:"+book.getBookName()+" User:"+book.getBorrowerName()+" CheckedOutOn:"+book.getCheckedOutDate();
            content.append("\n").append(details);}
            Dialog reportDialog = new Dialog();
            Window window = reportDialog.getDialogPane().getScene().getWindow();
            reportDialog.setTitle("Report");
            TextArea textArea = new TextArea(content.toString());
            Button exitBtn = new Button("Exit");
            exitBtn.setOnAction(event -> window.hide());
            textArea.setEditable(false);
            textArea.setWrapText(false);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane container = new GridPane();
            container.setMaxWidth(Double.MAX_VALUE);
            container.add(textArea, 0, 0);
            container.add(exitBtn, 0, 1);
            exitBtn.setAlignment(Pos.BOTTOM_RIGHT);
            reportDialog.getDialogPane().setContent(container);
            reportDialog.show();
        }else{
            alert= new Alert(Alert.AlertType.INFORMATION, "No checked out books!");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.show();

        }

    }

    public void checkOutBook(String bookName) {
        Book book=null;
        for(Book book0 : libraryBooks){

            if(book0.getBookName().equals(bookName)){book=book0; break;}
        }

        try {
            libraryBooks.remove(book);
            book.setBorrowerName(uUserName);
            book.setCheckedOutDate(currentTime());
            ArrayList<Book> backpack = users.get(uUserIndex).getBackpack();
            User user =new User(uUserName, uPassword, uRole, backpack);
            try {
                backpack.add(book);
                user.setBackpack(backpack);
            } catch (NullPointerException e) {
                backpack=new ArrayList<Book>(0);
                backpack.add(book);
                user.setBackpack(backpack);
            }
            users.set(uUserIndex, user);
            checkedOutBooks.add(book);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
        }
    }

    public void checkInBook() {

        ListView dialogList = new ListView();
        Dialog checkInDialog = new Dialog();
        Button checkInBtn = new Button("Check in this book");
        Button exitBtn = new Button("Exit");
        ArrayList<Book> backpack = users.get(uUserIndex).getBackpack();
        if (backpack!=null && backpack.size()!=0) {
            for (Book bbBook : backpack){
                dialogList.getItems().add(bbBook.getBookName());
            }
            dialogList.refresh();
            Window window = checkInDialog.getDialogPane().getScene().getWindow();
            checkInDialog.setTitle("Report");
            exitBtn.setOnAction(event -> window.hide());
            dialogList.setMaxWidth(Double.MAX_VALUE);
            dialogList.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(dialogList, Priority.ALWAYS);
            GridPane.setHgrow(dialogList, Priority.ALWAYS);
            GridPane container = new GridPane();
            container.setMaxWidth(Double.MAX_VALUE);
            container.setAlignment(Pos.CENTER);
            container.add(dialogList, 0, 0);
            HBox hBox =new HBox(exitBtn, checkInBtn);
            container.add(hBox ,0,1);
            exitBtn.setAlignment(Pos.BOTTOM_RIGHT);
            checkInDialog.getDialogPane().setContent(container);
            final ListView finalDialogList= dialogList;
            checkInBtn.setOnAction(event -> {
               Book book = null;
               for (Book bBook : backpack) {
                   if(bBook.getBookName().equals(dialogList.getSelectionModel().getSelectedItem())){
                       book =bBook;
                       break;
                   }
               }
               backpack.remove(book);
               users.get(uUserIndex).setBackpack(backpack);
               book.setBorrowerName(null);
               book.setCheckedOutDate(null);
               libraryBooks.add(book);
               //get the object by index then remove
               int counter = 0;
               for (Book cBook : checkedOutBooks) {
                   if(cBook.getBookName().equals(book.getBookName())){
                       checkedOutBooks.remove(cBook);
                       break;}
               }
               dialogList.getItems().removeAll(dialogList.getItems());
               for (Book bbBook : backpack){
                   dialogList.getItems().add(bbBook.getBookName());
               }
              listBooks();
           });
            listBooks().refresh();
            finalDialogList.refresh();
            checkInDialog.show();

        }else {Alert alert=new Alert(Alert.AlertType.INFORMATION,"You haven't checked out any books yet!");
        alert.setHeaderText(null); alert.setGraphic(null);alert.show();}


    }

    public void printMyInfo(){

        StringBuilder details=new StringBuilder();
        ArrayList<Book> backpack = users.get(uUserIndex).getBackpack();
        if (backpack!=null && backpack.size()!=0) {
            for (Book book : backpack) {
                details.append("\nYou checked out <").append(book.getBookName()).append("> at:").append(book.getCheckedOutDate());

            }
            Dialog reportDialog = new Dialog();
            Window window = reportDialog.getDialogPane().getScene().getWindow();
            reportDialog.setTitle("Report");
            TextArea textArea = new TextArea(details.toString());
            Button exitBtn = new Button("Exit");
            exitBtn.setOnAction(event -> window.hide());
            textArea.setEditable(false);
            textArea.setWrapText(false);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane container = new GridPane();
            container.setMaxWidth(Double.MAX_VALUE);
            container.setAlignment(Pos.CENTER);
            container.add(textArea, 0, 0);
            container.add(exitBtn, 0, 1);
            exitBtn.setAlignment(Pos.BOTTOM_RIGHT);
            reportDialog.getDialogPane().setContent(container);
            reportDialog.show();

        } else{
            Alert alert= new Alert(Alert.AlertType.INFORMATION, "You havent't checked out any books yet!");
            alert.setGraphic(null);
            alert.setHeaderText(null);
            alert.show();
        }
    }

    private String currentTime(){

        long timeStamp =System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd h:m a");
        return format1.format(calendar.getTime());
    }
}


