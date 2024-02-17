package org.uninadelivery;

import java.sql.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedList;
import java.util.List;


public class OracleOrdineClienteDAO implements OrdineClienteDAO {
    private final Connection connection = DatabaseSingleton.getIstance().connect();

    private OrdineCliente getOrdineClienteByResultSet(ResultSet resultSet) throws SQLException,PersistenceException {

        long id = resultSet.getLong("id");

        LocalDate dataOrdine = resultSet.getObject("dataOrdine", LocalDate.class);
        double importoTotale = resultSet.getDouble("importoTotale");

        LocalDate dataInizioLavorazione = resultSet.getObject("dataInizioLavorazione", LocalDate.class);
        if(resultSet.wasNull())
            dataInizioLavorazione = null;

        LocalDate dataFineLavorazione = resultSet.getObject("dataFineLavorazione", LocalDate.class);
        if(resultSet.wasNull())
            dataFineLavorazione = null;

        String stato = resultSet.getString("Stato");
        long idCliente = resultSet.getLong("idCliente");
        long idIndirizzoFatturazione = resultSet.getLong("idIndirizzoFatturazione");

        Long idIndirizzoSpedizione = resultSet.getLong("idIndirizzoSpedizione"); //uso Long e non long perché voglio poter assegnare null
        if(resultSet.wasNull())
            idIndirizzoSpedizione = null;

        String numeroOrdine = resultSet.getString("numeroOrdine");

        return new OrdineCliente(
                id,
                dataOrdine,
                importoTotale,
                dataInizioLavorazione,
                dataFineLavorazione,
                stato,
                new OracleClienteDAO().selectById(idCliente),
                new OracleIndirizzoDAO().selectById(idIndirizzoFatturazione),
                idIndirizzoSpedizione == null ? null : new OracleIndirizzoDAO().selectById(idIndirizzoSpedizione),
                numeroOrdine
        );
    }


    public OrdineCliente selectById(long id) throws PersistenceException {
        
        Statement statement = null;
        ResultSet resultSet = null;

        OrdineCliente ordineCliente = null;

        try {
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineCliente WHERE id = " + id);

            if(!resultSet.next())
                throw new PersistenceException("OrdineCliente non trovato");

            //sono sicuro che otterrò una sola tupla, non metto in un while

            ordineCliente = getOrdineClienteByResultSet(resultSet);


        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }
        }

