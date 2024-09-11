import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * This class is the user interface. It runs the simulation
 */
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

