/**
 * Class that implements a client object, which is used to store information about a person in need of assistance.
 * @author Ryan Wei
 */

public class Client implements Comparable<Client> {
    private String userName; //user name for anonymity, as those who are in need may not want to share their real name
    private String address;
    private String phone;
    private String email;
    private int age;

    /**
     * Constructor for the client object
     * 
     * @param name the user name of the client, is required
     * @param age the age of the client, is required
     * @param address the address of the client, is optional
     * @param phone the phone number of the client, either phone or email is required
     * @param email the email of the client, either phone or email is required
     *  */ 
    public Client(String name, String address, String phone, String email, int age) {
        this.userName = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    /**
     * Alternate constructor that does not require an address
     * 
     * @param name the user name of the client, is required
     * @param age the age of the client, is required
     * @param phone the phone number of the client, either phone or email is required
     * @param email the email of the client, either phone or email is required
     *  */
    public Client(String name, String phone, int age, String email) {
        this(name, null, phone, email, age);
    }

    /**
     * Alternate constructor that does not require an phone
     * 
     * @param name the user name of the client, is required
     * @param age the age of the client, is required
     * @param address the address of the client, is optional
     * @param email the phone number of the client, either phone or email is required
     *  */
    public Client(String name, String address, String email, int age) {
        this(name, address, null, email, age);
    }

    /**
     * Alternate constructor that does not require an email
     * 
     * @param name the user name of the client, is required
     * @param age the age of the client, is required
     * @param address the address of the client, is optional
     * @param phone the phone number of the client, either phone or email is required
     *  */
    public Client(String name, int age, String address, String phone) {
        this(name, address, phone, null, age);
    }

    /**
     * Alternate constructor that does not require an address or email
     * 
     * @param name the user name of the client, is required
     * @param age the age of the client, is required
     * @param phone the phone number of the client, either phone or email is required
     *  */
    public Client(String name, String phone, int age) {
        this(name, null, phone, null, age);
    }

    /**
     * Alternate constructor that does not require an address or phone number
     * 
     * @param name the user name of the client, is required
     * @param age the age of the client, is required
     * @param email the email of the client, either phone or email is required
     *  */
    public Client(String name, int age, String email) {
        this(name, null, null, email, age);
    }

    @Override
    public int compareTo(Client other) {
        return userName.compareTo(other.getName());
    }

    /**
     * retrieves the user name of the client
     * @return userName
     */
    public String getName() {
        return userName;
    }

    /**
     * retrieves the address of the client
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * retrieves the phone number of the client
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * retrieves the email of the client
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * retrieves the age of the client
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * retrieves all the information of the client
     * @return the information of the client
     */
    public String toString() {
        return "Name: " + userName + "\nAddress: " + address + "\nPhone: " + phone + "\nEmail: " + email + "\nAge: " + age;
    }
}