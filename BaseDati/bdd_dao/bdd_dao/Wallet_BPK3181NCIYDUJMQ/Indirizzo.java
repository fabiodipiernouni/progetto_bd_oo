package org.uninadelivery;
/**
 * Indirizzo rappresenta un indirizzo
 */
public class Indirizzo {

    private long id;
    private String nome;
    private String cognome;
    private String cf_pIVA;
    private String indirizzo_1;
    private String indirizzo_2;
    private String CAP;
    private ComuneFull comuneFull;
    private String noteAggiuntive;

    public Indirizzo(long id, String nome, String cognome, String cf_pIVA, String indirizzo_1, String indirizzo_2, String CAP, ComuneFull comuneFull, String noteAggiuntive) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.cf_pIVA = cf_pIVA;
        this.indirizzo_1 = indirizzo_1;
        this.indirizzo_2 = indirizzo_2;
        this.CAP = CAP;
        this.comuneFull = comuneFull;
        this.noteAggiuntive = noteAggiuntive;
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", cf_pIVA='" + cf_pIVA + '\'' +
                ", indirizzo_1='" + indirizzo_1 + '\'' +
                ", indirizzo_2='" + indirizzo_2 + '\'' +
                ", CAP='" + CAP + '\'' +
                ", comuneFull=" + comuneFull +
                ", noteAggiuntive='" + noteAggiuntive + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

}
