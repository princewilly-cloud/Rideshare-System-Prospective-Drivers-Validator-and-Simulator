import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the database collection containing the accepted drivers. The underlying
 * data structure is a HashMap.
 */
public class EligibleDriverPool {
  Map<String, List<ProspectiveDriver>> database;
  public EligibleDriverPool() {
    database = new HashMap<>();
  }
  public void addDriver(ProspectiveDriver driver){
    String lastName = driver.getDriverName().getLastName();
    //Possibly a list of drivers with same last name exist
    if(database.containsKey(lastName)){
      List<ProspectiveDriver> driversSameLastNameList = database.get(lastName);
      driversSameLastNameList.add(driver);
    }
    else{
      List<ProspectiveDriver> driversSameLastNameList = new ArrayList<>();
      driversSameLastNameList.add(driver);
      database.put(lastName,driversSameLastNameList);
    }
  }

  public Map<String, List<ProspectiveDriver>> getDatabase() {
    return database;
  }

  /**
   * This method displays on the console the full name of a driver whose last name is the same as the queried name.
   * it then displays the driver's Info like the vehicles registered with the driver, and violations
   * @param lastName, the queried last name
   */
  public void provideInfo(String lastName){
    List<ProspectiveDriver> drivers = database.get(lastName);
    if(drivers.isEmpty()){
      System.out.println("No registered driver found");
    }
    else{
      for(ProspectiveDriver driver : drivers){
        System.out.println(driver.getDriverName().toString());
        for(Vehicle vehicle : driver.getVehicles()){
          System.out.println(vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel());
        }
        List<AbstractViolation> violations = driver.getViolations();
        if (!violations.isEmpty()) {
          System.out.println("Driving violations:");
          for (AbstractViolation violation : violations) {
            System.out.println(violation.getViolation());
          }
        }
      }
    }
  }

}


