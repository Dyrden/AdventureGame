package com.company;

public enum Command {
    NORTH       ("'go north' - attempt going north"),
    SOUTH       ("'go south' - attempt going south"),
    EAST        ("'go east' - attempt going east"),
    WEST        ("'go west' - attempt going west"),

    LOOK        ("'look' - look around the current room"),
    STATUS      ("'status' - see character status"),
    INVENTORY   ("'inventory' - see inventory"),
    DROP        ("'drop <item>' - drop item"),
    TAKE        ("'take <item>' - take visible item"),
    USE         ("'use <item>' - use item"),
    EQUIP       ("'equip <item>' - equip item"),
    ATTACK      ("'attack' - attack"),

    HELP        ("'help' - see commands"),
    EXIT        ("'exit' - exit game");


    private final String commandDescription;

    Command(String commandDescription) {
        this.commandDescription = commandDescription;
    }

    public String getCommandDescription() {
        return commandDescription;
    }


}
