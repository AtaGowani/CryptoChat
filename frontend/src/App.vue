<template>
  <div id="app">
    <particles-bg type="tadpole" :bg="true" />
    <nav class="navbar navbar-expand-lg navbar-light bg-light" id="nav-bar">
      <a class="navbar-brand" href="#">CryptoChat</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <router-link class="nav-link" to="/">Home</router-link> 
          </li>
        </ul>
        <div id="newLogin">
        <router-link class="btn btn-outline-dark" to="/signin" id='login'>Log in</router-link> 
        <router-link class="btn btn-outline-dark" to="/register" id='register'>Register</router-link>
        <!-- put three bar menu here for Login/Register -->
        </div> 
        <div id="loggingOut" style="visibility: hidden">
        <router-link class="btn btn-outline-dark" to="/" id='logout' @click="onLogout()">Log out</router-link> 
        </div> 
      </div>
    </nav>
    <router-view />
  </div>
</template>

<script>
import { ParticlesBg } from "particles-bg-vue";
export default {
    components: {
    ParticlesBg
  },
  data() {
    return {
    };
  },
  methods: {
    onLogout() {
          document.getElementById('newLogin').style.visibility = 'visible';
          document.getElementById('loggingOut').style.visibility = 'hidden';
    },
  },
};
</script>

<style lang="scss">
#container {
  @import url("https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap");
  margin: 0px;
  background: repeat center center;
  background-color: #2c3e50;
  position: relative;
  border: none;
  padding: 0px;
  text-align: center;
  color: white;
}

#app {
  @import url("https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap");
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#left_nav {
  @import url("https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap");
  position: absolute;
  top: 8px;
  left: 16px;
  font-family: "Roboto Mono", monospace;
  font-size: 25px;
  a {
    font-weight: bold;
    color: #f6f7f8;

    &.router-link-exact-active {
      color: greenyellow;
    }
  }
}

#right_nav {
  @import url("https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap");
  position: absolute;
  top: 8px;
  right: 16px;
  font-family: "Roboto Mono", monospace;
  font-size: 25px;
  a {
    font-weight: bold;
    color: #f6f7f8;

    &.router-link-exact-active {
      color: greenyellow;
    }
  }
}

</style>
