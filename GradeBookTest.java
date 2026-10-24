/*
 * Name: Akene Crosse
 * Class: CMSC203 Computer Science I - Prof. Thai
 * Lab 3 - JUnit (Testing GradeBook)
 * Date: 10/23/2026
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 tests for the GradeBook class.
 */
public class GradeBookTest {

    // Test fixtures: shared objects used in multiple tests
    private GradeBook g1;
    private GradeBook g2;

    /**
     * Runs before each test: makes two gradebooks that hold 5 scores
     * and adds some scores to each.
     */
    @BeforeEach
    public void setUp() {
        g1 = new GradeBook(5);
        g1.addScore(50.0);
        g1.addScore(75.0);

        g2 = new GradeBook(5);
        g2.addScore(90.0);
        g2.addScore(85.0);
        g2.addScore(70.0);
    }

    /**
     * Runs after each test: clears both gradebooks.
     */
    @AfterEach
    public void tearDown() {
        g1 = null;
        g2 = null;
    }

    /**
     * Checks that addScore stores the scores in the order they were added,
     * that toString lists them with a space after each one, and that
     * addScore returns false once the gradebook is full.
     */
    @Test
    public void testAddScoreAndToString() {
        assertEquals("50.0 75.0 ", g1.toString());
        assertEquals("90.0 85.0 70.0 ", g2.toString());

        // adding another score shows up at the end
        assertTrue(g1.addScore(100.0));
        assertEquals("50.0 75.0 100.0 ", g1.toString());

        // g2 has room for 2 more scores, then it is full
        assertTrue(g2.addScore(60.0));
        assertTrue(g2.addScore(95.0));
        assertFalse(g2.addScore(80.0));
        assertEquals("90.0 85.0 70.0 60.0 95.0 ", g2.toString());
    }

    /**
     * Checks that getScoreSize returns how many scores were added.
     */
    @Test
    public void testGetScoreSize() {
        assertEquals(2, g1.getScoreSize());
        assertEquals(3, g2.getScoreSize());

        // size goes up by one after adding a score
        g1.addScore(88.0);
        assertEquals(3, g1.getScoreSize());

        // a new gradebook starts with no scores
        assertEquals(0, new GradeBook(5).getScoreSize());
    }

    /**
     * Checks that sum adds up all the scores.
     */
    @Test
    public void testSum() {
        assertEquals(125.0, g1.sum(), 0.0001);   // 50 + 75
        assertEquals(245.0, g2.sum(), 0.0001);   // 90 + 85 + 70
    }

    /**
     * Checks that minimum returns the smallest score,
     * and 0 when there are no scores.
     */
    @Test
    public void testMinimum() {
        assertEquals(50.0, g1.minimum(), 0.0001);
        assertEquals(70.0, g2.minimum(), 0.0001);

        GradeBook empty = new GradeBook(5);
        assertEquals(0.0, empty.minimum(), 0.0001);
    }

    /**
     * Checks that finalScore drops the lowest score when there are 2 or more
     * scores, returns the only score when there is 1, and returns 0 when
     * there are none.
     */
    @Test
    public void testFinalScore() {
        assertEquals(75.0, g1.finalScore(), 0.0001);    // 125 - 50
        assertEquals(175.0, g2.finalScore(), 0.0001);   // 245 - 70

        GradeBook empty = new GradeBook(5);
        assertEquals(0.0, empty.finalScore(), 0.0001);

        GradeBook single = new GradeBook(5);
        single.addScore(100.0);
        assertEquals(100.0, single.finalScore(), 0.0001);
    }
}
