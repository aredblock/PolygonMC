<script setup>

</script>

<template>
  <div class="welcome-container">
    <h1 class="title">PolygonMC</h1>
    <p style="color: gray;">PolygonMC is an extended version of the Minestom framework! It is intended to implement minor optimizations such as new events, the Addons and other things.</p>

    <a href="https://github.com/aredblock/PolygonMC?tab=readme-ov-file#Installation" class="link">Install</a>
    <a href="https://github.com/aredblock/PolygonMC/releases" class="link-margin">Runtime</a>
    <a href="https://github.com/aredblock/PolygonMC" class="link-margin">Github</a>
    <a href="https://minestom.net" class="link-margin">Minestom</a>
  </div>
</template>

<style scoped>
  .welcome-container {
    font-family: 'Trebuchet MS', sans-serif;

    width: 50%;
    height: 20%;

    position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;

    /*background-color: aqua;*/

    margin: auto;
  }

  .title {
    font-size: 3vh;

    animation: fadeInAnimation ease 1s;
    animation-iteration-count: 1;
    animation-fill-mode: forwards;
  }
  @keyframes fadeInAnimation {
    0% {
      transform: translateY(10%);
      opacity: 0;
    }
    100% {
      transform: translateY(0%);
      opacity: 1;
    }
  }

  .link {
    color: rgb(128, 54, 197);
    font-weight: bold;
    transition: 0.3s;
  }
  .link:hover {
    color: rgb(185, 123, 243);
  }

  .link-margin {
    margin-left: 1vh;

    color: rgb(128, 54, 197);
    font-weight: bold;
    transition: 0.3s;
  }
  .link-margin:hover {
    color: rgb(185, 123, 243);
  }
</style>