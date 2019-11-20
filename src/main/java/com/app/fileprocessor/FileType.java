package com.app.fileprocessor;

public enum FileType {
    XML("xml"),
    TEXT("text");

    public final String type;

    private FileType(String type) {
        this.type = type;
    }

    public static FileType valueOfLabel(String type) {
        for (FileType e : values()) {
            if (e.type.equalsIgnoreCase(type)) {
                return e;
            }
        }
        return null;
    }
}
