import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * This class parses the CSV file, and creates the list of prospective drivers. It loads the data
 * from the csv file into a List data structure.
 */
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
