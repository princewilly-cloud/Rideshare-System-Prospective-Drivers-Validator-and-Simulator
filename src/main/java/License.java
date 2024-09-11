import java.time.LocalDate;

/**
 * This class represents Information about a prospective driver's license including information about the license unique number,
 * a driver’s name, a driver’s address, a driver’s birthdate, country and state of issuance, and issuance and expiration date.
 */
public class License {
  private Name driverName;
  private LocalDate birthdate;
  private String countryOfIssuance;
  private String stateOfIssuance;
  private LocalDate issuanceDate;
  private LocalDate expirationDate;
  private String driversAddress;
  private String uniqueID;

  public License(Name driverName, LocalDate birthdate, String countryOfIssuance, String stateOfIssuance,
      LocalDate issuanceDate, LocalDate expirationDate, String driversAddress, String uniqueID) {
    this.driverName = driverName;
    this.birthdate = birthdate;
    this.countryOfIssuance = countryOfIssuance;
    this.stateOfIssuance = stateOfIssuance;
    this.issuanceDate = issuanceDate;
    this.expirationDate = expirationDate;
    this.driversAddress = driversAddress;
    this.uniqueID = uniqueID;
  }

  public Name getDriverName() {
    return driverName;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public String getCountryOfIssuance() {
    return countryOfIssuance;
  }

  public String getStateOfIssuance() {
    return stateOfIssuance;
  }

  public LocalDate getIssuanceDate() {
    return issuanceDate;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public String getDriversAddress() {
    return driversAddress;
  }

  public String getUniqueID() {
    return uniqueID;
  }
}
