<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
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
                        <button class="btn active px-4 mr-5 btn-outline-success" type="submit"><@spring.message "button.search"/></button>
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
                            <button class="btn btn-outline-success active px-4" type="submit"><@spring.message "button.logout"/></button>
                        </form>
                    </li>
                <#else>
                    <li class="nav-item">
                        <a href="/logout" class="btn btn-outline-success active px-4"><@spring.message "button.logout"/></a>
                    </li>
                </#if>
            </ul>
        </div>
    </nav>

    <#if !order.isPaid>
        <div class="card w-75 mx-auto mt-5 border-0">
                <h2 class="card-title text-center"><@spring.message "order.payment.confirm-message"/></h2>
            <div class="card-body text-center" style="font-size: 24px">
                <p class="card-text"><@spring.message "order.price"/>: ${order.product.name}</p>
                <p class="card-text"><@spring.message "order.amount"/>: ${order.product.amount}</p>
                <p class="card-text"><@spring.message "orders.total"/>: ${(order.product.price * order.amount)?string.currency}</p>
                <form action="/orders/${order.id}/pay" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                    </#if>
                    <input hidden name="productId" value="${order.product.id}">
                    <button class="btn btn-outline-success" type="submit"><@spring.message "order.payment.button-pay"/></button>
                </form>
            </div>
            <a href="/orders/basket" class="btn btn-success"><@spring.message "order.payment.button-back"/></a>
        </div>
        <#else>
            <div class="mt-3 mx-auto">
                <h2 style="font-weight: bold"><@spring.message "order.payment.thanks"/></h2>
                <a href="/orders/basket" class="btn btn-success mt-3"><@spring.message "order.payment.button-back"/></a>
            </div>
    </#if>
</body>
</html>