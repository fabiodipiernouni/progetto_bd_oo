package org.unina.uninadelivery.dal.factory.orgdomain;


import org.unina.uninadelivery.dal.exception.PersistenceException;
import org.unina.uninadelivery.dal.factory.DatabaseSingleton;
import org.unina.uninadelivery.entity.orgdomain.ProdottoDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

class OracleProdottoDAO implements ProdottoDAO {
    private final Connection connection = DatabaseSingleton.getInstance().connect();

    public Optional<ProdottoDTO> select(long id) throws PersistenceException {

        Optional<ProdottoDTO> prodotto = Optional.empty();

        Statement statement = null;
        ResultSet resultSet = null;

        String codiceEAN;
        String nome;
        String descrizione;
        String URLPhoto;
        String tipo;
        double prezzo;
        float peso;
        Float altezza;
        Float larghezza;
        Float profondita;
        String pericolosita;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM CatalogoProdotti WHERE id = "+ id);

            if(resultSet.next()) {

                codiceEAN = resultSet.getString("codiceEAN");
                nome = resultSet.getString("nome");
                descrizione = resultSet.getString("descrizione");
                URLPhoto = resultSet.getString("URLPhoto");
                tipo = resultSet.getString("tipo");
                prezzo = resultSet.getDouble("prezzo");
                peso = resultSet.getFloat("peso");

                altezza = resultSet.getFloat("altezza");
                if(resultSet.wasNull())
                    altezza = null;

                larghezza = resultSet.getFloat("larghezza");
                if(resultSet.wasNull())
                    larghezza = null;

                profondita = resultSet.getFloat("profondita");
                if(resultSet.wasNull())
                    profondita = null;

                pericolosita = resultSet.getString("pericolosita");


                prodotto = Optional.of(new ProdottoDTO(id, codiceEAN, nome, descrizione, URLPhoto, tipo, prezzo, peso, altezza, larghezza, profondita, pericolosita));
            }

            return prodotto;

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
}
