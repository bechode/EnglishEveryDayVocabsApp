package com.lenguage.englisheverydayvocabsapp.data.model;

public class Resultado<T> {

    public static final int TIPO_INSERCION = 1;
    public static final int TIPO_ACTUALIZACION = 2;
    public static final int TIPO_ELIMINACION = 3;
    public static final int TIPO_ANULACION = 4;

    private T item;
    private boolean aprobado;
    private String mensaje;
    private int tipoResultado;

    public Resultado(boolean aprobado, String mensaje) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
    }

    public Resultado(boolean aprobado, String mensaje, T item) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.item = item;
    }

    public Resultado(boolean aprobado, String mensaje, int tipoResultado) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.tipoResultado = tipoResultado;
    }

    public Resultado(boolean aprobado, String mensaje, int tipoResultado, T item) {
        this.aprobado = aprobado;
        this.mensaje = mensaje;
        this.tipoResultado = tipoResultado;
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(int tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

}
