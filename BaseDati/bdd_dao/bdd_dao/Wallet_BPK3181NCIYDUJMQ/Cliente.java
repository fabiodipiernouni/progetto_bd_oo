package org.uninadelivery;

/**
 * Cliente rappresenta un cliente che ha effettuato un ordine
 */

public class Cliente {

        private long id;
        private String nome;
        private String cognome;
        private String ragioneSociale;
        private String email;
        private String codiceFiscale;
        private String partitaIVA;

        /**
         * Constructor
         * @param id             id del cliente (chiave primaria)
         * @param nome           nome del cliente
         * @param cognome        cognome del cliente
         * @param ragioneSociale ragione sociale del cliente
         * @param email          email del cliente
         * @param codiceFiscale  codice fiscale del cliente
         * @param partitaIVA     partita IVA del cliente
         */
        public Cliente(long id, String nome, String cognome, String ragioneSociale, String email, String codiceFiscale, String partitaIVA) {
            this.id = id;
            this.nome = nome;
            this.cognome = cognome;
            this.ragioneSociale = ragioneSociale;
            this.email = email;
            this.codiceFiscale = codiceFiscale;
            this.partitaIVA = partitaIVA;
        }

        @Override
        public String toString() {
            return "Cliente{" +
                    "id=" + id +
                    ", nome='" + nome + '\'' +
                    ", cognome='" + cognome + '\'' +
                    ", ragioneSociale='" + ragioneSociale + '\'' +
                    ", email='" + email + '\'' +
                    ", codiceFiscale='" + codiceFiscale + '\'' +
                    ", partitaIVA='" + partitaIVA + '\'' +
                    '}';
        }

        public long getId() {
            return id;
        }
}
