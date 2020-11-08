<template>
  <div class="container">
    <h1>Create an account</h1>
    <br />
    <div class="row">
      <div class="col-md-3"></div>
      <div class="col-md-6">
        <form @submit.prevent="onSubmit()"> 
          <div class="form-group">
            <input
              type="email"
              class="form-control"
              id="exampleInputEmail1"
              aria-describedby="emailHelp"
              placeholder="Email Address"
              v-model="email"
            />
          </div>
          <div class="form-group">
            <input
              type="phoneNumber"
              class="form-control"
              id="exampleInputPhoneNumber1"
              aria-describedby="phoneNumberHelp"
              placeholder="Phone Number"
              v-model="phoneNumber"
            />
          </div>
          <br />
          <div class="form-group">
            <div class="form-group">
              <input
                type="password"
                id="inputPassword5"
                class="form-control"
                aria-describedby="passwordHelpBlock"
                placeholder="Password"
                v-model="password"
              />
            </div>

            <input
              type="password"
              class="form-control"
              aria-describedby="passwordHelpBlock"
              placeholder="Confirm Password"
              v-model.lazy="checkPassword"
            />

            <transition name="hint" appear>
              <div
                v-if="passwordValidation.errors.length > 0 && !submitted"
                class="hints"
              >
                <small id="passwordHelpBlock">Password must have:</small>
                <small
                  id="passwordHelpBlock"
                  class="form-text text-muted"
                  v-for="error in passwordValidation.errors"
                  :key="error"
                >
                  {{ error }}
                </small>
              </div>
            </transition>

            <div class="matches" v-if="notSamePasswords">
              <small id="passwordHelpBlock">Passwords don't match.</small>
            </div>
          </div>
          <button
            type="submit"
            class="btn btn-outline-light"
            v-on:submit="onSubmit()"
            v-if="
              passwordsFilled && !notSamePasswords && passwordValidation.valid
            "
          >
            Create an account
          </button>
        </form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import enc from "./encryption/encryption.js"
export default {
  data() {
    return {
      rules: [
        { message: "At least one lowercase letter.", regex: /[a-z]+/ },
        { message: "At least one uppercase letter.", regex: /[A-Z]+/ },
        { message: "At least one number.", regex: /[0-9]+/ },
        { message: "8 characters minimum.", regex: /.{8,}/ },
      ],
      email: "",
      phoneNumber: "",
      password: "",
      checkPassword: "",
      passwordVisible: false,
      submitted: false,
      pk: "IDK",
    };
  },
  methods: {
    resetPasswords() {
      this.password = "";
      this.checkPassword = "";
      this.email = "";
      this.phoneNumber = "";
      this.submitted = true;
      setTimeout(() => {
        this.submitted = false;
      }, 2000);
    },
    togglePasswordVisibility() {
      this.passwordVisible = !this.passwordVisible;
    },
    async onSubmit() {
      var bodyFormData = new FormData();
      bodyFormData.append("email", this.email);
      localStorage.setItem("useremail", this.email);
      console.log(this.email);
      bodyFormData.append("password", this.password);
      console.log(this.password);
      bodyFormData.append("phone", this.phoneNumber);
      console.log(this.phoneNumber);
      this.pk = enc.generateKeys(this.email) // added to gen keys
      bodyFormData.append("pk", this.pk);
      console.log(this.pk);

      const res = await axios
        .post("https://cryptochat-backend.herokuapp.com/signup", bodyFormData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          this.$router.push('/signin');
          alert("Account was successfully created.");
          console.log(res);
        })
        .catch((error) => {
          if (error.response) {
            alert("Account, PhoneNumber, or PK already exist. Try again.");
            console.log(error.response.data); // => the response payload
          }
        });
        console.log(res);
        this.resetPasswords();
    },
  },
  computed: {
    notSamePasswords() {
      if (this.passwordsFilled) {
        return this.password !== this.checkPassword;
      } else {
        return false;
      }
    },
    passwordsFilled() {
      return this.password !== "" && this.checkPassword !== "";
    },
    passwordValidation() {
      let errors = [];
      for (let condition of this.rules) {
        if (!condition.regex.test(this.password)) {
          errors.push(condition.message);
        }
      }
      if (errors.length === 0) {
        return { valid: true, errors };
      } else {
        return { valid: false, errors };
      }
    },
  },
};
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