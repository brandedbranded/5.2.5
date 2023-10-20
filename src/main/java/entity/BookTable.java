package entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "book")
public class BookTable {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "book_title")
    private String bookTitle;

    @Column(name = "author_id")
    private long authorId;

    @Column(name = "updated")
    private Date updated;
}
