package library;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Library {

    private final ArrayList<Book> books;
    private final ArrayList<Checkout> checkOuts;
    private final ArrayList<Member> members;
    private final ArrayList<Return> returns;
    private final ArrayList<User> users;
    private Auth auth;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
        checkOuts = new ArrayList<>();
        members = new ArrayList<>();
        returns = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        User user = findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            auth = new Auth(user);
            return true;
        } else {
            System.err.println("Username or password is incorrect please try again");
        }
        return false;
    }

    private User findUserByUsername(String username) {
        User user = null;
        for (User u : users) {
            if (u.getUserName().equals(username)) {
                user = u;
                break;
            }
        }
        return user;

    }

    public void addNewUser(User newUser) {
        if (users.isEmpty()) {
            newUser.setType(UserType.ADMIN);
            System.out.println("This is firt user of library therefore system create this user as an admin");
            users.add(newUser);
        } else if (isUserAvailable(auth.getUser())) {
            if (auth.getUser().getType() == UserType.ADMIN) {
                users.add(newUser);
                System.out.println("New user added to the library");
            } else {
                System.out.println("Cant add user because User:" + auth.getUser().getUserName() + " is not an admin");
            }
        } else {
            System.out.println("Cant add User because User:" + auth.getUser().getUserName() + " is not part of library");
        }
    }

    private boolean isUserAvailable(User user) {
        return users.stream().filter(user::equals).findFirst().isPresent();
    }

    public void addNewBook(Book book) {
        if (isUserAvailable(auth.getUser())) {
            if (auth.getUser().getType() == UserType.ADMIN) {
                Optional<Book> f = books.stream().filter(book::equals).findFirst();
                if (!f.isPresent()) {
                    books.add(book);
                    System.out.println("Book " + book.getName() + " Added to library by User:" + auth.getUser().getUserName());
                }else{
                    System.out.println("Book " + book.getName() + " already in");
                }
            } else {
                System.out.println("Cant add book because User:" + auth.getUser().getUserName() + " is not an admin");
            }
        } else {
            System.out.println("Cant add book because User:" + auth.getUser().getUserName() + " is not part of library");
        }

    }

    public Book searchBookByISBN(String isbn) {
        Optional<Book> bo = books.stream().filter(b -> b.getIsbn().equalsIgnoreCase(isbn)).findFirst();
        return bo.isPresent() ? bo.get() : null;
    }

    public ArrayList<Book> searchBookByName(String name) {
        return new ArrayList<>(books.stream().filter(b -> b.getName().equalsIgnoreCase(name)).collect(Collectors.toList()));
    }

    public void removeBook(Book book) {
        Optional<Book> f = books.stream().filter(book::equals).findFirst();
        if (f.isPresent()) {
            books.remove(f.get());
            System.out.println("Book " + book.getName() + " was Removed from library");
        } else {
            System.out.println("Can not find book " + book.getName() + " to delete");
        }
    }

    private void checkOutBook() {

    }

    private void returnBook() {

    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
