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
    @NamedQuery(name = "Jokes.deleteAllRows", query = "DELETE FROM Jokes"),
    @NamedQuery(name = "Jokes.findAll", query = "SELECT j FROM Jokes j"),
    @NamedQuery(name = "Jokes.findById", query = "SELECT j FROM Jokes j WHERE j.id = :id"),
    @NamedQuery(name = "Jokes.findByJokesLine", query = "SELECT j FROM Jokes j WHERE j.JokesLine = :JokesLine"),
    @NamedQuery(name = "Jokes.findByAuthorName", query = "SELECT j FROM Jokes j WHERE j.authorName = :authorName"),
    @NamedQuery(name = "Jokes.findByReference", query = "SELECT j FROM Jokes j WHERE j.reference = :reference"),
    @NamedQuery(name = "Jokes.findByRating", query = "SELECT j FROM Jokes j WHERE j.rating = :rating"),
    @NamedQuery(name = "Jokes.findCount", query = "SELECT COUNT(j) FROM Jokes j")
})
public class Jokes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String JokesLine;
    private String reference;
    private String type;
    private String authorName;
    private int rating;

    public Jokes() {
    }

    public Jokes(String JokesLine, String reference, String type, String authorName, int rating) {
        this.JokesLine = JokesLine;
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

    public String getJokesLine() {
        return JokesLine;
    }

    public void setJokesLine(String JokesLine) {
        this.JokesLine = JokesLine;
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
