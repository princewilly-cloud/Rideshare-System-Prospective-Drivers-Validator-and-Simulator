import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

/**
 * This class check if the prospective driver is suitable to be added to
 * the pool of registered and accepted drivers.
 */
public class RegistrationValidator {
  private ProspectiveDriver driver;
  private static final int CURRENT_YEAR = 2023;
  private static final int LEGAL_DRIVER_AGE = 21;
  private static final int ISSUANCE_VALID_MONTH = 6;
  private static final int MINIMUM_EXPIRATION_DIFF = 0;
  private static final int MAX_VEHICLE_YEAR = 15;


  public RegistrationValidator(ProspectiveDriver driver) {
    this.driver = driver;
  }

  public ProspectiveDriver getDriver() {
    return driver;
  }

  public boolean isValid(){
    return checkAge() && checkLicense() && checkInsurance() && checkVehicleHistory() && checkDriverHistory() && checkVehicleInfo();
  }

  public boolean checkAge(){
    boolean result = CURRENT_YEAR - driver.getBirthDay().getYear() >= LEGAL_DRIVER_AGE;
    System.out.println(result);
    return result;
  }

  //if the information in the license, matches all the information in the driver's app
  //then the driver can be registered
  public boolean checkLicense (){
    boolean result = true;
    long diffMonths = ChronoUnit.MONTHS.between(driver.getDriversLicense().getIssuanceDate(), LocalDate.now());
    long diffExpirationDate =ChronoUnit.DAYS.between(LocalDate.now(),driver.getDriversLicense().getExpirationDate());
    if(!driver.getDriverName().equals(driver.getDriversLicense().getDriverName()) || !driver.getBirthDay().equals(driver.getDriversLicense().getBirthdate())
    || !driver.getDriversLicense().getCountryOfIssuance().equals("US")
        || !driver.getDriversLicense().getCountryOfIssuance().equals("Canada") ||
        !(diffMonths < ISSUANCE_VALID_MONTH) || !(diffExpirationDate > MINIMUM_EXPIRATION_DIFF)){
      result = false;
    }

    return result;
  }

  public boolean checkInsurance (){
    boolean result = true;
    List<Vehicle> vehicles = driver.getVehicles();
    for (Vehicle vehicle : vehicles){
      long diffInsuranceExpirationDate =ChronoUnit.DAYS.between(vehicle.getVehicleInsurance().getExpirationDate(), LocalDate.now());
      if(!(diffInsuranceExpirationDate > MINIMUM_EXPIRATION_DIFF) || (!driver.getDriverName().equals(vehicle.getVehicleInsurance().getVehicleOwner())
          && !vehicle.getVehicleInsurance().getOtherDriversCovered().contains(driver.getDriverName())) ){
        result = false;
      }

    }

    return result;
  }
  public boolean checkVehicleInfo() {
    List<Vehicle> vehicles = driver.getVehicles();

    for (Vehicle vehicle : vehicles) {
      long vehicleAgeDiff = vehicle.getYear() - LocalDate.now().getYear();
      if (vehicleAgeDiff >= MAX_VEHICLE_YEAR) {
        return false; // one or more vehicles is too old
      }
    }

    return true; // All vehicles are within the age limit
  }

  public boolean checkVehicleHistory() {
    List<Vehicle> vehicles = driver.getVehicles();

    for (Vehicle vehicle : vehicles) {
      if (vehicle.hasRecentViolationOrCrash()) {
        return false; //one or more vehicle has recent violation or crash
      }
    }

    return true; // No vehicles have recent violation or crash
  }

  public boolean checkDriverHistory() {
    List<AbstractViolation> violations = driver.getViolations();
    for (AbstractViolation violation : violations) {
      if (violation instanceof MovingViolation) {
        String violationType = violation.getViolation();
        if (isInvalidMovingViolation(violationType)) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isInvalidMovingViolation(String violationType) {
    List<String> invalidMovingViolations = Arrays.asList(
        "Reckless driving",
        "Speeding",
        "Driving under influence",
        "Driving without a valid license/insurance"
    );

    return invalidMovingViolations.contains(violationType);
  }




}


