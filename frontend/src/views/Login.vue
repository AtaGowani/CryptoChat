<template>
  <div class="container">
  <h1>Login Page</h1>
    <br />
    <br />
    <div class="row">
      <div class="col-mod-6">
        <form class="login-container">
          <div class="form-group1">
            <h1 class="email">Email</h1>
            <input
              type="email"
              class="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
              placeholder="Enter email"
            />
          </div>
          <div class="form-group2">
            <h2 class="password">Password</h2>
            <input
              type="password"
              class="form-control"
              id="exampleInputPassword1"
              placeholder="Password"
            />
          </div>
          <button type="submit" class="btn-btn-primary">Login</button>
        </form>
      </div>
    </div>
  </div>
</template>
  

<style lang="scss">
    .container {
      padding:16px;
      position: relative;
      font-size: 20px;
      color: rgb(240, 240, 255);
      padding: 50px;
    }

    .login-container {
      border: 1px solid #2d2d2e;
      display: block;
      margin: 0 auto;
      width: 400px;
      height: 300px;
      background-color: #3e3e3f;
    }

    .form-group1 {
       margin-top: 50px;
       font-size: 10px;
        width: 100%; 
        display: inline-block; 
    }

    .email {
      margin-right: 100px;
    }

    .password {
      margin-right: 80px;
    }
    .form-group2 {
       font-size: 12px;
        width: 100%;
        display: inline-block; 
       
    }

    .btn-btn-primary {
      margin-top:50px;
    }

</style>