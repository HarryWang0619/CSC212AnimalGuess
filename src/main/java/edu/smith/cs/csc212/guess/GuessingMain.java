package edu.smith.cs.csc212.guess;
import java.io.File;
/**
 * This is the main file where the whole game play loop goes.
 *
 */
public class GuessingMain {
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        DecisionTreeNode knowledge;
        File tempFile = new File("knowledge.dat");
        if (tempFile.exists()) {
            knowledge = TreeIO.load("knowledge.dat");
        } else {
            knowledge = new DecisionTreeNode("Is it a mammal?", 
            new DecisionTreeNode("Mouse"), 
            new DecisionTreeNode("Snake"));
        }

        System.out.println(knowledge);

        System.out.println("Welcome to the guessing game!");
        TextInput input = new TextInput();


        while(true) {
            System.out.println("To play, first think of an animal.");

            // TODOED: Maybe something like this?
            knowledge.play(knowledge, true);
            // knowledge.play(); <- leading toward recursive solution.
            // playGame(knowledge) <- 

            // TODOED: remove this.
            // System.out.println("...I guess there's no game here yet...");
            TreeIO.save("knowledge.dat", knowledge);
            if (input.confirm("Do you want to play again?")) {
                continue;
            } else {
                TreeIO.saveHTML(knowledge);
                break;
            }
        }
    }
}