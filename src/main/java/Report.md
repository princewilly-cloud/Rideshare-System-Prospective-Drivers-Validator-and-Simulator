#### Question 1: Example of Inheritance and composition 

Inheritance
```java
import java.time.LocalDate;
import java.util.Date;

public class MovingViolation extends AbstractViolation {

public MovingViolation(LocalDate violationDate, String violation) {
super(violationDate, violation);
}
}
```
Composition 
```java
import java.time.LocalDate;
import java.util.List;

public class ProspectiveDriver {
  private Name driverName;
  private LocalDate birthDay;
  private List<AbstractViolation> violations;
  private List<Vehicle> vehicles;
  private License driversLicense;

```
the composition exist with Prospective Driver has a Name and License. 

#### Question 2: Example of Abstract class
```java
import java.time.LocalDate;

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
```
#### Question 3: Overloading

At the moment, the override is not necessary because any violation will have a date, as 
LocalDate object, and the violation description as a String object. The downfall of my 
current implementation of both violation classes is that there is no differentiation 
between a Moving violation and a Non-moving violation. The program has no way of determining when 
to create an instance of moving violation or an instance of non-moving violation. 

### Question 4: Encapsulation 

```java
public class Vehicle {
  private Name vehicleOwner;
  private String make;
  private String model;
  private   String color;
  private String LicensePlate;
  private int year;
  private Insurance vehicleInsurance;
  private List<Crash> vehicleCrashHistory;
  //Might change. I feel like this should be encapsulated in the Driver class not the Vehicle class
  private List<MovingViolation> movingViolationHistory;
  private static final int SIX = 6;

  public Vehicle(Name vehicleOwner, String make, String model, String color, String licensePlate,
      int year, Insurance vehicleInsurance, List<Crash> vehicleCrashHistory,
      List<MovingViolation> movingViolationHistory) {
    this.vehicleOwner = vehicleOwner;
    this.make = make;
    this.model = model;
    this.color = color;
    LicensePlate = licensePlate;
    this.year = year;
    this.vehicleInsurance = vehicleInsurance;
    this.vehicleCrashHistory = vehicleCrashHistory;
    this.movingViolationHistory = movingViolationHistory;
  }

```

### Question 5: Sub-type Polymorphism 

```java
public class CSVReader {
  public static List<ProspectiveDriver> loadData(String csvFile) {
    List<ProspectiveDriver> prospectiveDriversList = new ArrayList<>();
```

The sub-type polymorphism exist because an ArrayList is a sub-class of a list. 
An Array List implements the methods of the List interface. 

### Question 6: Generics
```java
public class EligibleDriverPool {
  Map<String, List<ProspectiveDriver>> database;
  public EligibleDriverPool() {
    database = new HashMap<>();
  }
```
The collection frameworks in Java, like List, Maps, etc. are generics. They allow the users
to specify the type parameters when using the collection class. 

### Question 7: Built-in data 
```java
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
```
I used an Arraylist which implements the List interface. 

### Question 10: Regex 
```java
 try (BufferedReader csv = new BufferedReader(new FileReader(csvFile))) {
      String header = csv.readLine();
      if (header != null) {
        String[] headers = header.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String line;
        while ((line = csv.readLine()) != null) {
          String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
```

### Question 11: Nested classes 

i did not use any nested classes because I want my code to be readable, good logical flow and 
comprehensible. The classes were straightforward. Each class focuses on a single responsibility 
of the overall functionality.

