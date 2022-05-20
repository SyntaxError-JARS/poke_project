-----------------------------------------
-------------C(reate)RUD-----------------
-- NOTE: MAKE SURE YOUR SCHEMA CONTAINS NONE OF THE FOLLOWING TABLE NAMES
	-- elemental_type
	-- abilites
	-- pokemon
-- Create ourselves a schema, un comment below command if you need to create it, 
-- then comment out again and execute script after selecting the appropriate schema in the toolbar above
-- create schema pokedex;

-- CREATE OUR TABLES & APPLY CONSTRAINTS

-- Added in the Trainer table to match the system

create table trainer (
	id serial primary key,
	fname varchar(20) not null,
	lname varchar(20) not null,
	email varchar(30) not null unique,
	"password" varchar(25) not null,
	dob varchar(15) not null
);

-- Need this incase we add any information pertaining to the element type and insuring consistency
create table elemental_type (
	id serial primary key,
	"type" varchar(30)
);

create table abilities (
	ability_name varchar(25) primary key,
	atk_multiplier int,
	dmg_type int
);

alter table abilities
add constraint fk_elemental_type
foreign key(dmg_type) references elemental_type(id);

create table pokemon (
	id serial primary key,
	pokemon_name varchar(50),
	hp int not null,
	atk int not null,
	element_type int not null,
	ability1 varchar(25) not null,
	ability2 varchar(25) not null check (ability1 != ability2)
	--constraint fk_abilities foreign key(ability_1) references abilities(ability_name)
);

-- The best and most useful case for alter
alter table pokemon
add constraint fk_abilities_1
foreign key(ability1) references abilities(ability_name);

alter table pokemon
add constraint fk_abilitie_2 
foreign key(ability2) references abilities(ability_name);

alter table pokemon
add constraint fk_elemental_type 
foreign key(element_type) references elemental_type(id);

------------------------------------
-- Uncomment and Only execute if you need to dropp
-- drop table pokemon; 
-- drop table abilities;
-- drop table elemental_type;
-- drop table trainer;
------------------------------------

-- CREATING NEW ENTRIES
-- Insert some trainers

insert into trainer 
values
(default, 'Charles', 'Jester', 'cj@mail.com', 'password', '01-01-0001'),
(default, 'Maxwell', 'House', 'mh@mail.com', 'password', '02-20-1900'),
(default, 'Soyoung', 'Lee', 'sl@mail.com', 'password', '06-01-2002');

-- single insert type first
insert into elemental_type 
values
(default, 'normal');

insert into elemental_type 
values
(default, 'fire'),
(default, 'water'),
(default, 'lightning'),
(default, 'grass');

-- single insert
insert into abilities
values
('Scratch', 2, 1);

-- multi-insert
insert into abilities
values
('Tackle', 3, 1),
('Fire Breath', 3, 2),
('Water Gun', 5, 3),
('Shock', 3, 4),
('Vine Whip', 3, 5);



-- single insert into pokemon
insert into pokemon 
values
(default, 'rattata', '12', '2', 1, 'Scratch', 'Tackle');

-- multi-insert 
insert into pokemon 
values
(default, 'pidgey', '10', '1', 1, 'Tackle', 'Scratch'),
(default, 'charmander', '25', '5', 2, 'Scratch', 'Fire Breath'),
(default, 'squirtle', '30', '3', 3, 'Scratch', 'Water Gun'),
(default, 'pikachu', '15', '10', 4, 'Tackle', 'Shock'),
(default, 'bulbasaur', '22', '6', 5, 'Tackle', 'Vine Whip');

-------------Create End------------------

-----------------------------------------
---------------CR(ead)UD-----------------
select * from pokemon p ;
select * from abilities a ;

-- joins

select pokemon.pokemon_name, pokemon.ability1, pokemon.atk, abilities.atk_multiplier, abilities.dmg_type 
from pokemon 
join abilities ON pokemon.ability1 = abilities.ability_name;


-- Same exact results as above, but we used the alias instead (pokemon p and ablities a)
-- If something is aliased, you have to use that
select p.pokemon_name, p.ability1, p.atk, a.atk_multiplier, a.dmg_type 
from pokemon p
join abilities a ON p.ability1 = a.ability_name;


-- views

create view pokemon_abilities as
select p.pokemon_name, p.ability1, p.atk, a.atk_multiplier, a.dmg_type 
from pokemon p
join abilities a ON p.ability1 = a.ability_name;

select * from pokemon_abilities;

select * from trainer;

-------------Read End--------------------

-----------------------------------------
-------------CRU(pdate)D-----------------

update trainer set dob = '06-01-2002' where email = 'sl@mail.com' ;

-------------Update End------------------

-----------------------------------------
-------------CRUD(elete)-----------------



-------------Delete End------------------
