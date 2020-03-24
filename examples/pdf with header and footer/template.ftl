<html>
<head>
<style>

@page{

    @bottom-left {                 
        content: element(footer);  
        vertical-align: top;
    }

    @top-right {
        content: element(header); 
        vertical-align: bottom;
    }

    size: A4 portrait;
    margin-top:3cm; 
    margin-left:1.5cm; 
    margin-right:1.5cm; 
    margin-bottom:3cm; 
}

div.header {
    display: block;
    position: running(header);
    border-bottom: 1px solid black;
}

div.footer {
    display: block;
    position: running(footer);
    height: 3cm;
    border-top: 1px solid black; 
}

div.content {
    display: block;
    text-align: justify;
}

div.newpage {
    page-break-after:always;
}

#pagenumber:before {
    content: counter(page);
}

#pagecount:before {
    content: counter(pages);
}

</style>
</head>
<body>

    <div class="header">
        <p>This is the header that will repeat on every page at top</p>
    </div>

    <div class="footer" >
        <p>This is the footer that will repeat on every page at bottom</p>
        <p>Page <span id="pagenumber"></span> of <span id="pagecount"></span></p>
    </div>

    <div class="content">
        <#list rows as row>
            <table style="text-align:center;margin:auto;font-size:1cm;padding-top:4cm;">
                <tr><td colspan=2>${row["SSN"]}</td></tr>
                <tr><td>${row["Last name"]}</td><td rowspan="2">${row["Grade"]}</td></tr>
                <tr><td>${row["First name"]}</td></tr>
            </table>
            <table style="text-align:center;margin:auto;font-size:0.5cm;padding-top:2cm;">
                <tr><th>Test1</th><th>Test2</th><th>Test3</th><th>Test4</th><th>Final</th></tr>
                <tr><td>${row.Test1}</td><td>${row.Test2}</td><td>${row.Test3}</td><td>${row.Test4}</td><td>${row.Final}</td></tr>
            </table>
            <#sep><div class="newpage"/></#sep>
        </#list>
    </div>

</body>
</html>