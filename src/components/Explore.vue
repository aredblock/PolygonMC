<script setup>

</script>

<template>
  <footer>
    <div class="center">
      <a href="https://github.com/aredblock/polygonmc?tab=readme-ov-file#features" class="explore">
        <p>Features</p>
        <img class="arrow" src="../assets/arrow-down.svg" alt="logo">
      </a>
    </div>
  </footer>
</template>

<style scoped>
  footer {
    font-family: 'Trebuchet MS', sans-serif;

    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    height: 35%;
    /*background-color: red;*/
    color: white;
    text-align: center;
  }
  .center {
    width: 50%;
    height: 20%;

    position: absolute;
    top:0;
    bottom: 0;
    left: 0;
    right: 0;

    margin: auto;

    display: flex;
    flex-direction: column;

    cursor: pointer;

  }

  .arrow {
    width: 10%;
    align-self: center;
  }

  .explore {
    text-decoration: none;
    color: #bababa;
    transition: 0.3s;

    animation: fadeInAnimation ease 1s;
    animation-iteration-count: 1;
    animation-fill-mode: forwards;
  }
  .explore:hover {
    color: rgb(160, 130, 191);
  }

  @keyframes fadeInAnimation {
    0% {
      transform: translateY(5%);
    }
    100% {
      transform: translateY(0%);
    }
  }

</style>