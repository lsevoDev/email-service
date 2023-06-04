create table dbo.Email(
    ID bigint identity(1,1) not null,
    External_ID nvarchar(36) not null unique,
    From_Email nvarchar(320) not null,
    To_Email nvarchar(320) not null,
    Subject nvarchar(255) not null,
    Content nvarchar(max) not null,
    Importance nvarchar(100) not null,
    Status nvarchar(100) not null,
    Error_Description nvarchar(max),
    Created_At datetime2(3) not null,
    Modified_At datetime2(3) null,
    constraint PK_Email primary key clustered(ID),
    constraint UK_Email_External_ID unique (External_ID)
)
go

create table dbo.Email_CC(
    ID bigint identity(1,1) not null,
    Email_ID bigint not null,
    Email varchar(320) not null,
    constraint PK_Email_CC primary key clustered(ID),
    constraint FK_Email_ID foreign key(Email_ID) references dbo.Email on delete cascade
)
go