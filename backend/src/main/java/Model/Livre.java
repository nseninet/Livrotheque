package Model;

public class Livre {

    int _id;
    String _titre;
    String _auteur;
    String _resume;
    String _dateParution;
    String _categorie;
    byte[] _cover;


    /**
     * Constructeur sans paramètres
     */
    public Livre() {
    }

    public Livre(String _titre, String _auteur, String _resume, String _dateParution, String _categorie, byte[] _cover) {
        this._titre = _titre;
        this._auteur = _auteur;
        this._resume = _resume;
        this._dateParution = _dateParution;
        this._categorie = _categorie;
        this._cover = _cover;
    }

    /**
     * Constructeur avec paramètres
     */
    public Livre(int _id, String _titre, String _auteur, String _resume, String _dateParution, String _categorie, byte[] _cover) {
        this._id = _id;
        this._titre = _titre;
        this._auteur = _auteur;
        this._resume = _resume;
        this._dateParution = _dateParution;
        this._categorie = _categorie;
        this._cover = _cover;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_titre() {
        return _titre;
    }

    public void set_titre(String _titre) {
        this._titre = _titre;
    }

    public String get_auteur() {
        return _auteur;
    }

    public void set_auteur(String _auteur) {
        this._auteur = _auteur;
    }

    public String get_resume() {
        return _resume;
    }

    public void set_resume(String _resume) {
        this._resume = _resume;
    }

    public String get_dateParution() {
        return _dateParution;
    }

    public void set_dateParution(String _dateParution) {
        this._dateParution = _dateParution;
    }

    public String get_categorie() {
        return _categorie;
    }

    public void set_categorie(String _categorie) {
        this._categorie = _categorie;
    }

    public byte[] get_cover() {
        return _cover;
    }

    public void set_cover(byte[] _cover) {
        this._cover = _cover;
    }
}
