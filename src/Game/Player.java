package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private int id;
    private int foodCount;
    private int eggCount;
    private int actionCubes;
    private ArrayList<Bird> birds;
    private Board board;
    private Dice dice;

    public Player(int id){
        this.id = id;
        this.foodCount = 5;
        this.eggCount = 0;
        this.actionCubes = 8;
        birds = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            birds.add(Main.drawBirdFromStack());
        }
        this.board = new Board();
        this.dice = new Dice();
    }

    // The player chooses a bird and plays it on his board
    public void playABird(){
        System.out.println("Available birds:");
        for (int i = 0; i < birds.size(); i++){ // displays all playable birds
            if (foodCount >= birds.get(i).getRequiredFood()){
            	String hab;
            	if(birds.get(i).getHabitat() == 0)
            		hab = "Forest";
            	else if(birds.get(i).getHabitat() == 1)
            		hab = "Grasslands";
            	else
            		hab = "Wetlands";
                System.out.println(i+1 + ": " + birds.get(i).getName() + ". Food cost " + birds.get(i).getRequiredFood()
                		+ ". lives in " + hab);
            }
        }
        System.out.println("Enter number of the bird you want to play!");
        Scanner in = new Scanner(System.in);

        int number = in.nextInt() - 1;
        Bird bird = birds.get(number);
        int habitat = bird.getHabitat();
        for (int i = 0; i < 5; i++){
            if (this.board.spacefree(habitat, i)){
                this.board.placeCard(habitat, i);
                foodCount -= bird.getRequiredFood();
                birds.remove(bird);
                break;
            }
        }
    }

    // gets food based on how many birds are in the first habitat
    public void gainFood(){
        int gainedFood = 1;
        gainedFood += board.birdsInForrest();
        this.foodCount += gainedFood;

        System.out.println("You have " + board.birdsInForrest() + " birds in your forrest section, so your food count has increased by " + gainedFood + "! Your new food count is " + this.foodCount + "!");
    }
    // lays eggs based on how many birds are in the middle habitat
    public void layEggs(){
    	int newEggs = 1;
    	newEggs += board.birdsInGrasslands();
    	eggCount += newEggs;
    	System.out.println("Your egg count has increased by " + newEggs  + "! Your new egg count is " +eggCount + "!");
    }

    // add a bird to the players bird arraylist
    public void drawBird(){
        birds.add(Main.drawBirdFromStack());
    }

    // returns the moves a player can make
    public ArrayList<String> getAvailableMoves(){

        ArrayList<String> availableMoves = new ArrayList<>();
        for (int i = 0; i < birds.size(); i++){
            if (birds.get(i).getRequiredFood() <= foodCount){
                availableMoves.add("1: place bird");
                break;
            }
        }

        availableMoves.add("2: gain food");

      
        availableMoves.add("3: lay eggs");
      

        availableMoves.add("4: draw bird card");
        return availableMoves;
    }

    // calculates score for the player
    public int getScore()
    {
    	return eggCount + board.numBirdsOnBoard();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public int getEggCount() {
        return eggCount;
    }

    public void setEggCount(int eggCount) {
        this.eggCount = eggCount;
    }

    public ArrayList<Bird> getBirds() {
        return birds;
    }

    public void setBirds(ArrayList<Bird> birds) {
        this.birds = birds;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getActionCubes() { return actionCubes; }

    public void setActionCubes(int actionCubes) { this.actionCubes = actionCubes; }
}
