import java.time.LocalDate;

public class Produce implements Comparable<Produce> {
    // produce object
    private String name; // name of the produce
    private boolean inSeason; // whether the produce is in season or not
    private String season; // season the produce is in

    // name, type, and season are required, inSeason will be calculated by Java
    /**
     * The constructor of the Produce class. Initializes the name of the produce, wether the 
     * produce is in season or not, and what season the produce is in. 
     * 
     * @param name The name of the produce. 
     * @param season The season the produce is in. 
     */
    public Produce(String name, String season) {
        this.name = name;
        this.season = season;
        this.inSeason = checkInSeason(season);
    }


    /**
     * Determines wether the produce is in season. 
     * 
     * @param season The String representation of the season of the produce.
     * @return True if the produce is in season, and false otherwise. 
     */
    public boolean checkInSeason(String season) {
        if (season.equalsIgnoreCase("year round")) {
            return true;
        }
        String currentSeason = getCurrentSeason();
        return season.equalsIgnoreCase(currentSeason);
    }

    /**
     * Returns the current season of the produce in conjunction with the local date/time. 
     * 
     * @return Spring, Summer, Fall, or Winter depending on the current time of year. 
     */
    public String getCurrentSeason() {
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

    @Override
    /**
     * Compares two produce items together by name. 
     * 
     * @param other The produce item to be compared to. 
     * @return 0 if the two produce are equal, -1 if the invoking string comes before (lexicographically),
     * and 1 if the invoking string comes after (lexicographically).
     */
    public int compareTo(Produce other) {
        return this.name.compareToIgnoreCase(other.getName());
    }

    /**
     * Determines if two produce items are equal to each other (by name).
     * 
     * @param other The produce item to be compared to. 
     * @return True if the produce items have the same name, and false otherwise. 
     */
    public boolean equals(Produce other) {
        return this.name.equalsIgnoreCase(other.getName());
    }

    //getters and setters
    /**
     * Gets the name of the produce. 
     * 
     * @return The String representation of the produce name. 
     */
    public String getName() {
        return name;
    }

    /**
     * Gets wether the produce is in season. 
     * 
     * @return True if in season, and false otherwise. 
     */
    public boolean getInSeason() {
        return inSeason;
    }

    /**
     * Sets the season of the produce. 
     * 
     * @param inSeason A boolean value corresponding to wether the produce is in season. 
     */
    public void setInSeason(boolean inSeason) {
        this.inSeason = inSeason;
    }

    /**
     * Gets the season of the produce item. 
     * 
     * @return The String representation of the season. 
     */
    public String getSeason() {
        return season;
    }

    /**
     * Sets the season of the produce item. 
     * 
     * @param season The desired season of the produce item. 
     */
    public void setSeason(String season) {
        this.season = season;
    }

    /**
     * Gets the String representation of produce name and season. 
     * 
     * @return The String representaiton of the produce. 
     */
    public String toString() {
        return name + " " + season;
    }

}
