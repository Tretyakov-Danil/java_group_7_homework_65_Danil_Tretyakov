<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="${.locale}">
<head>
    <meta charset="UTF-8">
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

    <div class="card mt-3 mx-auto w-75 border-0">
        <div class="text-right mt-3">
            <a href="/orders/basket" class="btn btn-outline-success"><@spring.message "order.button.back"/></a>
        </div>
        <h5 style="font-size: 12px"><@spring.message "order.id"/> №${order.id}</h5>
        <div class="row g-0">
            <div class="col-md-4">
                <img src="/images/${order.product.imagePath}" alt="Product's photo" class="img-fluid">
            </div>
            <div class="col-md-8">
                <div class="card-body" style="font-size: 20px">
                    <h3 class="card-title">${order.product.brand.name}</h3>
                    <h3 class="card-title">${order.product.name}</h3>
                    <p class="card-text">${order.product.description}</p>
                    <p class="card-text"><@spring.message "order.date"/>: ${order.dateOfOrder.chronology?string.short}</p>
                    <p class="card-text"><@spring.message "order.price"/>: ${order.product.price}$</p>
                    <p class="card-text"><@spring.message "orders.amount"/>: ${order.amount}</p>
                    <p class="card-text"><@spring.message "orders.total"/>: ${(order.product.price * order.amount)}$</p>
                </div>
                <hr>
                <#if !order.isPaid>
                    <form action="/orders/${order.id}/changeAmount" method="post">
                        <#if _csrf??>
                            <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                        </#if>
                        <label for="amount"><@spring.message "order.change-amount-label"/>:</label>
                        <select class="form-select mt-2 py-2" aria-label=".form-select-sm example" name="amount" style="width: 40%">
                            <option selected value="1">1</option>
                            <#list 2..order.product.amount as x>
                                <option value="${x}">${x}</option>
                            </#list>
                        </select>
                        <button class="btn btn-outline-success ml-3 mb-1" type="submit"><@spring.message "order.button-change-amount"/></button>
                    </form>
                    <hr>
                    <div>
                        <a href="/orders/${order.id}/pay" class="btn btn-outline-success"><@spring.message "order.button-pay"/></a>
                    </div>
                <#else>
                    <div style="color: green; font-size: 20px">
                        <@spring.message "order.message.purchased"/>
                    </div>
                </#if>
            </div>
        </div>
    </div>

    <#if !order.isPaid>
        <div class="w-75 mx-auto mt-3">
            <form action="/orders/delete" method="post">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                </#if>
                <input hidden type="text" name="orderId" value="${order.id}">
                <input hidden type="text" name="productId" value="${order.product.id}">
                <input hidden type="text" name="amount" value="${order.amount}">
                <button type="submit" class="btn btn-danger ml-3 my-auto"><@spring.message "order.button.delete"/></button>
            </form>
        </div>
    </#if>


</body>
</html>