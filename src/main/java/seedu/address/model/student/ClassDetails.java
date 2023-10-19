package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.model.student.grades.AssignmentTracker;
import seedu.address.model.student.grades.AttendanceTracker;
import seedu.address.model.student.grades.ClassParticipationTracker;

/**
 * Represents a Student's Class Number in Class Manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassDetails(String)}
 */
public class ClassDetails {

    public static final String MESSAGE_CONSTRAINTS = "Class number can take any values, and it should not be blank";

    // The class number should start with "T".
    public static final String VALIDATION_REGEX = "T.*";

    private static int tutorialCount;
    private static int assignmentCount;

    public final String classDetails;
    public final AttendanceTracker attendanceTracker;
    public final AssignmentTracker assignmentTracker;
    public final ClassParticipationTracker classParticipationTracker;

    /**
     * Constructs an {@code Class Number}.
     *
     * @param classDetails A valid Class Number
     *
     */
    public ClassDetails(String classDetails) {
        requireNonNull(classDetails);
        checkArgument(isValidClassDetails(classDetails), MESSAGE_CONSTRAINTS);
        this.classDetails = classDetails;
        attendanceTracker = new AttendanceTracker(tutorialCount);
        classParticipationTracker = new ClassParticipationTracker(tutorialCount);
        assignmentTracker = new AssignmentTracker(assignmentCount);
    }

    /**
     * Constructs a ClassDetails object.
     */
    public ClassDetails(String classDetails, AttendanceTracker attendanceTracker,
                        AssignmentTracker assignmentTracker, ClassParticipationTracker classParticipationTracker) {
        this.classDetails = classDetails;
        this.attendanceTracker = attendanceTracker;
        this.assignmentTracker = assignmentTracker;
        this.classParticipationTracker = classParticipationTracker;
    }

    /**
     * Marks the specific tutorial as present.
     */
    public ClassDetails markPresent(Index tutNum) {
        this.attendanceTracker.markPresent(tutNum);
        return this;
    }

    /**
     * Returns true if a given string is a valid class number.
     */
    public static boolean isValidClassDetails(String test) {
        return (test.matches(VALIDATION_REGEX));
    }

    public static void setTutorialCount(int tutorialCount) {
        ClassDetails.tutorialCount = tutorialCount;
    }

    public static void setAssignmentCount(int assignmentCount) {
        ClassDetails.assignmentCount = assignmentCount;
    }

    @Override
    public String toString() {
        return classDetails;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassDetails)) {
            return false;
        }

        ClassDetails otherAddress = (ClassDetails) other;
        return classDetails.equals(otherAddress.classDetails)
                && attendanceTracker.equals(otherAddress.attendanceTracker)
                && classParticipationTracker.equals(otherAddress.classParticipationTracker)
                && assignmentTracker.equals(otherAddress.assignmentTracker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classDetails, attendanceTracker, classParticipationTracker, assignmentTracker);
    }
}
