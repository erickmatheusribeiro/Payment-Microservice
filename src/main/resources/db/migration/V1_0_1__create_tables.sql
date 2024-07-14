CREATE TABLE IF NOT EXISTS payment
  (
     id               BIGSERIAL NOT NULL,
     status           VARCHAR(255) NOT NULL,
     value            NUMERIC(15, 4) DEFAULT 0 NOT NULL,
     datetimecreation TIMESTAMP NOT NULL,
     datetimeend      TIMESTAMP,
     dateTimeCancel   TIMESTAMP,
     orderid          VARCHAR(255) NOT NULL,
     userid           VARCHAR(255) NOT NULL,
     totalcardinstallment INT4 DEFAULT 1 NOT NULL,
     cardid           INT8 NOT NULL,
     PRIMARY KEY (id)
  );

CREATE TABLE IF NOT EXISTS card
  (
     id               BIGSERIAL NOT NULL,
     userid           VARCHAR(255) NOT NULL,
     status           BOOLEAN DEFAULT 'true' NOT NULL,
     name             VARCHAR(255) NOT NULL,
     cardnumber       VARCHAR(255) NOT NULL,
     validation       VARCHAR(6) NOT NULL,
     cvv              VARCHAR(3) NOT NULL,
     datetimecreation TIMESTAMP NOT NULL,
     PRIMARY KEY (id)
  );

ALTER TABLE payment
  ADD CONSTRAINT fk_card_payment FOREIGN KEY (cardid) REFERENCES card (id);