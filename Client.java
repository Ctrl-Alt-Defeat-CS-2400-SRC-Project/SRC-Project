public class Client {
    // client object
    private String name;
    private String address;
    private String phone;
    private String email;
    private int age;

    // name, age, and at least one contact method are required, null will be passed in for optional fields
    public Client(String name, String address, String phone, String email, int age) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public Client(String name, String address, String phone, int age) {
        this(name, address, phone, null, age);
    }

    public Client(String name, String phone, int age) {
        this(name, null, phone, null, age);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone: " + phone + "\nEmail: " + email + "\nAge: " + age;
    }
}