        return ordineCliente;

    }

    public void insert(OrdineCliente ordineCliente) throws PersistenceException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
            "INSERT INTO OrdineCliente"
                + " (id, dataOrdine, importoTotale, dataInizioLavorazione, dataFineLavorazione, stato, idCliente, idIndirizzoFatturazione, idIndirizzoSpedizione, numeroOrdine)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            );

            preparedStatement.setLong(1, ordineCliente.getId());
            preparedStatement.setObject(2, ordineCliente.getDataOrdine());
            preparedStatement.setDouble(3, ordineCliente.getImportoTotale());
            preparedStatement.setObject(4, ordineCliente.getDataInizioLavorazione()); //può essere null
            preparedStatement.setObject(5, ordineCliente.getDataFineLavorazione()); //può essere null
            preparedStatement.setString(6, ordineCliente.getStato());
            preparedStatement.setLong(7, ordineCliente.getCliente().getId());
            preparedStatement.setLong(8, ordineCliente.getIndirizzoFatturazione().getId());

            if(ordineCliente.getIndirizzoSpedizione() == null)
                preparedStatement.setNull(9, Types.NUMERIC );
            else
                preparedStatement.setLong(9, ordineCliente.getIndirizzoSpedizione().getId());

            preparedStatement.setString(10, ordineCliente.getNumeroOrdine());

            preparedStatement.executeUpdate();

        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(preparedStatement != null)
                    preparedStatement.close();

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }
        }


    }

    public void delete(OrdineCliente ordineCliente) throws PersistenceException {
        Statement statement = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM OrdineCliente WHERE id = " + ordineCliente.getId());
        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(statement != null)
                    statement.close();

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }
        }

    }

    public void update(OrdineCliente ordineCliente) throws PersistenceException {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(
            "UPDATE OrdineCliente"
                + " SET id = ?, dataOrdine = ?, importoTotale = ?, dataInizioLavorazione = ?, dataFineLavorazione = ?, stato = ?, idCliente = ?, idIndirizzoFatturazione = ?, idIndirizzoSpedizione = ?, numeroOrdine = ?"
                + " WHERE id = ?"
            );

            preparedStatement.setLong(1, ordineCliente.getId());
            preparedStatement.setObject(2, ordineCliente.getDataOrdine());
            preparedStatement.setDouble(3, ordineCliente.getImportoTotale());
            preparedStatement.setObject(4, ordineCliente.getDataInizioLavorazione()); //può essere null
            preparedStatement.setObject(5, ordineCliente.getDataFineLavorazione()); //può essere null
            preparedStatement.setString(6, ordineCliente.getStato());
            preparedStatement.setLong(7, ordineCliente.getCliente().getId());
            preparedStatement.setLong(8, ordineCliente.getIndirizzoFatturazione().getId());

            if(ordineCliente.getIndirizzoSpedizione() == null)
                preparedStatement.setNull(9, Types.NUMERIC );
            else
                preparedStatement.setLong(9, ordineCliente.getIndirizzoSpedizione().getId());

            preparedStatement.setString(10, ordineCliente.getNumeroOrdine());

            preparedStatement.setLong(11, ordineCliente.getId());

            preparedStatement.executeUpdate();

        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(preparedStatement != null)
                    preparedStatement.close();

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }

        }

    }

    public List<OrdineCliente> selectByCliente(Cliente cliente) throws PersistenceException {
        
        Statement statement = null;
        ResultSet resultSet = null;

        List<OrdineCliente> ordiniCliente = new LinkedList<OrdineCliente>();

        try {
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM OrdineCliente WHERE idCliente = " + cliente.getId());

            while(resultSet.next())
                ordiniCliente.add(getOrdineClienteByResultSet(resultSet));

        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }
        }

        return ordiniCliente;
    }

    public List<OrdineCliente> selectByIntervalloDiTempo(LocalDate dataInizio, LocalDate dataFine) throws PersistenceException {
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<OrdineCliente> ordiniCliente = new LinkedList<OrdineCliente>();

        try {
            

            //uso un prepared statement perché non voglio gestire i formati delle date, lascio fare a Java
            preparedStatement = connection.prepareStatement("SELECT * FROM OrdineCliente WHERE dataOrdine BETWEEN ? AND ?");
            preparedStatement.setObject(1, dataInizio); //setObject perché LocalDate non è supportato da setDate
            preparedStatement.setObject(2, dataFine);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
                ordiniCliente.add(getOrdineClienteByResultSet(resultSet));

        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(resultSet != null)
                    resultSet.close();
                if(preparedStatement != null)
                    preparedStatement.close();

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }
        }

        return ordiniCliente;
    }

    public int getNumeroMedioDiOrdiniByMese(YearMonth mese) throws PersistenceException {

            Statement statement = null;
            ResultSet resultSet = null;

            int numeroMedioDiOrdini = 0;

            try {
                statement = connection.createStatement();


                //uso un prepared statement perché non voglio gestire i formati delle date, lascio fare a Java
                resultSet = statement.executeQuery(
                        "SELECT AVG(COUNT(*)) FROM ordinecliente WHERE EXTRACT(MONTH FROM dataordine) = "
                                + mese.getMonthValue() +
                                " AND EXTRACT(YEAR FROM dataordine) = "
                                + mese.getYear()
                                + " GROUP BY EXTRACT(DAY FROM dataordine)"
                );

                if(!resultSet.next())
                    throw new PersistenceException("Errore in OracleOrdineClienteDAO: non ho ottenuto il risultato");

                numeroMedioDiOrdini = resultSet.getInt(1);

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }
            finally {
                //libero le risorse

                try {
                    if(resultSet != null)
                        resultSet.close();
                    if(statement != null)
                        statement.close();

                }
                catch(SQLException sqe) {
                    throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
                }
            }

            return numeroMedioDiOrdini;
    }

    private OrdineCliente selectOrdineQuantitaInMese(YearMonth mese, String ordinamento) throws PersistenceException {
        if( !(ordinamento.equals("ASC") || ordinamento.equals("DESC") ) )
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: ordinamento non valido");

        Statement statement = null;
        ResultSet resultSet = null;

        OrdineCliente ordineCliente = null;

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(
            "SELECT * FROM ordinecliente WHERE id = (" +
                    " SELECT ordinecliente.id" +
                    " FROM ordinecliente" +
                        " JOIN dettaglioordine" +
                            " ON dettaglioordine.idordine = ordinecliente.id" +
                    " WHERE EXTRACT(MONTH FROM dataordine) = " + mese.getMonthValue() +
                        " AND EXTRACT(YEAR FROM dataordine) = " + mese.getYear() +
                    " GROUP BY ordinecliente.id" +
                    " ORDER BY SUM(dettaglioordine.quantita) " + ordinamento +
                " FETCH FIRST ROW ONLY" +
            ")"

            );

            if(!resultSet.next())
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: non ho ottenuto il risultato");

            ordineCliente = getOrdineClienteByResultSet(resultSet);

        }
        catch(SQLException sqe) {
            throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
        }
        finally {
            //libero le risorse

            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();

            }
            catch(SQLException sqe) {
                throw new PersistenceException("Errore in OracleOrdineClienteDAO: " + sqe.getMessage());
            }
        }

        return ordineCliente;
    }

    public OrdineCliente selectOrdineMaxQuantitaInMese(YearMonth mese) throws PersistenceException {
        return selectOrdineQuantitaInMese(mese, "DESC");
    }

    public OrdineCliente selectOrdineMinQuantitaInMese(YearMonth mese) throws PersistenceException {
        return selectOrdineQuantitaInMese(mese, "ASC");
    }
}
