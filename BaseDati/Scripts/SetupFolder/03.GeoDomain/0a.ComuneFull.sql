begin
    execute immediate 'drop table ComuneFull cascade constraints purge';
exception when others then null;
end;
/

create table ComuneFull (
    Id SMALLINT NOT NULL,
	CodiceRegione varchar2(1000 byte),
	CodiceCittaMetropolitana varchar2(1000 byte),
	CodiceProvincia varchar2(1000 byte),
	ProgressivoDelComune varchar2(1000 byte),
	CodiceComuneFormatoAlfanumerico varchar2(1000 byte),
	DenominazioneInItaliano varchar2(1000 byte),
	DenominazioneAltraLingua varchar2(1000 byte),
	CodiceRipartizioneGeografica number,
	RipartizioneGeografica varchar2(1000 byte),
	DenominazioneRegione varchar2(1000 byte),
	DenominazioneCittaMetropolitana varchar2(1000 byte),
	DenominazioneProvincia varchar2(1000 byte),
	FlagComuneCapoluogoDiProvincia varchar2(1000 byte),
	SiglaAutomobilistica varchar2(1000 byte),
	CodiceComuneFormatoNumerico varchar2(1000 byte),
	CodiceComuneNumericoCon110Provincedal2010Al2016 varchar2(1000 byte),
	CodiceComuneNumericoCon107Provincedal2006Al2009 varchar2(1000 byte),
	CodiceComuneNumericoCon103Provincedal1995Al2005 varchar2(1000 byte),
	CodiceCatastaleDelComune varchar2(1000 byte),
	PopolazioneLegale20110910201 varchar2(1000 byte),
	CodiceNUTS12010 varchar2(1000 byte),
	CodiceNUTS22010 varchar2(1000 byte),
	CodiceNUTS32010 varchar2(1000 byte),
	CodiceNUTS12006 varchar2(1000 byte),
	CodiceNUTS22006 varchar2(1000 byte),
	CodiceNUTS32006 varchar2(1000 byte),
    constraint pk_ComuneFull primary key (id)
);
