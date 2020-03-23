# csv2pdf
![Java CI with Maven](https://github.com/redking00/csv2pdf/workflows/Java%20CI%20with%20Maven/badge.svg)
<br>
<br>
🔥A command line tool to generate PDF (using Flyingsaucer) or text files from csv data and Freemarker templates🔥
<br>

#### How it works?
<pre>
+--------------+           +--------------+                                                    
|   CSV data   |           | FTL Template |                                                   
+--------------+           +--------------+                                                   
        |                          |                                                          
        |                          |                                                           
        v                          v                                                           
+--------------+           +--------------+         +--------------+          +--------------+ 
|   CsvJDBC    |---------->|  Freemarker  |-------->|    JSoup     |--------->| FlyingSaucer | 
+--------------+           +--------------+         +--------------+          +--------------+ 
                                   |                                                  |        
                                   |                                                  |        
                                   v                                                  v        
                           +--------------+                                   +--------------+ 
                           |   TXT file   |                                   |   PDF file   | 
                           +--------------+                                   +--------------+ 
</pre>
<br>

### Usage: 
java -jar csv2pdf.jar &lt;options&gt;

### Options: 
<table>
 <tr><td>-csv_charset <arg></td><td>CSV file charset (optional default 'UTF-8')</td></tr>
 <tr><td>-csv_extension <arg></td><td>CSV file extesion (optional default '')</td></tr>
 <tr><td>-csv_file <arg></td><td>CSV input file (optional, use instead -csv_query)</td></tr>
 <tr><td>-csv_folder <arg></td><td>CSV folder path (optional default '.')</td></tr>
 <tr><td>-csv_query <arg></td><td>CSV query command (optional, use instead -csv_file)</td></tr>
 <tr><td>-csv_separator <arg></td><td>CSV separator character (optional default ';')</td></tr>
 <tr><td>-for_each</td><td>Generate one file per data record (optional, see notes below)</td></tr>
 <tr><td>-ftl_encoding <arg></td><td>FTL input file encoding (optional default 'UTF-8')</td></tr>
 <tr><td>-ftl_file <arg></td><td>FTL input file</td></tr>
 <tr><td>-pdf <arg></td><td>PDF output file (optional, use instead -txt)</td></tr>
 <tr><td>-txt <arg></td><td>TXT output file (optional, use instead -pdf)</td></tr>
 <tr><td>-txt_charset <arg></td><td>TXT output file charset (optional default 'UTF-8')</td></tr>
</table>
<br>

#### NOTES:<br>
 - Iterate "*rows*" collection if you aren´t using the *-for_each* option
 
## Example:<br>
&nbsp;&nbsp;java -jar csv2pdf.jar -csv_file data.csv -ftl_file template.ftl -pdf output.pdf
<br>


