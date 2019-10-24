package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private int id;
    private int foodCount;
    private int eggCount;
    private ArrayList<Bird> birds;
    private Board board;
    private Dice dice;

    public Player(int id){
        this.id = id;
        this.foodCount = 10;
        this.eggCount = 10;
        birds = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            birds.add(Main.drawBirdFromStack());
            System.out.println(i);
        }
        this.board = new Board();
    }

    public void playABird(){
        System.out.println("Available birds:");
        for (int i = 0; i < birds.size(); i++){
            System.out.println(i+1 + ": " + birds.get(i).getName());
        }
        System.out.println("Enter number of the bird you want to play!");
        Scanner in = new Scanner(System.in);

        int birdToPlay = in.nextInt() - 1;
        int habitat = birds.get(birdToPlay).getHabitat();
        for (int i = 0; i < 5; i++){
            if (this.board.spacefree(habitat, i)){
                this.board.placeCard(habitat, i);
                birds.remove(birdToPlay);
                break;
            }
        }
    }

    public void gainFood(){
        // not implemented yet
    }

    public void layEggs(){
        // not implemented yet
    }

    public void drawBird(){
        birds.add(Main.drawBirdFromStack());
    }

    public ArrayList<String> getAvailableMoves(){

        ArrayList<String> availableMoves = new ArrayList<>();
        for (int i = 0; i < birds.size(); i++){
            if (birds.get(i).getRequiredFood() <= foodCount){
                availableMoves.add("1: place bird");
                break;
            }
        }

        availableMoves.add("2: gain food");

        if (!board.isEmpty() && eggCount > 0){
            availableMoves.add("3: lay eggs");
        }

        availableMoves.add("4: draw bird card");
        return availableMoves;
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
}
