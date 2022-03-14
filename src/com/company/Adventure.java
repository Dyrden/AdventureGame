package com.company;

public class Adventure {

    private boolean running = true;
    private Room currentRoom;
    /*
        e
        w
        s
        n
        exit
        look
        help

        * */

    public static void main(String[] args) {
        new Adventure().run();
    }


    public void run() {
        while(running){

        }
    }

    public void commands(String command) {
        switch (command){
            case "e" -> System.out.println();
            case "w" -> System.out.println();
            case "n" -> System.out.println();
            case "s" -> System.out.println();
            case "look" -> System.out.println();
            case "help" -> System.out.println();
            case "exit" -> System.out.println();
        }


    }

    // public void createAndConnectRooms()


}

