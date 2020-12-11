package Greedy;

//Time complexity is O(n^2 * m + n*m) so O(n^2 * m) where n is the len(seq) and m is len(cache).

public class OptimalCaching {
    public static void main(String[] args) {
        char[] seq = new char[] {'a','b','b','c','c','d','a','e','e','c','a','e','d','b','a'};
        char[] cache = new char[3];
        for (int i=0; i<cache.length; i++) cache[i] = ' ';
        System.out.println(CacheMisses(seq, cache));

        seq = new char[] {'a','b','b','e','c','a'};
        cache = new char[2];
        for (int i=0; i<cache.length; i++) cache[i] = ' ';
        System.out.println(CacheMisses(seq, cache));
    }

    public static int CacheMisses(char[] seq, char[] cache) {
        int count = 0;

        for (int i=0; i<seq.length; i++) {
            boolean skip = false;

            for (int j=0; j<cache.length; j++) {
                if (cache[j] == ' ') {
                    cache[j] = seq[i];
                    count++;
                    skip = true;
                    break;
                }
                else if (seq[i] == cache[j]) {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;

            int furthest = 0;
            int index = 0;

            for (int j=0; j<cache.length; j++) {
                boolean found = false;
                for (int k=i+1; k<seq.length; k++) {
                    if (cache[j] == seq[k]) {
                        found = true;
                        if (k > furthest) {
                            furthest = k;
                            index = j;
                            break;
                        }
                    }
                }
                if (found == false) {
                    index = j;
                    break;
                }
            }
            cache[index] = seq[i];
            count++;
        }

        return count;
    }
}
