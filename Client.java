public class Client {
    // client object
    private String name;
    private String address;
    private String phone;
    private String email;
    private int age;

    private Produce produce[];
    private int produceCount;

    // name, age, and at least one contact method are required, null will be passed in for optional fields
    public Client(String name, String address, String phone, String email, int age) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    }
}