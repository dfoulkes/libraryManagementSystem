import java.util.Date;

/**
 * Created by mr-mou on 04/07/17.
 */
public class Book {

    public  String mBookName;
    public  String mBorrowerName;
    public  String mCheckedOutDate;

    public Book(String bookName, String borrowerName, String checkedOutDate){
        this.mBookName=bookName;
        this.mBorrowerName=borrowerName;
        this.mCheckedOutDate=checkedOutDate;
    }

    public  String getBookName() {
        return mBookName;
    }

    public  String getBorrowerName() {
        return mBorrowerName;
    }


    public String getCheckedOutDate() {
        return mCheckedOutDate;
    }




    public void setBorrowerName(String mBorrowerName) {
        this.mBorrowerName = mBorrowerName;
    }


    public void setCheckedOutDate(String mCheckedOutDate) {
        this.mCheckedOutDate = mCheckedOutDate;
    }


}
