package com.megagold.mongoennube_vuno.Model;

public class SCP {

    private String nombreSCP, claseObjeto, procedimientosDeContencion, id;
    private Integer identificador;

    public SCP() {
    }

    public SCP(String nombreSCP, String claseObjeto, String procedimientosDeContencion, Integer identificador, String id) {
        this.nombreSCP = nombreSCP;
        this.claseObjeto = claseObjeto;
        this.procedimientosDeContencion = procedimientosDeContencion;
        this.identificador = identificador;
        this.id = id;
    }

    public String getNombreSCP() {
        return nombreSCP;
    }

    public void setNombreSCP(String nombreSCP) {
        this.nombreSCP = nombreSCP;
    }

    public String getClaseObjeto() {
        return claseObjeto;
    }

    public void setClaseObjeto(String claseObjeto) {
        this.claseObjeto = claseObjeto;
    }

    public String getProcedimientosDeContencion() {
        return procedimientosDeContencion;
    }

    public void setProcedimientosDeContencion(String procedimientosDeContencion) {
        this.procedimientosDeContencion = procedimientosDeContencion;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
