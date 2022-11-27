rm ./*.pdf
java -jar ../../target/csv2pdf.jar -csv_file persons.csv -ftl_file birthday_card.ftl -for_each -pdf 'happy birthday ${name}!.pdf'