package Game;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int amountOfPlayers;
    private static Player[] players;
    private static Dice dice;
    private static int turn, round;
    private static ArrayList<Bird> birdStack = new ArrayList<>();

    public static void main(String[] args){

        initBirds();
        init();

        turn = 0;
        round = 0;
        while (round < 3){
            turn(players[turn%amountOfPlayers]);
            turn++;
            if(turn%amountOfPlayers == 0)
            	round++;
        }
    	printScores();
    }

    // checks the available actioncubes for a player and tells the player what options he can choose from to make a move
    private static void turn(Player player) {
        player.setActionCubes(8);
        while (player.getActionCubes() > 0){
            ArrayList<String> availableMoves = player.getAvailableMoves();
            System.out.println("It's the turn of Player " + player.getId());
            player.getBoard().display();
            System.out.println("You have " + player.getActionCubes() + " Action Cubes left!");
            System.out.println("Your available moves are:\n");
            for (int j = 0; j < availableMoves.size(); j++){
                System.out.println(availableMoves.get(j));
            }
            System.out.println("5: End turn");
            System.out.println("\nChoose one of the available moves by typing its number!");
            Scanner in = new Scanner(System.in);
            int moveNumber = in.nextInt();

            switch (moveNumber){
                case 1:
                    player.playABird();
                    break;
                case 2:
                    player.gainFood();
                    break;
                case 3:
                    player.layEggs();
                    break;
                case 4:
                    player.drawBird();
                    break;
                case 5:
                    player.setActionCubes(8);
                    player.getBoard().display();
                    return;
            }
            player.getBoard().display();
            player.setActionCubes(player.getActionCubes()-1);
        }
    }

    // initializes the game for the given amount of player
    private static void init(){
        System.out.println("+++ Welcome to Wingspan! +++\n" +
                "Please enter amount of players(1-4):");
        Scanner in = new Scanner(System.in);
        amountOfPlayers = in.nextInt();
        players = new Player[amountOfPlayers];

        for (int i = 0 ; i < players.length ; i++) {
            players[i] = new Player(i+1);
        }

        dice = new Dice();
    }

    // adds the birds to the birdstack
    public static void initBirds(){

        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("birds.json"));
            JSONArray birds = (JSONArray) jsonObject.get("birds");

            for (Object o : birds) {
                JSONObject bird = (JSONObject) o;
                System.out.println(bird.get("EnglishName"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*birdStack.add(new Bird(0,1,0,0,"Hawk"));
        birdStack.add(new Bird(1,3,0,1,"Klaus"));
        birdStack.add(new Bird(2,4,0,2,"Gunther"));
        birdStack.add(new Bird(0,2,0,3,"Rudiger"));
        birdStack.add(new Bird(1,1,0,4,"Maria"));
        birdStack.add(new Bird(2,1,0,5,"Stephanie"));
        birdStack.add(new Bird(0,4,0,6,"Mustafa"));
        birdStack.add(new Bird(1,11,0,7,"Elke"));
        birdStack.add(new Bird(2,14,0,8,"Hawk"));
        birdStack.add(new Bird(0,3,0,9,"Acorn Woodpecker"));
        birdStack.add(new Bird(2,3,0,10,"American Avocet"));
        birdStack.add(new Bird(2,3,0,11,"American Bittern"));
        birdStack.add(new Bird(2,2,0,12,"American Coot"));
        birdStack.add(new Bird(1,1,0,13,"American Crow"));
        birdStack.add(new Bird(1,2,0,14,"American Goldfinch"));
        birdStack.add(new Bird(1,2,0,15,"American Kestrel"));
        birdStack.add(new Bird(2,2,0,16,"American Oystercatcher"));
        birdStack.add(new Bird(0,1,0,17,"American Robin"));
        birdStack.add(new Bird(2,2,0,18,"American White Pelican"));
        birdStack.add(new Bird(0,3,0,19,"American Woodcock"));
        birdStack.add(new Bird(2,2,0,20,"Anhinga"));
        birdStack.add(new Bird(1,1,0,21,"Anna's Hummingbird"));
        birdStack.add(new Bird(2,3,0,22,"Ash-Throated Flycather"));
        birdStack.add(new Bird(2,3,0,23,"Atlantic Puffin"));
        birdStack.add(new Bird(1,2,0,24,"Baird's Sparrow"));
        birdStack.add(new Bird(3,2,0,25,"Bald Eagle"));
        birdStack.add(new Bird(0,3,0,26,"Baltimore Oriole"));
        birdStack.add(new Bird(0,2,0,27,"Barn Owl"));
        birdStack.add(new Bird(1,1,0,28,"Barn Swallow"));
        birdStack.add(new Bird(0,1,0,29,"Barred Owl"));
        birdStack.add(new Bird(2,3,0,30,"Barrow's Goldeneye"));
        birdStack.add(new Bird(0,2,0,31,"Bell's Vireo"));
        birdStack.add(new Bird(2,2,0,32,"Belted Kingfisher"));
        birdStack.add(new Bird(0,3,0,33,"Bewick's Wren"));
        birdStack.add(new Bird(2,2,0,34,"Black Skimmer"));
        birdStack.add(new Bird(2,2,0,35,"Black Tern"));
        birdStack.add(new Bird(0,1,0,36,"Black Vulture"));
        birdStack.add(new Bird(2,2,0,37,"Black-Bellied Whistling-Duck"));
        birdStack.add(new Bird(1,2,0,38,"Black-billed Maggie"));
        birdStack.add(new Bird(2,3,0,39,"Black-Crowned Night Heron"));
        birdStack.add(new Bird(2,2,0,40,"Black-necked Stilt"));
        birdStack.add(new Bird(1,3,0,41,"Blue Grosbeak"));
        birdStack.add(new Bird(0,2,0,42,"Blue Jay"));
        birdStack.add(new Bird(0,1,0,43,"Blue-Gray Gnatcatcher"));
        birdStack.add(new Bird(1,2,0,44,"Blue-Winged Warbler"));
        birdStack.add(new Bird(1,3,0,45,"Bobolink"));
        birdStack.add(new Bird(1,2,0,46,"Brewer's Blackbird"));
        birdStack.add(new Bird(1,2,0,47,"Bronzed Cowbird"));
        birdStack.add(new Bird(2,2,0,48,"Brown Pelican"));
        birdStack.add(new Bird(1,1,0,49,"Brown-headed Cowbird"));
        birdStack.add(new Bird(1,2,0,50,"Burrowing Owl"));
        birdStack.add(new Bird(0,2,0,51,"Bushtit"));
        birdStack.add(new Bird(0,1,0,52,"California Condor"));
        birdStack.add(new Bird(0,3,0,53,"California Quail"));*/
    }

    // return a random bird card add removes it from the bird card stack
    public static Bird drawBirdFromStack(){
        Random r = new Random();
        int i = r.nextInt(birdStack.size());

        return birdStack.get(i);
    }
    
    // prints the scores of all the players and says who the winner is
    private static void printScores()
    {
    	int i = 1;
    	for(Player p : players)
    	{
    		System.out.println("player " + i + " score is " + p.getScore());
    		i++;
    	}
    }
}
