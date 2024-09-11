import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents information of a Non-Movable violation.It inherits the properties of
 * the AbstractViolation
 */
public class NonMovingViolation extends AbstractViolation {

  public NonMovingViolation(LocalDate violationDate, String violation) {
    super(violationDate, violation);
    List<String> MovingViolations = Arrays.asList("Parking violation",
        "Paperwork violation","Paperwork issues","Problems with the vehicle");
    if(MovingViolations.contains(violation)){
      this.violation = violation;
    }
  }

}
