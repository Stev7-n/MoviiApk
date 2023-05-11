package com.example.moviiapk;

public class clasecontructor {

    private String numerotransferenia;
    private String cantidadtransferencia;
    private String fechatransferencia;
    private String horatransfernecia;

    public clasecontructor(String numerotransferenia, String cantidadtransferencia, String fechatransferencia, String horatransfernecia) {
        this.numerotransferenia = numerotransferenia;
        this.cantidadtransferencia = cantidadtransferencia;
        this.fechatransferencia = fechatransferencia;
        this.horatransfernecia = horatransfernecia;
    }

    public String getNumerotransferenia() {
        return numerotransferenia;
    }

    public void setNumerotransferenia(String numerotransferenia) {
        this.numerotransferenia = numerotransferenia;
    }

    public String getCantidadtransferencia() {
        return cantidadtransferencia;
    }

    public void setCantidadtransferencia(String cantidadtransferencia) {
        this.cantidadtransferencia = cantidadtransferencia;
    }

    public String getFechatransferencia() {
        return fechatransferencia;
    }

    public void setFechatransferencia(String fechatransferencia) {
        this.fechatransferencia = fechatransferencia;
    }

    public String getHoratransfernecia() {
        return horatransfernecia;
    }

    public void setHoratransfernecia(String horatransfernecia) {
        this.horatransfernecia = horatransfernecia;
    }
}
