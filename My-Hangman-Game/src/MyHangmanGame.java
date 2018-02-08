// hangman picturing is yet to be done
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.lang.Character;

public class MyHangmanGame {
	static String easyWord;
	static String mediumWord;
	static String randomWord;
	static String stateName;
	static String word;
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to hangman Game!");
		System.out.println(" Lets see if you are a life saviour");
		System.out.println();

		String playAgain;
		// ***********dictionary is loaded here*******************
		do {
			playAgain = "";
			NewGame();
			System.out.println();
			System.out.println("world you like to play again? Enter Y or y");
			playAgain = scan.nextLine();

		} while (playAgain.equalsIgnoreCase("Y"));

	}

	public static void NewGame() {
		WordsLoading();
		TakeUserInput();
		GameLogicCheck();

	}

	public static void WordsLoading() {
		Random rand = new Random();
		List<String> wordsList = new ArrayList<>();
		List<String> stateList = new ArrayList<>();
		List<String> easyList = new ArrayList<>();
		List<String> mediumList = new ArrayList<>();

		/*
		 * File file = new File(
		 * "C:/Users/gkneerukonda/Desktop/hangman game/words.txt");
		 */
		File file = new File("words.txt");
		try {

			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
			String line;

			while ((line = br.readLine()) != null) {

				// trims the data so that there are no extra spaces
				// adding the data to lists
				wordsList.add(line.trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * File fl = new File(
		 * "C:/Users/gkneerukonda/Desktop/hangman game/states.txt");
		 */
		File fl = new File("states.txt");
		try {

			FileReader fil = new FileReader(fl);
			BufferedReader br1 = new BufferedReader(fil);
			String line1;

			while ((line1 = br1.readLine()) != null) {

				// trims the data so that there are no extra spaces
				// adding the data to lists
				stateList.add(line1.trim());
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		stateName = stateList.get(rand.nextInt(stateList.size()));
		String[] words = new String[wordsList.size()];
		words = wordsList.toArray(words);
		for (int k = 0; k < words.length; k++) {
			if (words[k].length() == 4) {
				easyList.add(words[k]);
			}
		}
		easyWord = easyList.get(rand.nextInt(easyList.size()));

		for (int k = 0; k < words.length; k++) {
			if (words[k].length() == 5) {
				mediumList.add(words[k]);
			}
		}
		mediumWord = mediumList.get(rand.nextInt(mediumList.size()));
		randomWord = words[(int) (Math.random() * words.length)];
		// *for developer**************************
		System.out.println("for developer    " + easyWord + "   easy word");
		System.out.println("for developer   " + mediumWord + "  medium word");
		System.out.println("for developer   " + randomWord + "   random");
		System.out
				.println("for developer   " + stateName + "   USA State name");
		System.out.println();
	}

	public static void TakeUserInput() {
		// Scanner scan = new Scanner(System.in);
		System.out.println("choose your difficulty level");
		System.out.println("1--> Easy");
		System.out.println("2--> Medium");
		System.out.println("3--> Random");
		System.out.println("4--> USA State Names");
		int choice = scan.nextInt();
		scan.nextLine();
		word = null;
		if (choice == 1) {
			word = easyWord;
			// System.out.println(word);
		}
		if (choice == 2) {
			word = mediumWord;
			// System.out.println(word);
		}
		if (choice == 3) {
			word = randomWord;
		}
		if (choice == 4) {
			word = stateName;
		}
		// scan.close();

	}

	public static void GameLogicCheck() {
		// Scanner userInput = new Scanner(System.in);
		boolean retval = false;
		char[] wordA = word.toCharArray();
		char[] answer = new char[wordA.length];
		String letter = "";

		int chances = 10;
		// * initialized word to be guessed to ********
		for (int z = 0; z < answer.length; z++) {
			answer[z] = '*';
			System.out.print(answer[z]);

		}
		while (chances > 0) {
			System.out.println("    enter the letter" + "     " + "you have "
					+ chances + " chances to save the life");

			letter = scan.nextLine();
			letter.toLowerCase();
			char guess = letter.charAt(0);
			boolean check = false;
			int count = 0;

			// checking if entered letter (guess) matches any of the letter in
			// our word to be guessed
			// if it matches stores that letter in answer and removes *
			for (int j = 0; j < answer.length; j++) {
				check = new Character(Character.toLowerCase(guess))
						.equals(new Character(Character.toLowerCase(wordA[j])));
				if (check == true) {
					answer[j] = Character.toLowerCase(guess);
					count = count + 1;

				}

			}
			// logic to reduce chances when there is a failed attempt
			if (count == 0) {
				chances = chances - 1;

			}
			// for every iteration it prints current word
			for (int j = 0; j < answer.length; j++) {
				System.out.print(answer[j]);
			}
			// if guessed word is equal to to be original word ..you win and

			retval = Arrays.equals(wordA, answer);

			if (retval == true) {
				System.out.println();
				System.out.println("great! you just hit the bull's eye");
				break;
			}

		}

		if (retval == false) {
			System.out
					.println("you couldn't guess it in given number of chances");
		}

	}

}
