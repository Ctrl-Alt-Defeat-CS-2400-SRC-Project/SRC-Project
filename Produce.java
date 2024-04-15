import java.time.LocalDate;

public class Produce {
    // produce object
    private String name; // name of the produce
    private String type; // fruit, vegetable, etc.
    private boolean inSeason; // whether the produce is in season or not
    private String season; // season the produce is in

    // name, type, and season are required, inSeason will be calculated by Java
    public Produce(String name, String type, String season) {
        this.name = name;
        this.type = type;
        this.season = season;
        this.inSeason = checkInSeason(season);
    }

    private boolean checkInSeason(String season) {
        String currentSeason = getCurrentSeason();
        return season.equalsIgnoreCase(currentSeason);
    }

    private String getCurrentSeason() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        if (month >= 3 && month <= 5) {
            return "spring";
        } else if (month >= 6 && month <= 8) {
            return "summer";
        } else if (month >= 9 && month <= 11) {
            return "fall";
        } else {
            return "winter";
        }
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean getInSeason() {
        return inSeason;
    }

    public void setInSeason(boolean inSeason) {
        this.inSeason = inSeason;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

}
