package entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "author")
public class AuthorTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement(name = "id", required = true)
    private long id;

    @Column(name = "first_name")
    @XmlElement(name = "first_name", required = true)
    private String firstName;

    @Column(name = "family_name")
    @XmlElement(name = "family_name", required = true)
    private String familyName;

    @Column(name = "second_name")
    @XmlElement(name = "second_name", required = true)
    private String secondName;

    @Column(name = "birth_date")
    @XmlElement(name = "birth_date", required = false)
    private String birthDate;

}