### Question 14: MVC 
The View 
```java
public class RideShareDriverValidator {
  public static void main(String[]args){
    //Load the data
    List<ProspectiveDriver> prospectiveDriverList = CSVReader.loadData("driverInfo.csv");
    //Validate and add driver to the pool of eligible drivers if they accepted.
    EligibleDriverPool eligibleDriverPool = new EligibleDriverPool();
    for(ProspectiveDriver driver :prospectiveDriverList){
      RegistrationValidator registrationValidator = new RegistrationValidator(driver);
      if(registrationValidator.isValid()){
        eligibleDriverPool.addDriver(driver);
      }
    }
    //Take user input
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.print("Enter last name to search (or 'quit' to exit): ");
      try {
        String lastName = input.readLine().trim();
        if (lastName.equalsIgnoreCase("quit")) {
          break;
        }
        eligibleDriverPool.provideInfo(lastName);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


}
```
The Controller 
```java
public class CSVReader {
  public static List<ProspectiveDriver> loadData(String csvFile) {
    List<ProspectiveDriver> prospectiveDriversList = new ArrayList<>();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    try (BufferedReader csv = new BufferedReader(new FileReader(csvFile))) {
      String header = csv.readLine();
      if (header != null) {
        String[] headers = header.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for(String head: headers){
          System.out.println(head);
        }

        String line;
        while ((line = csv.readLine()) !=null) {
          if(line.isEmpty()){
            continue;
          }
          String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
          if(data.length < headers.length){
            continue;
          }
          String firstname = data[0];
          String lastName = data[1];
          String birthdayStr = data[2];
          String licenseUniqueNumberStr =  data[3];
          String licenseNameStr = data[4];
          String licenseAddressStr = data[5];
          String driverLicenseBirthdayStr = data[6];
          String countryOfIssuanceStr = data[7];
          String stateOfIssuanceStr = data[8];
          String issuanceDate = data[9];
          String ExpirationDate = data[10];
          String vehicleMake = data[11];
          String vehicleModel = data[12];
          String vehicleYearStr = data[13];
          String vehicleOwnerStr = data[14];
          String vehicleOwnerBirthDateStr = data[15];
          String vehicleOwnerAddress = data[16];
          String InsuranceOwnerStr = data[17];
          String InsuranceCoveredDrivers =data[18];
          String InsuranceExpirationDate = data[19];
          String TrafficViolationDate = data[20];
          String TrafficViolateType = data[21];
          String vehicleMovableViolationDate = data[23];
          String vehicleMovingViolation = data[24];

          Name driverName = new Name(firstname,lastName);
          LocalDate birthDay = LocalDate.parse(birthdayStr,dateFormatter);
          Name licenseName = new Name(licenseNameStr);
          LocalDate licenseBirthday = LocalDate.parse(driverLicenseBirthdayStr,dateFormatter);
          LocalDate driverLicenseIssuanceDate = LocalDate.parse(issuanceDate,dateFormatter);
          LocalDate driverLicenseExpirationDate = LocalDate.parse(ExpirationDate,dateFormatter);
          Integer vehicleYear = Integer.parseInt(vehicleYearStr);
          Name vehicleOwner = new Name(vehicleOwnerStr);
          LocalDate vehicleOwnerBirthday = LocalDate.parse(vehicleOwnerBirthDateStr,dateFormatter);
          Name insuranceOwner = new Name(InsuranceOwnerStr);
          LocalDate insuranceExpirationDate = LocalDate.parse(InsuranceExpirationDate,dateFormatter);
          Name coveredDriver = new Name(InsuranceCoveredDrivers);
          LocalDate trafficViolationDate = LocalDate.parse(TrafficViolationDate,dateFormatter);
          LocalDate movingViolationDate = LocalDate.parse(vehicleMovableViolationDate,dateFormatter);

          List<Vehicle> vehicleList = new ArrayList<>();
          List<Name> otherCoveredDriver = new ArrayList<>();
          otherCoveredDriver.add(coveredDriver);
          Insurance insurance = new Insurance(driverName,otherCoveredDriver,insuranceExpirationDate);
          List<MovingViolation> vehicleInvolvedMovingViolationList = new ArrayList<>();
          MovingViolation vehicleInvolvedMovingViolations = new MovingViolation(movingViolationDate,vehicleMovingViolation);
          vehicleInvolvedMovingViolationList.add(vehicleInvolvedMovingViolations);

          //I am using the driverName since the vehicleOwner object is buggy
          Vehicle vehicle = new Vehicle(driverName,vehicleMake,vehicleModel,null,null,vehicleYear,
              insurance,null,vehicleInvolvedMovingViolationList);
          vehicleList.add(vehicle);

          //I am using the driverName since the vehicleOwner object is buggy
          License driverLicense = new License(driverName,licenseBirthday,countryOfIssuanceStr,stateOfIssuanceStr,driverLicenseIssuanceDate,
              driverLicenseExpirationDate,licenseAddressStr,licenseUniqueNumberStr);

          List<AbstractViolation> violations = new ArrayList<>();
          AbstractViolation movingViolation = new MovingViolation(trafficViolationDate,TrafficViolateType);
          AbstractViolation nonMovingViolation= new NonMovingViolation(trafficViolationDate,TrafficViolateType);
          violations.add(movingViolation);
          violations.add(nonMovingViolation);

          ProspectiveDriver prospectiveDriver = new ProspectiveDriver(driverName,birthDay,violations, vehicleList,driverLicense);
          prospectiveDriversList.add(prospectiveDriver);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return prospectiveDriversList;
  }


}
```

