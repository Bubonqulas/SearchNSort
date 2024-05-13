public class Games {
    private String name;
    private int rating;
    private String difficulty;
    private int players;
    private int time;
    private int year;
    private String genre;

    public Games(String name, int rating, String difficulty, int players, int time, int year, String genre) {
        this.name = name;
        this.rating = rating;
        this.difficulty = difficulty;
        this.players = players;
        this.time = time;
        this.year = year;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getPlayers() {
        return players;
    }

    public int getTime() {
        return time;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String toString() {
        return name + ":\nRating: " + rating + ", Difficulty: " + difficulty + ", Players: " + players +
                ", Time: " + time + ", Year: " + year + ", Genre: " + genre;
    }
}
