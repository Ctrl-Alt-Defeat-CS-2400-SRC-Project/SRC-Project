public class Produce {
    // produce object
    private String name; // name of the produce
    private String type; // fruit, vegetable, etc.
    private boolean inSeason; // whether the produce is in season or not
    private int count; // number of produce in stock
    private boolean inStock; // whether the produce is in stock or not
    private String season; // season the produce is in

    // name, type, and season are required, count can be added later, inSeason will be calculated by Java
    public Produce(String name, String type, String season) {
        this.name = name;
        this.type = type;
        this.season = season;
    }

    // getters and setters

}
