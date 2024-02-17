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
where o.id = 1
order by o.id, do.id;

--analizzo ordine 8


select * from STATOORDINECLIENTEFILIALE where IDORDINECLIENTE = 1;

-- mi trovo un operatore della filiale 2
select * from utente where IDFILIALEOPERATORE = 7; --idutente 7

-- creo la spedizione che è unica per l'ordine e globale a tutte le spedizioni
declare
    vIdSpedizione Spedizione.ID%type;
begin
    CREASPEDIZIONE(1, 7, vIdSpedizione);
    dbms_output.put_line('Creata spedizione n. ' || vIdSpedizione);
exception when others then
    dbms_output.put_line('Errore: ' || sqlerrm);
end;

--vediamo la spedizione creata

select * from SPEDIZIONE where id = 21;

-- creo l'ordine di lavoro di packaging per la filiale 9 e 12
begin
    CREAORDINIPACKAGINGBYIDORDINE(1, 7);
    CREAORDINIPACKAGINGBYIDORDINE(1, 6);
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
where olp.IDSPEDIZIONE = 21;

-- prendo un gruppo corriere
select * from GRUPPOCORRIERE where IDFILIALE = 7; -- idgruppocorriere 19  -- idutente 33

select * from GRUPPOCORRIERE where IDFILIALE = 6;  -- idgruppocorriere 16  -- idutente 30

select id, IDGRUPPOCORRIERE from utente where IDGRUPPOCORRIERE = 16;



-- i corrieri prendono in carico gli ordini di packaging
begin
    CORRIEREPRESAINCARICOPACKAGING(33,21);
    CORRIEREPRESAINCARICOPACKAGING(30,22);
end;

-- gruppo corriere prepara il pacco....

-- chiude l'ordine per lavoro terminato
begin
    --CorriereChiusuraOrdinePackaging(1, null);
    CorriereChiusuraOrdinePackaging(21, null);
    CorriereChiusuraOrdinePackaging(22, 'Packaging terminato, si chiede di verificare indicazioni peso');
end;

delete from pacco where id in (41,42,43,44);

commit;

-- generiamo i pacchi, questa chiamata sarà automatizzata nel momento di chiusura di un'ordine,
-- con i trigger non funziona perché in un trigger non è possibile leggere i dati della tabella triggerante
begin
    GeneraPacchiByIdOrdinePackaging(21);
    GeneraPacchiByIdOrdinePackaging(22);
end;


--vediamo i pacchi

select p.id, p.CODICEPACCO, p.PESO as PESOTOTALE, m.IDFILIALE, p.IDMAGAZZINO, do.id as iddettaglioordine,
       cp.PERICOLOSITA,
       cp.nome as prodotto, cp.PESO as peso_unitario, do.QUANTITA
from pacco p
join MAGAZZINO m on m.id = p.IDMAGAZZINO
join PACCODETTAGLIOORDINE pdo on p.id = pdo.IDPACCO
join DETTAGLIOORDINE do on pdo.IDDETTAGLIOORDINE = do.ID
join CATALOGOPRODOTTI cp on do.IDPRODOTTO = cp.ID
where do.IDORDINE = 1;

