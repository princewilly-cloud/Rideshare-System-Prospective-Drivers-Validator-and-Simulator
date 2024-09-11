import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * This class contains information about the prospective driver's birthday,
 * license information, driver's violation history, driver's name, and vehicle Information.
 */
public class ProspectiveDriver {
  private Name driverName;
  private LocalDate birthDay;
  private List<AbstractViolation> violations;
  private List<Vehicle> vehicles;
  private License driversLicense;

  public ProspectiveDriver(Name driverName, LocalDate birthDay, List<AbstractViolation> violations,
      List<Vehicle> vehicles, License driversLicense) {
    this.driverName = driverName;
    this.birthDay = birthDay;
    this.violations = violations;
    this.vehicles = vehicles;
    this.driversLicense = driversLicense;
  }

  public Name getDriverName() {
    return driverName;
  }

  public LocalDate getBirthDay() {
    return birthDay;
  }

  public List<AbstractViolation> getViolations() {
    return violations;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public License getDriversLicense() {
    return driversLicense;
  }

  @Override
  public String toString() {
    return "ProspectiveDriver{" +
        "driverName=" + driverName +
        ", birthDay=" + birthDay +
        ", violations=" + violations +
        ", vehicles=" + vehicles +
        ", driversLicense=" + driversLicense +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProspectiveDriver that)) {
      return false;
    }
    return Objects.equals(getDriverName(), that.getDriverName())
        && Objects.equals(getBirthDay(), that.getBirthDay()) && Objects.equals(
        getViolations(), that.getViolations()) && Objects.equals(getVehicles(),
        that.getVehicles()) && Objects.equals(getDriversLicense(),
        that.getDriversLicense());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getDriverName(), getBirthDay(), getViolations(), getVehicles(),
        getDriversLicense());
  }
}
