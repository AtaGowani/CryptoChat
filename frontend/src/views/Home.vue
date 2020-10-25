<template>
<body>
  <div class = "banner">
    <span>CryptoChat</span>
    <p class = "description">CryptoChat is an encrypted messaging web application</p>
  </div>
    
</body>
  

</template>

<script>
export default {

};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@100;400&display=swap'); 

body {
  background-color: black;
}

 .banner {
  position: absolute;
  top: 20%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: rgb(240, 240, 255);
  font-family: 'Roboto Mono', monospace;
  font-size: 70px;
}

.description{
  color: rgb(240, 240, 255);
  font-size: 30px;
}
</style>