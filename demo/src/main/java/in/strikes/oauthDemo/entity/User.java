package in.strikes.oauthDemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private String email;
    private String provider;
    private String providerSubject;

    public User(String providerSubject, String provider, String name,String email) {
        this.providerSubject = providerSubject;
        this.provider = provider;
        this.name = name;
        this.email=email;
    }
}
