/*
 *  Trigger: CkWfNewOrdineClienteStato
 *  scopo: risoluzione vincolo VI.19 (fare riferimento al documento di analisi)
 *  descrizione:
 *      OrdineCliente.Stato deve essere valorizzato a 'Bozza' in fase di insert
*/

create or replace trigger CkWfNewOrdineClienteStato
    before insert on OrdineCliente
    for each row
    when (new.Stato != 'Bozza')
begin
    raise_application_error(-20001, 'Stato deve essere valorizzato inizialmente a ''Bozza''');
end;