the Model 
```java
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
```

#### Question15: Data and Stamp Coupling 

Data Coupling 
```java
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
}

```

The ProspectDriver data coupling. There is coupling with the Vehicle,
and License class for example. 

Stamp Coupling 
```java
public class Vehicle {
  private Name vehicleOwner;
  private String make;
  private String model;
  private   String color;
  private String LicensePlate;
  private int year;
  private Insurance vehicleInsurance;
  private List<Crash> vehicleCrashHistory;
  //Might change. I feel like this should be encapsulated in the Driver class not the Vehicle class
  private List<MovingViolation> movingViolationHistory;
  private static final int SIX = 6;

  public Vehicle(Name vehicleOwner, String make, String model, String color, String licensePlate,
      int year, Insurance vehicleInsurance, List<Crash> vehicleCrashHistory,
      List<MovingViolation> movingViolationHistory) {
    this.vehicleOwner = vehicleOwner;
    this.make = make;
    this.model = model;
    this.color = color;
    LicensePlate = licensePlate;
    this.year = year;
    this.vehicleInsurance = vehicleInsurance;
    this.vehicleCrashHistory = vehicleCrashHistory;
    this.movingViolationHistory = movingViolationHistory;
  }

  public Name getVehicleOwner() {
    return vehicleOwner;
  }

  public String getMake() {
    return make;
  }

  public String getModel() {
    return model;
  }

  public int getYear() {
    return year;
  }

  public Insurance getVehicleInsurance() {
    return vehicleInsurance;
  }

  public String getColor() {
    return color;
  }

  public String getLicensePlate() {
    return LicensePlate;
  }

  public List<Crash> getVehicleCrashHistory() {
    return vehicleCrashHistory;
  }

  public List<MovingViolation> getMovingViolationHistory() {
    return movingViolationHistory;
  }

  public boolean hasRecentViolationOrCrash(){
    LocalDate pastSixMonths = LocalDate.now().minusMonths(SIX);
    for(Crash crash : vehicleCrashHistory){
      if(crash.getCrashDate().isAfter(pastSixMonths)){
        return true;
      }
    }
    for (MovingViolation violation: movingViolationHistory){
      if(violation.getViolationDate().isAfter(pastSixMonths)){
        return true;

      }
    }
    return false;

  }
}
```

A stamp couple exists between the Vehicle and Insurance class. 

### Final Analysis
I faced many challenges designing the CSV reader class, which is a class that
parses the CSV files to create the database of Prospective Drivers that will
be registered.

A bug currently exists in the program, which stems from the Eligible Pool database.
The bug is preventing the validation of the Prospective Driver, so currently, I have an empty
HashMap.

Another challenge with the assignment was determining the relationship
between a vehicle's involvement in a moving violation vs the moving violation
the attribute of the driver.

I added Moving violation to both the Vehicle and the Prospective driver
class. 
















