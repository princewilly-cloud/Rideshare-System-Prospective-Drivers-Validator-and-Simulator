import java.util.Objects;

/**
 * This class contains information about a prospective driver's first and last name
 */
public class Name {
  String firstName;
  String lastName;
  String fullName;

  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Name(String fullName) {
    this.fullName = fullName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFullName() {
    return fullName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Name name)) {
      return false;
    }
    return Objects.equals(getFirstName(), name.getFirstName()) && Objects.equals(
        getLastName(), name.getLastName()) && Objects.equals(getFullName(),
        name.getFullName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getLastName(), getFullName());
  }

  @Override
  public String toString() {
    return getLastName()+ " "+getFirstName();
  }
}

