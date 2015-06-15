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
    static final String abstract12 ="";
    static final String abstract13 ="";
    static final String abstract14 ="";
    static final String abstract15 ="";

    // categorie : réseaux
    static final String abstract21 ="Vous apprendrez tout le fonctionnement d'un réseau sans fil WiFi 802.11.\nVous apprendrez également les bases du fonctionnement du réseau informatique.\nIdéal pour mettre en place son propre réseau sécurisé en entreprise / personnel.\nTrès bien expliqué même pour les débutants avec de la volonté";
    static final String abstract22 ="L’objectif de ce livre est simple : donner les bases solides et claires à tous ceux qui souhaitent se faire une idée des caractéristiques techniques des réseaux de transport de l’information, de leur fonctionnement, ainsi que de leur évolution à l’aube du IIIème millénaire. L’ouvrage est divisé en dix-sept chapitres concis et clairs. Précédé d’un court résumé, chaque cours est constitué de leçons,\n" +
            "elles-mêmes enrichies de définitions de glossaire, disposées dans les marges. En fin de volume, un glossaire général récapitule l’ensemble des définitions apparues au fil des leçons.";
    static final String abstract23 ="";
    static final String abstract24 ="";
    static final String abstract25 ="";

    // categorie : sécurité
    static final String abstract31 ="Assuming no previous experience in the field of computer security, this must-have book walks you through the many essential aspects of this vast topic, from the newest advances in software and technology to the most recent information on Web applications security. This new edition includes sections on Windows NT, CORBA, and Java and discusses cross-site scripting and JavaScript hacking";
    static final String abstract32 ="";
    static final String abstract33 ="";
    static final String abstract34 ="";
    static final String abstract35 ="";

    // categorie : système d'exploitation
    static final String abstract41 ="Assuming no previous experience in the field of computer security, this must-have book walks you through the many essential aspects of this vast topic, from the newest advances in software and technology to the most recent information on Web applications security. This new edition includes sections on Windows NT, CORBA, and Java and discusses cross-site scripting and JavaScript hacking";
    static final String abstract42 ="";
    static final String abstract43 ="";
    static final String abstract44 ="";
    static final String abstract45 ="";

    // categorie : bases de données
    static final String abstract51 ="Assuming no previous experience in the field of computer security, this must-have book walks you through the many essential aspects of this vast topic, from the newest advances in software and technology to the most recent information on Web applications security. This new edition includes sections on Windows NT, CORBA, and Java and discusses cross-site scripting and JavaScript hacking";
    static final String abstract52 ="";
    static final String abstract53 ="";
    static final String abstract54 ="";
    static final String abstract55 ="";


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
        image = BitmapFactory.decodeResource(rs, R.drawable.ic_effective_java);
        Livre book11 = new Livre("Effective Java", "Joshua Bloch", abstract11, "2013", "Programmation", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.ic_effective_java);
        Livre book12 = new Livre("Effective Java", "Joshua Bloch", abstract12, "2013", "Programmation", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.ic_effective_java);
        Livre book13 = new Livre("Effective Java", "Joshua Bloch", abstract13, "2013", "Programmation", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.ic_effective_java);
        Livre book14 = new Livre("Effective Java", "Joshua Bloch", abstract14, "2013", "Programmation", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.ic_effective_java);
        Livre book15 = new Livre("Effective Java", "Joshua Bloch", abstract15, "2013", "Programmation", getImageByte(image));



        // categorie : réseaux
        image = BitmapFactory.decodeResource(rs, R.drawable.ic_wifi);
        Livre book21 = new Livre("Ressources Informatiques - Wi-Fi Seconde Edition", "Philippe Atelin", abstract21, "2010", "Réseaux", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.ic_initiation_reseau);
        Livre book22 = new Livre(" Initiation aux réseaux : cours et exercices", "Guy Pujolle", abstract22, "2009", "Réseaux", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.ic_wifi);
        Livre book23 = new Livre("Ressources Informatiques - Wi-Fi Seconde Edition", "Philippe Atelin", abstract23, "2014", "Réseaux", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.ic_wifi);
        Livre book24 = new Livre("Ressources Informatiques - Wi-Fi Seconde Edition", "Philippe Atelin", abstract24, "2014", "Réseaux", getImageByte(image));

        image = BitmapFactory.decodeResource(rs, R.drawable.ic_wifi);
        Livre book25 = new Livre("Ressources Informatiques - Wi-Fi Seconde Edition", "Philippe Atelin", abstract25, "2013", "Réseaux", getImageByte(image));



        // categorie : securité
        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book31 = new Livre("Computer Security", "Dieter Gollmann", abstract31, "2011", "Sécurité", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book32 = new Livre("Computer Security", "Dieter Gollmann", abstract32, "2011", "Sécurité", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book33 = new Livre("Computer Security", "Dieter Gollmann", abstract33, "2011", "Sécurité", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book34 = new Livre("Computer Security", "Dieter Gollmann", abstract34, "2011", "Sécurité", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book35 = new Livre("Computer Security", "Dieter Gollmann", abstract35, "2011", "Sécurité", getImageByte(image));



        // categorie : système d'exploitation
        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book41 = new Livre("Computer Security", "Dieter Gollmann", abstract41, "2011", "Systèmes exploitation", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book42 = new Livre("Computer Security", "Dieter Gollmann", abstract42, "2011", "Systèmes exploitation", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book43 = new Livre("Computer Security", "Dieter Gollmann", abstract43, "2011", "Systèmes exploitation", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book44 = new Livre("Computer Security", "Dieter Gollmann", abstract44, "2011", "Systèmes exploitation", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book45 = new Livre("Computer Security", "Dieter Gollmann", abstract45, "2011", "Systèmes exploitation", getImageByte(image));



        // categorie : bases de données
        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book51 = new Livre("Computer Security", "Dieter Gollmann", abstract51, "2011", "Bases de données", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book52 = new Livre("Computer Security", "Dieter Gollmann", abstract52, "2011", "Bases de données", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book53 = new Livre("Computer Security", "Dieter Gollmann", abstract53, "2011", "Bases de données", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book54 = new Livre("Computer Security", "Dieter Gollmann", abstract54, "2011", "Bases de données", getImageByte(image));

        image =BitmapFactory.decodeResource(rs, R.drawable.ic_computer_security);
        Livre book55 = new Livre("Computer Security", "Dieter Gollmann", abstract55, "2011", "Bases de données", getImageByte(image));


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