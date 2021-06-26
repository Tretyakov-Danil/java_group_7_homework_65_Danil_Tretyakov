<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <style type="text/css">
        .list-item{
            width: 20%;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <form action="/products" method="get">
                <button class="border-0 bg-transparent" style="font-size: 28px" type="submit">Navbar</button>
            </form>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item mr-4">
                    <a class="nav-link active" aria-current="page" href="/orders">Orders</a>
                </li>
                <li class="nav-item">
                    <form action="/products/filter" method="get">
                        <button class="btn active px-4 mr-5 btn-outline-success" type="submit">Search</button>
                    </form>
                </li>
                <li class="nav-item">
                    <form action="/client/login" method="get">
                        <button class="btn active px-4 btn-outline-success" type="submit">Sign in</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>

    <ul class="list-group list-group-horizontal w-75 mx-auto">
        <li class="list-group-item list-item">Name</li>
        <li class="list-group-item list-item">Description</li>
        <li class="list-group-item list-item">Amount</li>
        <li class="list-group-item list-item">Total price</li>
        <li class="list-group-item list-item">Status</li>
    </ul>
    <#list orders as order>
        <ul class="list-group list-group-horizontal w-75 mx-auto">
            <li class="list-group-item list-item">${order.product.name}</li>
            <li class="list-group-item list-item">${order.product.description}</li>
            <li class="list-group-item list-item">${order.amount}</li>
            <li class="list-group-item list-item">${order.amount * order.product.price}</li>
            <#if order.isPaid>
                <li class="list-group-item list-item" style="color: green">On the way</li>
                <#else>
                    <li class="list-group-item list-item" style="color: darkred">Not paid yet</li>
            </#if>
        </ul>
    </#list>
</body>
</html>