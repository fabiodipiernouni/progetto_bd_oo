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
    private final Connection connection;

    OracleMagazzinoDAO() throws PersistenceException {
        connection = DatabaseSingleton.getInstance().connect();
    }

    private MagazzinoDTO getByResultSet(ResultSet resultSet, FilialeDTO filiale) throws SQLException, PersistenceException {
        long id = resultSet.getLong("id");
        String nome = resultSet.getString("nome");

        long idIndirizzo = resultSet.getLong("idIndirizzo");
        Optional<IndirizzoDTO> indirizzo = FactoryGeoDomain.buildIndirizzoDAO().select(idIndirizzo);
        if (indirizzo.isEmpty())
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

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Magazzino WHERE id= " + id);

            if (resultSet.next())
                magazzino = Optional.of(getByResultSet(resultSet));

            return magazzino;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new PersistenceException(throwables.getMessage());
        } finally {
            //libero le risorse

            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();

            } catch (SQLException sqe) {
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
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            throw new PersistenceException(sqe.getMessage());
        } finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException sqe) {
                //non faccio nulla
            }
        }

    }

    public MerceStoccataDTO selectMerciStoccateById(long id) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        MerceStoccataDTO merce = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM MerceStoccata WHERE id= " + id);

            if (resultSet.next()) {
                long idMerce = resultSet.getLong("id");
                ProdottoDTO prodotto = FactoryOrgDomain.buildProdottoDAO().select(resultSet.getLong("idProdotto")).get();
                int quantitaReale = resultSet.getInt("quantitaReale");
                int quantitaPrenotata = resultSet.getInt("quantitaPrenotata");
                int quantitaDisponibile = resultSet.getInt("quantitaDisponibile");
                int idMagazzino = resultSet.getInt("idMagazzino");

                String settoreMagazzinoStr = resultSet.getString("settoreMagazzino");
                Character settoreMagazzino;

                if (resultSet.wasNull())
                    settoreMagazzino = null;
                else if (settoreMagazzinoStr.isEmpty())
                    settoreMagazzino = null;
                else
                    settoreMagazzino = settoreMagazzinoStr.charAt(0);
                MagazzinoDTO magazzino = FactoryOrgDomain.buildMagazzinoDAO().select(idMagazzino).get();
                merce = new MerceStoccataDTO(idMerce, prodotto, quantitaReale, quantitaPrenotata, quantitaDisponibile, magazzino, settoreMagazzino);
            }

            return merce;
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            throw new PersistenceException(sqe.getMessage());
        } finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException sqe) {
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

            MerceStoccataDTO merce;

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                ProdottoDTO prodotto = FactoryOrgDomain.buildProdottoDAO().select(resultSet.getLong("idProdotto")).get();
                int quantitaReale = resultSet.getInt("quantitaReale");
                int quantitaPrenotata = resultSet.getInt("quantitaPrenotata");
                int quantitaDisponibile = resultSet.getInt("quantitaDisponibile");

                String settoreMagazzinoStr = resultSet.getString("settoreMagazzino");

                Character settoreMagazzino;

                if (resultSet.wasNull())
                    settoreMagazzino = null;
                else if (settoreMagazzinoStr.isEmpty())
                    settoreMagazzino = null;
                else
                    settoreMagazzino = settoreMagazzinoStr.charAt(0);

                merce = new MerceStoccataDTO(id, prodotto, quantitaReale, quantitaPrenotata, quantitaDisponibile, magazzino, settoreMagazzino);
                merce.setMagazzino(magazzino);

                listaMerciStoccate.add(merce);
            }

            return listaMerciStoccate;
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            throw new PersistenceException(sqe.getMessage());
        } finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException sqe) {
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
        } catch (SQLException sqe) {
            sqe.printStackTrace();
            throw new PersistenceException(sqe.getMessage());
        } finally {
            //libero le risorse
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
            } catch (SQLException sqe) {
                //non faccio nulla
            }

        }
    }
}
