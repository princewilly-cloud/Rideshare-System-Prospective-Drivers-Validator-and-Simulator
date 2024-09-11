import java.time.LocalDate;
import java.util.List;

/**
 * This class represents Information about the vehicles insurance
 */
public class Insurance {
  private Name vehicleOwner;
  private List<Name> otherDriversCovered;
  private LocalDate expirationDate;

  public Insurance(Name vehicleOwner, List<Name> otherDriversCovered, LocalDate expirationDate) {
    this.vehicleOwner = vehicleOwner;
    this.otherDriversCovered = otherDriversCovered;
    this.expirationDate = expirationDate;
  }

  public Name getVehicleOwner() {
    return vehicleOwner;
  }

  public List<Name> getOtherDriversCovered() {
    return otherDriversCovered;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }
}
