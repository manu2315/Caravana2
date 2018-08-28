package calculatuesfuerzo.finsol.com.mx.calcula.providers;

import calculatuesfuerzo.finsol.com.mx.calcula.models.Adicionales;

public class AdicionalesProvider {
    private AdicionalesProviderListener adicionalesProviderListener;

    private Adicionales adicionales;
    private String h_Incial,h_Final,exp_CreditoGrupal,camp,est;
    private boolean cons_BuroDeCredito;

    public AdicionalesProvider(AdicionalesProviderListener adicionalesProviderListener) {
        this.adicionalesProviderListener = adicionalesProviderListener;
    }

    public void setAdicionales(Adicionales adicionales){
        setAdicionales(adicionales.getDias_semana() ,adicionales.getHora_inicial(),adicionales.getHora_final(),adicionales.getExperiencia_credito_grupal(),adicionales.getCampana(),adicionales.getEstatus(),adicionales.isConsultar_buro_de_credito());
    }
    public void setAdicionales(String[] dias_semana_,String h_Incial,String h_Final,String exp_CreditoGrupal,String camp,String est,boolean cons_BuroDeCredito){
        nuevosAdicionales(dias_semana_,h_Incial,h_Final,exp_CreditoGrupal,camp,est,cons_BuroDeCredito);
    }
    private void nuevosAdicionales(String[] dias_semana_,String h_Incial_,String h_Final_,String exp_CreditoGrupal_,String camp_,String est,boolean cons_BuroDeCredito_){
        this.adicionales=new Adicionales(dias_semana_,h_Incial_,h_Final_,exp_CreditoGrupal_,camp_,est,cons_BuroDeCredito_);
    }

    public Adicionales getAdicionales(){
        return this.adicionales;
    }
    public interface AdicionalesProviderListener{
        void onResponse(Adicionales adicionalesModel);
        void onErrorResponse(Errors error);
    }
}
