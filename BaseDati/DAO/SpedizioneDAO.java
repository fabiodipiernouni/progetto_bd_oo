import java.util.List;

public interface SpedizioneDAO {
    public Spedizione getSpedizione(int id);
    public List<Spedizione> getSpedizioni();
    public boolean updateSpedizione(Spedizione spedizione);
    public boolean deleteSpedizione(int id);
    public boolean insertSpedizione(Spedizione spedizione);
}
