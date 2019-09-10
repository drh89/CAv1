package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity

@NamedQueries({
    @NamedQuery(name = "Joke.deleteAllRows", query = "DELETE FROM Joke"),
    @NamedQuery(name = "Joke.findAll", query = "SELECT j FROM Joke j"),
    @NamedQuery(name = "Joke.findById", query = "SELECT j FROM Joke j WHERE j.id = :id"),
    @NamedQuery(name = "Joke.findByJokeLine", query = "SELECT j FROM Joke j WHERE j.jokeLine = :jokeLine"),
    @NamedQuery(name = "Joke.findByAuthorName", query = "SELECT j FROM Joke j WHERE j.authorName = :authorName"),
    @NamedQuery(name = "Joke.findByReference", query = "SELECT j FROM Joke j WHERE j.reference = :reference"),
    @NamedQuery(name = "Joke.findByRating", query = "SELECT j FROM Joke j WHERE j.rating = :rating")
})
public class Joke implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jokeLine;
    private String reference;
    private String type;
    private String authorName;
    private int rating;

    public Joke() {
    }

    public Joke(String jokeLine, String reference, String type, String authorName, int rating) {
        this.jokeLine = jokeLine;
        this.reference = reference;
        this.type = type;
        this.authorName = authorName;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public String getJokeLine() {
        return jokeLine;
    }

    public void setJokeLine(String jokeLine) {
        this.jokeLine = jokeLine;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
