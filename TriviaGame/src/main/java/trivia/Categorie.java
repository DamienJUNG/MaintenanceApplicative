package trivia;

public enum Categorie {
    ROCK("Rock","rock"),
    POP("Pop","pop"),
    SCIENCE("science","science"),
    SPORT("Sports","sport"),
    GEOGRAPHY("Géographie","geography");

    private final String name;
    private final String fileName;
    Categorie(String name, String fileName) {
        this.name = new String(name);
        this.fileName = new String(fileName);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }
}
