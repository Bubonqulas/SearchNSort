import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchNSort {

    private static String gamesList = "1b_GameCollection - sheet 1.csv";

    public static List<Games> csvReader(String cSV) {
        List<Games> games = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(cSV))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int rating = Integer.parseInt(data[1]);
                String difficulty = data[2];
                int players = Integer.parseInt(data[3]);
                int time = Integer.parseInt(data[4]);
                int year = Integer.parseInt(data[5]);
                String genre = data[6];
                Games game = new Games(name, rating, difficulty, players, time, year, genre);
                games.add(game);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return games;
    }

    public static void saveToCSV(List<Games> games, String cSV) {
        try (FileWriter writer = new FileWriter(cSV)) {
            for (Games game : games) {
                writer.write(game.getName() + "," + game.getRating() + "," + game.getDifficulty() + "," +
                        game.getPlayers() + "," + game.getTime() + "," + game.getYear() + "," + game.getGenre() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addNewGame(Scanner scanner, List<Games> games) {
        System.out.println("Please enter the game details:");
        System.out.print("Name: ");
        String name = scanner.next();
        System.out.print("Rating: ");
        int rating = scanner.nextInt();
        System.out.print("Difficulty: ");
        String difficulty = scanner.next();
        System.out.print("Players: ");
        int players = scanner.nextInt();
        System.out.print("Time: ");
        int time = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Genre: ");
        String genre = scanner.next();
        Games newGame = new Games(name, rating, difficulty, players, time, year, genre);
        games.add(newGame);
    }

    public static void printGames(List<Games> games) {
        for (Games game : games) {
            System.out.println(game);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        List<Games> games = csvReader(gamesList);
        System.out.println("Welcome to your Board Game Directory!\n-----");
        choiceMenu(scanner);

    }

    public static void choiceMenu(Scanner scanner) {

        System.out.println(
                "\nWhat would you like to do?\n-----\n1. Add a Game\n2. Print Games Alphabetically\n3. Print Games Based on Difficulty\n4. Print Games Based on Genre\n5. Print Games Based on Time\n6. Save and Exit");
        int input = scanner.nextInt();

        if (input == 1) {

        } else if (input == 2) {

        } else if (input == 3) {

        } else if (input == 4) {

        } else if (input == 5) {

        }

    }

}