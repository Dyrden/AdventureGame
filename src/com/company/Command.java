package com.company;

public enum Command {
    NORTH       (0),
    SOUTH       (1),
    EAST        (2),
    WEST        (3),

    LOOK        (4),
    STATUS      (5),
    INVENTORY   (6),
    TAKE        (7),
    USE         (8),
    ATTACK      (9),

    HELP        (10),
    EXIT        (11);


    private final int commandCode;
    private String[] commandDescriptions = {
        "'go north' - attempt going north",
        "'go south' - attempt going south",
        "'go east' - attempt going east",
        "'go west' - attempt going west",
        "'look' - look around the current room",
        "'status' - see character status",
        "'inventory' - see inventory",
        "'take <item>' - take visible item",
        "'use <item>' - use item",
        "'attack' - attack",
        "'help' - see commands",
        "'exit' - exit game"
    };

    Command(int commandCode) {
        this.commandCode = commandCode;
    }

    public String getCommandDescription() {
        return commandDescriptions[commandCode];
    }


}
