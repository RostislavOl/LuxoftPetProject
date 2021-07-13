package com.luxoft.library.entities;

/**
 * Список жанров
 */
public enum Genre {

    DETECTIVE("Детктив"),
    SCIENCEFICTION("Научная фантастика"),
    FANTASY("Фэнтези"),
    FAIRYTALE("Сказка"),
    SCIENTIFIC("Научная литература"),
    STORY("Рассказ"),
    ANTHOLOGY("Сборник рассказов");

    private final String translateName;

    public String getTranslateName() {
        return translateName;
    }

    Genre(String translateName) {
        this.translateName = translateName;
    }

    public static Genre fromTranslateName(String translateName){
        for (Genre value : Genre.values()) {
            if (value.getTranslateName().equals(translateName))
                return value;
        }
        return null;
    }

}
