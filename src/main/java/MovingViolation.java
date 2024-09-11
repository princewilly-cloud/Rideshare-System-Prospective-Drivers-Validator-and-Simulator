import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents information of a Movable violation.It inherits the properties of
 * the AbstractViolation
 */
public class MovingViolation extends AbstractViolation {

  public MovingViolation(LocalDate violationDate, String violation) {
    super(violationDate, violation);
    List<String> MovingViolations = Arrays.asList("Distracted driving",
        "Reckless driving","Speeding","Driving under influence",
        "Failure to respect traffic signs","Driving without a valid license and/or insurance");
    if(MovingViolations.contains(violation)){
      this.violation = violation;
    }
  }
}
