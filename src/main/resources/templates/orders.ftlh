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
            width: 14%;
        }
        .descr{
            width: 20%;
            padding: 12px 5px;
        }
        .amount{
            width: 10%;
            padding: 12px 5px;
            text-align: center;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <form action="/products" method="get">
                <button class="border-0 bg-transparent" style="font-size: 28px" type="submit">ONLINE STORE</button>
            </form>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item mx-5">
                    <form action="/products/filter" method="get">
                        <button class="btn active px-4 mr-5 btn-outline-success" type="submit">Search</button>
                    </form>
                </li>
                <li class="nav-item mr-4">
                    <a href="/orders/basket"><i class="fas fa-shopping-cart mt-1" style="font-size: 24px"></i></a>
                </li>
                <li class="nav-item mr-3 my-auto">
                    ${username}
                </li>
                <#if _csrf??>
                    <li class="nav-item">
                        <form action="/logout" method="post">
                            <input type="hidden"
                                   name="${(_csrf.parameterName)!'csrf'}"
                                   value="${(_csrf.token)!'--no-token--'}">
                            <button class="btn btn-outline-success active px-4" type="submit">Logout</button>
                        </form>
                    </li>
                <#else>
                    <li class="nav-item">
                        <a href="/logout" class="btn btn-outline-success active px-4">Logout</a>
                    </li>
                </#if>
            </ul>
        </div>
    </nav>

    <div class="mx-auto" style="width: 85%">
    <ul class="list-group list-group-horizontal">
        <li class="list-group-item list-item">Name</li>
        <li class="list-group-item descr">Description</li>
        <li class="list-group-item amount">Amount</li>
        <li class="list-group-item list-item">Total price</li>
        <li class="list-group-item list-item">Date of order</li>
        <li class="list-group-item list-item">Status</li>
        <li class="list-group-item border-0 list-item"></li>
    </ul>
    <#list orders as order>
        <ul class="list-group list-group-horizontal">
            <li class="list-group-item list-item" style="padding: 12px 5px">${order.product.name}</li>
            <li class="list-group-item descr">${order.product.description}</li>
            <li class="list-group-item amount">${order.amount}</li>
            <li class="list-group-item list-item">${order.amount * order.product.price}</li>
            <li class="list-group-item list-item">
                ${order.dateOfOrder.dayOfMonth}.<#if order.dateOfOrder.monthValue<10>0${order.dateOfOrder.monthValue}<#else>${order.dateOfOrder.monthValue}</#if>.${order.dateOfOrder.year}
                <p>${order.dateOfOrder.hour}:${order.dateOfOrder.minute}</p>
            </li>
            <#if order.isPaid>
                <li class="list-group-item list-item" style="color: green">On the way</li>
                <#else>
                    <li class="list-group-item list-item" style="color: darkred">Not paid</li>
                    <li class="list-group-item list-item border-0">
                        <form action="/orders/delete" method="post">
                            <#if _csrf??>
                                <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                            </#if>
                            <input type="hidden" name="_method" value="delete" />
                            <input hidden type="text" name="orderId" value="${order.id}">
                            <input hidden type="text" name="productId" value="${order.product.id}">
                            <input hidden type="text" name="amount" value="${order.amount}">
                        <button type="submit" class="btn btn-danger ml-3 my-auto">Delete</button>
                        </form>
                    </li>
            </#if>
        </ul>
    </#list>
    <a href="/orders" class="btn btn-success mt-3">Show all</a>
    </div>
</body>
</html>