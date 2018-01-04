import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissorsLizardSpock_Game {

	public static void main(String[] args) {
		String playAgain = "";
		Scanner input = new Scanner(System.in);
		do {
			game();
			
			System.out.println("--------------------------------------------");
			System.out
					.println("Would you like to play again? Enter Y to play or any other key to quit: ");
			playAgain = input.nextLine();
		} while (playAgain.equalsIgnoreCase("Y"));
		System.out.println("Thanks for playing!");
		input.close();
	}

	public static void game() {
		// TODO Auto-generated method stub
		List<String> items = new ArrayList<String>();
		items.add("rock");
		items.add("paper");
		items.add("scissors");
		items.add("lizard");
		items.add("spock");
		Scanner scan = new Scanner(System.in);
		System.out
				.println("Please pick your choice: rock,paper,scissors,lizard,spock");
		String user = scan.nextLine();
		
		System.out.println("player choice is  : " + user);
		String userChoice = user;
		items.remove(user);
		String computer;
		Random random = new Random();
		computer = items.get(random.nextInt(items.size()));
		System.out.println("computer choice is: " + computer);
		int count = 0;
		System.out.println();

		switch (userChoice) {
		case "scissors":
			if (computer == "paper" || computer == "lizard") {
				count = count + 1;

			}
			break;
		case "paper":
			if (computer == "rock" || computer == "spock") {
				count = count + 1;

			}
			break;
		case "rock":
			if (computer == "lizard" || computer == "scissors") {
				count = count + 1;

			}
			break;
		case "lizard":
			if (computer == "spock" || computer == "paper") {
				count = count + 1;

			}
			break;
		case "spock":
			if (computer == "scissors" || computer == "rock") {
				count = count + 1;

			}
			break;
		default:
			System.out.println("invalid input");

		}

		if (count > 0) {
			System.out.println("congrats you win!");
			System.out.println(user + " beats " + computer);
		} else {
			System.out.println("you lost!");
			System.out.println(user + " loses to " + computer);
		}

	}
}
