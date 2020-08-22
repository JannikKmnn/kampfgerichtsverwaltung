# kampfgerichtsverwaltung
Application to manage the handball-kampfgericht for TSV Bargteheide Handball.

Application runs with MySQL-Database. Make sure to create a new local database and to adjust ["dburl"](src/DBConnection.java#L9), ["dbuser"](src/DBConnection.java#L10) and ["dbpassword"](src/DBConnection.java#L11) in class DBConnection. To recreate a database in the first place, activate the ["recreate"](src/WebConnection.java#L13)-lines in class "WebConnection" and, if necessary, deactive the ["DROP TABLE"](src/DBConnection.java#L17) - lines.
