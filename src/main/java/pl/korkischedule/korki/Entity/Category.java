package pl.korkischedule.korki.Entity;

public enum Category {
    MATEMATYKA,
    POLSKI,
    ANGIELSKI,
    NIEMIECKI,
    ROSYJSKI,
    GEOGRAFIA,
    CHEMIA,
    FIZYKA,
    INFORMATYKA,
    HISTORIA;

    public static boolean contains(String testEnum) {
        for (Category value : Category.values()) {
            if (value.name().equals(testEnum))
                return true;
        }
        return false;
    }

}
