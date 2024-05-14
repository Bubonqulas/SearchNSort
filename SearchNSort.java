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
                float rating = Float.parseFloat(data[1]);
                float difficulty = Float.parseFloat(data[2]);
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
        System.out.print("Rating(Integer): ");
        int rating = scanner.nextInt();
        System.out.print("Difficulty(Integer): ");
        int difficulty = scanner.nextInt();
        System.out.print("Players(Integer): ");
        int players = scanner.nextInt();
        System.out.print("Time(Integer): ");
        int time = scanner.nextInt();
        System.out.print("Year(Integer): ");
        int year = scanner.nextInt();
        System.out.print("Genre: ");
        String genre = scanner.next();
        Games newGame = new Games(name, rating, difficulty, players, time, year, genre);
        games.add(newGame);
        System.out.println("Game successfully added!");
    }

    public static void printGames(List<Games> games) {
        for (Games game : games) {
            System.out.println(game);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<Games> games = csvReader(gamesList);
        System.out.println("Welcome to your Board Game Directory!\n-----");
        choiceMenu(scanner, games);

    }

    public static void choiceMenu(Scanner scanner, List<Games> games) throws InterruptedException {
        while (true) {
            System.out.println(
                    "\nWhat would you like to do?\n-----\n1. Add a Game\n2. Search by name \n3. Filter Games Alphabetically\n4. Filter Games Based on Difficulty\n5. Filter Games Based on Genre\n6. Filter Games Based on Rating\n7. Filter Games Based on Player count\n8. Filter Games Based on Time\n9. Save to Csv and Exit");
            String choice = scanner.nextLine();
            int input = checkInput(choice, 1, 9);

            if (input == 1) {
                addNewGame(scanner, games);

            } else if (input == 2) {

            } else if (input == 3) {
                System.out.println("Here are the games filtered  Alphabetically(ascending):\n");
                insertionSort(games, "name");
                Thread.sleep(3000);// for readability
                printGames(games);

            } else if (input == 4) {
                System.out.println("Here are the games filtered based on Difficulty(ascending):\n");
                bubbleSort(games, "difficulty");
                Thread.sleep(3000);// for readability
                printGames(games);

            } else if (input == 5) {
                System.out.println("Here are the games filtered by Genre(ascending):\n");
                insertionSort(games, "name");
                Thread.sleep(3000);// for readability
                printGames(games);
            } else if (input == 6) {
                System.out.println("Here are the games filtered based on Rating(ascending):\n");
                bubbleSort(games, "rating");
                Thread.sleep(3000);// for readability
                printGames(games);

            } else if (input == 7) {
                System.out.println("Here are the games filtered based on Player Count(ascending):\n");
                bubbleSort(games, "players");
                Thread.sleep(3000);// for readability
                printGames(games);

            } else if (input == 8) {
                System.out.println("Here are the games filtered based on Time(ascending):\n");
                bubbleSort(games, "time");
                Thread.sleep(3000);// for readability
                printGames(games);

            } else if (input == 9) {
                saveToCSV(games, gamesList);
                System.out.println("Saving and exiting...");
                break;

            } else if (input == 99) {
                System.out.println("Please enter a valid number between 1 and 9");
            }
        }

    }

    public static void insertionSort(List<Games> games, String choice) {
        for (int i = 1; i < games.size(); i++) {

            if(choice == "name"){
            Games key = games.get(i);
            int j = i - 1;
            while (j >= 0 && games.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                games.set(j + 1, games.get(j));
                j = j - 1;
            }
            games.set(j + 1, key);
            } else if(choice == "genre"){
                Games key = games.get(i);
                int j = i - 1;
                while (j >= 0 && games.get(j).getGenre().compareToIgnoreCase(key.getGenre()) > 0) {
                    games.set(j + 1, games.get(j));
                    j = j - 1;
            }
        }
    }

    public static void bubbleSort(List<Games> games, String choice) {
        int gameSize = games.size();
        boolean swapped;
        for (int i = 0; i < gameSize - 1; i++) {
            swapped = false;
            for (int j = 0; j < gameSize - i - 1; j++) {
                boolean shouldSwap = false;
                switch (choice) {
                    case "rating":
                        if (games.get(j).getRating() > games.get(j + 1).getRating()) {
                            shouldSwap = true;
                        }
                        break;
                    case "difficulty":
                        if (games.get(j).getDifficulty() > games.get(j + 1).getDifficulty()) {
                            shouldSwap = true;
                        }
                        break;
                    case "time":
                        if (games.get(j).getTime() > games.get(j + 1).getTime()) {
                            shouldSwap = true;
                        }
                        break;
                    case "players":
                        if (games.get(j).getPlayers() > games.get(j + 1).getPlayers()) {
                            shouldSwap = true;
                        }
                        break;

                }
                if (shouldSwap) {
                    Games temp = games.get(j);
                    games.set(j, games.get(j + 1));
                    games.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public static int checkInput(String input, int min, int max) {
        try {
            int number = Integer.parseInt(input);
            if (number >= min && number <= max) {
                return number;
            } else {
                return 99;
            }
        } catch (NumberFormatException e) {
            return 99;
        }
    }
}