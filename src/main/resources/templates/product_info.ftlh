<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product</title>
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
<img src="..static/images/${product.imagePath}">
    ${product.brand}
    ${product.name}
    ${product.amount}
    ${product.description}
</body>
</html>