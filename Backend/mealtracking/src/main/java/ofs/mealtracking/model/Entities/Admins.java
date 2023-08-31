package ofs.mealtracking.model.Entities;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Admins {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
   // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
   // this.password = passwordEncoder.encode(password);
   this.password = password;

}

  private String username;
  private String password;
  
  
}
