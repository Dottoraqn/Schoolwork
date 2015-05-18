import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StringSearching implements StringSearchingInterface {

    @Override
    public List<Integer> kmp(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern == "" || pattern.length() == 0
                || pattern.length() == -1 || text == null || text == ""
                || text.length() == 0 || text.length() == -1) {
            throw new IllegalArgumentException("SHE'S A WITCH, BURN HER");
        }
        List<Integer> matches = new LinkedList<>();
        int i = 0;
        int j = 0;
        int plen = pattern.length();
        int tlen = text.length();

        int[] table = buildFailureTable(pattern);

        while (i < tlen - plen + 1) {
            while (i < tlen && j < plen
                    && text.charAt(i) == pattern.charAt(j)) {
                j++;
                i++;
            }
            if (j == plen) {
                matches.add(i - plen);
                j = 0;
                i = i - plen + 1;
            } else if (j == 0) {
                i++;
            } else {
                j = table[j - 1];
            }
        }
        return matches;
    }


    @Override
    public int[] buildFailureTable(CharSequence text) {
        if (text == null || text == "" || text.length() == 0
                || text.length() == -1) {
            throw new IllegalArgumentException("What else floats?");
        }
        int[] toReturn = new int[text.length()];
        for (int i = 1; i < text.length(); i++) {
            char on = text.charAt(i);
            if (on == text.charAt(toReturn[i - 1])) {
                toReturn[i] = toReturn[i - 1] + 1;
            } else if (on == text.charAt(0)) {
                toReturn[i] = 1;
            }
        }
        return toReturn;
    }

    @Override
    public List<Integer> boyerMoore(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern == "" || pattern.length() == 0
                || pattern.length() == -1 || text == null || text == ""
                || text.length() == 0 || text.length() == -1) {
            throw new IllegalArgumentException("SHE'S A WITCH, BURN HER");
        }
        List<Integer> matches = new ArrayList<Integer>();
        int[] last = buildLastTable(pattern);

        int i = pattern.length() - 1;
        int j = pattern.length() - 1;
        int l = 0;

        while (i < text.length()) {
            char pat = pattern.charAt(j);
            char tex = text.charAt(i);

            if (pat == tex) {
                if (j == 0) {
                    matches.add(i);
                    i += pattern.length();
                    j = pattern.length() - 1;
                } else {
                    i--;
                    j--;
                }
            } else {
                l = last[tex];
                i += pattern.length() - Math.min(j, 1 + l);
                j = pattern.length() - 1;
            }
        }
        return matches;
    }

    @Override
    public int[] buildLastTable(CharSequence pattern) {
        if (pattern == "" || pattern == null || pattern.length() == 0
                || pattern.length() == -1) {
            throw new IllegalArgumentException("Kiiiiiinigit");
        }
        int[] toReturn = new int[Character.MAX_VALUE + 1];
        for (int i = 0; i < toReturn.length; i++) {
            toReturn[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            toReturn[pattern.charAt(i)] = i;
        }
        return toReturn;
    }

    @Override
    public int generateHash(CharSequence current, int length) {
        if (current == null || current == "" || current.length() == 0
                || current.length() == -1 || length == 0 || length < 0
                || length > current.length()) {
            throw new IllegalArgumentException("What is your quest");
        }
        int hash = 0;
        for (int i = 0; i < length; i++) {
            hash += current.charAt(i) * exp(BASE, (length - 1 - i));
        }
        return hash;
    }

    @Override
    public int updateHash(int oldHash, int length, char oldChar, char newChar) {
        return (BASE * oldHash) + (newChar - (exp(BASE, length) * oldChar));
    }

    /**
     * a function for finding the exponent of numbers
     *
     * @param base the base
     * @param pow the number to raise the base to
     * @return result; the base to the pow
     */
    private static int exp(int base, int pow) {
        int result = base;
        if (pow == 0) {
            return 1;
        } else if (pow == 1) {
            return base;
        }
        for (int i = pow; i > 1; i--) {
            result *= base;
        }
        return result;
    }

    @Override
    public List<Integer> rabinKarp(CharSequence pattern, CharSequence text) {
        if (pattern == null || pattern == "" || pattern.length() == 0
                || pattern.length() == -1 || text == null || text == ""
                || text.length() == 0 || text.length() == -1) {
            throw new IllegalArgumentException("African or European");
        }

        List<Integer> matches = new LinkedList<Integer>();
        int patHash = generateHash(pattern, pattern.length());
        int texHash = generateHash(text, pattern.length());
        boolean doMatch = false;
        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (patHash == texHash) {
                for (int j = i; j < pattern.length() + i; j++) {
                    doMatch = pattern.charAt(j - i) == text.charAt(j);
                }
                if (doMatch) {
                    matches.add(i);
                }
            }

            texHash = updateHash(texHash, pattern.length(), text.charAt(i),
                    text.charAt(pattern.length() + i));
        }
        return matches;
    }
}
