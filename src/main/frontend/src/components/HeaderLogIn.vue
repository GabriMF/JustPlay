<script></script>
<template>
  <div class="headerLogin">
    <a href="/" aria-current="page" class="headerLogo"></a>
  </div>
</template>

<style lang="scss">
@use "@/scss/mixins" as m;

.headerLogin {
  left: 0%;
  top: 0%;
  right: 0%;
  bottom: 0%;
  display: flex;
  height: 5rem;
  -webkit-box-pack: center;
  justify-content: center;
  background-color: rgb(177, 5, 5);

  .headerLogo {
    background-image: url("../assets/images/justPlayLogo.png");
    background-size: contain;
    background-position: 0% 50%;
    background-repeat: no-repeat;
    width: 9rem;
    height: 4rem;
    margin: auto;
    margin-left: 1vw;
  }
}
</style>
