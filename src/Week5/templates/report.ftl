<!DOCTYPE html>
<html>
<head>
    <title>${name} Catalog</title>
</head>
<body>
<h1>${name} Catalog</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Location</th>
        <th>Tags</th>
    </tr>
    </thead>
    <tbody>
    <#list documents as doc>
    <tr>
        <td>${doc.id}</td>
        <td>${doc.name}</td>
        <td>${doc.path}</td>
        <td>
            <#if doc.tags??>
            <#list doc.tags as key, value>
            ${key}: ${value}<br>
        </#list>
    </#if>
    </td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>