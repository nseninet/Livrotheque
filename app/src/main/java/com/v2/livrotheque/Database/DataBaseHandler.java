package com.v2.livrotheque.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.v2.livrotheque.Model.Livre;
import com.v2.livrotheque.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DataBaseHandler extends SQLiteOpenHelper {
    static final String DB_NAME ="BiblioDB";
    static final int DB_VERSION = 1;

    static final String bookCreation = "create table livre " +
            "(id integer primary key, titre text, auteur text, resume text,date_parution text, categorie text, cover blob)";

    // categorie : programmation
    static final String abstract11 ="Are you looking for a deeper understanding of the Java™ programming language so that you can write code that is clearer, more correct, more robust, and more reusable? Look no further! Effective Java™, Second Edition, brings together seventy-eight indispensable programmer’s rules of thumb: working, best-practice solutions for the programming challenges you encounter every day";
    static final String abstract12 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract13 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract14 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract15 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";

    // categorie : réseaux
    static final String abstract21 ="Vous apprendrez tout le fonctionnement d'un réseau sans fil WiFi 802.11.\nVous apprendrez également les bases du fonctionnement du réseau informatique.\nIdéal pour mettre en place son propre réseau sécurisé en entreprise / personnel.\nTrès bien expliqué même pour les débutants avec de la volonté";
    static final String abstract22 ="L’objectif de ce livre est simple : donner les bases solides et claires à tous ceux qui souhaitent se faire une idée des caractéristiques techniques des réseaux de transport de l’information, de leur fonctionnement, ainsi que de leur évolution à l’aube du IIIème millénaire. L’ouvrage est divisé en dix-sept chapitres concis et clairs. Précédé d’un court résumé, chaque cours est constitué de leçons,\n" +
            "elles-mêmes enrichies de définitions de glossaire, disposées dans les marges. En fin de volume, un glossaire général récapitule l’ensemble des définitions apparues au fil des leçons.";
    static final String abstract23 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract24 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract25 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";

    // categorie : sécurité
    static final String abstract31 ="Assuming no previous experience in the field of computer security, this must-have book walks you through the many essential aspects of this vast topic, from the newest advances in software and technology to the most recent information on Web applications security. This new edition includes sections on Windows NT, CORBA, and Java and discusses cross-site scripting and JavaScript hacking";
    static final String abstract32 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract33 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract34 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract35 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";

    // categorie : système d'exploitation
    static final String abstract41 ="Assuming no previous experience in the field of computer security, this must-have book walks you through the many essential aspects of this vast topic, from the newest advances in software and technology to the most recent information on Web applications security. This new edition includes sections on Windows NT, CORBA, and Java and discusses cross-site scripting and JavaScript hacking";
    static final String abstract42 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract43 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract44 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract45 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";

    // categorie : bases de données
    static final String abstract51 ="Assuming no previous experience in the field of computer security, this must-have book walks you through the many essential aspects of this vast topic, from the newest advances in software and technology to the most recent information on Web applications security. This new edition includes sections on Windows NT, CORBA, and Java and discusses cross-site scripting and JavaScript hacking";
    static final String abstract52 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract53 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract54 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";
    static final String abstract55 ="Haec et huius modi quaedam innumerabilia ultrix facinorum impiorum bonorumque praemiatrix aliquotiens operatur Adrastia atque utinam semper quam vocabulo duplici etiam Nemesim appellamus: ius quoddam sublime numinis efficacis, humanarum mentium opinione lunari circulo superpositum, vel ut definiunt alii, substantialis tutela generali potentia partilibus praesidens fatis, quam theologi veteres fingentes Iustitiae filiam ex abdita quadam aeternitate tradunt omnia despectare terrena.";


    private Context ctx;



    public DataBaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(bookCreation);
        System.out.println("DataBase livre créée");

        // get cover image book
        Resources rs = ctx.getResources();
        Bitmap image ;

        // categorie : programmation
        image = BitmapFactory.decodeResource(rs, R.drawable.prog1);
        Livre book11 = new Livre("Effective Java", "Marcel Napole", abstract11, "2009", "Programmation", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.prog2);
        Livre book12 = new Livre("Programmer en python", "Akra raspoutine", abstract12, "2011", "Programmation", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.prog3);
        Livre book13 = new Livre("La programmation orienté objet", "Francois mouskouvitch", abstract13, "2013", "Programmation", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.prog4);
        Livre book14 = new Livre("Android", "Carl Marc", abstract14, "2014", "Programmation", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.prog5);
        Livre book15 = new Livre("programmation Systéme", "Mustapha Saber", abstract15, "2003", "Programmation", getImageByte(image));



        // categorie : réseaux
        image = BitmapFactory.decodeResource(rs, R.drawable.réseaux1);
        Livre book21 = new Livre("réseaux", "Carlos maya", abstract21, "2010", "Réseaux", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.réseaux2);
        Livre book22 = new Livre(" Initiation aux réseaux : cours et exercices", "Guy Pujolle", abstract22, "2009", "Réseaux", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.réseaux3);
        Livre book23 = new Livre("cisco", "Bouhamra amer", abstract23, "2005", "Réseaux", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.réseaux4);
        Livre book24 = new Livre("VPN", "Philippe Atelin", abstract24, "2011", "Réseaux", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.réseaux5);
        Livre book25 = new Livre("Administration réseaux", "guy sartier", abstract25, "2005", "Réseaux", getImageByte(image));



        // categorie : securité
        image =BitmapFactory.decodeResource(rs, R.drawable.securite1);
        Livre book31 = new Livre("Computer Security", "Dieter Gollmann", abstract31, "2011", "Sécurité", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.securite2);
        Livre book32 = new Livre("Management de la securite ", "Fernandez-Toro", abstract32, "2005", "Sécurité", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.securite3);
        Livre book33 = new Livre("Manager la sécurité du SI", "Bennasar", abstract33, "2007", "Sécurité", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.securite4);
        Livre book34 = new Livre("Sécurité opérationnelle", "Bernard Tapie", abstract34, "2003", "Sécurité", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.securite5);
        Livre book35 = new Livre("Sécurité informatique", "Claviez", abstract35, "1999", "Sécurité", getImageByte(image));



        // categorie : système d'exploitation
        image =BitmapFactory.decodeResource(rs, R.drawable.os1);
        Livre book41 = new Livre("Unix", "Pierre Colin", abstract41, "2011", "Systèmes exploitation", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.os2);
        Livre book42 = new Livre("Les systéme d'exploitation", "Samia Bouzefrane", abstract42, "2011", "Systèmes exploitation", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.os3);
        Livre book43 = new Livre("Windows 7", "Dietrich Morgan", abstract43, "2006", "Systèmes exploitation", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.os4);
        Livre book44 = new Livre("Windows XP", "Constant Benoit", abstract44, "2009", "Systèmes exploitation", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.os5);
        Livre book45 = new Livre("Ubuntu", "Dieter Gollmann", abstract45, "2012", "Systèmes exploitation", getImageByte(image));



        // categorie : bases de données
        image =BitmapFactory.decodeResource(rs, R.drawable.bdd1);
        Livre book51 = new Livre("MySQL pour les nules", "mokhtar Hamani", abstract51, "2007", "Bases de données", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.bdd2);
        Livre book52 = new Livre("Oracle", "Zin Xao", abstract52, "2013", "Bases de données", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.bdd3);
        Livre book53 = new Livre("SQL Server", "Ogral poustr", abstract53, "2008", "Bases de données", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.bdd4);
        Livre book54 = new Livre("PostgreSql est ce du Sql ?", "Michel de villier", abstract54, "2013", "Bases de données", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.bdd5);
        Livre book55 = new Livre("SqlLite ", "Peter Sachs", abstract55, "2015", "Bases de données", getImageByte(image));


        // ajout des livres à la base de données locale
        addBook(book11,db);
        addBook(book12,db);
        addBook(book13,db);
        addBook(book14,db);
        addBook(book15,db);

        addBook(book21,db);
        addBook(book22,db);
        addBook(book23,db);
        addBook(book24,db);
        addBook(book25,db);

        addBook(book31,db);
        addBook(book32,db);
        addBook(book33,db);
        addBook(book34,db);
        addBook(book35,db);

        addBook(book41,db);
        addBook(book42,db);
        addBook(book43,db);
        addBook(book44,db);
        addBook(book45,db);

        addBook(book51,db);
        addBook(book52,db);
        addBook(book53,db);
        addBook(book54,db);
        addBook(book55,db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS livre");
        onCreate(db);
    }

    public byte[] getImageByte(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] imageByte = stream.toByteArray();
        return imageByte;
    }


    /**
     * Ajout d'un livre à la base de données
     */
    public void addBook (Livre livre,SQLiteDatabase db) {
        System.out.println("depuis database = ajout livre");

        ContentValues contentValues = new ContentValues();
        contentValues.put("titre",livre.get_titre());
        contentValues.put("auteur",livre.get_auteur());
        contentValues.put("resume",livre.get_resume());
        contentValues.put("date_parution",livre.get_dateParution());
        contentValues.put("categorie",livre.get_categorie());
        contentValues.put("cover",livre.get_cover());
        db.insert("livre", null, contentValues);
    }


    /**
     * Recherche d'un livre par titre
     */
    public Livre getBookByTitle(String titre) {
        Livre livre =null;
        String query ="select * from livre where lower(titre)=?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, new String[]{titre.toLowerCase()});

        if (cursor.moveToFirst()) {
            livre = new Livre();
            livre.set_id(cursor.getInt(0));
            livre.set_titre(cursor.getString(1));
            livre.set_auteur(cursor.getString(2));
            livre.set_resume(cursor.getString(3));
            livre.set_dateParution(cursor.getString(4));
            livre.set_categorie(cursor.getString(5));
            livre.set_cover(cursor.getBlob(6));
        }
        db.close();
        return livre;
    }


    /**
     * Recherche d'un livre par categorie
     */
    public List<Livre> getBooksByCategory(String categorie) {
        ArrayList<Livre> livreList = new ArrayList<Livre>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from livre where categorie='"+categorie+"'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                Livre livre = new Livre();
                livre.set_id(cursor.getInt(0));
                livre.set_titre(cursor.getString(1));
                livre.set_auteur(cursor.getString(2));
                livre.set_resume(cursor.getString(3));
                livre.set_dateParution(cursor.getString(4));
                livre.set_categorie(cursor.getString(5));
                livre.set_cover(cursor.getBlob(6));

                livreList.add(livre);
            }while(cursor.moveToNext());
        }
        db.close();
        return livreList;
    }


    /*
    Recupère dans une liste l'ensemble des livres
    */
    public List<Livre> getAllBooks() {
        ArrayList<Livre> livreList = new ArrayList<Livre>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query= "select * from livre ";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            do {
                Livre livre = new Livre();
                livre.set_id(cursor.getInt(0));
                livre.set_titre(cursor.getString(1));
                livre.set_auteur(cursor.getString(2));
                livre.set_resume(cursor.getString(3));
                livre.set_dateParution(cursor.getString(4));
                livre.set_categorie(cursor.getString(5));
                livre.set_cover(cursor.getBlob(6));

                livreList.add(livre);
            }while(cursor.moveToNext());
        }
        db.close();
        return livreList;
    }
}
