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

    @Override
    public int compareTo(Client other) {
        return userName.compareTo(other.getName());
    }

    /**
     * Sets the user name of the client.
     * @param name the user name to set
     */
    public void setName(String name) {
        this.userName = name;
    }

    /**
     * Sets the address of the client.
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the phone number of the client.
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the email of the client.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the age of the client.
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieves the user name of the client.
     * @return the user name
     */
    public String getName() {
        return userName;
    }

    /**
     * Retrieves the address of the client.
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Retrieves the phone number of the client.
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Retrieves the email of the client.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the age of the client.
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns a string representation of the client object.
     * @return the string representation
     */
    public String toString() {
        return "Name: " + userName + "\nAddress: " + address + "\nPhone: " + phone + "\nEmail: " + email + "\nAge: " + age;
    }
}