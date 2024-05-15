// SearchNSort,java
/*
  Title: SearchNSort class
  Author: Hassan Darky
  Date: May 10th, 2024
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchNSort {

    // PROCESSING

    private static String gamesList = "1b_GameCollection - sheet 1.csv";

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<Games> games = csvReader(gamesList);
        System.out.println("Welcome to your Board Game Directory!\n-----");
        choiceMenu(scanner, games);

    }

    // choice menue method for handling how to sort and filter based on suer input
    public static void choiceMenu(Scanner scanner, List<Games> games) throws InterruptedException {
        while (true) {
            System.out.println(
                    "\nWhat would you like to do?\n-----\n1. Add a Game\n2. Search by name \n3. Filter Games Alphabetically\n4. Filter Games Based on Difficulty\n5. Filter Games Based on Genre\n6. Filter Games Based on Rating\n7. Filter Games Based on Player count\n8. Filter Games Based on Time\n9. Save to Csv and Exit");
            String choice = scanner.nextLine();
            int input = checkInput(choice, 1, 9);

            if (input == 1) {
                // adding game option
                addNewGame(scanner, games);

            } else if (input == 2) {
                // specific game search
                System.out.print("Enter the name of the game to search for: ");
                String title = scanner.nextLine();
                insertionSort(games);
                int choiceThree = binarySearch(games, title);
                if (choiceThree != -1) {
                    System.out.println("\nGame found!\n-----\n" + games.get(choiceThree));
                } else {
                    System.out.println("\nGame was not found. Re-try or check spelling.");
                }

            } else if (input == 3) {
                // ordering by alphabet
                System.out.println("Here are the games filtered  Alphabetically(ascending):\n");
                insertionSort(games);
                Thread.sleep(1000);// for readability
                printGames(games);

            } else if (input == 4) {
                // filtering by difficulty
                System.out.println("Here are the games filtered based on Difficulty(ascending):\n");
                bubbleSort(games, "difficulty");
                Thread.sleep(1000);// for readability
                printGames(games);

            } else if (input == 5) {
                // finding and sorting all games of specfic genre based on user input
                System.out.print("Enter the genre to search for: ");
                String genre = scanner.nextLine();
                List<Games> genreGames = genreSearch(games, genre);
                if (!genreGames.isEmpty()) {
                    System.out.println("Games found in genre " + genre + ":");
                    for (Games game : genreGames) {
                        System.out.println(game);
                    }
                } else {
                    System.out.println("No games found for genre " + genre + ".");
                }

            } else if (input == 6) {
                // filtering based on rating
                System.out.println("Here are the games filtered based on Rating(ascending):\n");
                bubbleSort(games, "rating");
                Thread.sleep(1000);// for readability
                printGames(games);

            } else if (input == 7) {
                // filtered based on player count
                System.out.println("Here are the games filtered based on Player Count(ascending):\n");
                bubbleSort(games, "players");
                Thread.sleep(1000);// for readability
                printGames(games);

            } else if (input == 8) {
                // filtered based on time
                System.out.println("Here are the games filtered based on Time(ascending):\n");
                bubbleSort(games, "time");
                Thread.sleep(1000);// for readability
                printGames(games);

            } else if (input == 9) {
                saveToCSV(games, gamesList);
                System.out.println("Saving and exiting...");
                System.exit(0);

            } else if (input == 99) {
                // checkinput retuns 99 if input is invald or not within options
                System.out.println("Please enter a valid number between 1 and 9");
            }
        }

    }

    // adding new game method, uses intcheck and str checker methods
    public static void addNewGame(Scanner scanner, List<Games> games) {
        System.out.println("Please enter the game details:");
        String name = strChecker(scanner, "Name: ");
        int rating = intChecker(scanner, "Rating(Integer): ");
        int difficulty = intChecker(scanner, "Difficulty(Integer): ");
        int players = intChecker(scanner, "Players(Integer): ");
        int time = intChecker(scanner, "Time(Integer): ");
        int year = intChecker(scanner, "Year(Integer): ");
        String genre = strChecker(scanner, "Genre: ");
        Games newGame = new Games(name, rating, difficulty, players, time, year, genre);
        games.add(newGame);
        System.out.println("Game was successfully added!");
    }

    public static List<Games> genreSearch(List<Games> games, String genre) {
        List<Games> matchedGenre = new ArrayList<>();
        // coudnt get this for loop and printing to fully work so had assitance from
        // chatgpt
        for (Games game : games) {
            if (game.getGenre().equalsIgnoreCase(genre)) {
                matchedGenre.add(game);
            }
        }
        return matchedGenre;
    }

    public static int binarySearch(List<Games> games, String gameTitle) {
        int start = 0;
        int end = games.size() - 1;

        while (start <= end) {
            int midPoint = (end + start) / 2;
            int result = games.get(midPoint).getName().compareToIgnoreCase(gameTitle);

            if (result == 0) {
                return midPoint;
            }

            if (result < 0) {
                start = midPoint + 1;
            } else {
                end = midPoint - 1;
            }
        }
        return -1;
    }

    // isnertion sort to sort by alphabet
    public static void insertionSort(List<Games> games) {
        for (int i = 1; i < games.size(); i++) {
            Games key = games.get(i);
            int j = i - 1;
            while (j >= 0 && games.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                games.set(j + 1, games.get(j));
                j = j - 1;
            }
            games.set(j + 1, key);
        }
    }

    // bubble sort to filter based on param and order list
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

                // i was doing this without should swap and wasnt getting it to work properly,
                // so i found a similar solution on stack overflow and youtube
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

    // the reading and saving to csv methods were made with the help of an article
    // mr artym gave to su last year and a video i watched on youtube
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

    public static int intChecker(Scanner scanner, String prompt) {
        int number;
        while (true) {
            System.out.print(prompt);
            try {
                number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    public static String strChecker(Scanner scanner, String prompt) {
        String text;
        while (true) {
            System.out.print(prompt);
            text = scanner.nextLine();
            if (text != null && !text.trim().isEmpty()) {
                return text;
            } else {
                System.out.println("Please enter a valid non-empty string.");
            }
        }
    }

    // reused code from textbased rpg
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

    // OUTPUT
    public static void printGames(List<Games> games) {
        for (Games game : games) {
            System.out.println(game);
        }
    }
}
