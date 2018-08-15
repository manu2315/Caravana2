package calculatuesfuerzo.finsol.com.mx.calcula.adapters.user;

import java.io.Serializable;

import calculatuesfuerzo.finsol.com.mx.calcula.adapters.Model;

public class UserModel extends Model implements Serializable {


    public static final String USER_NACIONAL        = "ADMI";
    public static final String USER_DIVICIONAL = "DIVI";
    public static final String USER_REGIONAL        = "REGI";
    public static final String USER_SUCURSAL        = "GRTE";
    public static final String USER_CORDINADOR      = "CORD";
    public static final String USER_ASESOR          = "ASES";
    public static final String USER_SUCURSAL_45     = "AS45";

    private String apellidoMat ;
    private String apellidoPat ;
    private String cloudMessageToken ;
    private String codigoPuesto ;
    private String desc_sucursal ;
    private String division ;
    private String email ;
    private String nombre ;
    private String persona ;
    private String region ;
    private Integer sucursal ;
    private String ranking ;
    private Integer noEstrellas ;
    private String idPuesto ;

    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public Integer getNoEstrellas() {
        return noEstrellas;
    }

    public void setNoEstrellas(Integer noEstrellas) {
        this.noEstrellas = noEstrellas;
    }

    public String getApellidoMat() {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat) {
        this.apellidoMat = apellidoMat;
    }

    public String getApellidoPat() {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat) {
        this.apellidoPat = apellidoPat;
    }

    public String getCloudMessageToken() {
        return cloudMessageToken;
    }

    public void setCloudMessageToken(String cloudMessageToken) {
        this.cloudMessageToken = cloudMessageToken;
    }

    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    public String getDesc_sucursal() {
        return desc_sucursal;
    }

    public void setDesc_sucursal(String desc_sucursal) {
        this.desc_sucursal = desc_sucursal;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getSucursal() {
        return sucursal;
    }

    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }
}
