package edu.smith.cs.csc212.guess;
import java.util.List;

/**
 * A class to store the knowledge learned by the animal guessing-game.
 */
public class DecisionTreeNode {
    /**
     * This is a question (in the middle) or an animal (at a leaf).
     */
    String text;
    /**
     * This is a reference to a node for a "yes" answer.
     */
    DecisionTreeNode yes;
    /**
     * This is a reference to a node for a "no" answer.
     */
    DecisionTreeNode no;

    /**
     * Create a leaf node -- probably just an animal.
     * @param value the name of the animal.
     */
    public DecisionTreeNode(String value) {
        this.text = value;
        this.yes = null;
        this.no = null;
    }

    /**
     * Create a node with children.
     * 
     * @param question the question to split the data.
     * @param yes the animal or next question if the answer is yes.
     * @param no the animal or next question if the answer is no.
     */
    public DecisionTreeNode(String question, DecisionTreeNode yes, DecisionTreeNode no) {
        this.text = question;
        this.yes = yes;
        this.no = no;
    }

    /**
     * Is this a leaf node?
     */
    public boolean isLeaf() {
        return this.yes == null && this.no == null;
    }

    /**
     * Turn this decision tree into a String.
     */
    public String toString() {
        return "(" + text + "\t" + this.yes + "\t" + this.no + ")";
    }

    /**
     * Is Yes or No empty?
     */
    public boolean haveChildren() {
        return (!(this.yes == null) && !(this.no == null));
    }

    /**
     * play!
     */
    public void play(DecisionTreeNode now, boolean choice) {
        TextInput input = new TextInput();
        if (this.haveChildren()) {
            if (input.confirm(this.text)) {
                this.yes.play(this, true);
            } else {
                this.no.play(this, false);
            }
        } else {
            if (input.endGame(this.text)) {
                this.update(this.text, now, choice);
            }
        }  
    }

    public void update(String prompt, DecisionTreeNode pre, boolean choice) {
        TextInput input = new TextInput();
		String response0 = input.getNotEmptyInput("What animal were you thinking of?\n");
		System.out.println(response0);
		String response1 = input.getNotEmptyInput("What is a question that would be YES for a " + prompt + " but not a " + response0 + "?\n");
        System.out.println(response1);
        // System.out.println(pre.text);
        // System.out.println(pre.yes);
        // System.out.println(pre.yes.text);
        if (choice) {
            pre.yes = new DecisionTreeNode(response1, pre.yes, new DecisionTreeNode(response0));
        } else {
            pre.no = new DecisionTreeNode(response1, pre.no, new DecisionTreeNode(response0));
        }
        // pre.yes = new DecisionTreeNode(response1, pre.yes, new DecisionTreeNode(response0));
	}
}