// Games,java
/*
  Title: Games class
  Author: Hassan Darky
  Date: May 10th, 2024
 */

public class Games {
    private String name;
    private float rating;
    private float difficulty;
    private int players;
    private int time;
    private int year;
    private String genre;

    // PROCESSING

    public Games(String name, float rating, float difficulty, int players, int time, int year, String genre) {
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

    public float getDifficulty() {
        return difficulty;
    }

    public float getRating() {
        return rating;
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
        return name + ":\n ~ Rating: " + rating + "\n ~ Difficulty: " + difficulty + "\n ~ Players: " + players +
                "\n ~ Time: " + time + "\n ~ Year: " + year + "\n ~ Genre: " + genre + "\n";
    }
}
