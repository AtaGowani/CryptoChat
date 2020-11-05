<template>
  <div class="container">
    <h1>Login to CryptoChat</h1>
    <br>
    <div class="row">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <form>
          <div class="form-group">
            <input
              type="email"
              class="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
              placeholder="Email Address"
            />
          </div>
          <div class="form-group">
            <input
              type="password"
              class="form-control"
              id="exampleInputPassword1"
              placeholder="Password"
            />
          </div>
          <button type="submit" class="btn btn-primary">Log In</button>
          <b-row class="mt-3">
            <b-col cols="6">
              <router-link to="/forgotpassword" class="text-light"
                ><small> Forgot password?</small></router-link
              > </b-col
            ><!--TODO: Make forgot password-->
            <b-col cols="6" class="text-right"><small> | </small> </b-col>
            <b-col cols="6" class="text-right">
              <router-link to="/register" class="text-light"
                ><small>Create new account</small></router-link
              >
            </b-col>
          </b-row>
        </form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.container {
  padding: 16px;
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
  margin-top: 50px;
}
</style>