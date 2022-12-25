import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("**************************************************************************");
        System.out.println("********************Welcome to Library Management System******************");
        System.out.println("**************************************************************************");

        //object for books
        Books ob= new Books();
        //object creation for students
        students obStudent = new students();

        int choice; //searchChoice;
        do{
            {
                // Displaying menu
                System.out.println("----------------------------------------------------------------------------------------------------------");
                System.out.println("Press 1 to Add a new Book in Library");
                System.out.println("Press 2 to Show All Books int the Library.");
                System.out.println("Press 3 to Register a Student in the Database.");
                System.out.println("Press 4 to Registered Students in the Database.");
                System.out.println("Press 5 to Borrow a Book ");
                System.out.println("Press 6 to Return a Book");
                System.out.println("Press 7 to Exit Application.");
                System.out.println("-------------------------------------------------------------------------------------------------------");
                choice = sc.nextInt();
            }
            switch (choice) {
                // Case
                case 1 -> {
                    Book b = new Book();
                    ob.addBook(b);
                }
                // Case
                case 2 -> ob.showAllBooks();
                // Case
                case 3 -> {
                    student s = new student();
                    //  students obStudent;
                    obStudent.addStudent(s);
                }
                // Case
                case 4 -> obStudent.showAllStudents();
                // Case
                case 5 -> obStudent.checkOutBook(ob);
                // Case
                case 6 -> obStudent.checkInBook(ob);
                case 7 -> System.exit(0);
            }
        }while(choice<=7);
    }

    public static class Books {
        Book []theBooks = new Book[50];
        Scanner sc = new Scanner(System.in);
        public int count;

        public void addBook(Book b)
        {   for (int i = 0; i < count; i++) {
            if (this.compareBookObjects(b, this.theBooks[i]) == 0)
                    return;
            }
            if (count < 50) {
                theBooks[count] = b;
                count++;
            }
            else {
                System.out.println("No Space to Add More Books.");
            }
        }
        //for comparing books entered
        public int compareBookObjects(Book b1, Book b2)
        {
            if (b1.bookName.equalsIgnoreCase(b2.bookName)) {

                // Printing book exists
                System.out.println("Book of this Name Already Exists.");
                return 0;
            }
            // if book serial matches
            if (b1.sNo == b2.sNo) {
                // Print book exists
                System.out.println("Book of this Serial No Already Exists.");
                return 0;
            }
            return 1;
        }
        public void showAllBooks()
        {
            System.out.println("\t\t\t\tSHOWING ALL BOOKS\n");
            System.out.println("S.No\t\tName\t\tAuthor\t\tAvailable Qty\t\tTotal Qty");
            for (int i = 0; i < count; i++) {
                System.out.println(
                        theBooks[i].sNo + "\t\t" + theBooks[i].bookName + "\t\t" + theBooks[i].authorName + "\t\t"
                                + theBooks[i].bookQtyCopy + "\t\t"
                                + theBooks[i].bookQty);
            }
        }
        public int isAvailable(int sNo)
        {
            for (int i = 0; i < count; i++) {
                if (sNo == theBooks[i].sNo) {
                    if (theBooks[i].bookQtyCopy > 0) {
                        System.out.println("Book is Available.");
                        return i;
                    }
                    System.out.println("Book is Unavailable");
                    return -1;
                }
            }
            System.out.println("No Book of Serial Number " + " Available in Library.");
            return -1;
        }
        public Book checkOutBook()
        {

            System.out.println(
                    "Enter Serial No of Book to be Checked Out.");
            int sNo = sc.nextInt();

            int bookIndex = isAvailable(sNo);

            if (bookIndex != -1) {
                theBooks[bookIndex].bookQtyCopy--;
                return theBooks[bookIndex];
            }
            return null;
        }

        // Method 10
        // To add the Book to the Library
        public void checkInBook(Book b)
        {
            for (int i = 0; i < count; i++) {
                if (b.equals(theBooks[i])) {
                    theBooks[i].bookQtyCopy++;
                    return;
                }
            }
        }
    }
    public static class Book{
        public int sNo;
        public String bookName;
        public String authorName;
        public int bookQty;
        public int bookQtyCopy;
        Scanner sc = new Scanner(System.in);

        public Book(){
            System.out.print("Enter Serial No of Book : ");
            this.sNo = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Book Name : ");
            this.bookName = sc.nextLine();

            System.out.print("Enter Author Name : ");
            this.authorName = sc.nextLine();

            System.out.print("Enter Quantity of Books : ");
            this.bookQty = sc.nextInt();
            bookQtyCopy = this.bookQty;

        }
    }

    //class for creating a student instance and entering its details
    public static class student{
        String Stud_Name;
        String Stud_regNo;
        String Stud_branch;
        Scanner sc = new Scanner(System.in);
        Book []borrowedBooks = new Book[3];
        public int booksCount = 0;
        public student(){
            //entering student name
            System.out.print("Enter the Student Name : ");
            this.Stud_Name = sc.nextLine();
            //entering student reg number
            //this is used for current instance
            System.out.print("Enter Registration Number of Student : ");
            this.Stud_regNo = sc.nextLine();
            //entering student branch
            System.out.print("Enter Branch of Student : ");
            this.Stud_branch = sc.nextLine();
        }
    }

    public static class students{
        Scanner sc = new Scanner(System.in);
        student []theStudents = new student[50];
        int count = 0;
        public void addStudent(student s)
        {for (int i = 0; i < count; i++) {
            if (s.Stud_regNo.equalsIgnoreCase(theStudents[i].Stud_regNo)) {

                    // Print statement
                    System.out.println("Student of Reg Num " + s.Stud_regNo + " is Already Registered.");
                    return;
                }
            }
            if (count <= 50) {
                theStudents[count] = s;
                count++;
            }
        }
        public void showAllStudents()
        {
            // Printing student name and
            // corresponding registered number
            System.out.println("Student Name\t\tReg Number\t\tBranch Name");
            for (int i = 0; i < count; i++) {

                System.out.println(theStudents[i].Stud_Name + "\t\t" + theStudents[i].Stud_regNo + "\t\t" + theStudents[i].Stud_branch);
            }
        }

        public int isStudent()
        {
            // Display message only
            System.out.println("Enter Reg Number:");

            String regNum = sc.nextLine();

            for (int i = 0; i < count; i++) {

                if (theStudents[i].Stud_regNo.equalsIgnoreCase(
                        regNum)) {
                    return i;
                }
            }

            // Print statements
            System.out.println("Student is not Registered.");
            System.out.println("Get Registered First.");

            return -1;
        }
        public void checkOutBook(Books book)
        {
            int studentIndex = this.isStudent();

            if (studentIndex != -1) {
                System.out.println("checking out");

                book.showAllBooks();
                Book b = book.checkOutBook();

                System.out.println("checking out");
                if (b != null) {

                    if (theStudents[studentIndex].booksCount<= 3) {

                        System.out.println("adding book");
                        theStudents[studentIndex].borrowedBooks[theStudents[studentIndex].booksCount] = b;
                        theStudents[studentIndex].booksCount++;
                        System.out.println("\t\t\tBOOK ISSUED!!!!");
                        return;
                    }
                    else {

                        System.out.println("Student Can not Borrow more than 3 Books.");
                        return;
                    }
                }
                System.out.println("Book is not Available.");
            }
        }

        // Method 5
        // To add the book
        public void checkInBook(Books book)
        {
            int studentIndex = this.isStudent();
            if (studentIndex != -1) {

                // Printing credentials corresponding to student
                System.out.println("S.No\t\t\tBook Name\t\t\tAuthor Name");

                student s = theStudents[studentIndex];

                for (int i = 0; i < s.booksCount; i++) {

                    System.out.println(
                            s.borrowedBooks[i].sNo + "\t\t\t"
                                    + s.borrowedBooks[i].bookName + "\t\t\t"
                                    + s.borrowedBooks[i].authorName);
                }

                // Display message only
                System.out.println(
                        "Enter Serial Number of Book to be Checked In:");

                int sNo = sc.nextInt();

                for (int i = 0; i < s.booksCount; i++) {
                    if (sNo == s.borrowedBooks[i].sNo) {
                        book.checkInBook(s.borrowedBooks[i]);
                        s.borrowedBooks[i] = null;
                        System.out.println("\t\t\tBOOK RETURNED!!!!!");
                        return;
                    }
                }
                System.out.println("Book of Serial No " + sNo
                        + "not Found");
            }
        }
    }


}
