package detector;

/**
 * @Author Maximiliano Schultheis
 * @Date 20/02/18
 * @Since V1.0.0
 **/
public class MutantDetector implements Detector{

    private static final String ALLOWED_CHARS = "ATCG";
    private static final Integer SPACES_TO_CHECK = 3;

    @Override
    public boolean isMutant(String[] dna) {

        validateDna(dna);

        final Integer rows = dna.length;
        final Integer columns = dna[0].length();

        Integer matches = 0;

        for (Integer r = 0; r < rows; r++) {
            for (Integer c = 0; c < columns; c++){
                Character actualChar = dna[r].charAt(c);
                if(!isValidChar(actualChar)) throw new IllegalArgumentException("Illegal characters in DNA sequence");

                // If we already met our condition, we skip the detection steps, we just
                // keep iterating for character validations.
                if (matches >= 2) continue;

                // We consider (0,0) on the upper left corner.
                if(c + SPACES_TO_CHECK < columns && checkRight(r, c, actualChar, dna)) {
                    matches++;
                }

                if (r + SPACES_TO_CHECK < rows){
                    if(checkDown(r, c, actualChar, dna)){
                        matches++;
                    }
                    if(c + SPACES_TO_CHECK < columns && checkDownAndRight(r, c, actualChar, dna)) {
                        matches++;
                    }
                    if(c - SPACES_TO_CHECK >= 0 && checkDownAndLeft(r, c, actualChar, dna)) {
                        matches++;
                    }
                }
            }
        }

        // We let the double for finish so the isValidChar function is called on every position.
        // This way we compute and validate the matrix on one iteration.
        return matches >= 2;
    }

    private void validateDna(String[] dna) {
        if (dna == null) {
            throw new IllegalArgumentException("Null DNA");
        }
        if(!isSquareMatrix(dna)) {
            throw new IllegalArgumentException("DNA sequence is not an NxN matrix");
        }
        if (dna.length < SPACES_TO_CHECK + 1) {
            throw new IllegalArgumentException("DNA sequence to small, must be at least: " + (SPACES_TO_CHECK + 1) + "x" + (SPACES_TO_CHECK + 1));
        }
    }

    private Boolean isSquareMatrix(String[] dna) {
        Integer amountRows = dna.length;

        for (String row : dna) {
            if (row.length() != amountRows)
                return false;
        }
        return true;
    }

    private Boolean isValidChar(Character c) {
        return ALLOWED_CHARS.indexOf(Character.toUpperCase(c)) != -1;
    }

    private Boolean checkRight(Integer r, Integer c, Character actualCharacter, String[] dna) {
        Integer i;
        for(i = c + 1; i <= c + SPACES_TO_CHECK; i++) {
            if (!actualCharacter.equals(dna[r].charAt(i)))
                return false;
        }
        return true;
    }

    private Boolean checkDown(Integer r, Integer c, Character actualCharacter, String[] dna) {
        Integer i;
        for(i = r + 1; i <= r + SPACES_TO_CHECK; i++) {
            if (!actualCharacter.equals(dna[i].charAt(c)))
                return false;
        }
        return true;
    }

    private Boolean checkDownAndRight(Integer r, Integer c, Character actualCharacter, String[] dna) {
        Integer i, j = c + 1;
        for(i = r + 1; i <= r + SPACES_TO_CHECK; i++) {
            if (!actualCharacter.equals(dna[i].charAt(j)))
                return false;
            j++;
        }
        return true;
    }

    private Boolean checkDownAndLeft(Integer r, Integer c, Character actualCharacter, String[] dna) {
        Integer i, j = c - 1;
        for(i = r + 1; i <= r + SPACES_TO_CHECK; i++) {
            if (!actualCharacter.equals(dna[i].charAt(j)))
                return false;
            j--;
        }
        return true;
    }
}
