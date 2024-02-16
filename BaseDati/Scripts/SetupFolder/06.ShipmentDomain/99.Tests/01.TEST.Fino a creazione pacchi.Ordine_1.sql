select count(*)
from ordinecliente o
join DETTAGLIOORDINE do on o.id = do.IDORDINE;

-- set di dati a disposizione
select o.id as idordine, o.STATO as statoOrdine, m.IDFILIALE as idfilialeriferimento, p.NOME, do.id as idDettaglioOrdine, do.QUANTITA, do.FLAGCOMPLETATO, do.FLAGQUANTITADISPONIBILE, dm.IDMAGAZZINO
from ordinecliente o
join DETTAGLIOORDINE do on o.id = do.IDORDINE
join CATALOGOPRODOTTI p on do.IDPRODOTTO = p.ID
join DETTAGLIOORDINEMAGAZZINO dm on dm.IDDETTAGLIOORDINE = do.ID
join MAGAZZINO m on m.id = dm.IDMAGAZZINO
where o.id = 10
order by o.id, do.id;

--analizzo ordine 8


select * from STATOORDINECLIENTEFILIALE where IDORDINECLIENTE = 10;

-- mi trovo un operatore della filiale 2
select * from utente where IDFILIALEOPERATORE = 14; --id 14

-- creo la spedizione che è unica per l'ordine e globale a tutte le spedizioni
declare
    vIdSpedizione Spedizione.ID%type;
begin
    CREASPEDIZIONE(10, 14, vIdSpedizione);
    dbms_output.put_line('Creata spedizione n. ' || vIdSpedizione);
exception when others then
    dbms_output.put_line('Errore: ' || sqlerrm);
end;

--vediamo la spedizione creata

select * from SPEDIZIONE where id = 2;

-- creo l'ordine di lavoro di packaging per la filiale 9 e 12
begin
    CREAORDINIPACKAGINGBYIDORDINE(10, 14);
    CREAORDINIPACKAGINGBYIDORDINE(10, 4);
    CREAORDINIPACKAGINGBYIDORDINE(10, 10);
end;


-- vediamo gli ordini di packaging creati in relazione all'ordine cliente
select
    olp.id, olp.STATO, olp.IDFILIALE, olp.IDMAGAZZINO, olp.NOTEAGGIUNTIVEOPERATORE, olp.NOTEAGGIUNTIVECLIENTE, olp.IDINDIRIZZOSPEDIZIONE,
    ms.SETTOREMAGAZZINO,
    olp.IDGRUPPOCORRIERE, olp.IDOPERATORECORRIERE,
    pd.IDDETTAGLIOORDINE, pd.IDMERCESTOCCATARIFERIEMENTO,
    ms.QUANTITAREALE, ms.QUANTITAPRENOTATA, ms.QUANTITADISPONIBILE,
    pd.PERICOLOSITA, pd.CODICEPROPOSTAPACCO
from
    ORDINEDILAVOROPACKAGING olp
    join PACKAGINGDETAILS pd on olp.ID = pd.IDORDINEDILAVOROPACKAGING
    join MERCESTOCCATA ms on ms.id = pd.IDMERCESTOCCATARIFERIEMENTO
where olp.IDSPEDIZIONE = 2;

-- prendo un gruppo corriere
select * from GRUPPOCORRIERE where IDFILIALE = 14; -- idgruppocorriere 40  -- idutente 54

select * from GRUPPOCORRIERE where IDFILIALE = 4;  -- idgruppocorriere 10  -- idutente 24

select * from GRUPPOCORRIERE where IDFILIALE = 10; -- idgruppocorriere 28  -- idutente 42

select id, IDGRUPPOCORRIERE from utente where IDGRUPPOCORRIERE = 28;



-- i corrieri prendono in carico gli ordini di packaging
begin
    CORRIEREPRESAINCARICOPACKAGING(54,2);
    CORRIEREPRESAINCARICOPACKAGING(24,3);
    CORRIEREPRESAINCARICOPACKAGING(42,4);
end;

-- gruppo corriere prepara il pacco....

-- chiude l'ordine per lavoro terminato
begin
    --CorriereChiusuraOrdinePackaging(1, null);
    CorriereChiusuraOrdinePackaging(2, null);
    CorriereChiusuraOrdinePackaging(3, null);
    CorriereChiusuraOrdinePackaging(4, 'Packaging terminato, si chiede di verificare indicazioni peso');
end;

-- generiamo i pacchi, questa chiamata sarà automatizzata nel momento di chiusura di un'ordine,
-- con i trigger non funziona perché in un trigger non è possibile leggere i dati della tabella triggerante
begin
    GeneraPacchiByIdOrdinePackaging(2);
    GeneraPacchiByIdOrdinePackaging(3);
    GeneraPacchiByIdOrdinePackaging(4);
    --GeneraPacchiByIdOrdinePackaging(3);
end;


--vediamo i pacchi

select p.id, p.CODICEPACCO, p.PESO as PESOTOTALE, p.IDMAGAZZINO, do.id as iddettaglioordine,
       cp.PERICOLOSITA,
       cp.nome as prodotto, cp.PESO as peso_unitario, do.QUANTITA
from pacco p
join PACCODETTAGLIOORDINE pdo on p.id = pdo.IDPACCO
join DETTAGLIOORDINE do on pdo.IDDETTAGLIOORDINE = do.ID
join CATALOGOPRODOTTI cp on do.IDPRODOTTO = cp.ID
where do.IDORDINE = 10;
