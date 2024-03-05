package org.unina.uninadelivery33.dal.factory;

import org.unina.uninadelivery33.dal.exception.ConsistencyException;
import org.unina.uninadelivery33.dal.exception.PersistenceException;
import org.unina.uninadelivery33.entity.customerdomain.DettaglioOrdineDTO;import org.unina.uninadelivery33.entity.orgdomain.MagazzinoDTO;
import org.unina.uninadelivery33.entity.orgdomain.ProdottoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class OracleDettaglioOrdineDAO implements DettaglioOrdineDAO {

    private final Connection connection = DatabaseSingleton.getInstance().connect();

    public List<DettaglioOrdineDTO> getDettagliOrdineByIdOrdine(long idOrdine) throws PersistenceException {
        Statement statement = null;
        ResultSet resultSet = null;

        List<DettaglioOrdineDTO> listaDettagli = new LinkedList<DettaglioOrdineDTO>();

        //TODO
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM DettaglioOrdine WHERE idOrdine = " + idOrdine);


            while (resultSet.next()) {
                Optional<ProdottoDTO> prodotto = Factory.buildProdottoDAO().selectById(resultSet.getLong("idProdotto"));

                if(prodotto.isEmpty())
                    throw new ConsistencyException("Prodotto non trovato");

                List<MagazzinoDTO> magazzini = Factory.buildMagazzinoDAO().selectMagazziniByIdDettaglioOrdine(resultSet.getLong("id"));
                if(magazzini.isEmpty())
                    throw new ConsistencyException("Magazzini non trovati");


                listaDettagli.add(new DettaglioOrdineDTO(
                    prodotto.get(),
                    resultSet.getInt("quantita"),
                    magazzini
                ));

            }

            return listaDettagli;
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

