<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Online Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
</head>
<body>
    <div class="w-50 mx-auto text-center">
        <h3 class="mt-3">Password recovery</h3>
        <#if !email??>
            <form action="/client/restore" method="post" class="mb-3">
                <#if _csrf??>
                    <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}" value="${(_csrf.token)!'csrf-token'}">
                </#if>
                <div class="mb-3">
                    <label for="email" class="form-label">Enter your email address, we will sent new password for you</label>
                    <input type="email" id="email" class="form-control" name="email" aria-describedby="emailHelp" placeholder="Your email">
                </div>
                <button type="submit" class="btn btn-success login-button mt-2">Get new password</button>
            </form>
        <#else>
            <div class="mb-3" style="font-size: 18px">
                <p>Email: ${email}</p>
                <hr>
                <p>Remember your new password:</p>
                <p style="font-size: 24px; font-weight: bold">${password}</p>
                <span>Never give anyone your password</span>
            </div>
        </#if>
        <div class="text-center">
            <a href="/client/login" class="btn btn-success">Back to login</a>
        </div>
    </div>

</body>
</html>