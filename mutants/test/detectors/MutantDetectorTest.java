package detectors;

import detector.MutantDetector;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * @Author MutantDetectorTest
 * @Date 20/02/18
 * @Since Vx.y.z
 **/
public class MutantDetectorTest {

    private final static String[] providedDna = {"ATGCGA",
                                                 "CAGTGC",
                                                 "TTATGT",
                                                 "AGAAGG",
                                                 "CCCCTA",
                                                 "TCACTG"};

    private final static String[] doubleVerticalDna = {"ATGCGA",
                                                       "CGGTGC",
                                                       "TTATGT",
                                                       "ATAAGG",
                                                       "CTCCTA",
                                                       "TTACTG"};

    private final static String[] doubleHorizontalDna = {"ATGCGA",
                                                         "CGGTTC",
                                                         "TTTTGT",
                                                         "ATAAGG",
                                                         "CCCCTA",
                                                         "TTACTG"};

    private final static String[] downAndLeftAndHorizontalDna = {"ATGCGG",
                                                                 "CGGTAC",
                                                                 "TTTAGT",
                                                                 "ATAAGG",
                                                                 "CAACTA",
                                                                 "TTTTAG"};

    private final static String[] downAndRightAndVerticalDna = {"ATGCGG",
                                                                "CGGTAG",
                                                                "TTTACG",
                                                                "ATATGG",
                                                                "CAATTA",
                                                                "TTCTAT"};

    private final static String[] notMutantDna = {"ATGCGA",
                                                  "CGGTGC",
                                                  "TTATAT",
                                                  "ATAAGG",
                                                  "CTCCTA",
                                                  "TTACTG"};

    private final static String[] notSquareMatrix = {"ATGCGG",
                                                     "CGGT",
                                                     "TTTACG",
                                                     "ATAG",
                                                     "CAATTA",
                                                     "TTCTAT"};

    private final static String[] illegalCharactersDna = {"ATGCGG",
                                                          "CGGTXG",
                                                          "TTBACG",
                                                          "ATATGG",
                                                          "CAATTA",
                                                          "TTCTAT"};


    @Test
    public void exampleProvidedDna() {
        MutantDetector md = new MutantDetector();
        assertTrue(md.isMutant(providedDna));
    }

    @Test
    public void doubleVerticalMutant() {
        MutantDetector md = new MutantDetector();
        assertTrue(md.isMutant(doubleVerticalDna));
    }

    @Test
    public void doubleHorizontalMutant() {
        MutantDetector md = new MutantDetector();
        assertTrue(md.isMutant(doubleHorizontalDna));
    }

    @Test
    public void downAndLeftAndHorizontalDna() {
        MutantDetector md = new MutantDetector();
        assertTrue(md.isMutant(downAndLeftAndHorizontalDna));
    }

    @Test
    public void downAndRightAndVerticalDna() {
        MutantDetector md = new MutantDetector();
        assertTrue(md.isMutant(downAndRightAndVerticalDna));
    }

    @Test
    public void notMutantDna() {
        MutantDetector md = new MutantDetector();
        assertFalse(md.isMutant(notMutantDna));
    }

    @Test
    public void notSquareMatrix() {
        MutantDetector md = new MutantDetector();
        try {
            md.isMutant(notSquareMatrix);
            fail();
        }catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void illegalCharactersDna() {
        MutantDetector md = new MutantDetector();
        try {
            md.isMutant(illegalCharactersDna);
            fail();
        }catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

}
