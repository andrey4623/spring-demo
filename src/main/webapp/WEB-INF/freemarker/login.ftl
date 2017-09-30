<h1>Login</h1>

<#if msg??>
${msg}
</#if>

<#if error??>
Error: ${error}
</#if>

<p>You can use: user:user or admin:admin</p>

<form role="form" action="/login" method="post">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

  <div>
    <label for="username">Username</label>
    <input type="text" name="username" id="username" required autofocus/>
  </div>
  <div>
    <label for="password">Password</label>
    <input type="password" name="password" id="password" required/>
  </div>
  <button type="submit">Sign in</button>
</form>
