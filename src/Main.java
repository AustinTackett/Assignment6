import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> candidates = new LinkedList<>();
        candidates.add("Marcus Fenix");
        candidates.add("Dominic Santiago");
        candidates.add("Damon Baird");
        candidates.add("Cole Train");
        candidates.add("Anya Stroud");


        ElectionSystem election = new ElectionSystem(candidates, 100);
    }
}