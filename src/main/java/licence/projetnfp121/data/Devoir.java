package licence.projetnfp121.data;

import java.util.Date;

public class Devoir {

    private String description;
    private int categorie;
    private Date dateCreation;
    private Double coefficient;

    private Matiere matiere;

    public Devoir(String description, int categorie, Date dateCreation, Double coefficient, Matiere matiere) {
        this.description = description;
        this.categorie = categorie;
        this.dateCreation = dateCreation;
        this.coefficient = coefficient;
        this.matiere = matiere;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
}
