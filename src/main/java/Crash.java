import java.time.LocalDate;

/**
 * This class represents a Vehicle Crash History
 */
public class Crash {
  private LocalDate crashDate;
  private String violationType;
  private Name offendingDriver;

  public Crash(LocalDate crashDate, String violationType, Name offendingDriver) {
    this.crashDate = crashDate;
    this.violationType = violationType;
    this.offendingDriver = offendingDriver;
  }

  public LocalDate getCrashDate() {
    return crashDate;
  }

  public String getViolationType() {
    return violationType;
  }

  public Name getOffendingDriver() {
    return offendingDriver;
  }
}
