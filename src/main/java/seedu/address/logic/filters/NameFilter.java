package seedu.address.logic.filters;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.customer.Customer;


public class NameFilter extends AbstractFilter {
    protected static final int MISTAKE_THRESHOLD = 3;

    private final String[] nameList;

    /**
     * Creates a filter that filters by name, with tolerances for mis-spelling.
     *
     * @param nameListSingleString String with names to search, separated by spaces.
     */
    public NameFilter(String nameListSingleString) { //TODO: Have good error checking
        super(nameListSingleString);
        this.nameList = nameListSingleString.split("\\s+");
    }

    @Override
    public boolean test(Customer customer) {
        String[] customerNameTokens = customer.getName().fullName.split("\\s+");
        for (String token : customerNameTokens) {
            for (String possibleName : nameList) {
                if (levenshteinDistance(token, possibleName) <= MISTAKE_THRESHOLD) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Customer> filterAllCustomers(List<Customer> customer) {
        return customer.parallelStream() //TODO: Consider this carefully
                       .filter(this::test)
                       .collect(Collectors.toUnmodifiableList());
    }
    //@@author nighoggDatatype-reused
    //Reused from https://stackoverflow.com/a/13564498/11358676
    private static int levenshteinDistance(String s1, String s2) {
        return dist(s1.toCharArray(), s2.toCharArray());
    }

    private static int dist(char[] s1, char[] s2) {
        // memoize only previous line of distance matrix
        int[] prev = new int[ s2.length + 1 ];

        for (int j = 0; j < s2.length + 1; j++) {
            prev[ j ] = j;
        }

        for (int i = 1; i < s1.length + 1; i++) {
            // calculate current line of distance matrix
            int[] curr = new int[ s2.length + 1 ];
            curr[0] = i;

            for (int j = 1; j < s2.length + 1; j++) {
                int d1 = prev[ j ] + 1;
                int d2 = curr[ j - 1 ] + 1;
                int d3 = prev[ j - 1 ];
                if (s1[i - 1] != s2[j - 1]) {
                    d3 += 1;
                }
                curr[j] = Math.min(Math.min(d1, d2), d3);
            }
            // define current line of distance matrix as previous
            prev = curr;
        }
        return prev[s2.length];
    }
    //@@author
}
