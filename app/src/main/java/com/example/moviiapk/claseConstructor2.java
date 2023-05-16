package com.example.moviiapk;

public class claseConstructor2 {

    private String numeroQEnvio;;
    private String cantidadQRecibio;
    private String fechaQTrans;
    private String horaQTrans;

    public claseConstructor2(String numeroQEnvio, String cantidadQRecibio, String fechaQTrans, String horaQTrans) {
        this.numeroQEnvio = numeroQEnvio;
        this.cantidadQRecibio = cantidadQRecibio;
        this.fechaQTrans = fechaQTrans;
        this.horaQTrans = horaQTrans;
    }

    public claseConstructor2() {

    }

    public String getnumeroQEnvio() {
        return numeroQEnvio;
    }

    public void setnumeroQEnvio(String numeroQEnvio) {
        this.numeroQEnvio = numeroQEnvio;
    }

    public String getcantidadQRecibio() {
        return cantidadQRecibio;
    }

    public void setcantidadQRecibio(String cantidadQRecibio) {
        this.cantidadQRecibio = cantidadQRecibio;
    }

    public String getfechaQTrans() {
        return fechaQTrans;
    }

    public void setfechaQTrans(String fechaQTrans) {
        this.fechaQTrans = fechaQTrans;
    }

    public String gethoraQTrans() {
        return horaQTrans;
    }

    public void sethoraQTrans(String horaQTrans) {
        this.horaQTrans = horaQTrans;
    }
}