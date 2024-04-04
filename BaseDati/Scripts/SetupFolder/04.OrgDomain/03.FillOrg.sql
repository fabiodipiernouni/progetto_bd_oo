-- puliamo le tabelle

truncate table Utente;
truncate table MerceStoccata;
truncate table CatalogoProdotti;
truncate table Magazzino;
truncate table MezzoDiTrasporto;
truncate table GruppoCorriere;
truncate table Filiale;
truncate table Org;


-- Org

INSERT INTO Org (Id, RagioneSociale, Paese, PartitaIva, SedeLegaleIndirizzo) VALUES (1, 'Unina Delivery ITA', 'Italia', '12345678901', 3);
commit;



-- Filiali

INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (1, 'Filiale Roma', 1, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (2, 'Filiale Milano', 2, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (3, 'Filiale Napoli', 3, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (4, 'Filiale Torino', 4, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (5, 'Filiale Palermo', 5, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (6, 'Filiale Genova', 6, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (7, 'Filiale Bologna', 7, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (8, 'Filiale Firenze, 1', 8, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (9, 'Filiale Bari', 9, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (10, 'Filiale Catania', 10, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (11, 'Filiale Venezia', 11, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (12, 'Filiale Verona', 12, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (13, 'Filiale Messina', 13, 1);
INSERT INTO Filiale (Id, Nome, Localita, IdOrg) VALUES (14, 'Filiale Padova', 14, 1);



-- Gruppi corrieri

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (1, 'Corriere Roma 1', 'ROM1', 10, 1);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (2, 'Corriere Roma 2', 'ROM2', 15, 1);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (3, 'Corriere Roma 3', 'ROM3', 12, 1);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (4, 'Corriere Milano 1', 'MIL1', 3, 2);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (5, 'Corriere Milano 2', 'MIL2', 33, 2);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (6, 'Corriere Milano 3', 'MIL3', 112, 2);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (7, 'Corriere Napoli 1', 'NAP1', 10, 3);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (8, 'Corriere Napoli 2', 'NAP2', 125, 3);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (9, 'Corriere Napoli 3', 'NAP3', 11, 3);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (10, 'Corriere Torino 1', 'TOR1', 10, 4);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (11, 'Corriere Torino 2', 'TOR2', 15, 4);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (12, 'Corriere Torino 3', 'TOR3', 12, 4);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (13, 'Corriere Palermo 1', 'PAL1', 3, 5);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (14, 'Corriere Palermo 2', 'PAL2', 33, 5);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (15, 'Corriere Palermo 3', 'PAL3', 112, 5);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (16, 'Corriere Genova 1', 'GEN1', 10, 6);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (17, 'Corriere Genova 2', 'GEN2', 125, 6);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (18, 'Corriere Genova 3', 'GEN3', 11, 6);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (19, 'Corriere Bologna 1', 'BOL1', 10, 7);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (20, 'Corriere Bologna 2', 'BOL2', 15, 7);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (21, 'Corriere Bologna 3', 'BOL3', 12, 7);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (22, 'Corriere Firenze 1', 'FIR1', 3, 8);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (23, 'Corriere Firenze 2', 'FIR2', 33, 8);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (24, 'Corriere Firenze 3', 'FIR3', 112, 8);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (25, 'Corriere Bari 1', 'BAR1', 10, 9);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (26, 'Corriere Bari 2', 'BAR2', 125, 9);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (27, 'Corriere Bari 3', 'BAR3', 11, 9);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (28, 'Corriere Catania 1', 'CAT1', 10, 10);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (29, 'Corriere Catania 2', 'CAT2', 15, 10);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (30, 'Corriere Catania 3', 'CAT3', 12, 10);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (31, 'Corriere Venezia 1', 'VEN1', 3, 11);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (32, 'Corriere Venezia 2', 'VEN2', 33, 11);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (33, 'Corriere Venezia 3', 'VEN3', 112, 11);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (34, 'Corriere Verona 1', 'VER1', 10, 12);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (35, 'Corriere Verona 2', 'VER2', 125, 12);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (36, 'Corriere Verona 3', 'VER3', 11, 12);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (37, 'Corriere Messina 1', 'MES1', 10, 13);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (38, 'Corriere Messina 2', 'MES2', 15, 13);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (39, 'Corriere Messina 3', 'MES3', 12, 13);

INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (40, 'Corriere Padova 1', 'PAD1', 3, 14);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (41, 'Corriere Padova 2', 'PAD2', 33, 14);
INSERT INTO GruppoCorriere (Id, Nome, CodiceCorriere, NumeroDipendenti, IdFiliale) VALUES (42, 'Corriere Padova 3', 'PAD3', 112, 14);



--  MezzoDiTrasporto

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('MD140ZG', 'Camion', 1, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YQ139SI', 'Camion', 1, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('PB409CE', 'Furgone', 1, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WS534BP', 'Furgone', 1, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RH318CP', 'Auto', 1, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VI513ZS', 'Auto', 1, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('HO147EH', 'Camion', 2, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XV413VQ', 'Camion', 2, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VX326NE', 'Furgone', 2, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('MN018MU', 'Furgone', 2, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AG416CM', 'Auto', 2, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FK105FK', 'Auto', 2, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KL685BH', 'Camion', 3, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('GQ061VT', 'Camion', 3, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('GV137DQ', 'Furgone', 3, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FS600ED', 'Furgone', 3, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('GP210XS', 'Auto', 3, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VK189UX', 'Auto', 3, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZG483LQ', 'Camion', 4, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RF293AL', 'Camion', 4, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NS721DA', 'Furgone', 4, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NX660JB', 'Furgone', 4, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VQ640ME', 'Auto', 4, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZG319AC', 'Auto', 4, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WX513IJ', 'Camion', 5, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CO234MP', 'Camion', 5, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('HB407AC', 'Furgone', 5, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XE261BA', 'Furgone', 5, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('OI823CY', 'Auto', 5, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KH616SB', 'Auto', 5, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('PE663AT', 'Camion', 6, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LK106PK', 'Camion', 6, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('EW036JK', 'Furgone', 6, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XW284VD', 'Furgone', 6, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YL252MT', 'Auto', 6, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VI335YP', 'Auto', 6, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('SI148VU', 'Camion', 7, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZR695LO', 'Camion', 7, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('MB410PW', 'Furgone', 7, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('SS888SE', 'Furgone', 7, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CD775ZA', 'Auto', 7, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WI587UI', 'Auto', 7, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('MB247XU', 'Camion', 8, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CP557FI', 'Camion', 8, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WP804WR', 'Furgone', 8, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('GO001ED', 'Furgone', 8, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('UY310RP', 'Auto', 8, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WA057BD', 'Auto', 8, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YT498HT', 'Camion', 9, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZY681YG', 'Camion', 9, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YT236EC', 'Furgone', 9, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('MP162SN', 'Furgone', 9, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XG720ZA', 'Auto', 9, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZL608XN', 'Auto', 9, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LV728IM', 'Camion', 10, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DY002SF', 'Camion', 10, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('MB433EH', 'Furgone', 10, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('PX611DL', 'Furgone', 10, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KZ033IV', 'Auto', 10, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VD830MG', 'Auto', 10, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KS795ID', 'Camion', 11, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IX538AJ', 'Camion', 11, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CN370OT', 'Furgone', 11, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('SO989AV', 'Furgone', 11, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DY569PA', 'Auto', 11, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DX177RK', 'Auto', 11, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AY623YQ', 'Camion', 12, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZE809BJ', 'Camion', 12, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CG315NT', 'Furgone', 12, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FX461NI', 'Furgone', 12, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('TA871IF', 'Auto', 12, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JC013EZ', 'Auto', 12, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DG876JQ', 'Camion', 13, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZZ144MO', 'Camion', 13, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('BP719BT', 'Furgone', 13, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZB966JU', 'Furgone', 13, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CN312NL', 'Auto', 13, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VJ838EL', 'Auto', 13, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('QP481AE', 'Camion', 14, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XL248WI', 'Camion', 14, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ID695RF', 'Furgone', 14, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XF555YR', 'Furgone', 14, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LM660NT', 'Auto', 14, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ID286QI', 'Auto', 14, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DJ794YW', 'Camion', 15, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('SS413DD', 'Camion', 15, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FM447SF', 'Furgone', 15, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DJ653JQ', 'Furgone', 15, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VZ659OZ', 'Auto', 15, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AO005CV', 'Auto', 15, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AE882CH', 'Camion', 16, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CG375QO', 'Camion', 16, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YG205QO', 'Furgone', 16, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CP707AJ', 'Furgone', 16, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('GB159OR', 'Auto', 16, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('OX691PO', 'Auto', 16, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NH248DU', 'Camion', 17, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KY137XP', 'Camion', 17, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('QO860VK', 'Furgone', 17, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IU619PZ', 'Furgone', 17, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YG826TJ', 'Auto', 17, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AD704BN', 'Auto', 17, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZZ026WF', 'Camion', 18, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RU258VM', 'Camion', 18, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('TR680BM', 'Furgone', 18, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DZ874OV', 'Furgone', 18, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NA998AW', 'Auto', 18, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NT429RE', 'Auto', 18, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AP543PX', 'Camion', 19, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('OL844CB', 'Camion', 19, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RV240VE', 'Furgone', 19, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VZ975VH', 'Furgone', 19, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('HL233XC', 'Auto', 19, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RG171XG', 'Auto', 19, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KC591JM', 'Camion', 20, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IX410TO', 'Camion', 20, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JG791EQ', 'Furgone', 20, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NX571AD', 'Furgone', 20, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YM023MP', 'Auto', 20, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VF553IK', 'Auto', 20, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('HQ742QZ', 'Camion', 21, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IF929ZZ', 'Camion', 21, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RF850EQ', 'Furgone', 21, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AS321AQ', 'Furgone', 21, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KC262EN', 'Auto', 21, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FY697CN', 'Auto', 21, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IQ996DB', 'Camion', 22, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('SV574ZZ', 'Camion', 22, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ND790PP', 'Furgone', 22, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('EV891OC', 'Furgone', 22, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JF155NE', 'Auto', 22, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RG931YS', 'Auto', 22, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('SW352QM', 'Camion', 23, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RW585GZ', 'Camion', 23, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AO961LV', 'Furgone', 23, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZV100YN', 'Furgone', 23, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LW893EO', 'Auto', 23, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CF348DY', 'Auto', 23, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IT817JU', 'Camion', 24, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('UX665KH', 'Camion', 24, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CL671AP', 'Furgone', 24, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('HK727VD', 'Furgone', 24, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LE285OQ', 'Auto', 24, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YG038XH', 'Auto', 24, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DM174SC', 'Camion', 25, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VB927JG', 'Camion', 25, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RL163RG', 'Furgone', 25, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WR325TR', 'Furgone', 25, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('UB477HY', 'Auto', 25, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JX488VO', 'Auto', 25, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('TI157VQ', 'Camion', 26, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('GV268OQ', 'Camion', 26, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('HP626DM', 'Furgone', 26, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JP592ZV', 'Furgone', 26, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FH611RU', 'Auto', 26, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('EA802IN', 'Auto', 26, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IM072HN', 'Camion', 27, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XX599WE', 'Camion', 27, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZZ599JM', 'Furgone', 27, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IQ651XV', 'Furgone', 27, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XB959OT', 'Auto', 27, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('OE044BS', 'Auto', 27, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('TY698YC', 'Camion', 28, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('AY420XG', 'Camion', 28, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RX531KT', 'Furgone', 28, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FS077VY', 'Furgone', 28, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('BY435QN', 'Auto', 28, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZY976MZ', 'Auto', 28, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YN726QG', 'Camion', 29, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KW895UH', 'Camion', 29, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IV456XJ', 'Furgone', 29, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('SY916BF', 'Furgone', 29, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZR224CI', 'Auto', 29, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LK722YS', 'Auto', 29, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NK185NJ', 'Camion', 30, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LV171JU', 'Camion', 30, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('UC373PX', 'Furgone', 30, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CV541UE', 'Furgone', 30, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LE529FY', 'Auto', 30, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NT939SH', 'Auto', 30, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('HM834NY', 'Camion', 31, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WM830FZ', 'Camion', 31, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YY220NX', 'Furgone', 31, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ZT527YZ', 'Furgone', 31, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RR251XC', 'Auto', 31, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('PH934FV', 'Auto', 31, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NC821TK', 'Camion', 32, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('EB302IP', 'Camion', 32, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('SD551XV', 'Furgone', 32, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IA062HH', 'Furgone', 32, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('TJ794FE', 'Auto', 32, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('QM317JX', 'Auto', 32, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XK162OW', 'Camion', 33, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WK982SC', 'Camion', 33, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JH751QP', 'Furgone', 33, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CM778KB', 'Furgone', 33, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('OT979NM', 'Auto', 33, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RT509JI', 'Auto', 33, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LU754DM', 'Camion', 34, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JT527WI', 'Camion', 34, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KK928ME', 'Furgone', 34, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LB652QQ', 'Furgone', 34, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('CA836JI', 'Auto', 34, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('EK105MA', 'Auto', 34, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('MO026LQ', 'Camion', 35, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YG047JG', 'Camion', 35, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NE971VJ', 'Furgone', 35, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ER799QO', 'Furgone', 35, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DI369UA', 'Auto', 35, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LV302HO', 'Auto', 35, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('UL820GD', 'Camion', 36, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('MI169IC', 'Camion', 36, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VU080SY', 'Furgone', 36, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('OI573QA', 'Furgone', 36, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JV181QX', 'Auto', 36, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('OA831GX', 'Auto', 36, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('XD843HM', 'Camion', 37, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FI545QN', 'Camion', 37, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DP228DC', 'Furgone', 37, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('UR014KP', 'Furgone', 37, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DP660UT', 'Auto', 37, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('JY378PM', 'Auto', 37, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NH572QD', 'Camion', 38, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('EB389NW', 'Camion', 38, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('BM043CD', 'Furgone', 38, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('FY376NO', 'Furgone', 38, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YD861DE', 'Auto', 38, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RS263PI', 'Auto', 38, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RC122LX', 'Camion', 39, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('IO475CK', 'Camion', 39, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LV972MM', 'Furgone', 39, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('BZ740QC', 'Furgone', 39, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RB856CK', 'Auto', 39, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('NI240MO', 'Auto', 39, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('RB592OX', 'Camion', 40, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('OO028LR', 'Camion', 40, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('HD854MD', 'Furgone', 40, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WQ191OK', 'Furgone', 40, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VG803JX', 'Auto', 40, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VX999VB', 'Auto', 40, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('GD146IP', 'Camion', 41, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('YC779ZL', 'Camion', 41, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VD279CT', 'Furgone', 41, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('LU302KL', 'Furgone', 41, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('WI233CV', 'Auto', 41, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('ML375EC', 'Auto', 41, 150);

INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('PL008AX', 'Camion', 42, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DB893ON', 'Camion', 42, 5000);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KJ419QV', 'Furgone', 42, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('KX581GS', 'Furgone', 42, 500);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('DW369QY', 'Auto', 42, 150);
INSERT INTO MezzoDiTrasporto (Targa, TipoMezzo, IdGruppoCorriere, PesoTrasportabile) VALUES ('VZ286HO', 'Auto', 42, 150);



-- supponiamo una configurazione iniziale di 3 magazzini a filiale

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (1, 'Magazzino Roma 1', 15, 1);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (2, 'Magazzino Roma 2', 16, 1);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (3, 'Magazzino Roma 3', 17, 1);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (4, 'Magazzino Milano 1', 18, 2);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (5, 'Magazzino Milano 2', 19, 2);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (6, 'Magazzino Milano 3', 20, 2);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (7, 'Magazzino Napoli 1', 21, 3);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (8, 'Magazzino Napoli 2', 22, 3);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (9, 'Magazzino Napoli 3', 23, 3);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (10, 'Magazzino Torino 1', 24, 4);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (11, 'Magazzino Torino 2', 25, 4);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (12, 'Magazzino Torino 3', 26, 4);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (13, 'Magazzino Palermo 1', 27, 5);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (14, 'Magazzino Palermo 2', 28, 5);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (15, 'Magazzino Palermo 3', 29, 5);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (16, 'Magazzino Genova 1', 30, 6);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (17, 'Magazzino Genova 2', 31, 6);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (18, 'Magazzino Genova 3', 32, 6);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (19, 'Magazzino Bologna 1', 33, 7);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (20, 'Magazzino Bologna 2', 34, 7);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (21, 'Magazzino Bologna 3', 35, 7);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (22, 'Magazzino Firenze 1', 36, 8);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (23, 'Magazzino Firenze 2', 37, 8);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (24, 'Magazzino Firenze 3', 38, 8);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (25, 'Magazzino Bari 1', 39, 9);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (26, 'Magazzino Bari 2', 40, 9);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (27, 'Magazzino Bari 3', 41, 9);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (28, 'Magazzino Catania 1', 42, 10);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (29, 'Magazzino Catania 2', 43, 10);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (30, 'Magazzino Catania 3', 44, 10);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (31, 'Magazzino Venezia 1', 45, 11);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (32, 'Magazzino Venezia 2', 46, 11);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (33, 'Magazzino Venezia 3', 47, 11);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (34, 'Magazzino Verona 1', 48, 12);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (35, 'Magazzino Verona 2', 49, 12);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (36, 'Magazzino Verona 3', 50, 12);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (37, 'Magazzino Messina 1', 51, 13);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (38, 'Magazzino Messina 2', 52, 13);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (39, 'Magazzino Messina 3', 53, 13);

INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (40, 'Magazzino Padova 1', 54, 14);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (41, 'Magazzino Padova 2', 55, 14);
INSERT INTO Magazzino(Id, Nome, IdIndirizzo, IdFiliale) VALUES (42, 'Magazzino Padova 3', 56, 14);

commit;

-- Catalogo prodotti

INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (1, '7509144935515', 'Maglia a Righe', 'Maglia a righe di cotone', 'http://www.example.com/maglia_righe.jpg', 'Abbigliamento', 29.99, 0.2, 40, 60, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (2, '1712098084550', 'Jeans Classici', 'Jeans denim blu', 'http://www.example.com/jeans_classici.jpg', 'Abbigliamento', 49.99, 0.5, 30, 70, 10, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (3, '8557015233760', 'Scarpe Sportive', 'Scarpe da ginnastica leggere', 'http://www.example.com/scarpe_sportive.jpg', 'Abbigliamento', 59.99, 0.4, 10, 25, 8, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (4, '1418199637434', 'Cappotto Invernale', 'Cappotto imbottito per l''inverno', 'http://www.example.com/cappotto_invernale.jpg', 'Abbigliamento', 79.99, 1.2, 50, 80, 15, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (5, '1333355765953', 'Giacca di Pelle', 'Giacca di pelle nera', 'http://www.example.com/giacca_pelle.jpg', 'Abbigliamento', 99.99, 0.8, 45, 65, 12, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (6, '4253659738446', 'Camicia Formale', 'Camicia bianca formale', 'http://www.example.com/camicia_formale.jpg', 'Abbigliamento', 39.99, 0.3, 35, 55, 8, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (7, '3660789506125', 'Occhiali da Sole', 'Occhiali da sole neri', 'http://www.example.com/occhiali_sole.jpg', 'Abbigliamento', 29.99, 0.1, 15, 5, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (8, '6088019117257', 'Pantaloni Casual', 'Pantaloni casual beige', 'http://www.example.com/pantaloni_casual.jpg', 'Abbigliamento', 44.99, 0.4, 30, 75, 10, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (9, '5362856909130', 'Borsa a Tracolla', 'Borsa a tracolla in pelle', 'http://www.example.com/borsa_tracolla.jpg', 'Abbigliamento', 59.99, 0.7, 25, 20, 10, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (10, '6839630236642', 'Calze Sportive', 'Calze sportive leggere', 'http://www.example.com/calze_sportive.jpg', 'Abbigliamento', 9.99, 0.1, 8, 18, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (11, '3339514612682', 'Pasta Penne', 'Penne rigate di semola di grano duro', 'http://www.example.com/pasta_penne.jpg', 'Alimentari', 1.99, 0.5, 5, 20, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (12, '3854724953410', 'Olio Extra Vergine di Oliva', 'Olio extravergine di oliva italiano', 'http://www.example.com/olio_oliva.jpg', 'Alimentari', 8.99, 0.75, 7, 25, 7, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (13, '4238371129502', 'Riso Arborio', 'Riso Arborio per risotti', 'http://www.example.com/riso_arborio.jpg', 'Alimentari', 3.99, 1.0, 8, 18, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (14, '1720692542692', 'Salsa di Pomodoro', 'Salsa di pomodoro fresco', 'http://www.example.com/salsa_pomodoro.jpg', 'Alimentari', 2.49, 0.8, 6, 15, 4, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (15, '6375782112265', 'Caffè Arabica', 'Caffè Arabica macinato', 'http://www.example.com/caffe_arabica.jpg', 'Alimentari', 5.99, 0.25, 10, 15, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (16, '8056898029717', 'Cioccolato Fondente', 'Tavoletta di cioccolato fondente', 'http://www.example.com/cioccolato_fondente.jpg', 'Alimentari', 3.49, 0.1, 8, 15, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (17, '3546849156555', 'Miele di Acacia', 'Miele di acacia 100% naturale', 'http://www.example.com/miele_acacia.jpg', 'Alimentari', 6.99, 0.5, 7, 12, 4, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (18, '7355101014634', 'Tonno in Scatola', 'Tonno sott''olio in scatola', 'http://www.example.com/tonno_in_scatola.jpg', 'Alimentari', 4.79, 0.3, 6, 10, 3, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (19, '7250357439323', 'Formaggio Parmigiano', 'Formaggio Parmigiano Reggiano DOP', 'http://www.example.com/formaggio_parmigiano.jpg', 'Alimentari', 12.99, 0.8, 10, 8, 6, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (20, '4736543201904', 'Acqua Minerale', 'Acqua minerale naturale in bottiglia', 'http://www.example.com/acqua_minerale.jpg', 'Alimentari', 1.29, 1.5, 5, 25, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (21, '6843997231663', 'Smartphone AndroId', 'Smartphone AndroId con schermo HD', 'http://www.example.com/smartphone_androId.jpg', 'Elettronica', 299.99, 0.2, 7, 15, 0.8, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (22, '6946570939586', 'Laptop Ultraleggero', 'Laptop ultraleggero con processore i7', 'http://www.example.com/laptop_ultraleggero.jpg', 'Elettronica', 999.99, 1.0, 13, 18, 1.5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (23, '9183452784691', 'Televisore 4K', 'Televisore 4K da 55 pollici', 'http://www.example.com/tv_4k.jpg', 'Elettronica', 799.99, 20.0, 50, 30, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (24, '1005190381881', 'Cuffie Wireless', 'Cuffie wireless con cancellazione del rumore', 'http://www.example.com/cuffie_wireless.jpg', 'Elettronica', 129.99, 0.3, 6, 8, 4, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (25, '9052881371462', 'Tablet AndroId', 'Tablet AndroId con schermo da 10 pollici', 'http://www.example.com/tablet_androId.jpg', 'Elettronica', 199.99, 0.6, 8, 12, 0.7, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (26, '1186743083716', 'Fotocamera DSLR', 'Fotocamera DSLR professionale', 'http://www.example.com/fotocamera_dslr.jpg', 'Elettronica', 899.99, 2.0, 6, 5, 4, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (27, '9934222348713', 'Stampante Multifunzione', 'Stampante multifunzione a colori', 'http://www.example.com/stampante_multifunzione.jpg', 'Elettronica', 129.99, 10.0, 15, 10, 8, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (28, '6279333174434', 'Mouse Gaming', 'Mouse da gaming con illuminazione RGB', 'http://www.example.com/mouse_gaming.jpg', 'Elettronica', 49.99, 0.15, 3, 5, 1.5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (29, '6259103119420', 'Router Wi-Fi AC', 'Router Wi-Fi AC dual-band', 'http://www.example.com/router_wifi_ac.jpg', 'Elettronica', 79.99, 0.5, 8, 12, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (30, '2211482903459', 'Batteria Esterna', 'Batteria esterna da 10000 mAh', 'http://www.example.com/batteria_esterna.jpg', 'Elettronica', 29.99, 0.2, 5, 10, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (31, '8356121389646', 'Tappeto Shaggy', 'Tappeto Shaggy a pelo lungo', 'http://www.example.com/tappeto_shaggy.jpg', 'Casa', 79.99, 5.0, 80, 120, 1, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (32, '1966139549059', 'Set di Pentole Antiaderenti', 'Set di pentole e padelle antiaderenti', 'http://www.example.com/pentole_antiaderenti.jpg', 'Casa', 129.99, 6.0, 30, 40, 20, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (33, '3181300163245', 'Divano a 3 Posti', 'Divano moderno a tre posti', 'http://www.example.com/divano_3_posti.jpg', 'Casa', 599.99, 30.0, 200, 90, 100, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (34, '9524246681713', 'Lampada da Tavolo', 'Lampada da tavolo con base in legno', 'http://www.example.com/lampada_tavolo.jpg', 'Casa', 49.99, 2.0, 20, 40, 20, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (35, '1470174883137', 'Asciugamani di Cotone', 'Set di asciugamani di cotone', 'http://www.example.com/asciugamani_cotone.jpg', 'Casa', 19.99, 1.0, 10, 15, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (36, '2604208813729', 'Tavolo da Pranzo', 'Tavolo da pranzo rettangolare in legno', 'http://www.example.com/tavolo_pranzo.jpg', 'Casa', 299.99, 40.0, 150, 75, 80, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (37, '4221301604938', 'Cuscini Decorativi', 'Set di cuscini decorativi per divano', 'http://www.example.com/cuscini_decorativi.jpg', 'Casa', 24.99, 1.5, 18, 18, 6, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (38, '7643790375749', 'Set di Posate', 'Set di posate in acciaio inossIdabile', 'http://www.example.com/posate_acciaio.jpg', 'Casa', 39.99, 2.0, 10, 25, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (39, '2226896591245', 'Portaoggetti da Parete', 'Portaoggetti da parete con ripiani', 'http://www.example.com/portaoggetti_parete.jpg', 'Casa', 29.99, 3.0, 30, 40, 10, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (40, '7298266441636', 'Tenda oscurante', 'Tenda oscurante per finestre', 'http://www.example.com/tenda_oscurante.jpg', 'Casa', 19.99, 1.2, 50, 200, 1, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (41, '3802749358261', 'Scarpe da Corsa', 'Scarpe da corsa leggere e ammortizzate', 'http://www.example.com/scarpe_corsa.jpg', 'Sport', 79.99, 0.4, 10, 25, 8, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (42, '5642254812689', 'Palla da Calcio', 'Palla da calcio professionale in cuoio', 'http://www.example.com/palla_calcio.jpg', 'Sport', 19.99, 0.5, 22, 22, 22, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (43, '6926162597788', 'Tuta da Ginnastica', 'Tuta da ginnastica in tessuto traspirante', 'http://www.example.com/tuta_ginnastica.jpg', 'Sport', 49.99, 0.8, 15, 30, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (44, '3408675357468', 'Borsa da Palestra', 'Borsa da palestra resistente con scomparti', 'http://www.example.com/borsa_palestra.jpg', 'Sport', 34.99, 0.6, 25, 40, 15, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (45, '4000933931498', 'Pallone da Basket', 'Pallone da basket ufficiale in gomma', 'http://www.example.com/pallone_basket.jpg', 'Sport', 24.99, 0.7, 29, 29, 29, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (46, '7304553830054', 'Cyclette Pieghevole', 'Cyclette pieghevole per allenamento cardio', 'http://www.example.com/cyclette_pieghevole.jpg', 'Sport', 199.99, 15.0, 50, 100, 30, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (47, '8198349740279', 'Tappetino Yoga', 'Tappetino yoga antiscivolo ed ecologico', 'http://www.example.com/tappetino_yoga.jpg', 'Sport', 29.99, 1.0, 24, 68, 0.6, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (48, '3105259185399', 'Guanti da Boxe', 'Guanti da boxe in pelle con imbottitura', 'http://www.example.com/guanti_boxe.jpg', 'Sport', 49.99, 0.5, 12, 18, 6, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (49, '9730030545587', 'T-shirt Tecnica', 'T-shirt tecnica traspirante per l''allenamento', 'http://www.example.com/tshirt_tecnica.jpg', 'Sport', 24.99, 0.3, 35, 50, 3, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (50, '8157522916545', 'Pallina da Tennis', 'Pallina da tennis professionale in gomma', 'http://www.example.com/pallina_tennis.jpg', 'Sport', 3.99, 0.1, 5, 5, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (51, '8544400224396', 'Set di Mobili da Giardino', 'Set di mobili da giardino in rattan', 'http://www.example.com/mobili_giardino.jpg', 'Giardino', 499.99, 50.0, 150, 80, 60, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (52, '1999229830121', 'Barbecue a Gas', 'Barbecue a gas con griglia in acciaio', 'http://www.example.com/barbecue_gas.jpg', 'Giardino', 299.99, 30.0, 80, 120, 40, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (53, '7829662664010', 'Ombrellone da Giardino', 'Ombrellone rettangolare con base', 'http://www.example.com/ombrellone_giardino.jpg', 'Giardino', 89.99, 10.0, 300, 250, 20, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (54, '8023944789602', 'Set di Attrezzi da Giardinaggio', 'Set completo di attrezzi da giardinaggio', 'http://www.example.com/attrezzi_giardinaggio.jpg', 'Giardino', 49.99, 5.0, 20, 40, 10, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (55, '3505547027468', 'Lanterna Solare', 'Lanterna da giardino alimentata a energia solare', 'http://www.example.com/lanterna_solare.jpg', 'Giardino', 19.99, 1.0, 10, 25, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (56, '1735431696348', 'Piscina Gonfiabile', 'Piscina gonfiabile per bambini', 'http://www.example.com/piscina_gonfiabile.jpg', 'Giardino', 59.99, 7.0, 150, 30, 200, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (57, '5560620800572', 'Ornamento da Giardino', 'Statua decorativa da giardino in ceramica', 'http://www.example.com/ornamento_giardino.jpg', 'Giardino', 39.99, 4.0, 15, 40, 15, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (58, '8982300066838', 'Irrigatore Automatico', 'Irrigatore automatico programmabile', 'http://www.example.com/irrigatore_automatico.jpg', 'Giardino', 79.99, 1.5, 8, 20, 8, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (59, '6867113666157', 'Amaca da Giardino', 'Amaca da giardino con supporto in acciaio', 'http://www.example.com/amaca_giardino.jpg', 'Giardino', 69.99, 15.0, 150, 200, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (60, '9739097816667', 'Vaso di Fiori in Ceramica', 'Vaso decorativo per piante in ceramica', 'http://www.example.com/vaso_fiori.jpg', 'Giardino', 29.99, 2.0, 10, 20, 10, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (61, '6426778699412', 'Fertilizzante Universale', 'Fertilizzante per piante universale', 'http://www.example.com/fertilizzante_universale.jpg', 'Giardino', 12.99, 2.0, 5, 15, 3, 'Chimico');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (62, '2292138991712', 'InsetticIda Naturale', 'InsetticIda naturale a base di oli essenziali', 'http://www.example.com/insetticIda_naturale.jpg', 'Giardino', 8.99, 1.0, 3, 10, 2, 'Tossico');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (63, '1470740078702', 'Fioriera in Metallo', 'Fioriera decorativa in metallo', 'http://www.example.com/fioriera_metallo.jpg', 'Giardino', 39.99, 5.0, 30, 40, 20, 'Corrosivo');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (64, '5923677909594', 'Luci Solari per Vialetto', 'Luci solari per illuminare il vialetto', 'http://www.example.com/luci_solari_vialetto.jpg', 'Giardino', 29.99, 1.5, 8, 15, 5, 'Radioattivo');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (65, '9656874717555', 'Rasaerba Elettrico', 'Rasaerba elettrico per piccoli giardini', 'http://www.example.com/rasaerba_elettrico.jpg', 'Giardino', 149.99, 10.0, 20, 60, 40, 'Infiammabile');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (66, '9343664388824', 'Piante Carnivore', 'Set di piante carnivore per il controllo degli insetti', 'http://www.example.com/piante_carnivore.jpg', 'Giardino', 19.99, 2.0, 10, 20, 10, 'Infettante');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (67, '7956092606684', 'Spruzzatore Manuale', 'Spruzzatore manuale per liquIdi giardinaggio', 'http://www.example.com/spruzzatore_manuale.jpg', 'Giardino', 14.99, 0.5, 5, 15, 3, 'Chimico');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (68, '9257117037108', 'Set di Semi di Fiori Selvatici', 'Set di semi di fiori selvatici per giardino naturale', 'http://www.example.com/semi_fiori_selvatici.jpg', 'Giardino', 9.99, 0.2, 5, 10, 2, 'Infettante');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (69, '3779210461280', 'FungicIda Concentrato', 'FungicIda concentrato per piante', 'http://www.example.com/fungicIda_concentrato.jpg', 'Giardino', 17.99, 1.0, 3, 12, 2, 'Tossico');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (70, '4669748927697', 'Pergolato da Giardino', 'Pergolato in legno per zone d''ombra', 'http://www.example.com/pergolato_giardino.jpg', 'Giardino', 299.99, 50.0, 300, 250, 100, 'Corrosivo');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (71, '1413840845563', 'Cuscino Decorativo', 'Cuscino decorativo per il soggiorno', 'http://www.example.com/cuscino_decorativo.jpg', 'Altro', 14.99, 0.5, 18, 18, 6, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (72, '8040748587545', 'Set di Pennelli Artistici', 'Set di pennelli per pittura artistica', 'http://www.example.com/set_pennelli_artistici.jpg', 'Altro', 29.99, 0.3, 10, 30, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (73, '5621182608794', 'Orologio da Parete', 'Orologio decorativo da parete', 'http://www.example.com/orologio_parete.jpg', 'Altro', 49.99, 1.0, 30, 30, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (74, '6198232675055', 'Set di Chiavi a Cricchetto', 'Set di chiavi a cricchetto per lavori manuali', 'http://www.example.com/set_chiavi_cricchetto.jpg', 'Altro', 39.99, 2.0, 8, 25, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (75, '1032141533785', 'Puzzle 1000 Pezzi', 'Puzzle con immagine panoramica', 'http://www.example.com/puzzle_1000_pezzi.jpg', 'Altro', 19.99, 1.5, 30, 40, 0.2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (76, '5685828608659', 'Tazza da Colazione', 'Tazza con motivi divertenti per la colazione', 'http://www.example.com/tazza_colazione.jpg', 'Altro', 9.99, 0.3, 8, 10, 8, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (77, '9041647686642', 'Portafoglio in Pelle', 'Portafoglio elegante in pelle', 'http://www.example.com/portafoglio_pelle.jpg', 'Altro', 29.99, 0.2, 10, 12, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (78, '1040580205971', 'Candela Profumata', 'Candela profumata con fragranza alla lavanda', 'http://www.example.com/candela_profumata.jpg', 'Altro', 12.99, 0.4, 5, 8, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (79, '6347574408869', 'Borsa da Viaggio', 'Borsa da viaggio resistente e capiente', 'http://www.example.com/borsa_viaggio.jpg', 'Altro', 49.99, 1.5, 25, 35, 15, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (80, '6437475225369', 'Set di Tazze da Caffè', 'Set di tazze da caffè con piattini assortiti', 'http://www.example.com/set_tazze_caffe.jpg', 'Altro', 24.99, 1.0, 15, 10, 8, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (81, '6791585511164', 'Altoparlante Bluetooth', 'Altoparlante portatile con connessione Bluetooth', 'http://www.example.com/altoparlante_bluetooth.jpg', 'Altro', 79.99, 0.8, 10, 15, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (82, '7117028248796', 'Cornice Foto in Legno', 'Cornice in legno per foto 10x15 cm', 'http://www.example.com/cornice_foto_legno.jpg', 'Altro', 14.99, 0.5, 15, 20, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (83, '7549092584797', 'Mouse Wireless', 'Mouse ottico wireless per computer', 'http://www.example.com/mouse_wireless.jpg', 'Altro', 29.99, 0.2, 6, 10, 3, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (84, '7301819177438', 'Quaderno in Pelle', 'Quaderno elegante con copertina in pelle', 'http://www.example.com/quaderno_pelle.jpg', 'Altro', 17.99, 0.8, 12, 18, 1, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (85, '3957857892797', 'Set di Posate da Dessert', 'Set di posate da dessert in acciaio inox', 'http://www.example.com/set_posate_dessert.jpg', 'Altro', 19.99, 0.5, 8, 18, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (86, '1054560577600', 'Zaino Antifurto', 'Zaino con chiusure antifurto e tasche nascoste', 'http://www.example.com/zaino_antifurto.jpg', 'Altro', 39.99, 1.2, 20, 40, 15, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (87, '9790570552038', 'Set di Matite Colorate', 'Set di matite colorate per disegno artistico', 'http://www.example.com/set_matite_colorate.jpg', 'Altro', 9.99, 0.3, 5, 15, 1, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (88, '8766206006938', 'Coperta MorbIda in Pile', 'Coperta morbIda e calda in pile', 'http://www.example.com/coperta_pile.jpg', 'Altro', 29.99, 1.0, 50, 60, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (89, '1436484027181', 'Tastiera Bluetooth', 'Tastiera compatta con connessione Bluetooth', 'http://www.example.com/tastiera_bluetooth.jpg', 'Altro', 49.99, 0.5, 30, 10, 2, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (90, '6119728901660', 'Lucchetto Antifurto', 'Lucchetto robusto e antifurto per bagagli', 'http://www.example.com/lucchetto_antifurto.jpg', 'Altro', 19.99, 0.3, 8, 10, 2, 'Corrosivo');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (91, '4855203410797', 'Rullo Verniciatore', 'Rullo per verniciatura professionale', 'http://www.example.com/rullo_verniciatore.jpg', 'Altro', 24.99, 0.8, 5, 20, 5, 'Infiammabile');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (92, '5883581348830', 'Kit di Sopravvivenza', 'Kit completo di sopravvivenza outdoor', 'http://www.example.com/kit_sopravvivenza.jpg', 'Altro', 79.99, 2.5, 15, 25, 10, 'Radioattivo');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (93, '6251166834971', 'Spray Anti-Insetti', 'Spray repellente per insetti', 'http://www.example.com/spray_anti_insetti.jpg', 'Altro', 9.99, 0.2, 3, 15, 3, 'Tossico');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (94, '5095025946581', 'Guanti da Lavoro', 'Guanti resistenti per lavori manuali', 'http://www.example.com/guanti_lavoro.jpg', 'Altro', 14.99, 0.3, 10, 25, 5, 'Corrosivo');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (95, '9765508662054', 'Portachiavi con Allarme', 'Portachiavi con allarme antifurto', 'http://www.example.com/portachiavi_allarme.jpg', 'Altro', 17.99, 0.1, 3, 5, 1, 'Infiammabile');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (96, '2253363244713', 'Rilevatore di Movimento', 'Rilevatore di movimento per sicurezza', 'http://www.example.com/rilevatore_movimento.jpg', 'Altro', 39.99, 0.3, 5, 10, 2, 'Radioattivo');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (97, '6771800938803', 'Set di Cacciaviti Magnetici', 'Set di cacciaviti con punte magnetiche', 'http://www.example.com/set_cacciaviti_magnetici.jpg', 'Altro', 19.99, 0.4, 8, 15, 3, 'Tossico');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (98, '1431165718753', 'Scatola di Pronto Soccorso', 'Scatola completa di primo soccorso', 'http://www.example.com/scatola_pronto_soccorso.jpg', 'Altro', 49.99, 1.5, 10, 20, 5, 'Infettante');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (99, '6865225859128', 'Lavatrice Samsung a Carica Frontale', 'Lavatrice con capacità 8 kg, classe energetica A++', 'http://www.example.com/lavatrice_frontale.jpg', 'Elettrodomestici', 499.99, 80.0, 60, 85, 60, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (100, '3990775978661', 'Frigorifero Side-by-Side', 'Frigorifero con doppia porta e dispenser d''acqua', 'http://www.example.com/frigorifero_side_by_side.jpg', 'Elettrodomestici', 999.99, 150.0, 90, 180, 70, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (101, '2903978405328', 'Asciugatrice a Pompa di Calore', 'Asciugatrice con tecnologia a pompa di calore', 'http://www.example.com/asciugatrice_pompa_calore.jpg', 'Elettrodomestici', 699.99, 70.0, 60, 85, 60, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (102, '3317544825620', 'TV LED 65 pollici', 'TV LED con risoluzione 4K e Smart TV integrata', 'http://www.example.com/tv_led_65_pollici.jpg', 'Elettronica', 1299.99, 25.0, 145, 85, 10, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (103, '7149012113533', 'Forno a Microonde', 'Forno a microonde con capacità 20 litri', 'http://www.example.com/forno_microonde.jpg', 'Elettrodomestici', 149.99, 15.0, 45, 30, 35, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (104, '3373011415643', 'Bicicletta Elettrica', 'Bicicletta elettrica con motore 250W', 'http://www.example.com/bicicletta_elettrica.jpg', 'Sport', 999.99, 25.0, 70, 110, 20, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (105, '1185339164551', 'Aspirapolvere Robot', 'Aspirapolvere robot con funzione di mappatura', 'http://www.example.com/aspirapolvere_robot.jpg', 'Elettrodomestici', 299.99, 5.0, 35, 10, 35, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (106, '5702100989508', 'Lavastoviglie da Incasso', 'Lavastoviglie integrabile con capacità 13 coperti', 'http://www.example.com/lavastoviglie_incasso.jpg', 'Elettrodomestici', 699.99, 40.0, 60, 82, 55, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (107, '1196100542172', 'Macchina da Caffè Automatica', 'Macchina da caffè automatica con cappuccinatore', 'http://www.example.com/macchina_caffe_automatica.jpg', 'Elettrodomestici', 249.99, 10.0, 20, 40, 30, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (108, '2561397789407', 'Forno a Microonde con Grill', 'Forno a microonde con funzione grill', 'http://www.example.com/forno_microonde_grill.jpg', 'Elettrodomestici', 179.99, 18.0, 45, 30, 40, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (109, '5364927376255', 'Bicicletta Elettrica Mountain Bike', 'Mountain bike elettrica con motore potente', 'http://www.example.com/bicicletta_elettrica_mountain_bike.jpg', 'Sport', 1499.99, 22.0, 70, 110, 20, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (110, '9350609207199', 'Lavastoviglie Slim', 'Lavastoviglie slim con capacità 9 coperti', 'http://www.example.com/lavastoviglie_slim.jpg', 'Elettrodomestici', 549.99, 35.0, 45, 85, 60, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (111, '2967150847959', 'Frigorifero Compatto', 'Frigorifero compatto per spazi ridotti', 'http://www.example.com/frigorifero_compatto.jpg', 'Elettrodomestici', 299.99, 40.0, 50, 80, 50, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (112, '4948299519633', 'Lavatrice-Seggiolone', 'Lavatrice con funzione seggiolone integrato', 'http://www.example.com/lavatrice_seggiolone.jpg', 'Elettrodomestici', 649.99, 85.0, 60, 100, 70, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (113, '7209571233537', 'Congelatore Verticale', 'Congelatore verticale con capacità 200 litri', 'http://www.example.com/congelatore_verticale.jpg', 'Elettrodomestici', 799.99, 90.0, 60, 150, 70, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (114, '6887342511778', 'Macchina per il Caffè Espresso', 'Macchina per il caffè espresso manuale', 'http://www.example.com/macchina_caffe_espresso.jpg', 'Elettrodomestici', 179.99, 7.0, 15, 30, 25, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (115, '2536483010870', 'Robot Aspirapolvere con Mappatura', 'Robot aspirapolvere con sistema di mappatura', 'http://www.example.com/robot_aspirapolvere_mappatura.jpg', 'Elettrodomestici', 399.99, 5.0, 30, 8, 30, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (116, '8427363469584', 'TV OLED 55 pollici', 'TV OLED con risoluzione 8K e Dolby Vision', 'http://www.example.com/tv_oled_55_pollici.jpg', 'Elettronica', 1899.99, 20.0, 120, 70, 5, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (117, '6425677770011', 'Tritatutto Professionale', 'Tritatutto professionale per cucina', 'http://www.example.com/tritatutto_professionale.jpg', 'Elettrodomestici', 129.99, 4.0, 10, 25, 15, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (118, '1551870497277', 'Macchina per il Pane', 'Macchina automatica per la preparazione del pane', 'http://www.example.com/macchina_pane.jpg', 'Elettrodomestici', 89.99, 6.0, 25, 30, 20, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (119, '6227595498112', 'Bicicletta Elettrica da Corsa', 'Bicicletta elettrica da corsa con motore potente', 'http://www.example.com/bicicletta_elettrica_corsa.jpg', 'Sport', 1699.99, 18.0, 70, 100, 15, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (120, '7790231498860', 'Cucina a Gas con Forno', 'Cucina a gas con forno elettrico integrato', 'http://www.example.com/cucina_gas_forno.jpg', 'Elettrodomestici', 899.99, 100.0, 80, 90, 60, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (121, '1656430588695', 'Ventilatore da Soffitto', 'Ventilatore da soffitto con luci LED', 'http://www.example.com/ventilatore_soffitto.jpg', 'Elettrodomestici', 129.99, 7.0, 120, 40, 40, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (122, '3498459548093', 'Bollitore Elettrico', 'Bollitore elettrico con capacità 1.7 litri', 'http://www.example.com/bollitore_elettrico.jpg', 'Elettrodomestici', 39.99, 1.5, 15, 25, 20, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (123, '5041540653650', 'Robot da Cucina Multifunzione', 'Robot da cucina multifunzione con cottura a vapore', 'http://www.example.com/robot_cucina_multifunzione.jpg', 'Elettrodomestici', 299.99, 8.0, 20, 40, 30, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (124, '7446727475345', 'Lavasciuga Integrabile', 'Lavasciuga integrabile con capacità 8 kg/5 kg', 'http://www.example.com/lavasciuga_integrabile.jpg', 'Elettrodomestici', 849.99, 70.0, 60, 85, 60, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (125, '3794468789137', 'Macchina per il Caffè a Capsule', 'Macchina per il caffè a capsule con cappuccinatore', 'http://www.example.com/macchina_caffe_capsule.jpg', 'Elettrodomestici', 129.99, 6.0, 15, 30, 25, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (126, '1737813108651', 'Ventilatore da Terra', 'Ventilatore da terra con tre velocità', 'http://www.example.com/ventilatore_terra.jpg', 'Elettrodomestici', 69.99, 10.0, 40, 120, 30, 'Nessuna');
INSERT INTO CatalogoProdotti (Id, CodiceEAN, Nome, Descrizione, URLPhoto, Tipo, Prezzo, Peso, Larghezza, Altezza, Profondita, Pericolosita) VALUES (127, '4354532443419', 'Forno a Microonde con Funzione Grill', 'Forno a microonde con funzione grill e convezione', 'http://www.example.com/forno_microonde_grill_convezione.jpg', 'Elettrodomestici', 219.99, 20.0, 45, 30, 40, 'Nessuna');
commit;


-- MerceStoccata

BEGIN
    FillMerceStoccata();
END;

commit;

insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (1, 'fabio', 'fabio', 1, 1);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (2, 'mario', 'mario', 1, 2);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (3, 'giuseppe', 'giuseppe', 1, 3);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (4, 'giovanni', 'giovanni', 1, 4);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (5, 'luca', 'luca', 1, 5);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (6, 'andrea', 'andrea', 1, 6);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (7, 'francesco', 'francesco', 1, 7);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (8, 'marco', 'marco', 1, 8);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (9, 'alessandro', 'alessandro', 1, 9);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (10, 'davide', 'davide', 1, 10);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (11, 'simone', 'simone', 1, 11);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (12, 'matteo', 'matteo', 1, 12);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (13, 'paolo', 'paolo', 1, 13);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDFILIALEOPERATORE) values (14, 'lorenzo', 'lorenzo', 1, 14);

insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (15, 'Marco_corriere_1', 'marco', 2, 1);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (16, 'Luca_corriere_2', 'luca', 2, 2);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (17, 'Andrea_corriere_3', 'andrea', 2, 3);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (18, 'Francesco_corriere_4', 'francesco', 2, 4);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (19, 'Giovanni_corriere_5', 'giovanni', 2, 5);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (20, 'Giuseppe_corriere_6', 'giuseppe', 2, 6);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (21, 'Mario_corriere_7', 'mario', 2, 7);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (22, 'Fabio_corriere_8', 'fabio', 2, 8);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (23, 'Alessandro_corriere_9', 'alessandro', 2, 9);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (24, 'Davide_corriere_10', 'davide', 2, 10);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (25, 'Simone_corriere_11', 'simone', 2, 11);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (26, 'Matteo_corriere_12', 'matteo', 2, 12);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (27, 'Paolo_corriere_13', 'paolo', 2, 13);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (28, 'Lorenzo_corriere_14', 'lorenzo', 2, 14);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (29, 'Giacomo_corriere_15', 'giacomo', 2, 15);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (30, 'Riccardo_corriere_16', 'riccardo', 2, 16);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (31, 'Tommaso_corriere_17', 'tommaso', 2, 17);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (32, 'Vincenzo_corriere_18', 'vincenzo', 2, 18);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (33, 'Salvatore_corriere_19', 'salvatore', 2, 19);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (34, 'Antonio_corriere_20', 'antonio', 2, 20);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (35, 'Angelo_corriere_21', 'angelo', 2, 21);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (36, 'Daniele_corriere_22', 'daniele', 2, 22);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (37, 'Giovanni_corriere_23', 'giovanni', 2, 23);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (38, 'Giuseppe_corriere_24', 'giuseppe', 2, 24);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (39, 'Mario_corriere_25', 'mario', 2, 25);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (40, 'Fabio_corriere_26', 'fabio', 2, 26);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (41, 'Alessandro_corriere_27', 'alessandro', 2, 27);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (42, 'Davide_corriere_28', 'davide', 2, 28);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (43, 'Simone_corriere_29', 'simone', 2, 29);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (44, 'Matteo_corriere_30', 'matteo', 2, 30);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (45, 'Paolo_corriere_31', 'paolo', 2, 31);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (46, 'Lorenzo_corriere_32', 'lorenzo', 2, 32);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (47, 'Giacomo_corriere_33', 'giacomo', 2, 33);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (48, 'Riccardo_corriere_34', 'riccardo', 2, 34);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (49, 'Tommaso_corriere_35', 'tommaso', 2, 35);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (50, 'Vincenzo_corriere_36', 'vincenzo', 2, 36);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (51, 'Salvatore_corriere_37', 'salvatore', 2, 37);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (52, 'Antonio_corriere_38', 'antonio', 2, 38);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (53, 'Angelo_corriere_39', 'angelo', 2, 39);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (54, 'Daniele_corriere_40', 'daniele', 2, 40);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (55, 'Giovanni_corriere_41', 'giovanni', 2, 41);
insert into utente (ID, USERNAME, PASSWORD, IDPROFILO, IDGRUPPOCORRIERE) values (56, 'Giuseppe_corriere_42', 'giuseppe', 2, 42);
insert into UTENTE (ID, USERNAME, PASSWORD, MATRICOLAUNINA, IDPROFILO, IDFILIALEOPERATORE, IDGRUPPOCORRIERE)
values(57, 'Manager', 'manager', NULL, 3, NULL, NULL);




commit;

