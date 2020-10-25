<template>
  <div class = "container">
    <div id=left_nav>
 <router-link to='/'>Home</router-link>
  </div>
  <div id="right_nav">
    <router-link to='/login'>Login</router-link>|
    <router-link to="/register">Register</router-link>
  </div>
  </div>
  <router-view/>
</template>

<style lang="scss">
#container {
  @import url('https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap');
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
  @import url('https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap');
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#left_nav{
  @import url('https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap');
  position: absolute;
  top: 8px;
  left: 16px;
  font-family: 'Roboto Mono', monospace;
  font-size: 25px;
  a {
    font-weight: bold;
    color: #f6f7f8;

    &.router-link-exact-active {
      color: #424eb9;
    }
  }
}

#right_nav {
  @import url('https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap');
  position: absolute;
  top: 8px;
  right: 16px;
  font-family: 'Roboto Mono', monospace;
  font-size: 25px;
  a {
    font-weight: bold;
    color: #f6f7f8;

    &.router-link-exact-active {
      color:  #424eb9;
    }
  }
}
</style>
