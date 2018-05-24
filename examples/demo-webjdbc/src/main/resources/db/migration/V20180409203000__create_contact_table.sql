CREATE TABLE contact
(
  id NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
  first_name VARCHAR(255),
  lastname VARCHAR(255),
  email VARCHAR(255)
);

insert INTO contact (first_name, lastname, email) VALUES ('Bent', 'Solheim', 'BentAndre.Solheim@skatteetaten.no');