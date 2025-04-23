package ma.formations.jdbc.dao;

import ma.formations.jdbc.service.model.Article;

public class Utils {
    private static Utils instance;

    private Utils() {
    }

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public static void check(Article a) {
        if (a == null) {
            throw new DaoException("Article null", 10);
        }
        if (a.getId() != -1 && a.getId() < 0) {
            throw new DaoException("Id [" + a.getId() + "] invalide", 11);
        }
        if (a.getDescription() == null || a.getDescription().trim().isEmpty()) {
            throw new DaoException("Description manquante", 12);
        }
        if (a.getPrice() < 0) {
            throw new DaoException("le prix [" + a.getPrice() + "] invalide", 13);
        }
        if (a.getQuantite() == null ) {
            throw new DaoException("Qunatite manquant", 14);
        }
    }
}
