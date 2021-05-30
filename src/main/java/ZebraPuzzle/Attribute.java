package ZebraPuzzle;

import java.util.EnumSet;

public enum Attribute {
    ENGLISHMAN,
    SPANIARD,
    UKRANIAN,
    NORWEGIAN,
    JAPANESE,

    RED,
    GREEN,
    IVORY,
    YELLOW,
    BLUE,

    WATER,
    TEA,
    MILK,
    ORANGE_JUICE,
    COFFEE,

    KOOLS,
    CHESTERFIELD,
    OLD_GOLD,
    LUCKY_STRIKE,
    PARLIAMENT,

    DOG,
    FOX,
    HORSE,
    ZEBRA,
    SNAIL;

    public static EnumSet<Attribute> nationalities = EnumSet.of(ENGLISHMAN, SPANIARD, UKRANIAN, NORWEGIAN, JAPANESE);
    public static EnumSet<Attribute> colors = EnumSet.of(RED, GREEN, IVORY, YELLOW, BLUE);
    public static EnumSet<Attribute> drinks = EnumSet.of(WATER, TEA, MILK, ORANGE_JUICE, COFFEE);
    public static EnumSet<Attribute> smokes = EnumSet.of(KOOLS, CHESTERFIELD, OLD_GOLD, LUCKY_STRIKE, PARLIAMENT);
    public static EnumSet<Attribute> pets = EnumSet.of(DOG, FOX, HORSE, ZEBRA, SNAIL);

}
