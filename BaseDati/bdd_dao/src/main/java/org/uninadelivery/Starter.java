package org.uninadelivery;
import java.time.LocalDate;
import java.time.YearMonth;

public class Starter {
    /*
    Nella traccia era richiesto soltanto di mostrare il funzionamento di una classe di tipo DAO.

    In questo progetto abbiamo scelto di mostrare il funzionamento della classe DAO di OrdineCliente.

    Altre classi di tipo DAO sono state inserite soltanto perché necessarie al funzionamento del DAO di cui si intende
    mostrare il funzionamento. Infatti a tali classi sono stati implementati soltanto i metodi strettamente necessari.

    Inoltre per semplicità ignoriamo i dettagli dell'euristica entity-boundary-control e nel metodo Main facciamo soltanto
    qualche test per mostrare il funzionamento del DAO di OrdineCliente.
    */

    public static void main(String[] args) {
        try {

            //Test selezione di un ordineCliente tramite il suo id
            System.out.println(
                    new OracleClienteDAO().selectById(1)
            );

            /*
            //Test selezione di un comuneFull tramite il suo id
            System.out.println(
                    new OracleComuneFullDAO().selectById(1)
            );

            //Test selezione di un indirizzo tramite il suo id
            System.out.println(
                    new OracleIndirizzoDAO().selectById(1)
            );

            //Test selezione di un ordineCliente tramite il suo id
            System.out.println(
                    new OracleOrdineClienteDAO().selectById(1)
            );

            //Test selezione di tutti gli ordiniCliente di un cliente
            for(OrdineCliente i : new OracleOrdineClienteDAO().selectByCliente(
                    new OracleClienteDAO().selectById(1)
            )) {
                System.out.println(i);
            }


            //Test selezione di tutti gli ordiniCliente in un periodo di riferimento
            for(OrdineCliente i : new OracleOrdineClienteDAO().selectByIntervalloDiTempo(
                    LocalDate.of(2020, 1, 1),
                    LocalDate.of(2020, 1, 5)
            )) {
                System.out.println(i);
            }

            //Test numero medio di ordini in un mese
            System.out.println(
                    new OracleOrdineClienteDAO().getNumeroMedioDiOrdiniByMese(
                            YearMonth.of(2020, 1)
                    )
            );


            //Test ordine con maggior numero di prodotto in un mese
            System.out.println(
                    new OracleOrdineClienteDAO().selectOrdineMaxQuantitaInMese(
                            YearMonth.of(2020, 1)
                    )
            );

            //Test inserimento ordine
            new OracleOrdineClienteDAO().insert(
                    new OrdineCliente(
                            99,
                            LocalDate.of(2020, 2, 1),
                            100.0,
                            LocalDate.of(2020, 1, 1),
                            null,
                            "Bozza",
                            new OracleClienteDAO().selectById(1),
                            new OracleIndirizzoDAO().selectById(1),
                            null,
                            "ABC1234"
                    )
            );
            //Test aggiornamento ordine
            new OracleOrdineClienteDAO().update(
                    new OrdineCliente(
                            99,
                            LocalDate.of(2020, 2, 1),
                            100.0,
                            LocalDate.of(2020, 1, 1),
                            null,
                            "Bozza",
                            new OracleClienteDAO().selectById(1),
                            new OracleIndirizzoDAO().selectById(1),
                            null,
                            "DEF1234"
                    )
            );



            //Test eliminazione ordine
            new OracleOrdineClienteDAO().delete(
                    new OracleOrdineClienteDAO().selectById(99)
            );


            */

        }
        catch (PersistenceException persistenceException) {
            persistenceException.printStackTrace();
        }

    }
}
