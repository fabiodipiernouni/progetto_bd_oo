begin
    execute immediate 'drop table OrdineDiLavoroSpedizionePacchi cascade constraints purge';
    dbms_output.put_line('OrdineDiLavoroSpedizionePacchi dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella OrdineDiLavoroSpedizionePacchi non esistente.');
end;
begin
    execute immediate 'drop table OrdineDiLavoroSpedizione cascade constraints purge';
    dbms_output.put_line('OrdineDiLavoroSpedizione dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella OrdineDiLavoroSpedizione non esistente.');
end;
begin
    execute immediate 'drop table PaccoDettaglioOrdine cascade constraints purge';
    dbms_output.put_line('PaccoDettaglioOrdine dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella PaccoDettaglioOrdine non esistente.');
end;
begin
    execute immediate 'drop table Pacco cascade constraints purge';
    dbms_output.put_line('Pacco dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Pacco non esistente.');
end;
begin
    execute immediate 'drop table PackagingDetails cascade constraints purge';
    dbms_output.put_line('PackagingDetails dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella PackagingDetails non esistente.');
end;
begin
    execute immediate 'drop table OrdineDiLavoroPackaging cascade constraints purge';
    dbms_output.put_line('OrdineDiLavoroPackaging dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella OrdineDiLavoroPackaging non esistente.');
end;
begin
    execute immediate 'drop table Spedizione cascade constraints purge';
    dbms_output.put_line('Spedizione dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Spedizione non esistente.');
end;
begin
    execute immediate 'drop table LocationDettaglioOrdine cascade constraints purge';
    dbms_output.put_line('LocationDettaglioOrdine dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella LocationDettaglioOrdine non esistente.');
end;
begin
    execute immediate 'DROP TABLE DettaglioOrdine CASCADE CONSTRAINTS PURGE';
    dbms_output.put_line('DettaglioOrdine dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella DettaglioOrdine non esistente.');
end;
begin
    execute immediate 'DROP TABLE StatoOrdineClienteFiliale CASCADE CONSTRAINTS PURGE';
    dbms_output.put_line('StatoOrdineClienteFiliale dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella StatoOrdineClienteFiliale non esistente.');
end;
begin
    execute immediate 'DROP TABLE OrdineCliente CASCADE CONSTRAINTS PURGE';
    dbms_output.put_line('OrdineCliente dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella OrdineCliente non esistente.');
end;
begin
    execute immediate 'DROP TABLE Cliente CASCADE CONSTRAINTS PURGE';
    dbms_output.put_line('Cliente dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Cliente non esistente.');
end;
begin
    execute immediate 'drop table ImpegnoMezzo cascade constraints purge';
    dbms_output.put_line('ImpegnoMezzo dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella ImpegnoMezzo non esistente.');
end;
begin
    execute immediate 'drop table MezzoDiTrasporto cascade constraints purge';
    dbms_output.put_line('MezzoDiTrasporto dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella MezzoDiTrasporto non esistente.');
end;
begin
    execute immediate 'drop table GruppoCorriere cascade constraints purge';
    dbms_output.put_line('GruppoCorriere dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella GruppoCorriere non esistente.');
end;
begin
    execute immediate 'drop table MerceStoccata cascade constraints purge';
    dbms_output.put_line('MerceStoccata dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella MerceStoccata non esistente.');
end;
begin
    execute immediate 'drop table CatalogoProdotti cascade constraints purge';
    dbms_output.put_line('CatalogoProdotti dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella CatalogoProdotti non esistente.');
end;
begin
    execute immediate 'drop table Magazzino cascade constraints purge';
    dbms_output.put_line('Magazzino dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Magazzino non esistente.');
end;
begin
    execute immediate 'drop table Filiale cascade constraints purge';
    dbms_output.put_line('Filiale dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Filiale non esistente.');
end;
begin
    execute immediate 'drop table Org cascade constraints purge';
    dbms_output.put_line('Org dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Org non esistente.');
end;
begin
    execute immediate 'drop table Indirizzo cascade constraints purge';
    dbms_output.put_line('Indirizzo dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Indirizzo non esistente.');
end;
begin
    execute immediate 'drop view Comune';
    dbms_output.put_line('View Comune dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Comune non esistente.');
end;
begin
    execute immediate 'drop view Provincia';
    dbms_output.put_line('View Provincia dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Provincia non esistente.');
end;
begin
    execute immediate 'drop view Regione';
    dbms_output.put_line('View Regione dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Regione non esistente.');
end;
begin
    execute immediate 'drop table ComuneFull cascade constraints purge';
    dbms_output.put_line('ComuneFull dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella ComuneFull non esistente.');
end;
begin
    execute immediate 'DROP TABLE ProfiloFunzione CASCADE CONSTRAINTS PURGE';
    dbms_output.put_line('ProfiloFunzione dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella ProfiloFunzione non esistente.');
end;
begin
    execute immediate 'DROP TABLE Funzione CASCADE CONSTRAINTS PURGE';
    dbms_output.put_line('Funzione dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Funzione non esistente.');
end;
begin
    execute immediate 'DROP TABLE Utente CASCADE CONSTRAINTS PURGE';
    dbms_output.put_line('Utente dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Utente non esistente.');
end;
begin
    execute immediate 'DROP TABLE Profilo CASCADE CONSTRAINTS PURGE';
    dbms_output.put_line('Profilo dropped');
exception when others then
    dbms_output.put_line('WARNING: tabella Profilo non esistente.');
end;
