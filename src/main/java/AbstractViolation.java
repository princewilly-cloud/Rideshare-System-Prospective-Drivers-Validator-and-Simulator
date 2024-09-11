import java.time.LocalDate;
/**
 * This represents a generic violation
 */

public abstract class AbstractViolation {
  protected LocalDate violationDate;
  protected String violation;

  public AbstractViolation(LocalDate violationDate, String violation) {
    this.violationDate = violationDate;
    this.violation = violation;
  }

  public String getViolation() {
    return violation;
  }

  public LocalDate getViolationDate() {
    return violationDate;
  }
}
