<html>
<head>
    <style>
        @page {
            size: A4 portrait;
        }

        body {
            text-align:center;
        }

        div.newpage {
            page-break-after:always;
        }

    </style>
</head>
<body>
    <#list rows as row>
        <h1>${row.name}</h1>
        <img src="images/${row.image}">
        <h2>${row.price}</h2>
        <#sep><div class="newpage"/></#sep>
    </#list>
</body>
</html>

