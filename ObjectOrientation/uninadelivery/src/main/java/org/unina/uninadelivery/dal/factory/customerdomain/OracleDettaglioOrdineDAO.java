package org.unina.uninadelivery.dal.factory.customerdomain;

import org.unina.uninadelivery.dal.exception.ConsistencyException;
import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;

import org.unina.uninadelivery.dal.factory.orgdomain.FactoryOrgDomain;
import org.unina.uninadelivery.entity.customerdomain.DettaglioOrdineDTO;
import org.unina.uninadelivery.entity.customerdomain.OrdineClienteDTO;
import org.unina.uninadelivery.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery.entity.orgdomain.ProdottoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OracleDettaglioOrdineDAO implements DettaglioOrdineDAO {

    private final Connection connection;

    OracleDettaglioOrdineDAO() throws PersistenceException {
        connection = DatabaseSingleton.getInstance().connect();
    }

    public List<DettaglioOrdineDTO> select(OrdineClienteDTO ordineCliente) throws PersistenceException {
        return this.select(ordineCliente.getId());
    }

    @Override
    public List<DettaglioOrdineDTO> select(long idOrdineCliente) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<DettaglioOrdineDTO> listaDettagli = new LinkedList<DettaglioOrdineDTO>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM DettaglioOrdine WHERE idOrdine = " + idOrdineCliente);

            while (resultSet.next()) {
                Optional<ProdottoDTO> prodotto = FactoryOrgDomain.buildProdottoDAO().select(resultSet.getLong("idProdotto"));

                if (prodotto.isEmpty())
                    throw new ConsistencyException("Prodotto non trovato");

                DettaglioOrdineDTO dettaglio = new DettaglioOrdineDTO(
                        resultSet.getLong("id"),
                        prodotto.get(),
                        resultSet.getInt("quantita")
                );

                List<MagazzinoDTO> magazzini = FactoryOrgDomain.buildMagazzinoDAO().select(dettaglio);
                if (magazzini.isEmpty())
                    throw new ConsistencyException("Magazzini non trovati");

                dettaglio.setMagazzini(magazzini);

                listaDettagli.add(dettaglio);
            }

            return listaDettagli;
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

