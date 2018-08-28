package calculatuesfuerzo.finsol.com.mx.calcula.models;

import android.graphics.Bitmap;

import calculatuesfuerzo.finsol.com.mx.calcula.adapters.Model;

public class Adicionales extends Model {
    private String horaIncial,horaFinal,experienciaCreditoGrupal,campana,estatus;
    private boolean consultarBuroDeCredito;
    //Estas ultimas son para Storage de Firebase
    private Bitmap ineFrontal,ineReverso;

    public Adicionales() {
    }

    public Adicionales(String horaIncial, String horaFinal, String experienciaCreditoGrupal, String campana, String estatus, boolean consultarBuroDeCredito) {
        this.horaIncial = horaIncial;
        this.horaFinal = horaFinal;
        this.experienciaCreditoGrupal = experienciaCreditoGrupal;
        this.campana = campana;
        this.estatus = estatus;
        this.consultarBuroDeCredito = consultarBuroDeCredito;
    }

    //Este constructor es para cuando se puedan guardar las INE por delante y detras
    public Adicionales(String horaIncial, String horaFinal, String experienciaCreditoGrupal, String campana, String estatus, boolean consultarBuroDeCredito, Bitmap ineFrontal, Bitmap ineReverso) {
        this.horaIncial = horaIncial;
        this.horaFinal = horaFinal;
        this.experienciaCreditoGrupal = experienciaCreditoGrupal;
        this.campana = campana;
        this.estatus = estatus;
        this.consultarBuroDeCredito = consultarBuroDeCredito;
        this.ineFrontal = ineFrontal;
        this.ineReverso = ineReverso;
    }

    public String getHoraIncial() {
        return horaIncial;
    }

    public void setHoraIncial(String horaIncial) {
        this.horaIncial = horaIncial;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getExperienciaCreditoGrupal() {
        return experienciaCreditoGrupal;
    }

    public void setExperienciaCreditoGrupal(String experienciaCreditoGrupal) {
        this.experienciaCreditoGrupal = experienciaCreditoGrupal;
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

    public boolean isConsultarBuroDeCredito() {
        return consultarBuroDeCredito;
    }

    public void setConsultarBuroDeCredito(boolean consultarBuroDeCredito) {
        this.consultarBuroDeCredito = consultarBuroDeCredito;
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
                "horaIncial='" + horaIncial + '\'' +
                ", horaFinal='" + horaFinal + '\'' +
                ", experienciaCreditoGrupal='" + experienciaCreditoGrupal + '\'' +
                ", campana='" + campana + '\'' +
                ", estatus='" + estatus + '\'' +
                ", consultarBuroDeCredito=" + consultarBuroDeCredito +
                ", ineFrontal=" + ineFrontal +
                ", ineReverso=" + ineReverso +
                '}';
    }
}
