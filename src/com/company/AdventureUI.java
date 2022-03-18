package com.company;

public class AdventureUI {


    public void displayGameIntro() {
        System.out.println("\nWelcome to the Adventure of your lifetime.");
        System.out.println("\nYou are walking around in the forest near your town.");
        System.out.println("After wandering for hours, you decided to take a break, laying down your backpack.");
        System.out.println("After a short break, you decide to set up camp and go to look for firewood.");
        System.out.println("All of a sudden, the feeling of the ground pushing against your feet fails you.");
        System.out.println("The sensation of falling is the last thing you remember\n");
    }

    public void displayNoSuchCommand() {
        System.out.println("No such command is available, try again.");
    }


    public void displayExitGame() {
        System.out.println("Game over.");
        System.out.println("Thanks for playing.");
    }

    public void displayWinGame() {

    }

    public void displayLoseGame() {

    }


    public void displayTurnStartText(){
        System.out.println("What would you like to do? (type 'help' to view commands)");
    }

    public void displayCommandOptions(){
        for (Command command : Command.values()) {
            System.out.println(command.getCommandDescription());
        }
    }

    public void displayDropMessage(Item item){
        System.out.println("Dropped " + item.toString());
    }

}


