package models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "author")
public class RequestGetAllBooksXML {

    @XmlElement(name = "author_id", required = true)
    private long authorId;
}