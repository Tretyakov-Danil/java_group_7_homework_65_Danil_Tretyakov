<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
</head>
<body>
    <#list products as product>
        <div style="border: red 2px solid">
            <img src="../static/images/${product.imagePath}">
            ${product.name}
            <p>
                ${product.brand.name}
            </p>
            <p>
                ${product.description}
            </p>
            Amount: ${product.amount}
        </div>
    </#list>
    <a href="/products?size=${pages.getPageSize()}&page=${pages.pageNumber+1}" type="button">Next Page</a>
    <hr>
    <form action="/products/name" method="get">
        <input type="text" name="name" placeholder="By name for now">
        <button type="submit">Submit</button>
    </form>

</body>
</html>