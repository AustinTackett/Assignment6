import java.util.Arrays;
import java.util.LinkedList;

public class ElectionSystem {
    Election election;
    public ElectionSystem(LinkedList<String> candidates, int p) {
        election = new Election();
        election.initializeCandidates(candidates);
        for(int i = 0; i < p; i++) {
           election.castRandomVote();
        }

        System.out.println("Top 3 candidates after " + p + " votes: " + Arrays.toString(election.getTopKCandidates(3)) );

        election.auditElection();

        election.rigElection("Marcus Fenix");

        System.out.println("Top 3 candidates after rigging election: " + Arrays.toString(election.getTopKCandidates(3)) );

        election.auditElection();
    }
}
