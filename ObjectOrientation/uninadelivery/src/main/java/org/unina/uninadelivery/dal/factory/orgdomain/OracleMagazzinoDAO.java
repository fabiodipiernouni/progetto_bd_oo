package org.unina.uninadelivery.dal.factory.orgdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.dal.factory.geodomain.FactoryGeoDomain;
import org.unina.uninadelivery.entity.customerdomain.DettaglioOrdineDTO;
import org.unina.uninadelivery.entity.geodomain.IndirizzoDTO;
import org.unina.uninadelivery.entity.orgdomain.FilialeDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.MerceStoccataDTO;
import org.unina.uninadelivery.entity.orgdomain.ProdottoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OracleMagazzinoDAO implements MagazzinoDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    private MagazzinoDTO getByResultSet(ResultSet resultSet, FilialeDTO filiale) throws SQLException, PersistenceException {
        long id = resultSet.getLong("id");
        String nome = resultSet.getString("nome");

        long idIndirizzo = resultSet.getLong("idIndirizzo");
        Optional<IndirizzoDTO> indirizzo = FactoryGeoDomain.buildIndirizzoDAO().select(idIndirizzo);
        if(indirizzo.isEmpty())
            throw new ConsistencyException("Indirizzo non trovato");

        return new MagazzinoDTO(id, nome, indirizzo.get(), filiale);

    }
    private MagazzinoDTO getByResultSet(ResultSet resultSet) throws SQLException, PersistenceException {

        long idFiliale = resultSet.getLong("idFiliale");
        Optional<FilialeDTO> filiale = FactoryOrgDomain.buildFilialeDAO().select(idFiliale);
        if (filiale.isEmpty())
            throw new ConsistencyException("Filiale non trovata");

        return getByResultSet(resultSet, filiale.get());

    }

    public Optional<MagazzinoDTO> select(long id) throws PersistenceException {
        Optional<MagazzinoDTO> magazzino = Optional.empty();

        Statement statement = null;
        ResultSet resultSet = null;

        String nome;
        Optional<IndirizzoDTO> indirizzo;
        Optional<FilialeDTO> filiale;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Magazzino WHERE id= " + id);

            if(resultSet.next())
                magazzino = Optional.of(getByResultSet(resultSet));

            return magazzino;

        }
        catch(SQLException throwables) {
            throw new PersistenceException(throwables.getMessage());
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
                //non faccio nulla
            }
        }

    }

    public List<MagazzinoDTO> select(FilialeDTO filiale) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<MagazzinoDTO> listaMagazzini = new LinkedList<MagazzinoDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Magazzino WHERE idFiliale= " + filiale.getId());


            while (resultSet.next())
                listaMagazzini.add(getByResultSet(resultSet, filiale));


            return listaMagazzini;
        }
        catch(SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
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
                //non faccio nulla
            }
        }

    }

    public List<MerceStoccataDTO> selectMerciStoccate(MagazzinoDTO magazzino) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<MerceStoccataDTO> listaMerciStoccate = new LinkedList<>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM MerceStoccata WHERE idMagazzino= " + magazzino.getId());

            long id;
            ProdottoDTO prodotto;
            int quantitaReale;
            int quantitaPrenotata;
            int quantitaDisponibile;
            char settoreMagazzino;
            MerceStoccataDTO merce;

            while (resultSet.next()) {
                id = resultSet.getLong("id");
                prodotto = FactoryOrgDomain.buildProdottoDAO().select(resultSet.getLong("idProdotto")).get();
                quantitaReale = resultSet.getInt("quantitaReale");
                quantitaPrenotata = resultSet.getInt("quantitaPrenotata");
                quantitaDisponibile = resultSet.getInt("quantitaDisponibile");
                settoreMagazzino = resultSet.getString("settoreMagazzino").charAt(0);

                merce = new MerceStoccataDTO(id, prodotto, quantitaReale, quantitaPrenotata, quantitaDisponibile, settoreMagazzino);
                merce.setMagazzino(magazzino);

                listaMerciStoccate.add(merce);

            }

            return listaMerciStoccate;
        }
        catch(SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
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
                //non faccio nulla
            }
        }


    }

    public List<MagazzinoDTO> select(DettaglioOrdineDTO dettaglioOrdine) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<MagazzinoDTO> listaMagazzini = new LinkedList<MagazzinoDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("""
                SELECT Magazzino.*
                FROM DettaglioOrdineMagazzino
                JOIN Magazzino
                on Magazzino.id = DettaglioOrdineMagazzino.IdMagazzino
                WHERE idDettaglioOrdine= """ + dettaglioOrdine.getId());

            while (resultSet.next())
                listaMagazzini.add(getByResultSet(resultSet));


            return listaMagazzini;
        }
        catch(SQLException sqe) {
            throw new PersistenceException(sqe.getMessage());
        }
        finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            }
            catch(SQLException sqe) {
                //non faccio nulla
            }

        }
    }
}
