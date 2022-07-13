create table if not exists Ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);
ALTER TABLE Ingredient ADD PRIMARY KEY (id);

create table if not exists Taco (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
    taco bigint not null,
    ingredient varchar(4) not null
);

alter table Taco_Ingredients
    add foreign key (taco) references Taco(id);
alter table Taco_Ingredients
    add foreign key (ingredient) references Ingredient(id);

create table if not exists Taco_Order (
    id identity,
    deliveryName varchar(50),
    deliveryStreet varchar(50) ,
    deliveryCity varchar(50) ,
    deliveryState varchar(2) ,
    deliveryZip varchar(10) ,
    ccNumber varchar(16) ,
    ccExpiration varchar(5) ,
    ccCVV varchar(3) ,
    placedAt timestamp
);

create table if not exists Taco_Order_Tacos (
    tacoOrder bigint not null,
    taco bigint not null
);

alter table Taco_Order_Tacos
    add foreign key (tacoOrder) references Taco_Order(id);
alter table Taco_Order_Tacos
    add foreign key (taco) references Taco(id);