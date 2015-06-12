package com.v2.livrotheque.Model;


public class SpinnerNavItem {
    private String _title;
   // private int _icon;

    public SpinnerNavItem(String _title) {
        this._title = _title;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }



   // dans le cas où on voudra rajouter une icone à chaque categorie
   /* public SpinnerNavItem(String _title, int _icon) {
        this._title = _title;
        this._icon = _icon;
    }

    public int get_icon() {
        return _icon;
    }

    public void set_icon(int _icon) {
        this._icon = _icon;
    }*/
}
