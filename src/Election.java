import java.util.LinkedList;
import java.util.HashMap;
import java.util.Random;
public class Election {
    public HashMap<String, Integer> votingMap;
    public String[] candidateMaxHeap;
    private Random gen;

    public Election() {
        this.candidateMaxHeap = null;
        this.gen = new Random();
    }
    public void initializeCandidates(LinkedList<String> candidates) {
        int size = candidates.size();
        this.votingMap = new HashMap<>();

        this.candidateMaxHeap = new String[size];
        for(int i = 0; i < size; i++) {
            votingMap.put(candidates.get(i), 0);
            candidateMaxHeap[i] = candidates.get(i);
        }

    }

    public void castVote(String candidate) {
        votingMap.put(candidate, votingMap.get(candidate) + 1);

        for(int i = candidateMaxHeap.length/2; i >= 0 ; i--) {
            heapify(candidateMaxHeap, i);
        }

    }

    public void castRandomVote(){
        int rand_int = gen.nextInt(candidateMaxHeap.length);
        String candidate = candidateMaxHeap[rand_int];
        castVote(candidate);
    }

    public void rigElection(String candidate) {
        int totalVotes = 0;
        for(int votes : votingMap.values()) {
            totalVotes += votes;
        }

        for(String key : votingMap.keySet()) {
            votingMap.put(key, 0);
        }

        votingMap.put(candidate, totalVotes);

    }
    /*public void rigElection(String candidate) {
        // Swap the rigged candidates and the currently winning candidates votes
        String winningCandidate = candidateMaxHeap[0];
        int topVotes = votingMap.get(winningCandidate);
        int riggedVotes = votingMap.get(candidate);

        if (topVotes == 0)
            return;

        // Check for ties, if there is a tie just run the algorithm if not you are already done
        boolean tie = false;
        for (String key : votingMap.keySet()) {
            if (votingMap.get(key) == votingMap.get(winningCandidate) && key != winningCandidate) {
                //if(key != candidate)
                //    winningCandidate = key;
                tie = true;
                break;

            }
        }
        if(!tie && winningCandidate == candidate) {
            return;
        }

        // Give the candidate winning votes
        votingMap.put(candidate, topVotes+1);
        votingMap.put(winningCandidate, riggedVotes);

        //Shave off vote from other candidates
        boolean votingCorrected = false;
        for (String key : votingMap.keySet()) {
            // find one candidate with non-zero votes
            if (!votingCorrected && key != candidate && votingMap.get(key) != 0) {
                votingMap.put(key, votingMap.get(key) - 1);
                votingCorrected = true;
            }
        }
        // If we need to take one vote away from candidate
        if (!votingCorrected) {
            votingMap.put(candidate, votingMap.get(candidate) - 1);
        }


        for (int i = candidateMaxHeap.length / 2; i >= 0; i--) {
            heapify(candidateMaxHeap, i);
        }
    `}
    */


        public String[] getTopKCandidates ( int k){
            String[] topk = new String[k];
            int heapLen = candidateMaxHeap.length;

            // Remove root of heap K times
            for (int i = 0; i < k && i < heapLen; i++) {
                topk[i] = candidateMaxHeap[0];
                candidateMaxHeap[0] = candidateMaxHeap[heapLen - 1 - i];
                candidateMaxHeap[heapLen - 1 - i] = null;

                for (int j = candidateMaxHeap.length / 2; j >= 0; j--) {
                    heapify(candidateMaxHeap, j);
                }
            }

            // Add removed nodes back into heap
            for (int i = 0; i < k && i < heapLen; i++) {
                candidateMaxHeap[heapLen - 1 - i] = topk[i];
            }

            //reheapify
            for (int j = candidateMaxHeap.length / 2; j >= 0; j--) {
                heapify(candidateMaxHeap, j);
            }

            return topk;
        }

        public void auditElection () {
            String[] candidates = getTopKCandidates(candidateMaxHeap.length);
            for (int i = 0; i < candidateMaxHeap.length; i++) {
                System.out.println(candidates[i] + " - " + votingMap.get(candidates[i]));
            }
        }

        public void heapify (String[] maxHeap,int i){
            int index_largest = i;
            int index_left = 2 * i + 1;
            int index_right = 2 * i + 2;

            if (index_left < candidateMaxHeap.length &&
                    candidateMaxHeap[index_left] != null &&
                    votingMap.get(maxHeap[index_left]) > votingMap.get(maxHeap[index_largest])) {
                index_largest = index_left;
            }
            if (index_right < candidateMaxHeap.length &&
                    candidateMaxHeap[index_right] != null &&
                    votingMap.get(maxHeap[index_right]) > votingMap.get(maxHeap[index_largest])) {
                index_largest = index_right;
            }

            if (index_largest != i) {
                String temp = maxHeap[i];
                maxHeap[i] = maxHeap[index_largest];
                maxHeap[index_largest] = temp;
                heapify(maxHeap, index_largest);
            }
        }
}
