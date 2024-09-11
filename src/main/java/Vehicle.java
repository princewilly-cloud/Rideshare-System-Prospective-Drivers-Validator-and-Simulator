import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Vehicle vehicle)) {
      return false;
    }
    return getYear() == vehicle.getYear() && Objects.equals(getVehicleOwner(),
        vehicle.getVehicleOwner()) && Objects.equals(getMake(), vehicle.getMake())
        && Objects.equals(getModel(), vehicle.getModel()) && Objects.equals(
        getColor(), vehicle.getColor()) && Objects.equals(getLicensePlate(),
        vehicle.getLicensePlate()) && Objects.equals(getVehicleInsurance(),
        vehicle.getVehicleInsurance()) && Objects.equals(getVehicleCrashHistory(),
        vehicle.getVehicleCrashHistory()) && Objects.equals(getMovingViolationHistory(),
        vehicle.getMovingViolationHistory());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getVehicleOwner(), getMake(), getModel(), getColor(), getLicensePlate(),
        getYear(), getVehicleInsurance(), getVehicleCrashHistory(), getMovingViolationHistory());
  }
}
