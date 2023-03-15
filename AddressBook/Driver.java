import java.util.Scanner;

class Contact {
    String name;
    String phone_number;
    String email;
    Contact next;

    public Contact(String name, String phone_number, String email) {
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.next = null;
    }
}

class AddressBook {
    Contact head;

    public AddressBook() {
        this.head = null;
    }

    public void addContact(String name, String phone_number, String email) {
        Contact newContact = new Contact(name, phone_number, email);

        if (head == null) {
            head = newContact;
        } else {
            Contact current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newContact;
        }
        System.out.println("Your contact has been added.");
    }

    public void deleteContact(String email) {
        if (head == null) {
            System.out.println("The address book is empty.");
            return;
        }

        if (head.email.equals(email)) {
            head = head.next;
            System.out.println("Contact eliminated successfully.");
            return;
        }

        Contact current = head;
        while (current.next != null && !current.next.email.equals(email)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Contact not found.");
            return;
        }

        current.next = current.next.next;
        System.out.println("Contact eliminated. successfully!");
    }

    public void searchByEmail(String email) {
        if (head == null) {
            System.out.println("Address book is empty.");
            return;
        }

        Contact current = head;
        while (current != null && !current.email.equals(email)) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Contact cannot be found.");
        } else {
            System.out.println("Name: " + current.name);
            System.out.println("Phone Number: " + current.phone_number);
            System.out.println("Email: " + current.email);
            System.out.println(" ");
        }
    }

    public void printAllContacts() {
        if (head == null) {
            System.out.println("Address book is empty.");
            return;
        }

        Contact current = head;
        while (current != null) {
            System.out.println("Name: " + current.name);
            System.out.println("Phone Number: " + current.phone_number);
            System.out.println("Email: " + current.email);
            System.out.println(" ");
            current = current.next;
        }
    }

    public void searchContact() {
        if (head == null) {
            System.out.println("Address book is empty.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("Enter search keyword: ");
        String keyword = input.nextLine().toLowerCase();

        Contact current = head;
        int count = 0;
        while (current != null) {
            if (current.name.toLowerCase().contains(keyword) ||
                    current.phone_number.contains(keyword) ||
                    current.email.toLowerCase().contains(keyword)) {
                System.out.println("Name: " + current.name);
                System.out.println("Phone Number: " + current.phone_number);
                System.out.println("Email: " + current.email);
                count++;
            }
            current = current.next;
        }

        if (count == 0) {
            System.out.println("Unfortunately, there are no contacts that match your descriptions.");
        }
    }
}

public class Driver {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner input = new Scanner(System.in);

        boolean loop_condition = true;

        while (loop_condition) {
            System.out.println("******************************************");
            System.out.println("(A)dd Contact");
            System.out.println("(D)elete Contact");
            System.out.println("(E)mail Search");
            System.out.println("(S)earch contacts");
            System.out.println("(P)rint all contacts");
            System.out.println("(Q)uit");
            System.out.println("******************************************");

            String option = input.nextLine().toUpperCase();

            switch (option) {
                case "A":
                    System.out.print("Enter Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone_number = input.nextLine();
                    System.out.print("Enter Email: ");
                    String email = input.nextLine();
                    addressBook.addContact(name, phone_number, email);
                    break;
                case "D":
                    System.out.print("Enter the target's email: ");
                    email = input.nextLine();
                    addressBook.deleteContact(email);
                    break;
                case "E":
                    System.out.print("Enter contact email: ");
                    email = input.nextLine();
                    addressBook.searchByEmail(email);
                    break;
                case "S":
                    addressBook.searchContact();
                    break;
                case "P":
                    addressBook.printAllContacts();
                    break;
                case "Q":
                    loop_condition = false;
                    break;
                default:
                    System.out.println("You've entered an unknown input.");
            }
        }

        System.out.println("Thanks for using this program.");
    }
}