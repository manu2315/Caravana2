package calculatuesfuerzo.finsol.com.mx.calcula.models;

import android.graphics.Bitmap;

import java.util.ArrayList;

import calculatuesfuerzo.finsol.com.mx.calcula.adapters.Model;

public class Adicionales extends Model {
    private ArrayList<String>dias_semana;
    private String hora_inicial, hora_final, experiencia_credito_grupal,campana,estatus;
    private boolean consultar_buro_de_credito;
    //Estas ultimas son para Storage de Firebase
    private Bitmap ineFrontal,ineReverso;

    public Adicionales() {
    }

    public Adicionales(ArrayList<String> dias_semana, String hora_inicial, String hora_final, String experiencia_credito_grupal, String campana, String estatus, boolean consultar_buro_de_credito) {
        this.dias_semana = dias_semana;
        this.hora_inicial = hora_inicial;
        this.hora_final = hora_final;
        this.experiencia_credito_grupal = experiencia_credito_grupal;
        this.campana = campana;
        this.estatus = estatus;
        this.consultar_buro_de_credito = consultar_buro_de_credito;
    }

    //Este constructor es para cuando se puedan guardar las INE por delante y detras


    public Adicionales(ArrayList<String> dias_semana, String hora_inicial, String hora_final, String experiencia_credito_grupal, String campana, String estatus, boolean consultar_buro_de_credito, Bitmap ineFrontal, Bitmap ineReverso) {
        this.dias_semana = dias_semana;
        this.hora_inicial = hora_inicial;
        this.hora_final = hora_final;
        this.experiencia_credito_grupal = experiencia_credito_grupal;
        this.campana = campana;
        this.estatus = estatus;
        this.consultar_buro_de_credito = consultar_buro_de_credito;
        this.ineFrontal = ineFrontal;
        this.ineReverso = ineReverso;
    }

    public ArrayList<String> getDias_semana() {
        return dias_semana;
    }

    public void setDias_semana(ArrayList<String> dias_semana) {
        this.dias_semana = dias_semana;
    }

    public String getHora_inicial() {
        return hora_inicial;
    }

    public void setHora_inicial(String hora_inicial) {
        this.hora_inicial = hora_inicial;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public String getExperiencia_credito_grupal() {
        return experiencia_credito_grupal;
    }

    public void setExperiencia_credito_grupal(String experiencia_credito_grupal) {
        this.experiencia_credito_grupal = experiencia_credito_grupal;
    }

    public String getCampana() {
        return campana;
    }

    public void setCampana(String campana) {
        this.campana = campana;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public boolean isConsultar_buro_de_credito() {
        return consultar_buro_de_credito;
    }

    public void setConsultar_buro_de_credito(boolean consultar_buro_de_credito) {
        this.consultar_buro_de_credito = consultar_buro_de_credito;
    }

    public Bitmap getIneFrontal() {
        return ineFrontal;
    }

    public void setIneFrontal(Bitmap ineFrontal) {
        this.ineFrontal = ineFrontal;
    }

    public Bitmap getIneReverso() {
        return ineReverso;
    }

    public void setIneReverso(Bitmap ineReverso) {
        this.ineReverso = ineReverso;
    }

    @Override
    public String toString() {
        return "Adicionales{" +
                "hora_inicial='" + hora_inicial + '\'' +
                ", hora_final='" + hora_final + '\'' +
                ", experiencia_credito_grupal='" + experiencia_credito_grupal + '\'' +
                ", campana='" + campana + '\'' +
                ", estatus='" + estatus + '\'' +
                ", consultar_buro_de_credito=" + consultar_buro_de_credito +
                ", ineFrontal=" + ineFrontal +
                ", ineReverso=" + ineReverso +
                '}';
    }
}
