<template>
  <div class="container">
    <h1>Create an account</h1>
    <br />
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
              type="phoneNumber"
              class="form-control"
              id="exampleInputPhoneNumber1"
              aria-describedby="phoneNumberHelp"
              placeholder="Phone Number"
            />
          </div>
          <div class="form-group">
            <input
              type="password"
              id="inputPassword5"
              class="form-control"
              aria-describedby="passwordHelpBlock"
              placeholder="Password"
            />
            <small id="passwordHelpBlock" class="form-text text-muted">
              Your password must be 8-20 characters long, contain letters and
              numbers, and must not contain spaces, special characters, or
              emoji.
            </small>
          </div>
          <button type="submit" class="btn btn-primary">
            Create an account
          </button>
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