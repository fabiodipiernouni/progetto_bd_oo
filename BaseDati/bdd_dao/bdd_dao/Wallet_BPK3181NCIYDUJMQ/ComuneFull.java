package org.uninadelivery;
/**
 * ComuneFull rappresenta le informazioni di un comune italiano
 */

public class ComuneFull {

    private long id;

    private String codiceComuneFormatoNumerico;

    private String codiceProvincia;

    private String codiceRegione;

    private String denominazioneCittaMetropolitana;

    private String denominazioneInItaliano;

    private String denominazioneProvincia;

    private String denominazioneRegione;

    public ComuneFull(long id, String codiceComuneFormatoNumerico, String codiceProvincia, String codiceRegione, String denominazioneCittaMetropolitana, String denominazioneInItaliano, String denominazioneProvincia, String denominazioneRegione) {
        this.id = id;
        this.codiceComuneFormatoNumerico = codiceComuneFormatoNumerico;
        this.codiceProvincia = codiceProvincia;
        this.codiceRegione = codiceRegione;
        this.denominazioneCittaMetropolitana = denominazioneCittaMetropolitana;
        this.denominazioneInItaliano = denominazioneInItaliano;
        this.denominazioneProvincia = denominazioneProvincia;
        this.denominazioneRegione = denominazioneRegione;
    }

    @Override
    public String toString() {
        return "ComuneFull{" +
                "id=" + id +
                ", codiceComuneFormatoNumerico='" + codiceComuneFormatoNumerico + '\'' +
                ", codiceProvincia='" + codiceProvincia + '\'' +
                ", codiceRegione='" + codiceRegione + '\'' +
                ", denominazioneCittaMetropolitana='" + denominazioneCittaMetropolitana + '\'' +
                ", denominazioneInItaliano='" + denominazioneInItaliano + '\'' +
                ", denominazioneProvincia='" + denominazioneProvincia + '\'' +
                ", denominazioneRegione='" + denominazioneRegione + '\'' +
                '}';
    }

}
