create database voyage_s3;

\c voyage_s3

create sequence seqbouquet 
increment by 1
start with 1 
minValue 1;

create sequence seqtransport 
increment by 1
start with 1 
minValue 1;

create sequence seqdestination
increment by 1
start with 1 
minValue 1;

create sequence seqage
increment by 1
start with 1 
minValue 1;

create sequence seqvoyage
increment by 1
start with 1 
minValue 1;

create sequence seqdetailvoyage
increment by 1
start with 1 
minValue 1;


create sequence seqvoyageactivite
increment by 1
start with 1 
minValue 1;

create sequence seqbouquetactivite
increment by 1
start with 1 
minValue 1;

create sequence seqactivite
increment by 1
start with 1 
minValue 1;

create sequence seqlieu
increment by 1
start with 1 
minValue 1;

create table bouquet(
id varchar(256) default 'B' || LPAD (nextval('seqbouquet')::text,4,'0') not null primary key, 
nombouquet varchar(256)
);

create table transport(
id varchar(256) default 'T' || LPAD (nextval('seqtransport')::text,4,'0') not null primary key, 
nomtransport varchar(256)
);

create table destination(
id varchar(256) default 'D' || LPAD (nextval('seqdestination')::text,4,'0') not null primary key,
nomdestination varchar(256)
);

create table activite(
id varchar(256) default 'A' || LPAD (nextval('seqactivite')::text,4,'0') not null primary key,
nomactivite varchar(256),
prix double 
);

create table bouquetactivite(
id varchar(256) default 'BA' || LPAD (nextval('seqbouquetactivite')::text,4,'0') not null primary key,
idactivite varchar(256) references activite(id) ,
idtransport varchar(256) references transport(id)
);

create table trancheage(
id varchar(256) default 'TA' || LPAD (nextval('seqage')::text,4,'0') not null primary key,
nomage varchar(256),
min int,
max int
);

create table lieu(
id varchar(256) default 'L' || LPAD (nextval('seqlieu')::text,4,'0') not null primary key,
nomlieu varchar(256)

);

create table voyage(
id varchar(256) default 'V' || LPAD (nextval('seqvoyage')::text,4,'0') not null primary key,
iddestination varchar(256) references destination(id),
idtrancheage varchar(256) references trancheage(id),
idbouquet varchar(256) references bouquet(id),
groupe int,
persgroupe int,
datedebut date ,
datefin date
);

create table detailvoyage(
id varchar(256) default 'DV' || LPAD (nextval('seqdetailvoyage')::text,4,'0') not null primary key,
idvoyage varchar(256) references voyage(id),
idlieu varchar(256) references lieu(id),
idtransport varchar(256) references transport(id),
nbractivite int
);

create table voyageactivite(
id varchar(256) default 'VA' || LPAD (nextval('seqvoyageactivite')::text,4,'0') not null primary key,
idvoyage varchar(256) references voyage(id),
idactivite varchar(256) references activite(id),
daty date,
heuredebut time,
heurefin time,
lieu varchar(256)
);

create or replace view vues_voyage as
	select v.id, v.datedebut, v.datefin, d.nomdestination, a.nomage, b.nombouquet , l.nomlieu from detailvoyage as dv
	join voyage as v on dv.idvoyage = v.id 
	join destination as d on v.iddestination = d.id
	join trancheage as a on v.idtrancheage = a.id
	join bouquet as b on v.idbouquet = b.id
	join lieu as l on dv.idlieu = l.id;

ALTER TABLE voyage
ADD COLUMN duree INT GENERATED ALWAYS AS (datefin - datedebut) STORED;

create or replace view voyage_court as 
select * from voyage where duree<3;

create view vues_activite 
as select v.id, v.datedebut, v.datefin, v.nomdestination, v.nombouquet,v.nomlieu ,va.daty, va.heuredebut,va.heurefin, va.lieu, va.nombre, a.nomactivite, a.prix , (a.prix*va.nombre) as totalactivite from voyageactivite as va
join vues_voyage as v on va.idvoyage = v.id
join activite as a on va.idactivite = a.id order by id;

create table billet(
	id serial primary key,
	idactivite varchar(20) references activite(id),
	quantite int 
);

create table reservation(
	id serial primary key,
	nomclient varchar(256),
	idvoyage varchar(20) references voyage(id)
);

create table historiquebillet(
	id serial primary key,
	idactivite varchar(20) references activite(id),
	quantite int
);

create view vues as 
select distinct reservation.nomclient reservation.idvoyage , voyageactivite.nombre, historiquebillet.quantite, (historiquebillet.quantite - voyageactivite.nombre) as reste, activite.nomactivite from reservation 
join voyageactivite on reservation.idvoyage = voyageactivite.idvoyage
join activite on voyageactivite.idactivite = activite.id
join historiquebillet on voyageactivite.idactivite = historiquebillet.idactivite;